package com.tennisReservation.test;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tennisReservation.PremiumMember;
import com.tennisReservation.PremiumMemberIMPL;

public class PremiumMemberTest {

	PremiumMember premiumMember = new PremiumMember();
	PremiumMemberIMPL premiumImpl = new PremiumMemberIMPL();
	ArrayList<PremiumMember> locListPremiumMem = premiumImpl.getAllPremiumMember();
	@Before
	public void init() {
		premiumMember = new PremiumMember();
		premiumImpl = new PremiumMemberIMPL();
	
	}
	
	@Test
	public void testGetPremiumMember() {
		premiumMember.setMemberId(12345);
		Assert.assertEquals(premiumMember.getMemberId(), 12345);	
		
	}
	@Test
	public void testHibernateConnection() {
		Assert.assertNotEquals(new PremiumMemberIMPL().setHibernateOpenSession(), null);
	}
	
	@Test
	public void testAddSuccessPremiumMember() {
	//AddTest: please have the FF data Change when testing is true(based on Database)
		premiumMember.setPrefferedCourt("COURT3");
		premiumMember.setRecurringDay("THURSDAY");
		premiumMember.setRecurringSlot("15:00-18:00");
		Assert.assertEquals(new PremiumMemberIMPL().AddPremiumMemberSchedule(premiumMember), "true");
	}
	@Test
	public void testUpdateSuccessPremiumMember() {
		//AddTest: please have the FF data Change when testing is true(based on Database)
		
	}
	

	@Test
	public void testPremiumRule1(){
		testPremiumRuleValue1();
		Assert.assertEquals(premiumImpl.PremiumMemberRule1(locListPremiumMem, premiumMember), "true");
	}
	@Test
	public void testPremiumRule2() {
		testPremiumRuleValue2();
		Assert.assertEquals(premiumImpl.PremiumMemberRule1(locListPremiumMem, premiumMember), "true");
	}
	@Test 
	public void testPremiumRule3() {
		testPremiumRuleValue3();
		Assert.assertEquals(premiumImpl.PremiumMemberRule1(locListPremiumMem, premiumMember), "true");
	}
	
	//
	@Test(expected = HibernateException.class)
	public void testPremiumRule4() {
		testPremiumRuleValue4();
		Assert.assertEquals(premiumImpl.PremiumMemberRule1(locListPremiumMem, premiumMember), "true");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	/*given: a user reserving /updating is a premium member
	when: court booked is not taken
		and day is not taken 
		and slot is not taken
	then: true*/
	public void testPremiumRuleValue1() {
		premiumMember.setPrefferedCourt("COURT3");
		premiumMember.setRecurringDay("WEDNESDAY");
		premiumMember.setRecurringSlot("15:00-18:00");
	
	}
	/*given: a user reserving/updating is a premium member
	when: court booked is taken
		and day is not taken 
		and slot is not taken
	then: true*/
	public void testPremiumRuleValue2() {
		
		premiumMember.setPrefferedCourt("COURT1");
		premiumMember.setRecurringDay("FRIDAY");
		premiumMember.setRecurringSlot("13:00-15:00");

	}
	
	/*given: a user reserving/updating is a premium member
	when: court booked is taken
		and day is taken 
		and slot is not taken
	then: true*/
	public void testPremiumRuleValue3() {
		
		premiumMember.setPrefferedCourt("COURT1");
		premiumMember.setRecurringDay("TUESDAY");
		premiumMember.setRecurringSlot("13:00-15:00");
	
	}
	
	/*given: a user reserving/updating is a premium member
	when: court booked is taken
		and day is taken 
		and slot is taken
	then: true*/
	public void testPremiumRuleValue4() {
		
		premiumMember.setPrefferedCourt("COURT2");
		premiumMember.setRecurringDay("MONDAY");
		premiumMember.setRecurringSlot("13:00-15:00");

	}
	
	
	

	
//	@Test
//	public void testAddPremiumMember1() {
//		//add premium member and day is taken and slot is not taken
//
//		premiumMember.setRecurringDay("Monday");
//		premiumMember.setRecurringSlot("15:00-18:00");
//		premiumMember.setIsActive("1");
//		Assert.assertEquals(new PremiumMemberIMPL().AddPremiumMemberSchedule(premiumMember), "false");
//	}

	
}
