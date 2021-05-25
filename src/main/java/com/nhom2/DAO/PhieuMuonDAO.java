package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhom2.entity.PHIEUMUON;
import com.nhom2.entity.THIETBI;

@Transactional
public class PhieuMuonDAO {
	

	//Lấy dữ liệu ra list
	public List<PHIEUMUON> getAll(SessionFactory factory ){
		Session session;
		try {
			session = factory.getCurrentSession();
		}
		catch(Exception e) {
			session = factory.openSession();
		}
		String hql = "FROM PHIEUMUON";
		Query query = session.createQuery(hql);
		List <PHIEUMUON> list = query.list();
		return list;
	}  
	public Boolean save(SessionFactory factory,PHIEUMUON phieumuon_moi) {
		Boolean result=true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(phieumuon_moi);
			t.commit();
			result = true;
		}
		catch(Exception e) {
			t.rollback();
			result = false;
		}
		finally {
			session.close();
		}
		return result;
	}
	/* TÌM Phiếu mượn BẰNG ID*/
	public PHIEUMUON getById(String id, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from PHIEUMUON where mapm = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<PHIEUMUON> query = session.createQuery(hql);		
		query.setParameter("id", id.toString()	);
		PHIEUMUON list = query.list().get(0); //Lấy ra phần tử đầu tiên vì chắc chắn 1 id chỉ cho ra 1 phiếu mượn
		return list;
	}
	
	//Update
	public Boolean update(SessionFactory factory,PHIEUMUON phieumuon_sua) {
		Boolean result=false;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(phieumuon_sua);
			t.commit();
			result = true;
		}
		catch(Exception e) {
			t.rollback();
			result=false;
		}
		finally {
			session.close();
		}
		return result;
	}
	//Delete
	public Boolean delete(SessionFactory factory,PHIEUMUON phieumuon_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = false;
		try {
			session.delete(phieumuon_xoa);
			t.commit();
			result = true;
		}
		catch(Exception e) {
			t.rollback();
			result=false;
		}
		finally {
			session.close();
		}
		return result;
		
	}
}
