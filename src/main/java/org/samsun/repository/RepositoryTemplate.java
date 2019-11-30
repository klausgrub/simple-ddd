package org.samsun.repository;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年06月07日
 */
public interface RepositoryTemplate {

    /**
     * get instance of entity by id and class
     * @param clazz class of entity
     * @param id primary key
     * @return instance of entity
     */
    <T,ID> T  get(Class<T> clazz, ID id);

    /**
     * get list
     * @param clazz 实体对象类
     * @return 实体对象的列表
     */
    <T> List<T> list(Class<T> clazz, Specification<T> specification);

    /**
     * save
     * @param pojo 实体对象实例
     */
    <T> void save(T pojo);

    /**
     * merge 合并一个实体, 比如，当pojo被显示层传过来，不受管理，需要merge
     * @param pojo
     */
    <T> void merge(T pojo);

    /**
     * 删除一个实体
     * @param pojo 实体对象实例
     */
    <T> void delete(T pojo);




}
