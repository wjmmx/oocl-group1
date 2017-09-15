package com.tennisReservation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.group1.booking.configurations.HibernateContext;

public class PremiumMemberIMPL {
	Session session;

	public Session setHibernateOpenSession() {
		return new HibernateContext().GetSessionFactory().openSession();
	}
	
//	public void main(String[] args) {
//		session = setHibernateOpenSession();
//		session.close();
//	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PremiumMember> getAllPremiumMember() {
		ArrayList<PremiumMember> alM = null;
		
		Session session = setHibernateOpenSession();
		Transaction tx = null;
		List<PremiumMember> tempHold = null;
		System.err.println("asdfasdfasdfasdfasdfasd");
		try {
			tx = session.beginTransaction();
			tempHold = (List<PremiumMember>) session.createQuery("FROM PremiumMember").list();
			alM = (ArrayList<PremiumMember>) tempHold;
			System.err.println("asdfasdfasdfasdfsadfasdfasdf"+alM.size());
			session.flush();
			tx.commit();
		} catch (HibernateException x) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}

		return alM;	
	}
	
	//Add Premium Member recurring schedule
	public String AddPremiumMemberSchedule(PremiumMember premiumMember) {
		// TODO Auto-generated method stub
		String isCreated = "false";
		Session session = setHibernateOpenSession();
		Transaction tx = null;
		PremiumMember pMember = premiumMember;
		try {
			tx = session.beginTransaction();
			pMember.setMemberId((Integer) session.save(premiumMember));
			ArrayList<PremiumMember> alM = getAllPremiumMember();
			PremiumMemberRule1(alM, premiumMember);
			session.flush();
			isCreated = "true";
			tx.commit();
		} catch (HibernateException x) {
			if (tx != null)
				isCreated = "false";
			tx.rollback();
		} finally {
			session.close();
		}
		return isCreated;
	}
	//updateMember recurring scheduling
	public String updateMemberSchedule(PremiumMember pm) {
		session = setHibernateOpenSession();
		Transaction tx = null;
		String isSuccess = "false";
		ArrayList<PremiumMember> alM = getAllPremiumMember();
		try {
			tx = session.beginTransaction();
			
			PremiumMember updatePremiumMember = (PremiumMember) session.get(PremiumMember.class, pm.getMemberId());
			PremiumMemberRule1(alM, pm);
			updatePremiumMember.setRecurringDay(pm.getRecurringDay()!=null&&!pm.getRecurringDay().equals("")?pm.getRecurringDay():updatePremiumMember.getRecurringDay());
			updatePremiumMember.setRecurringSlot(pm.getRecurringSlot()!=null&&!pm.getRecurringSlot().equals("")?pm.getRecurringSlot():updatePremiumMember.getRecurringSlot());	
			session.update(updatePremiumMember);
			tx.commit();
			isSuccess = "true";
		}catch (HibernateException e) {
			if (tx != null)
				isSuccess = "false";
			tx.rollback();
		}finally {
			session.close();
		}
		return isSuccess;
	}
	
	
	public String PremiumMemberRule1(ArrayList<PremiumMember> alM, PremiumMember pm){
		for(PremiumMember temp:alM) {
			if(temp.getMemberId()!= pm.getMemberId() && temp.getPrefferedCourt().equals(pm.getPrefferedCourt()) 
						&& temp.getRecurringDay().equals(pm.getRecurringDay()) 
							&& temp.getRecurringSlot().equals(pm.getRecurringSlot())) {
				throw new HibernateException("fail");
			}
		}
		return "true";
	}
}
