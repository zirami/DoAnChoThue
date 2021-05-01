package com.nhom2.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.LOAITHIETBI;

@Transactional
public class LoaiThietBiDAO {

	/* HIỂN THỊ DANH SÁCH THIẾT BỊ */
	public List<LOAITHIETBI> getAll(SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from LOAITHIETBI";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<LOAITHIETBI> query = session.createQuery(hql);
		List<LOAITHIETBI> list = query.list();
		return list;
	}

	/* THÊM THIẾT BỊ */
	public Boolean save(SessionFactory factory, LOAITHIETBI LOAITHIETBI_them) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(LOAITHIETBI_them);
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

	/* SỬA THIẾT BỊ */
	public Boolean update(SessionFactory factory, LOAITHIETBI LOAITHIETBI_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.update(LOAITHIETBI_sua);
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

	/* XOÁ THIẾT BỊ */
	public Boolean del(SessionFactory factory, LOAITHIETBI LOAITHIETBI_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.delete(LOAITHIETBI_xoa);
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

	/* TÌM THIẾT BỊ BẰNG ID */
	public LOAITHIETBI getById(String id, SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from LOAITHIETBI where matb = :id";

		/* Bắt đầu quá trình truy vấn vào DB */

		Query<LOAITHIETBI> query = session.createQuery(hql);
		query.setParameter("id", id.toString());
		LOAITHIETBI list = query.list().get(0); // Lấy ra phần tử đầu tiên vì chắc chắn 1 id chỉ cho ra 1 thiết bị
		return list;
	}

	/* TÌM KIẾM THIẾT BỊ */
	// viết cho vui chứ chưa đụng tới
	public List<LOAITHIETBI> getByName(String name, SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from LOAITHIETBI where ten = :name";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<LOAITHIETBI> query = session.createQuery(hql);
		List<LOAITHIETBI> list = query.list();
		return list;
	}

}
