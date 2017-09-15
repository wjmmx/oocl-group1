package com.tennisReservation;

public class TimeSlot{

	
	
	boolean slots[] = {true,true,true,true,true,true};


	public boolean[] getSlots() {
		return slots;
	}

	public void setSlots(boolean[] slots) {
		this.slots = slots;
	}
	
	public void setTrue(int i) {
		this.slots[i] = true;
	}

	public void setFalse(int i) {
		this.slots[i] = false;
	}
	
	public boolean getSlot(int i) {
		return slots[i];
	}


}
