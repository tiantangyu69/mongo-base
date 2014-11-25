/*******************************************************
 * @author LIZHITAO
 * @date 2014-6-22 下午1:22:26
 * @name BaseMongoDao.java
 * @JDK version 1.6
 * @version 0
 ******************************************************/
package org.sagacity.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.sagacity.core.page.Pager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.CommandResult;

/**
 * @author LIZHITAO mongodb 数据库操作基类，封装最常用的CRUD方法
 */
@Repository
public class BaseMongoDao<T, PK extends Serializable> {

	/**
	 * 注入mongoTemplate使用spring提供的封装方法进行数据库相关操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;
	private Class<T> entityClass = null;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 在子类中取entityClass的值
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 在子类中取entityClass的值
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 创建默认构造方法，以取得真正的泛型类型
	 * 
	 * @author LIZHITAO
	 */
	@SuppressWarnings("unchecked")
	public BaseMongoDao() {
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			entityClass = (Class<T>) parameterizedType[0];
		}
	}

	/**
	 * 查询实体列表
	 * 
	 * @param
	 * @name findAll
	 * @active
	 * @state
	 * @type List<T>
	 * @time 下午1:31:59
	 * @exception/throws
	 * @see
	 * @since
	 * @return
	 */
	public List<T> findAll() {
		return mongoTemplate.findAll(entityClass);
	}

	/**
	 * 按条件查询列表
	 * 
	 * @param
	 * @name findByCriteria
	 * @active
	 * @state
	 * @type List<T>
	 * @time 下午3:47:04
	 * @exception/throws
	 * @see
	 * @since
	 * @param criteria
	 * @return
	 */
	public List<T> findByCriteria(Criteria criteria) {
		return mongoTemplate.find(new Query(criteria), entityClass);
	}

	/**
	 * 按条件查询
	 * 
	 * @param query
	 * @return
	 */
	public List<T> findByQuery(Query query) {
		return mongoTemplate.find(query, entityClass);
	}

	/**
	 * 保存实体
	 * 
	 * @param
	 * @name insert
	 * @active
	 * @state
	 * @type void
	 * @time 下午2:10:22
	 * @exception/throws
	 * @see
	 * @since
	 * @param entity
	 */
	public T insert(T entity) {
		mongoTemplate.insert(entity);
		return entity;
	}

	/**
	 * 按id更新单条记录
	 * 
	 * @param
	 * @name update
	 * @active
	 * @state
	 * @type void
	 * @time 下午3:28:50
	 * @exception/throws
	 * @see
	 * @since
	 * @param id
	 * @param update
	 */
	public void update(String id, Update update) {
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)),
				update, entityClass);

	}

	/**
	 * 按条件更新单条数据
	 * 
	 * @param
	 * @name update
	 * @active
	 * @state
	 * @type void
	 * @time 下午3:31:16
	 * @exception/throws
	 * @see
	 * @since
	 * @param criteria
	 * @param update
	 */
	public void updateFirst(Criteria criteria, Update update) {
		mongoTemplate.updateFirst(new Query(criteria), update, entityClass);

	}

	/**
	 * 按条件更新多条数据
	 * 
	 * @param
	 * @name updateMulti
	 * @active
	 * @state
	 * @type void
	 * @time 下午3:43:03
	 * @exception/throws
	 * @see
	 * @since
	 * @param criteria
	 * @param update
	 */
	public void updateMulti(Criteria criteria, Update update) {
		mongoTemplate.updateMulti(new Query(criteria), update, entityClass);

	}

	/**
	 * 保存或更新实体
	 * 
	 * @param
	 * @name save
	 * @active
	 * @state
	 * @type void
	 * @time 下午1:32:23
	 * @exception/throws
	 * @see
	 * @since
	 * @param entity
	 */
	public T saveOrUpdate(T entity) {
		mongoTemplate.save(entity);
		return entity;
	}

	/**
	 * 根据条件查询单个对象
	 * 
	 * @param
	 * @name findOne
	 * @active
	 * @state
	 * @type T
	 * @time 下午1:39:11
	 * @exception/throws
	 * @see
	 * @since
	 * @param id
	 * @return
	 */
	public T findOne(Criteria criteria) {
		T entity = (T) mongoTemplate.findOne(new Query(criteria), entityClass);
		return entity;
	}

	/**
	 * 根据id查询单个对象
	 * 
	 * @param
	 * @name findById
	 * @active
	 * @state
	 * @type T
	 * @time 下午3:53:43
	 * @exception/throws
	 * @see
	 * @since
	 * @param id
	 * @return
	 */
	public T fetch(PK id) {
		return mongoTemplate.findById(id, entityClass);
	}

	/**
	 * 根据id删除单个对象
	 * 
	 * @param
	 * @name deleteById
	 * @active
	 * @state
	 * @type void
	 * @time 下午1:41:47
	 * @exception/throws
	 * @see
	 * @since
	 * @param id
	 */
	public void deleteById(PK id) {
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
		if (query != null && mongoTemplate.findOne(query, entityClass) != null) {
			mongoTemplate.remove(mongoTemplate.findOne(query, entityClass));
		}
	}

	/**
	 * 获取列表分页
	 * 
	 * @param
	 * @name queryPage
	 * @active
	 * @state
	 * @type Pager<T>
	 * @time 下午2:20:32
	 * @exception/throws
	 * @see
	 * @since
	 * @param currentPage
	 *            当前多少页
	 * @param pageSize
	 *            每页的显示条数
	 * @return
	 */
	public Pager<T> queryPage(int currentPage, int pageSize) {
		Query query = new Query();
		int totalCount = (int) this.mongoTemplate.count(query, this
				.getEntityClass());
		Pager<T> pager = new Pager<T>(pageSize, totalCount, currentPage);
		query.skip(getSkipNumber(currentPage, pageSize));// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录即每页显示多少条
		List<T> dataList = mongoTemplate.find(query, entityClass);
		pager.setDataList(dataList);
		return pager;
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
		int totalCount = (int) this.mongoTemplate.count(query, this
				.getEntityClass());
		Pager<T> pager = new Pager<T>(pageSize, totalCount, currentPage);
		query.skip(getSkipNumber(currentPage, pageSize));// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录即每页显示多少条
		List<T> dataList = mongoTemplate.find(query, entityClass);
		pager.setDataList(dataList);
		return pager;
	}

	/**
	 * 按条件查询并获取列表分页
	 * 
	 * @param
	 * @name queryPage
	 * @active
	 * @state
	 * @type Pager<T>
	 * @time 下午2:20:32
	 * @exception/throws
	 * @see
	 * @since
	 * @param currentPage
	 *            当前多少页
	 * @param pageSize
	 *            每页的显示条数
	 * @param criteria
	 *            查询条件
	 * @return
	 */
	public Pager<T> queryPage(int currentPage, int pageSize, Criteria criteria) {
		Query query = new Query();
		if (null != criteria) {
			query.addCriteria(criteria);
		}
		int totalCount = count(query);// 计算总条数
		Pager<T> pager = new Pager<T>(pageSize, totalCount, currentPage);
		query.skip(getSkipNumber(currentPage, pageSize));// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录即每页显示多少条
		List<T> dataList = mongoTemplate.find(query, entityClass);
		pager.setDataList(dataList);
		return pager;
	}

	/**
	 * 获取分页时跳过的条数
	 * 
	 * @param
	 * @name getSkipNumber
	 * @active
	 * @state
	 * @type int
	 * @time 下午2:19:37
	 * @exception/throws
	 * @see
	 * @since
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 */
	private int getSkipNumber(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 统计条数
	 * 
	 * @param criteria
	 * @return
	 */
	public int count(Criteria criteria) {
		Query query = new Query();
		if (null != criteria) {
			query.addCriteria(criteria);
		}
		return (int) mongoTemplate.count(query, this.getEntityClass());
	}

	/**
	 * 计算统计条数
	 * 
	 * @param query
	 * @return
	 */
	public int count(Query query) {
		return (int) mongoTemplate.count(query, this.getEntityClass());
	}

	/**
	 * 直接执行mongodb scripts(类似于sql)对数据库进行操作
	 * 
	 * @param jsonCommand
	 * @return
	 */
	public CommandResult executeCommand(String jsonCommand) {
		return mongoTemplate.executeCommand(jsonCommand);
	}

	/**
	 * 按条件查询并删除
	 * 
	 * @param query
	 */
	public void findAndRemove(Query query) {
		mongoTemplate.findAndRemove(query, entityClass);
	}

	/**
	 * 获取最大id,集合名称为实体名称的首字母小写
	 * 
	 * @return
	 */
	// @Deprecated
	// public int getMaxId() {
	// DBObject orderBy = new BasicDBObject("id", -1);// -1标示降序 1表示升序
	// DBCursor dbc = mongoTemplate.getCollection(
	// StringUtils.uncapitalize(entityClass.getSimpleName())).find()
	// .sort(orderBy).limit(1);
	// DBObject dbo = null;
	// if (dbc.hasNext()) {
	// dbo = dbc.next();
	// }
	// if (null != dbo) {
	// return (Integer) dbo.get("id");
	// }
	// return 0;
	// }
}
