package com.tennisReservation;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	ArrayList <WeekDays> wd = new ArrayList <WeekDays>() ;
	String times[] = {"9:00-11:00","11:00-13:00","13:00-15:00","15:00-17:00","17:00-19:00","19:00-21:00"};
	String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	PremiumMemberIMPL pm = new PremiumMemberIMPL();
	

	public static void main(String[] args) {
		
//		WeekDays wd = new WeekDays();
		Main m = new Main();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		m.initWeekDays();
		int day;
		int slot;
		int court;
		int premiumNumber;
		
		while (true) {
			System.out.println();
			m.printMenu();
			switch (scan.nextInt()) {
			case 1:
				m.printWeek();
				day = scan.nextInt();
				m.printCourts();
				court = scan.nextInt();
				m.printSlots(day,court);
				
				break;
			case 2:
				m.printWeek();
				day = scan.nextInt();
				m.printCourts();
				court = scan.nextInt();
				m.printSlots(day,court);
				System.out.println("Please select desired available slot: ");
				slot = scan.nextInt();
				m.reserveSlot(day,court, slot);
				break;

			case 3:
				
				m.printWeek();
				day = scan.nextInt();
				m.printCourts();
				court = scan.nextInt();
				m.printSlots(day,court);
				System.out.println("Please select desired available slot: ");
				slot = scan.nextInt();
				System.out.println("Please enter your Premium ID Number: ");
				premiumNumber = scan.nextInt();
				
				m.reserveSlot(day,court, slot, premiumNumber);
				
				break;
				
			default:
				System.out.println("Program Ended");
				System.exit(0);
			}
		}
		
	}
	
	public void reserveSlot(int day,int court, int slot, int premiumNumber) {
		PremiumMember premiumMember = new PremiumMember();
		
		if (wd.get(day).getCourts().getTimeSlot(court).getSlot(slot)) {
			wd.get(day).getCourts().getTimeSlot(court).setFalse(slot);			

			premiumMember.setMemberId(premiumNumber);
			premiumMember.setPrefferedCourt("COURT"+(court+1));
			premiumMember.setRecurringDay(days[day].toUpperCase());
			premiumMember.setRecurringSlot(times[slot]);
			String result = pm.AddPremiumMemberSchedule(premiumMember);
			System.out.println(result);
			if (result.equals("true")) {
				System.out.println("Reservation Successfull! ");
				System.out.println("You have reserved court " + (court+1) + " on " + days[day] + times[slot]);
			}
		} else {
			System.out.println("Cannot reserve. Slot already taken");
		}
		
	}
	public void reserveSlot(int day,int court, int slot) {
		
		if (wd.get(day).getCourts().getTimeSlot(court).getSlot(slot)) {
			wd.get(day).getCourts().getTimeSlot(court).setFalse(slot);			
			System.out.println("You have reserved court " + (court+1) + " on " + days[day] + times[slot]);

			
		} else {
			System.out.println("Cannot reserve. Slot already taken");
		}
		
	}
	
	
	
	public void printMenu() {
		System.out.println("==================================================");
		System.out.println("Agile Tennis Club");
		System.out.println("==================================================\n");
		System.out.println("Court Reservation: "
				+ "\n1 - View Schedules"
				+ "\n2 - Reserve Slot"
				+ "\n3 - Premium Reserve");
	}
	
	public void printWeek(){
		System.out.println("Please choose day:"
				+ "\n0 - Monday"
				+ "\n1 - Tuesday"
				+ "\n2 - Wednesday"
				+ "\n3 - Thursday"
				+ "\n4 - Friday"
				+ "\n5 - Saturday"
				+ "\n6 - Sunday"
				+ "\n7 - Back");
	}
	
	public void printCourts(){
		
		System.out.println("Please choose Court:"
				+ "\n0 - Court 1"
				+ "\n1 - Court 2"
				+ "\n2 - Court 3"
				+ "\n3 - Court 4"
				+ "\n4 - Court 5"
				+ "\n5 - Court 6"
				+ "\n6 - Court 7"
				+ "\n7 - Court 8"
				+ "\n8 - Back");
	}
	
	public void printSlots(int day,int court) {
		System.out.println("Court "+(court + 1)+" Available Times: ");
		
		for (int i = 0; i < wd.get(day).getCourts().getTimeSlot(court).getSlots().length; i++) {
			System.out.print("Slot " + i + " is ");
			if (wd.get(day).getCourts().getTimeSlot(court).getSlot(i)) {
				System.out.print("available " + times[i] +"\n");
			} else {
				System.out.print("not available "+ times[i] +"\n");
			}
		}
		
	}
	
	public void initWeekDays(){
		
		for(int i = 0; i < 7; i++) {
			
			wd.add(new WeekDays());
			
		}
		
		
		ArrayList<PremiumMember> pmList = pm.getAllPremiumMember();
		
		int recurringDay = 0;
		int recurringSlot = 0;
		int prefferedCourt = 0;
		for (PremiumMember premiumMember : pmList) {
			
			for (int i = 0; i < days.length; i++) {
				if (days[i].toUpperCase().equals(premiumMember.getRecurringDay())) {
					recurringDay = i;
					break;
				}
			}
			for (int i = 0; i < times.length; i++) {
				if (times[i].equals(premiumMember.getRecurringSlot())) {
					recurringSlot = i;
					break;
				}
			}
			 
			prefferedCourt = Integer.parseInt(premiumMember.getPrefferedCourt().substring(5)) ;
			
			wd.get(recurringDay).getCourts().getTimeSlot(prefferedCourt-1).setFalse(recurringSlot);
			
		}
		
	}
	
}
