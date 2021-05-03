package com.nhom2.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhom2.entity.PHIEUMUON;

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
}
