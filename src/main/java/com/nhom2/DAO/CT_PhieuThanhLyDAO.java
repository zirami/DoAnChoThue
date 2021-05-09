package com.nhom2.DAO;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nhom2.entity.CT_PHIEUTHANHLY;

@Transactional
public class CT_PhieuThanhLyDAO {
	
	// Thêm CT_PhieuThanhLy
	public Boolean save(SessionFactory factory, CT_PHIEUTHANHLY ct_phieuthanhly) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(ct_phieuthanhly);
			t.commit();
			result =  true;
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
	//Update
	public Boolean update(SessionFactory factory, CT_PHIEUTHANHLY ct_phieuthanhly_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = false;
		try {
			session.update(ct_phieuthanhly_sua);
			t.commit();
			result=true;
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
	//Xoa CT_PHIEUTHANHLY
	public Boolean delete(SessionFactory factory,CT_PHIEUTHANHLY ct_phieuthanhly_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = false;
		try {
			session.delete(ct_phieuthanhly_xoa);
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
}
