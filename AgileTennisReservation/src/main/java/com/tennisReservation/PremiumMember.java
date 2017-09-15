package com.tennisReservation;

public class PremiumMember {
	int memberId;
	String recurringDay;
	String recurringSlot;
	String prefferedCourt;

	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getRecurringDay() {
		return recurringDay;
	}
	public void setRecurringDay(String recurringDay) {
		this.recurringDay = recurringDay;
	}
	public String getRecurringSlot() {
		return recurringSlot;
	}
	public void setRecurringSlot(String recurringSlot) {
		this.recurringSlot = recurringSlot;
	}
	public String getPrefferedCourt() {
		return prefferedCourt;
	}
	public void setPrefferedCourt(String prefferedCourt) {
		this.prefferedCourt = prefferedCourt;
	}
}
