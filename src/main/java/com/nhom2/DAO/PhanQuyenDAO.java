package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nhom2.entity.PHANQUYEN;

@Transactional
public class PhanQuyenDAO {
	public List<PHANQUYEN> getAll(SessionFactory factory){
		Session session = factory.getCurrentSession();
		String hql = "FROM PHANQUYEN";
		Query<PHANQUYEN> query = session.createQuery(hql);
		List<PHANQUYEN> list = query.list();
		return list;
	}
}
