package com.nhom2.DAO;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhom2.entity.CT_PHIEUMUON;

@Transactional
public class CT_PhieuMuonDAO {
	
	// Thêm CT_PhieuMuon
	public Boolean save(SessionFactory factory, CT_PHIEUMUON ct_phieumuon) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(ct_phieumuon);
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
}
