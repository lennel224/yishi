package com.dao.admin;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.dao.BasicDao;
import com.entity.admin.AdminBaseInfo;
import com.other.Page;
import com.other.PublicDefine;
@Component
public class AdminBaseInfoDao extends BasicDao {
	
	/**
	 * 通过id查询 体检机构
	 * @param id
	 * @return 体检机构
	 */
	public AdminBaseInfo findById(Long id) {
		return (AdminBaseInfo) getSession().get(AdminBaseInfo.class, id);
	}

	public AdminBaseInfo findByLoginAccount(String username,Boolean enabled) {
		String hql = "from AdminBaseInfo where account=:username and enabled=:enabled";
		Query query = getSession().createQuery(hql);
		query.setString("username", username);
		query.setBoolean("enabled", enabled);
		return (AdminBaseInfo) query.uniqueResult();
	}
	
	/**
	 * 分页管理员
	 * @param settledId
	 * @param name
	 * @param account
	 * @param enabled
	 * @param locked
	 * @param order
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<AdminBaseInfo> searchListByCondition(Long settledId,String name,String account,Boolean enabled,Boolean locked, int order, Page<AdminBaseInfo> page){
		String hql = "from AdminBaseInfo h where 1=1 ";
		if(settledId != null){
			hql += "and h.settledId=:settledId ";
		}
		if(StringUtils.isNotEmpty(name)){
			hql += "and h.name=:name ";
		}
		if(StringUtils.isNotEmpty(account)){
			hql += "and h.account=:account ";
		}
		if(enabled != null){
			hql += "and h.enabled=:enabled ";
		}
		if(locked != null){
			hql += "and h.locked=:locked ";
		}
		
		hql += this.getPageOrderByCondition(order);
		
		Query query = getSession().createQuery(hql);
		
		if(settledId != null){
			query.setLong("settledId", settledId);
		}
		if(StringUtils.isNotEmpty(name)){
			query.setString("name", name);
		}
		if(StringUtils.isNotEmpty(account)){
			query.setString("account", account);
		}
		if(enabled != null){
			query.setBoolean("enabled", enabled);
		}
		if(locked != null){
			query.setBoolean("locked", locked);
		}
		
		query.setMaxResults(page.getPageSize());
		query.setFirstResult(page.getStart());
		page.setResults(query.list());
		return page;
	}
	
	/**
	 * 分页管理员数量
	 * @param settledId
	 * @param name
	 * @param account
	 * @param enabled
	 * @param locked
	 * @return
	 */
	public int getListCountByCondition(Long settledId,String name,String account,Boolean enabled,Boolean locked){
		String hql = "select count(*) from AdminBaseInfo h where 1=1 ";
		if(settledId != null){
			hql += "and h.settledId=:settledId ";
		}
		if(StringUtils.isNotEmpty(name)){
			hql += "and h.name=:name ";
		}
		if(StringUtils.isNotEmpty(account)){
			hql += "and h.account=:account ";
		}
		if(enabled != null){
			hql += "and h.enabled=:enabled ";
		}
		if(locked != null){
			hql += "and h.locked=:locked ";
		}
		
		Query query = getSession().createQuery(hql);
		
		if(settledId != null){
			query.setLong("settledId", settledId);
		}
		if(StringUtils.isNotEmpty(name)){
			query.setString("name", name);
		}
		if(StringUtils.isNotEmpty(account)){
			query.setString("account", account);
		}
		if(enabled != null){
			query.setBoolean("enabled", enabled);
		}
		if(locked != null){
			query.setBoolean("locked", locked);
		}
		return ((Long) query.uniqueResult()).intValue();
	}
	
	/**
	 * 分页管理员 排序规则
	 * @param order
	 * @return
	 */
	public String getPageOrderByCondition(int order){
		if(PublicDefine.ORDER_BY_CREATE_DATE_ASC == order){
			return " order by h.createDate asc ";
		}
		if(PublicDefine.ORDER_BY_CREATE_DATE_DESC == order){
			return " order by h.createDate desc ";
		}
		return "";
	}
	
	/**
	 * 管理员
	 * @param settledId
	 * @param name
	 * @param account
	 * @param enabled
	 * @param locked
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdminBaseInfo> searchListByCondition(Long settledId,String name,String account,Boolean enabled,Boolean locked, int order){
		String hql = "from AdminBaseInfo h where 1=1 ";
		if(settledId != null){
			hql += "and h.settledId=:settledId ";
		}
		if(StringUtils.isNotEmpty(name)){
			hql += "and h.name=:name ";
		}
		if(StringUtils.isNotEmpty(account)){
			hql += "and h.account=:account ";
		}
		if(enabled != null){
			hql += "and h.enabled=:enabled ";
		}
		if(locked != null){
			hql += "and h.locked=:locked ";
		}
		
		hql += this.getListOrderByCondition(order);
		
		Query query = getSession().createQuery(hql);
		
		if(settledId != null){
			query.setLong("settledId", settledId);
		}
		if(StringUtils.isNotEmpty(name)){
			query.setString("name", name);
		}
		if(StringUtils.isNotEmpty(account)){
			query.setString("account", account);
		}
		if(enabled != null){
			query.setBoolean("enabled", enabled);
		}
		if(locked != null){
			query.setBoolean("locked", locked);
		}
		return query.list();
	}
	
	/**
	 * 查询管理员
	 * @param order
	 * @return
	 */
	public String getListOrderByCondition(int order){
		return "";
	}
}
