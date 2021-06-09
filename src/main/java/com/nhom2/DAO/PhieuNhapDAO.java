package com.nhom2.DAO;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.CT_PHIEUNHAP;
import com.nhom2.entity.PHIEUNHAP;

@Transactional
public class PhieuNhapDAO {
	@PersistenceContext
	private Session session;
	/* HIỂN THỊ DANH SÁCH THIẾT BỊ */
	public List<PHIEUNHAP> getAll(SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from PHIEUNHAP";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<PHIEUNHAP> query = session.createQuery(hql);
		List<PHIEUNHAP> list = query.list();
		return list;
	}

	/* THÊM PHIẾU NHẬP + CT_PHIEUNHAP */
	public Boolean saveOrUpdate(SessionFactory factory, PHIEUNHAP phieunhap) {
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
			session.saveOrUpdate(phieunhap);
			for (CT_PHIEUNHAP ct_pn : phieunhap.getCt_phieunhaps()) {
				session.save(ct_pn);
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

	public Boolean deleteCT_PN_by_MAPN(SessionFactory factory, PHIEUNHAP phieunhap) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		// Xoá hết các dòng liên quan tới phiếu nhập đang truy xuất
		String hql = "delete from CT_PHIEUNHAP where phieunhap = :phieunhap";

		/* Bắt đầu quá trình truy vấn vào DB */
		try {
			Query<PHIEUNHAP> query = session.createQuery(hql);
			query.setParameter("phieunhap", phieunhap);

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

	/* CẬP NHẬT PHIẾU NHẬP */
	public Boolean update(SessionFactory factory, PHIEUNHAP phieunhap) {

		if (!deleteCT_PN_by_MAPN(factory, phieunhap))
			return false; // Thực thi xoá ct_pn liên quan thất bại

		// Bắt đầu cập nhật CT_PN mới
		return saveOrUpdate(factory, phieunhap);
	}

	/* XOÁ PHIẾU NHẬP */
	public Boolean delete(SessionFactory factory, PHIEUNHAP phieunhap) {
		Boolean result = false;
		if (!deleteCT_PN_by_MAPN(factory, phieunhap))
			return result; // Thực thi xoá ct_pn liên quan thất bại
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(phieunhap);
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
	public PHIEUNHAP getById(String id, SessionFactory factory) {
		Session session = factory.openSession();
		String hql = "from PHIEUNHAP where mapn = :id";
		/* Bắt đầu quá trình truy vấn vào DB */
		Query<PHIEUNHAP> query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PHIEUNHAP> list = query.list();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public PHIEUNHAP getById2delete(String id, SessionFactory factory) {
		Session session = factory.openSession();
		String hql = "from PHIEUNHAP where mapn = :id";
		/* Bắt đầu quá trình truy vấn vào DB */
		Query<PHIEUNHAP> query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PHIEUNHAP> list = query.list();
		if (list.isEmpty())
			return null;
		session.close();
		return list.get(0);
	}

}
