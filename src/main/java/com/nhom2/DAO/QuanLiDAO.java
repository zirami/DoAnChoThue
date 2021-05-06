package com.nhom2.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.QUANLI;
@Transactional
public class QuanLiDAO {
	
	/* HIỂN THỊ DANH SÁCH  */
	public List<QUANLI> getAll(SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from QUANLI";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<QUANLI> query = session.createQuery(hql);
		List<QUANLI> list = query.list();
		return list;
	}
	
	/* THÊM  */
	public Boolean save(SessionFactory factory, QUANLI quanli_them) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(quanli_them);
			t.commit();	
			result = true;
			

		} catch (Exception e) {
			t.rollback();
			result = false;
			
		} finally {
			session.close();
		}
		
		return result;
	}
	/* SỬA */
	public Boolean update(SessionFactory factory, QUANLI quanli_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.update(quanli_sua);
			t.commit();	
			result = true;
			

		} catch (Exception e) {
			t.rollback();
			result = false;
			
		} finally {
			session.close();
		}
		
		return result;
	}
	/* XOÁ */
	public Boolean del(SessionFactory factory, QUANLI quanli_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.delete(quanli_xoa);;
			t.commit();	
			result = true;
			

		} catch (Exception e) {
			t.rollback();
			result = false;
			
		} finally {
			session.close();
		}
		
		return result;
	}
	
	
	/* TÌM KIẾM BẰNG ID*/
	public QUANLI getById(String id, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from QUANLI where maql = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<QUANLI> query = session.createQuery(hql);		
		query.setParameter("id", id.toString()	);
		QUANLI list = query.list().get(0);
		return list;
	}
}