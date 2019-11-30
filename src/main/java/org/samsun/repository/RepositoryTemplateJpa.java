package org.samsun.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RepositoryTemplateJpa implements RepositoryTemplate, org.springframework.data.repository.Repository {

    static final Integer MAX_NUMBER_PAGE = 999999;

    @Autowired
    private EntityManager entityManager;


    @Override
    public <T, ID> T get(Class<T> clazz, ID id) {
        return getRepo(clazz).getOne(id);
    }

    @Override
    public <T> List<T> list(Class<T> clazz, Specification<T> specification) {
        return getRepo(clazz).findAll(specification);
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

    @Override
    @Transactional
    public <T> void delete(T pojo) {
        entityManager.remove(pojo);
    }


    public Object execute(EmCallback callback, Object... objects) {
        return callback.doInEntiyManager(entityManager);
    }

    /**
     * 没有分页，谨慎使用
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

    private <T, ID> SimpleJpaRepository<T, ID> getRepo(Class<T> clazz) {
        return RepositoryTemplateFactory.getRepository(clazz);
    }

    private <T> EntityInformation<T, ?> getEntityInfomation(Class<T> tClass) {
        return JpaEntityInformationSupport.getEntityInformation(tClass, entityManager);
    }

}
