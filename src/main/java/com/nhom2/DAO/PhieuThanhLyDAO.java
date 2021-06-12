package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom2.entity.CT_PHIEUTHANHLY;
import com.nhom2.entity.PHIEUTHANHLY;
import com.nhom2.entity.PHIEUTHANHLY;

@Transactional
public class PhieuThanhLyDAO {

	/* HIỂN THỊ DANH SÁCH THIẾT BỊ */
	public List<PHIEUTHANHLY> getAll(SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from PHIEUTHANHLY";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<PHIEUTHANHLY> query = session.createQuery(hql);
		List<PHIEUTHANHLY> list = query.list();
		return list;
	}

	/* THÊM PHIẾU NHẬP + CT_PHIEUTHANHLY */
	public Boolean saveOrUpdate(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
			System.out.println("session current");
		} catch (HibernateException e) {
			session = factory.openSession();
			System.out.println("session opened");
		}
		Transaction t = session.beginTransaction();

		Boolean result = true;
		try {
			session.saveOrUpdate(PHIEUTHANHLY);
			for (CT_PHIEUTHANHLY ct_ptl : PHIEUTHANHLY.getCt_phieuthanhlys()) {
				session.save(ct_ptl);
			}

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

	public Boolean deletect_ptl_by_MAPTL(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		// Xoá hết các dòng liên quan tới phiếu nhập đang truy xuất
		String hql = "delete from CT_PHIEUTHANHLY where phieuthanhly = :PHIEUTHANHLY";

		/* Bắt đầu quá trình truy vấn vào DB */
		try {
			Query<PHIEUTHANHLY> query = session.createQuery(hql);
			query.setParameter("PHIEUTHANHLY", PHIEUTHANHLY);

			// Nếu < 0 là thực thi thất bại
			if (query.executeUpdate() < 0)
				return false;

			t.commit();

		} catch (Exception e) {
			t.rollback();
			return false;

		} finally {
			session.close();
		}

		return true;
	}

	/* CẬP NHẬT PHIẾU THANH LÝ */
	public Boolean update(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {

		if (!deletect_ptl_by_MAPTL(factory, PHIEUTHANHLY))
			return false; // Thực thi xoá ct_ptl liên quan thất bại

		// Bắt đầu cập nhật ct_ptl mới
		return saveOrUpdate(factory, PHIEUTHANHLY);
	}

	/* XOÁ PHIẾU THANH LÝ */
	public Boolean delete(SessionFactory factory, PHIEUTHANHLY PHIEUTHANHLY) {
		Boolean result = false;
		if (!deletect_ptl_by_MAPTL(factory, PHIEUTHANHLY))
			return result; // Thực thi xoá ct_ptl liên quan thất bại
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(PHIEUTHANHLY);
			t.commit();
			result = true;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			result = false;
		} finally {
			session.close();
		}
		return result;
	}

	/* TÌM BẰNG ID */
	public PHIEUTHANHLY getById(String id, SessionFactory factory) {
		Session session = factory.openSession();
		String hql = "from PHIEUTHANHLY where maptl = :id";
		/* Bắt đầu quá trình truy vấn vào DB */
		Query<PHIEUTHANHLY> query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PHIEUTHANHLY> list = query.list();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public PHIEUTHANHLY getById2delete(String id, SessionFactory factory) {
		Session session = factory.openSession();
		String hql = "from PHIEUTHANHLY where maptl = :id";
		/* Bắt đầu quá trình truy vấn vào DB */
		Query<PHIEUTHANHLY> query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PHIEUTHANHLY> list = query.list();
		if (list.isEmpty())
			return null;
		session.close();
		return list.get(0);
	}
}
