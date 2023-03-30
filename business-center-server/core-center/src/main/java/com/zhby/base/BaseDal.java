package com.zhby.base;
 
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.zhby.config.dbhelper.DbHelperOraImpl;
import com.zhby.utils.core.ZhCollectionUtils;
import com.zhby.utils.database.inter.DataBaseDal;
import com.zhby.utils.entity.PagedList;
import com.zhby.utils.entity.PgQuery;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseDal<T> implements DataBaseDal<T> {

	@Autowired
	protected Mapper<T> mapper;
	
	@Autowired
	public DbHelperOraImpl jdbcDao;

	public Mapper<T> getMapper() {
		return mapper;
	}

	public T getModel(Object key) {
		
		return mapper.selectByPrimaryKey(key);
	}

	public int saveNull(T entity) {

		return mapper.insert(entity);
	}

	public int Add(T entity) {

		return mapper.insertSelective(entity);
	}

	public int delete(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	public int updateNull(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	public int update(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	public List<T> getListByExample(Object example) {

		return mapper.selectByExample(example);
	}

	public List<T> getListAll() {
		return mapper.selectAll();
	}

	public List<T> getList(T entity) {

		return mapper.select(entity);
	}
	
	public boolean exists(String strWhere,Object... sqlParams){
		Class<T> clazz =getRealClass();
		return jdbcDao.Exists("select COUNT(1) from "+clazz.getSimpleName()+" where "+strWhere, sqlParams);
	}
	
	public Boolean isDataExists(String zbprocode) {
		Class<T> clazz =getRealClass();
		return jdbcDao.Exists("select COUNT(1) from "+clazz.getSimpleName()+" where zbprocode=? AND NVL(Scbj, '0')='0' ", zbprocode);
	}

	public List<T> getListByWhere(String sWhere) {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		 
		List<T> list = jdbcDao.queryListForSql(
				"select * from " + clazz.getSimpleName() + " where " + sWhere,
				clazz);
		return ZhCollectionUtils.parseToUnNullList(list);

	}
	public List<T> getListByWhere(String sWhere,Object... params) {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		 
		List<T> list = jdbcDao.queryListForSql("select * from " + clazz.getSimpleName() + " where " + sWhere, params, clazz);
		return ZhCollectionUtils.parseToUnNullList(list);

	}
	public T getModelByT(T entity) {
		return mapper.selectOne(entity);
	}
	// TODO 其他...
	/**
	 * 根据条件获取分页列表
	 * @param pgq 分页控件信息
	 * @param strWhere sql参数化查询条件，不需要where关键字
	 * @param lstSqlParam lstSqlParam sql参数化查询的集合
	 * @return 查询结果，为空返回空的pagelist类
	 */
	public PagedList<T> getPgQuery(PgQuery pgq, String strWhere, List<String> lstSqlParam) {
		Class<T> clazz = getRealClass();
		return jdbcDao.getPagedListData(clazz , "select * from "+clazz.getSimpleName()+" where "+strWhere, pgq, lstSqlParam.toArray());
	}

	public Class<T> getRealClass(){
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}


}
