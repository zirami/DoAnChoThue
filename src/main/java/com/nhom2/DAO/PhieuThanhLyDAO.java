package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom2.entity.PHIEUTHANHLY;

@Transactional
public class PhieuThanhLyDAO {
	

	//Lấy dữ liệu ra list
	public List<PHIEUTHANHLY> getAll(SessionFactory factory ){
		Session session;
		try {
			session = factory.getCurrentSession();
		}
		catch(Exception e) {
			session = factory.openSession();
		}
		String hql = "FROM PHIEUTHANHLY";
		Query query = session.createQuery(hql);
		List <PHIEUTHANHLY> list = query.list();
		return list;
	}
	public Boolean save(SessionFactory factory,PHIEUTHANHLY phieuthanhly_moi) {
		Boolean result=true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(phieuthanhly_moi);
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
	public PHIEUTHANHLY getById(String id, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from PHIEUTHANHLY where maptl = :id";
		
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<PHIEUTHANHLY> query = session.createQuery(hql);		
		query.setParameter("id", id.toString()	);
		PHIEUTHANHLY list = query.list().get(0); //Lấy ra phần tử đầu tiên vì chắc chắn 1 id chỉ cho ra 1 phiếu thanh lý
		return list;
	}
	
	//Update
	public Boolean update(SessionFactory factory,PHIEUTHANHLY phieuthanhly_sua) {
		Boolean result=false;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(phieuthanhly_sua);
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
	public Boolean delete(SessionFactory factory,PHIEUTHANHLY phieuthanhly_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = false;
		try {
			session.delete(phieuthanhly_xoa);
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
