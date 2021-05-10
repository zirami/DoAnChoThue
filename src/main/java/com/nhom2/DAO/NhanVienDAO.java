package com.nhom2.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.NHANVIEN;
@Transactional
public class NhanVienDAO {
	
	/* HIỂN THỊ DANH SÁCH  */
	public List<NHANVIEN> getAll(SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from NHANVIEN";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<NHANVIEN> query = session.createQuery(hql);
		List<NHANVIEN> list = query.list();
		return list;
	}
	
	/* THÊM NHÂN VIÊN */
	public Boolean save(SessionFactory factory, NHANVIEN nhanvien_them) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(nhanvien_them);
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
	public Boolean update(SessionFactory factory, NHANVIEN nhanvien_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.update(nhanvien_sua);
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
	/* XÓA NHÂN VIÊN */
	public Boolean del(SessionFactory factory, NHANVIEN nhanvien_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.delete(nhanvien_xoa);
			;
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
	public NHANVIEN getById(String id, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from NHANVIEN where manv = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<NHANVIEN> query = session.createQuery(hql);		
		query.setParameter("id", id.toString()	);
		NHANVIEN list = query.list().get(0);
		return list;
	}
}