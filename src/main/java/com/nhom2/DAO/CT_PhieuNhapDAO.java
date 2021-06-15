package com.nhom2.DAO;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nhom2.entity.CT_PHIEUNHAP;

@Transactional
public class CT_PhieuNhapDAO {
	
	// Thêm CT_PhieuNhap
	public Boolean save(SessionFactory factory, CT_PHIEUNHAP ct_phieunhap) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(ct_phieunhap);
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
	public Boolean update(SessionFactory factory, CT_PHIEUNHAP ct_phieunhap_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = false;
		try {
			session.update(ct_phieunhap_sua);
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
	//Delete
	public Boolean delete(SessionFactory factory,CT_PHIEUNHAP ct_phieunhap_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = false;
		try {
			session.delete(ct_phieunhap_xoa);
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
