package org.samsun.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RepositoryTemplateJpa implements RepositoryTemplate, org.springframework.data.repository.Repository {

    static final Integer MAX_NUMBER_PAGE = 999999;

    @Autowired
    private EntityManager entityManager;


    @Override
    public <T, ID> T get(Class<T> clazz, ID id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public <T> List<T> list(Class<T> clazz, Specification<T> specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);

        Root<T> root = query.from(clazz);

        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, query, builder);

            if (predicate != null) {
                query.where(predicate);
            }
        }
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public <T> void save(T pojo) {
        entityManager.persist(pojo);
    }

    @Override
    @Transactional
    public <T> void merge(T pojo) {
        entityManager.merge(pojo);

    }

    public <T> void refresh(T pojo) {
        entityManager.refresh(pojo);
    }

    @Override
    @Transactional
    public <T> void delete(T pojo) {
        entityManager.remove(pojo);
    }


    public Object execute(EmCallback callback, Object... objects) {
        return callback.doInEntiyManager(entityManager);
    }

    /**
     * no paging, used carefully
     * @param jpql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> find(String jpql, Object... params) {

        Query query = entityManager.createQuery(jpql);
        if (params != null) {
            for (int i = 0; i < params.length; i++)
                query.setParameter(i + 1, params[i]);

        }
        return query.getResultList();

    }


    private <T> EntityInformation<T, ?> getEntityInfomation(Class<T> tClass) {
        return JpaEntityInformationSupport.getEntityInformation(tClass, entityManager);
    }

}
