package org.samsun.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunmin
 * @version 1.0.0
 * @ClassName RepositoryTemplateFactory.java
 * @Description TODO
 * @createTime 2019年06月07日
 */
@Service
public class RepositoryTemplateFactory {

    private static RepositoryTemplate repositoryTemplate;

    private static RepositoryTemplateJpa repositoryTemplateJpa;

    private static EntityManager entityManager;

    private static ApplicationContext applicationContext;


    private static Map<Class, SimpleJpaRepository> repositoryPool = new HashMap<>();

    public static RepositoryTemplate getRepositoryTemplate() {
        return repositoryTemplate;
    }

    public static RepositoryTemplateJpa getRepositoryTemplateJpa() {
        return repositoryTemplateJpa;
    }

    @Autowired
    protected void setRepositoryTemplate(RepositoryTemplate repositoryTemplate) {
        RepositoryTemplateFactory.repositoryTemplate = repositoryTemplate;
    }

    @Autowired
    protected void setRepositoryTemplateJpa(RepositoryTemplateJpa repositoryTemplateJpa) {
        RepositoryTemplateFactory.repositoryTemplateJpa = repositoryTemplateJpa;
    }

    @Autowired
    protected void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    @Autowired
    protected void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static <T, ID> SimpleJpaRepository<T, ID> getRepository(Class<T> domainClass) {

        if(repositoryPool.get(domainClass) == null)
            repositoryPool.put(domainClass, new SimpleJpaRepository(domainClass, entityManager));
        return repositoryPool.get(domainClass);
    }

    public static  <T extends JpaRepository> T getJpaRepository(Class<T> jpaRepositoryClass) {
        return applicationContext.getBean(jpaRepositoryClass);
    }
}
