package com.lahacks.library.model;


import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.lahacks.library.Main;
import com.lahacks.library.model.BooksOut;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Member {

	private final StringProperty fName;
	private final StringProperty lName;
	private final StringProperty status;
	public final IntegerProperty sBookLimit;
	public final IntegerProperty sTimeLimit;
	public final IntegerProperty sAmtOwed;
	public final IntegerProperty tBookLimit;
	public final IntegerProperty tTimeLimit;
	public final IntegerProperty tAmtOwed;
	/**
	 * Creates a member with no number and a blank name
	 */
	public Member(){
		this("", "", "");
		
	}
	
	

	/*
	 * Constructor, creates member of desired number, first name, and last name but other information hard coded
	 * @param membershipNumber The member's number
	 * @param firstName The member's first name
	 * @param lastName The member's last name
	 */
	public Member(String firstName, String lastName, String rank){
		fName = new SimpleStringProperty(firstName);
		lName = new SimpleStringProperty(lastName);
		status = new SimpleStringProperty(rank);

		//Dummy data to fill in blanks automatically
		sBookLimit = new SimpleIntegerProperty(2);
		sTimeLimit = new SimpleIntegerProperty(14);
		sAmtOwed = new SimpleIntegerProperty(2);
		tBookLimit = new SimpleIntegerProperty(6);
		tTimeLimit = new SimpleIntegerProperty(28);
		tAmtOwed = new SimpleIntegerProperty(1);
	}
	
	
	
	public int booksOutLimit(String rank) {
		if (rank.equalsIgnoreCase("Student")) {
			return sBookLimit.get();
		}
		else {
			return tBookLimit.get();
		}
	}
	
	
	public int booksOutTime (String rank) {
		if (rank.equalsIgnoreCase("student")) {
			return sTimeLimit.get();
		}
		else {
			return tTimeLimit.get();
		}
	}
	
	
	public int booksOutOwed (String rank) {
		if (rank.equalsIgnoreCase("Student")) {
			return sAmtOwed.get();
		}
		else {
			return tAmtOwed.get();
		}
	}
	
	/**
	 * Sets the first name as something else
	 * @param firstName
	 */
	public void setFirstName(String firstName){
		fName.set(firstName);
	}

	/**
	 * 
	 * @return the first name of the member
	 */
	public String getFirstName(){
		return fName.get();
	}

	/**
	 * 
	 * @return the property of the first name of the member
	 */
	public StringProperty getFirstNameProperty(){
		return fName;
	}


	/**
	 * Sets the last name of the member to something else
	 * @param lastName
	 */
	public void setLastName(String lastName){
		lName.set(lastName);
	}

	/**
	 * 
	 * @return the last name of the member
	 */
	public String getLastName(){
		return lName.get();
	}

	/**
	 * 
	 * @return the last name property of the member
	 */
	public StringProperty getLastNameProperty(){
		return lName;
	}


	/**
	 * Sets the student book limit
	 * @param school
	 */
	public void setSBookLimit(int booksLimit){
		this.sBookLimit.set(booksLimit);
	}

	/**
	 * 
	 * @return the student book limit
	 */
	public int getSBookLimit(){
		return sBookLimit.get();
	}
	

	/**
	 * 
	 * @return the property of the student book limit
	 */
	public IntegerProperty getSBookLimitProperty(){
		return sBookLimit;
	}
	
	public StringProperty getSBookLimitPropertyString(){
		String str = Integer.toString(sBookLimit.get());
		return new SimpleStringProperty(str);
	}
	
	public String getSBookLimitString() {
		String str = Integer.toString(sBookLimit.get());
		return str;
	}
	
	public void setTBookLimit(int booksLimit){
		this.tBookLimit.set(booksLimit);
	}

	/**
	 * 
	 * @return the teacher book limit
	 */
	public int getTBookLimit(){
		return tBookLimit.get();
	}
	

	/**
	 * 
	 * @return the property of the teacher book limit
	 */
	public IntegerProperty getTBookLimitProperty(){
		return tBookLimit;
	}
	
	public StringProperty getTBookLimitPropertyString(){
		String str = Integer.toString(tBookLimit.get());
		return new SimpleStringProperty(str);
	}
	
	public String getTBookLimitString() {
		String str = Integer.toString(tBookLimit.get());
		return str;
	}


	/**
	 * 
	 * sets the status
	 */
	public void setStatus(String status){
		this.status.set(status);
	}

	/**
	 * 
	 * @return the member's status
	 */
	public String getStatus(){
		return status.get();
	}

	/**
	 * 
	 * @return the property of the member's status
	 */
	public StringProperty getStatusProperty(){
		return status;
	}
	
	public String getStatusPropertyString() {
		String str = status + "";
		return str;
	}
	
	/**
	 * 
	 * sets the student time limit
	 */
	public void setSTimeLimit(int time) {
		sTimeLimit.set(time);
	}

	/**
	 * 
	 * @return the student time limit
	 */
	public int getSTimeLimit() {
		return sTimeLimit.get();
	}
	
	/**
	 * 
	 * @return the property of the student time limit
	 */
	public IntegerProperty getSTimeLimitProperty() {
		return sTimeLimit;
	}
	
	
	public StringProperty getSTimeLimitPropertyString() {
		String timeLimitString = Integer.toString(sTimeLimit.get());
		return new SimpleStringProperty(timeLimitString);
	}
	
	public String getSTimeLimitString() {
		String timeLimitString = Integer.toString(sTimeLimit.get());
		return timeLimitString;
	}
	
	
	/**
	 * 
	 * sets the teacher time limit
	 */
	public void setTTimeLimit(int time) {
		tTimeLimit.set(time);
	}

	/**
	 * 
	 * @return the teacher time limit
	 */
	public int getTTimeLimit() {
		return tTimeLimit.get();
	}

	/**
	 * 
	 * @return the property of the teacher time limit
	 */
	public IntegerProperty getTTimeLimitProperty() {
		return tTimeLimit;
	}
	
	
	public StringProperty getTTimeLimitPropertyString() {
		String timeLimitString = Integer.toString(tTimeLimit.get());
		return new SimpleStringProperty(timeLimitString);
	}
	
	public String getTTimeLimitString() {
		String timeLimitString = Integer.toString(tTimeLimit.get());
		return timeLimitString;
	}
	/**
	 * Sets the amount owed of the student
	 * @param amountOwed
	 */
	public void setSAmountOwed(int amountOwed){
		sAmtOwed.set(amountOwed);
	}

	/**
	 * 
	 * @return the amount owed of the student
	 */
	public int getSAmountOwed(){
		return sAmtOwed.get();
	}
	
	public String getSAmountOwedString() {
		return sAmtOwed.get() + "";
	}

	/**
	 * 
	 * @return the property of the amount owed of the student
	 */
	public IntegerProperty getSAmountOwedProperty(){
		return sAmtOwed;
	}

	/**
	 * 
	 * @return the property of the amount owed of the student as a string
	 */
	public StringProperty getSAmountOwedPropertyString(){
		String amtOwedString = "$" + Integer.toString(sAmtOwed.get());
		return new SimpleStringProperty(amtOwedString);
	}
	
	/**
	 * 
	 * sets the amount owed for teachers
	 */
	public void setTAmountOwed(int amountOwed){
		tAmtOwed.set(amountOwed);
	}

	/**
	 * 
	 * @return the amount owed of the teacher
	 */
	public int getTAmountOwed(){
		return tAmtOwed.get();
	}

	/**
	 * 
	 * @return the property of the amount owed of the teacher
	 */
	public IntegerProperty getTAmountOwedProperty(){
		return tAmtOwed;
	}

	/**
	 * 
	 * @return the property of the amount owed of the teacher as a string
	 */
	public StringProperty getTAmountOwedPropertyString(){
		String amtOwedString = "$" + Integer.toString(tAmtOwed.get());
		return new SimpleStringProperty(amtOwedString);
	}
	public String getTAmountOwedString() {
		return tAmtOwed.get() + "";
	}
}
