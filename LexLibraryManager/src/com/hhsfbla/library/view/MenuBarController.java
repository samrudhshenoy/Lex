package com.hhsfbla.library.view;

import com.hhsfbla.library.Main;

import javafx.fxml.FXML;

public class MenuBarController{
	// Reference to the main application
	private Main main;

	/**
	 * Sets main of class
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main main){
		this.main = main;
	}


	/**
	 * opens full books report when clicked
	 */
	@FXML private void handleBooks(){
		main.showBooks();
	}
	
	/**
	 * opens weekly books and fines report when clicked
	 */
	@FXML private void handleWeeklyBooks() {
		main.showWeeklyBooks();
	}

	/**
	 * opens limits window 
	 */
	@FXML private void handleLimits(){
		main.showMembersLimits();
	}

}
