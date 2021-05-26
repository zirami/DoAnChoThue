package com.nhom2.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.PHIEUNHAP;

@Transactional
public class PhieuNhapDAO {
	/* HIỂN THỊ DANH SÁCH THIẾT BỊ */
	public List<PHIEUNHAP> getAll(SessionFactory factory){
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
}
