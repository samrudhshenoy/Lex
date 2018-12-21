package com.lahacks.library.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.lahacks.library.Main;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class BooksOut {

	//declaring all of the parameters
	private final StringProperty fName;
	private final StringProperty lName;
	private final StringProperty status;
	private final IntegerProperty bookLimit;
	public final IntegerProperty timeLimit;
	private final IntegerProperty amtOwed;
	private final StringProperty title;
	private final StringProperty author;
	private final IntegerProperty id;
	private int numBooks;
	private static Main main;
	
	
	//creating outline for BooksOut object
	public BooksOut(){
		this("", "", "", 0, 0, 0, "", "", 0);
}
	//setting values to all parameters
	public BooksOut(String firstName, String lastName, String rank, int booksLimit, int timesLimit, int amountOwed, String bookTitle, String bookAuthor, int bookID){
		fName = new SimpleStringProperty(firstName);
		lName = new SimpleStringProperty(lastName);
		status = new SimpleStringProperty(rank);
		bookLimit = new SimpleIntegerProperty(booksLimit);
		timeLimit = new SimpleIntegerProperty(timesLimit);
		amtOwed = new SimpleIntegerProperty(amountOwed);
		title = new SimpleStringProperty(bookTitle);
		author = new SimpleStringProperty(bookAuthor);
		id = new SimpleIntegerProperty(bookID);
	}
	
	
	

	//sets limits with inputted information
	public void changeLimits(Main main, int sBookLimit, int sTimeLimit, int sFinesLimit, int tBookLimit, int tTimeLimit, int tFinesLimit) {
		numBooks = main.getBooksOutData().size(); //calculates size of the data
		for(int i = 0; i < numBooks; i++){ 
			if (main.getBooksOutData().get(i).getMemberType().equalsIgnoreCase("student")) {
				main.getBooksOutData().get(i).bookLimit.set(sBookLimit);
				main.getBooksOutData().get(i).timeLimit.set(sTimeLimit);
				main.getBooksOutData().get(i).amtOwed.set(sFinesLimit);
			} 
			else if (main.getBooksOutData().get(i).getMemberType().equalsIgnoreCase("teacher")) {
				main.getBooksOutData().get(i).bookLimit.set(tBookLimit);
				main.getBooksOutData().get(i).timeLimit.set(tTimeLimit);
				main.getBooksOutData().get(i).amtOwed.set(tFinesLimit);
			}
		}
	}
	
	//returns StringProperty of author name
	public StringProperty getAuthorProperty() {
		return author;
	}
	
	//returns time limit
	public int getTimeLimit() {
		return timeLimit.get();
	}
	
	//sets first name
	public void setFirstName(String first) {
		fName.set(first);
	}
	
	//sets last name
	public void setLastName(String last) {
		lName.set(last);
	}
	
	//sets status
	public void setStatus(String mRank) {
		status.set(mRank);
	}
	
	//sets books limit
	public void setBooksLimit(int bLimit) {
		bookLimit.set(bLimit);
	}
	
	//sets time limit
	public void setTimeLimit(int tLimit) {
		timeLimit.set(tLimit);
	}
	
	//sets daily fines
	public void setAmtOwed(int a) {
		amtOwed.set(a);
	}
	
	//returns yes/no if a member has checked out a book
	public String getMember() {
		String firstName = fName.get() + "";
		if (firstName.equals("-")) 
			return "No";
		
		else 
			return "Yes";
		
	}
	
	//returns StringProperty of yes/no if a member 
	//has checked out a book
	public StringProperty getMemberProperty() {
		String firstName = fName.get() + "";
		if (firstName.equals("-")) 
			return new SimpleStringProperty("No");
		
		else 
			return new SimpleStringProperty("Yes");
	}
	
	//returns status
	public String getMemberType() {
		return status.get();
	}
	
	//returns StringProperty of member type
	public StringProperty getMemberTypeProperty() {
		return new SimpleStringProperty(status.get());
	}
	
	//returns member whole name
	public String getMemberName() {
		return fName.get() + " " + lName.get();
	}
	
	//returns member first name
	public String getMemberFirstName() {
		return fName.get();
	}
	
	//returns member last name
	public String getMemberLastName() {
		return lName.get();
	}
	
	//returns StringProperty of member first name
	public StringProperty getMemberFirstNameProperty() {
		return new SimpleStringProperty(fName.get());
	}
	
	//returns StringProperty of member last name
	public StringProperty getMemberLastNameProperty() {
		return new SimpleStringProperty(lName.get());
	}
	
	//returns StringProperty of member whole name
	public StringProperty getMemberNameProperty() {
		return new SimpleStringProperty(fName.get() + " " + lName.get());
	}
	
	
	//returns StringProperty of days till return
	public StringProperty getDaysTillReturnProperty(StringProperty rank) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = (dtf.format(localDate)) + "";
		int days = 0;
		if (date.substring(5, 7).equals("02")) {
			days += 31;
		}
		else if (date.substring(5, 7).equals("03")) {
			days += 59;
		}
		if (date.substring(5, 7).equals("04")) {
			days += 90;
		}
		int currentDays = days + Integer.parseInt(date.substring(8));
		String daysTillReturn;
		if (date.substring(5, 7).equals("02")) 
			daysTillReturn = Integer.toString((42 + timeLimit.get()) - currentDays);
		else
			daysTillReturn = Integer.toString((70 + timeLimit.get()) - currentDays);
		
		if (fName.get().equals("-"))
			return new SimpleStringProperty("-");
		else if (!(Integer.parseInt(daysTillReturn) == 0)) {
		if (!((Integer.parseInt(daysTillReturn) >= 0))) {
			return new SimpleStringProperty("Due " + (-1 * Integer.parseInt(daysTillReturn)) + " days ago");
		}
		else {
			return new SimpleStringProperty(daysTillReturn);
			
		}
		}
		else 
			return new SimpleStringProperty("due today");
		}
		
		
	//returns days till return
	public int getDaysTillReturn(StringProperty rank) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = (dtf.format(localDate)) + "";
		int days = 0;
		if (date.substring(5, 7).equals("02")) {
			days += 31;
		}
		else if (date.substring(5, 7).equals("03")) {
			days += 59;
		}
		if (date.substring(5, 7).equals("04")) {
			days += 90;
		}
		int currentDays = days + Integer.parseInt(date.substring(8));
		String daysTillReturn;
		if (date.substring(5, 7).equals("02")) 
			daysTillReturn = Integer.toString((42 + timeLimit.get()) - currentDays);
		else
			daysTillReturn = Integer.toString((70 + timeLimit.get()) - currentDays);
		
		
		if (!((Integer.parseInt(daysTillReturn) >= 0))) {
			return (-1 * Integer.parseInt(daysTillReturn));
		}
		else {
			return Integer.parseInt(daysTillReturn);
		}
		
		
		}
	
		//returns StringProperty of amount owed
		public StringProperty getAmountOwedProperty() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			String date = (dtf.format(localDate)) + "";
			int days = 0;
			if (date.substring(5, 7).equals("02")) {
				days += 31;
			}
			else if (date.substring(5, 7).equals("03")) {
				days += 59;
			}
			if (date.substring(5, 7).equals("04")) {
				days += 90;
			}
			int currentDays = days + Integer.parseInt(date.substring(8));
			String daysTillReturn;
			if (date.substring(5, 7).equals("02")) 
				daysTillReturn = Integer.toString((42 + timeLimit.get()) - currentDays);
			else
			daysTillReturn = Integer.toString((70 + timeLimit.get()) - currentDays);
			
			if (fName.get().equals("-"))
				return new SimpleStringProperty("-");
			else if (!(Integer.parseInt(daysTillReturn) > 0)) {
				if (status.get().equalsIgnoreCase("student"))
					return new SimpleStringProperty(((-1 * (Main.member.booksOutOwed("student") * Integer.parseInt(daysTillReturn))) + "¢"));
		
				else
					return new SimpleStringProperty(((-1 * (Main.member.booksOutOwed("teacher") * Integer.parseInt(daysTillReturn))) + "¢"));
			}
			else {
				return new SimpleStringProperty(0 + "¢");
			}
		}
		
		//returns book title
		public String getTitle(){
			return title.get();
		}

		//returns StringProperty of book title
		public StringProperty getTitleProperty(){
			return title;
		}

		//returns book ID
		public int getID(){
			return id.get();
		}
		
		//returns StringProperty of book ID
		public StringProperty getIDProperty() {
			return new SimpleStringProperty((id.get() + ""));
		}
		
		//returns book ID as a string
		public String getIDString() {
			String ID = id.get() + "";
			return ID;
		}
		
		//returns IntegerProperty of book ID
		public IntegerProperty getIdProperty(){
			return id;
		}

		//returns StringProperty of book ID
		public StringProperty getIdPropertyString(){
			String idString = Integer.toString(id.get());
			return new SimpleStringProperty(idString);
		}
		
		
		

		
	
}