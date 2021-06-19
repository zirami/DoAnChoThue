package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom2.entity.NGUOIMUON;
import com.nhom2.entity.THIETBI;

@Transactional
public class NguoiMuonDAO {
	/* HIỂN THỊ DANH SÁCH NGUOIMUON */
	public List<NGUOIMUON> getAll(SessionFactory factory) {
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from NGUOIMUON";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<NGUOIMUON> query = session.createQuery(hql);
		List<NGUOIMUON> list = query.list();
		return list;
	}

	/* THÊM NGƯỜI MƯỢN */
	public Boolean save(SessionFactory factory, NGUOIMUON nguoimuon_them) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(nguoimuon_them);
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
	public Boolean update(SessionFactory factory, NGUOIMUON nguoimuon_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.update(nguoimuon_sua);
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

	/* XÓA NGƯỜI MƯỢN */
	public Boolean del(SessionFactory factory, NGUOIMUON nguoimuon_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.delete(nguoimuon_xoa);
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
	public NGUOIMUON getById(String id, SessionFactory factory) {
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
//		Query<THIETBI> query = session.createQuery(hql);		
//		query.setParameter("id", id.toString()	);
		NGUOIMUON list = session.get(NGUOIMUON.class, id);
		// Lấy ra phần tử đầu tiên vì chắc chắn 1 id chỉ cho ra 1 thiết bị
		return list;
	}
	public String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }
	public String chuanHoaTen(String str) {

		  str = chuanHoa(str);
	        String temp[] = str.split(" ");
	        str = ""; // ? ^-^
	        for (int i = 0; i < temp.length; i++) {
	            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1).toLowerCase();
	            if (i < temp.length - 1) // ? ^-^
	                str += " ";
	        }
	        return str;
	}

}

