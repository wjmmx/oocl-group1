package com.tennisReservation;

public class Courts {

	TimeSlot timeSlot [] = new TimeSlot[8];;
	
	public Courts() {
		
		for (int i = 0; i < timeSlot.length; i++) {
			timeSlot[i] = new TimeSlot();
		}
		
	}

	public TimeSlot getTimeSlot(int i) {
		
		return timeSlot[i];
	}
	
	
	
	
	
}
