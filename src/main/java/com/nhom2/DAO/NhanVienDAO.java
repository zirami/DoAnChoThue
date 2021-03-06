package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom2.entity.NHANVIEN;
import com.nhom2.entity.THIETBI;

@Transactional
public class NhanVienDAO {
	/* HIỂN THỊ DANH SÁCH NHÂN VIÊN */
	public List<NHANVIEN> getAll(SessionFactory factory) {
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

	/* XÓA  */
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

	/* TÌM BẰNG ID */
	public NHANVIEN getById(String id, SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
//		String hql = "from THIETBI where matb = :id";
//		
//		/* Bắt đầu quá trình truy vấn vào DB */
//
		NHANVIEN list = session.get(NHANVIEN.class, id);
		// Lấy ra phần tử đầu tiên vì chắc chắn 1 id chỉ cho ra 1 nhân viên
		return list;
	}

	public NHANVIEN getByUserName(String username, SessionFactory factory) {
		Session hibernate_session;
		/* Nếu chưa có session nào thì tạo session mới */
		try {
			hibernate_session = factory.getCurrentSession();
		} catch (HibernateException e) {
			hibernate_session = factory.openSession();
		}
		
		//String hql = "from NHANVIEN where username = :username";
		NHANVIEN nv = hibernate_session.get(NHANVIEN.class, username);
		
		return nv;
	}
	

	
	

}

