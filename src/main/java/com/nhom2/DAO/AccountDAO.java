	package com.nhom2.DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhom2.entity.ACCOUNT;
@Transactional
public class AccountDAO {
	
	/* HIỂN THỊ DANH SÁCH TK */
	public List<ACCOUNT> getAll(SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from ACCOUNT";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<ACCOUNT> query = session.createQuery(hql);
		List<ACCOUNT> list = query.list();
		return list;
	}
	
	/* THÊM  */
	public Boolean save(SessionFactory factory, ACCOUNT account_them) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.save(account_them);
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
	/* SỬA */
	public Boolean update(SessionFactory factory, ACCOUNT account_sua) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.update(account_sua);
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
	/* XOÁ */
	public Boolean del(SessionFactory factory, ACCOUNT account_xoa) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean result = true;
		try {
			session.delete(account_xoa);;
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
	
	

	
	/* TÌM KIẾM THIẾT BỊ*/
	public List<ACCOUNT> getByName(String name, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		String hql = "from ACCOUNT where username = :name";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<ACCOUNT> query = session.createQuery(hql);
		List<ACCOUNT> list = query.list();
		return list;
	}
	
	/* TÌM KIẾM 1 ACCOUNT*/
	public ACCOUNT getById(String name, SessionFactory factory){
		Session session;

		/* Nếu chưa có session nào thì tạo session mới */
		try {
			session = factory.getCurrentSession();
		} catch (Exception e) {
			session = factory.openSession();
		}
		String hql = "from ACCOUNT where username = :name";
		/* Bắt đầu quá trình truy vấn vào DB */

		Query<ACCOUNT> query = session.createQuery(hql);
		query.setParameter("name", name);
		if (query.list().isEmpty()) return null; 
		ACCOUNT list = query.list().get(0);
		return list;
	}
	
	public ACCOUNT getByGmail(String gmail, SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ACCOUNT WHERE gmail = '"+gmail+"'";
		Query <ACCOUNT> query = session.createQuery(hql);
		ACCOUNT account;
		try {
			account = (ACCOUNT)query.list().get(0);
		}
		catch(Exception e) {
			account=null;
		}
		return account;
	}
	public String getMd5(String input)
	   {
	       try {
	 
	           // Static getInstance method is called with hashing MD5
	           MessageDigest md = MessageDigest.getInstance("MD5");
	 
	           // digest() method is called to calculate message digest
	           //  of an input digest() return array of byte
	           byte[] messageDigest = md.digest(input.getBytes());
	 
	           // Convert byte array into signum representation
	           BigInteger no = new BigInteger(1, messageDigest);
	 
	           // Convert message digest into hex value
	           String hashtext = no.toString(16);
	           while (hashtext.length() < 32) {
	               hashtext = "0" + hashtext;
	           }
	           return hashtext;
	       } 
	 
	       // For specifying wrong message digest algorithms
	       catch (NoSuchAlgorithmException e) {
	           throw new RuntimeException(e);
	       }
	   }

}
