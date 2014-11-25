/**
 * 
 */
package org.sagacity.core.service;

import java.util.List;

import org.sagacity.core.dao.BaseMongoDao;
import org.sagacity.core.page.Pager;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author LIZHITAO 基础mongodb 增删改查操作类
 */
public abstract class BaseMongoService<T, DaoImpl extends BaseMongoDao<T, String>> {
	public abstract DaoImpl getDaoImpl();

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return getDaoImpl().findAll();
	}

	/**
	 * 按条件查询列表
	 * 
	 * @param criteria
	 * @return
	 */
	public List<T> findByCriteria(Criteria criteria) {
		return getDaoImpl().findByCriteria(criteria);
	}

	/**
	 * 按条件查询
	 * 
	 * @param query
	 * @return
	 */
	public List<T> findByQuery(Query query) {
		return getDaoImpl().findByQuery(query);
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @return
	 */
	public T insert(T entity) {
		return getDaoImpl().insert(entity);
	}

	/**
	 * 更新实体
	 * 
	 * @param id
	 * @param update
	 */
	public void update(String id, Update update) {
		getDaoImpl().update(id, update);
	}

	/**
	 * 按条件更新一条数据
	 * 
	 * @param criteria
	 * @param update
	 */
	public void updateFirst(Criteria criteria, Update update) {
		getDaoImpl().updateFirst(criteria, update);
	}

	/**
	 * 按条件更新多条数据
	 * 
	 * @param criteria
	 * @param update
	 */
	public void updateMulti(Criteria criteria, Update update) {
		getDaoImpl().updateMulti(criteria, update);
	}

	/**
	 * 保存或更新实体
	 * 
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity) {
		return getDaoImpl().saveOrUpdate(entity);
	}

	/**
	 * 按条件查询单个对象
	 * 
	 * @param criteria
	 * @return
	 */
	public T findOne(Criteria criteria) {
		return getDaoImpl().findOne(criteria);
	}

	/**
	 * 根据id查询单个对象
	 * 
	 * @param id
	 * @return
	 */
	public T fetch(String id) {
		return getDaoImpl().fetch(id);
	}

	/**
	 * 按id删除对象
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		getDaoImpl().deleteById(id);
	}

	/**
	 * 查询并分页
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Pager<T> queryPage(int currentPage, int pageSize) {
		return getDaoImpl().queryPage(currentPage, pageSize);
	}

	/**
	 * 按条件查询并分页
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public Pager<T> queryPage(int currentPage, int pageSize, Query query) {
		return getDaoImpl().queryPage(currentPage, pageSize, query);
	}

	/**
	 * 按条件查询并分页
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public Pager<T> queryPage(int currentPage, int pageSize, Criteria criteria) {
		return getDaoImpl().queryPage(currentPage, pageSize, criteria);
	}

	/**
	 * 按条件统计条数
	 * 
	 * @param criteria
	 * @return
	 */
	public int count(Criteria criteria) {
		return getDaoImpl().count(criteria);
	}

	/**
	 * 按条件统计条数
	 * 
	 * @param query
	 * @return
	 */
	public int count(Query query) {
		return getDaoImpl().count(query);
	}

	/**
	 * 按条件查询并删除
	 * 
	 * @param query
	 */
	public void findAndRemove(Query query) {
		getDaoImpl().findAndRemove(query);
	}
}
