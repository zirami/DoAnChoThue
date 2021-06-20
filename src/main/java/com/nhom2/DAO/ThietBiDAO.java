package com.nhom2.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.THIETBI;
@Transactional
public class ThietBiDAO {
	
	/* HIỂN THỊ DANH SÁCH THIẾT BỊ */
	public List<THIETBI> getAll(SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from THIETBI order by matb desc";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<THIETBI> query = session.createQuery(hql);
		List<THIETBI> list = query.list();
		return list;
	}
	
	/* THÊM THIẾT BỊ */
	public Boolean save(SessionFactory factory, THIETBI thietbi_them) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(thietbi_them);
			t.commit();	
			result = true;
			

		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			result = false;
			
		} finally {
			session.close();
		}
		
		return result;
	}
	/* SỬA THIẾT BỊ*/
	public Boolean update(SessionFactory factory, THIETBI thietbi_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.update(thietbi_sua);
			t.commit();	
			result = true;
			

		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			result = false;
			
		} finally {
			session.close();
		}
		
		return result;
	}
	/* XOÁ THIẾT BỊ */
	public Boolean del(SessionFactory factory, THIETBI thietbi_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.delete(thietbi_xoa);;
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
	
	
	/* TÌM THIẾT BỊ BẰNG ID*/
	public THIETBI getById(String id, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from THIETBI where matb = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<THIETBI> query = session.createQuery(hql);		
		query.setParameter("id", id.toString()	);
		if (query.list().isEmpty()) return null;
		THIETBI list = query.list().get(0); //Lấy ra phần tử đầu tiên vì chắc chắn 1 id chỉ cho ra 1 thiết bị
		return list;
	}
	
	/* TÌM KIẾM THIẾT BỊ*/
	//viết cho vui chứ chưa đụng tới
	public List<THIETBI> getByName(String name, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from THIETBI where ten = :name";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<THIETBI> query = session.createQuery(hql);
		List<THIETBI> list = query.list();
		return list;
	}

	public THIETBI getById2thanhly(String id, SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from THIETBI where matb = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<THIETBI> query = session.createQuery(hql);		
		query.setParameter("id", id	);
		THIETBI thietbi = null;
		
		if (query.list().isEmpty())
			return thietbi;
		else thietbi = query.list().get(0);
		
		session.close();
		return thietbi;
	}
	
	public THIETBI getById2nhap(String id, SessionFactory factory) {
//		return getById2thanhly(id, factory);
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from THIETBI where matb = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<THIETBI> query = session.createQuery(hql);		
		query.setParameter("id", id	);
		THIETBI thietbi = null;
		
		if (query.list().isEmpty())
			return thietbi;
		else thietbi = query.list().get(0);
		
		session.close();
		return thietbi;
	}

	
}
