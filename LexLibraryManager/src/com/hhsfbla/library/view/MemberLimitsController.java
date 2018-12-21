package com.hhsfbla.library.view;

import com.hhsfbla.library.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MemberLimitsController {
	//Table of the report
	@FXML private Label sBooks;
	@FXML private Label sDays;
	@FXML private Label sFines;
	@FXML private Label tBooks;
	@FXML private Label tDays;
	@FXML private Label tFines;
	@FXML private TextField sBooksField;
	@FXML private TextField sTimeField;
	@FXML private TextField sFinesField;
	@FXML private TextField tBooksField;
	@FXML private TextField tTimeField;
	@FXML private TextField tFinesField;
	private Main main; //the main
	private Stage dialogStage; //the main stage
	private boolean cancelClicked; //if cancel is clicked
	private Boolean setClicked = false;
	
	
	
	

	/**
	 * Creates a new MemberLimitsController. Sets cancelClicked to false.
	 */
	public MemberLimitsController(){
		cancelClicked = false;
		
	}

	/**
	 * Initializes the table.
	 */
	@FXML private void initialize(){
		//Initializes labels so that they display correct information.
		sBooks.setText(main.member.getSBookLimitString());
		sDays.setText(main.member.getSTimeLimitString());
		sFines.setText(main.member.getSAmountOwedString());
		tBooks.setText(main.member.getTBookLimitString());
		tDays.setText(main.member.getTTimeLimitString());
		tFines.setText(main.member.getTAmountOwedString());
	}
	

	/**
	 * Sets main to the main
	 * @param main
	 */
	public void setMain(Main main){
		this.main = main; //sets main to the main
	}

	/**
	 * Sets the stage
	 * @param dialogStage
	 */
	public void setDialog(Stage dialogStage){
		this.dialogStage = dialogStage;
		//Does not allow resizing
		this.dialogStage.setResizable(false);
		this.dialogStage.getIcons().add(new Image("file:resources/report-icon.png"));
	}


	/**
	 * Cancels program, closes
	 */
	@FXML private void handleCancel(){
		dialogStage.close(); //closes program
	}

	/**
	 * 
	 * @return if cancel is clicked
	 */
	public boolean isCancelClicked(){
		return cancelClicked;
	}

	public boolean isSetClicked() {
		return setClicked;
	}
	/**
	 * Handles printing for report
	 */
	@FXML private void handleSet() {
		if(isInputValid()){ //makes sure input is valid
			//sets Limits and Fines, as well as text
			main.member.setSBookLimit(Integer.parseInt(sBooksField.getText()));
			main.member.setSTimeLimit(Integer.parseInt(sTimeField.getText()));
			main.member.setSAmountOwed(Integer.parseInt(sFinesField.getText()));
			main.member.setTBookLimit(Integer.parseInt(tBooksField.getText()));
			main.member.setTTimeLimit(Integer.parseInt(tTimeField.getText()));
			main.member.setTAmountOwed(Integer.parseInt(tFinesField.getText()));
			sBooks.setText(main.member.getSBookLimitString());
			sDays.setText(main.member.getSTimeLimitString());
			sFines.setText(main.member.getSAmountOwedString());
			tBooks.setText(main.member.getTBookLimitString());
			tDays.setText(main.member.getTTimeLimitString());
			tFines.setText(main.member.getTAmountOwedString());
			
			main.booksOut.changeLimits(this.main, Integer.parseInt(sBooksField.getText()), Integer.parseInt(sTimeField.getText()), Integer.parseInt(sFinesField.getText()), Integer.parseInt(tBooksField.getText()), Integer.parseInt(tTimeField.getText()), Integer.parseInt(tFinesField.getText()));
			//sets okclicked to true
			
			setClicked = true;
		}
		
	}
	private boolean isInputValid(){
		String errorMessage = "";


		if(sBooksField == null || sBooksField.getText().length() == 0)
			errorMessage += "No valid student book limit!\n";
		else{
			try{
				Integer.parseInt(sBooksField.getText()); //tests if field is integer
			}
			catch (NumberFormatException e){
				errorMessage += "No valid student book limit (must be an integer)!\n";
			}
		}
		
		if(sTimeField == null || sTimeField.getText().length() == 0)
			errorMessage += "No valid student time limit!\n";
		else{
			try{
				Integer.parseInt(sTimeField.getText()); //tests if field is integer
			}
			catch (NumberFormatException e){
				errorMessage += "No valid student time limit (must be an integer)!\n";
			}
		}
		
		if(sFinesField == null || sFinesField.getText().length() == 0)
			errorMessage += "No valid student fine amount!\n";
		else{
			try{
				Integer.parseInt(sFinesField.getText()); //tests if field is integer
			}
			catch (NumberFormatException e){
				errorMessage += "No valid student fine amount (must be an integer)!\n";
			}
		}
		if(tBooksField == null || tBooksField.getText().length() == 0)
			errorMessage += "No valid teacher book limit!\n";
		else{
			try{
				Integer.parseInt(tBooksField.getText()); //tests if field is integer
			}
			catch (NumberFormatException e){
				errorMessage += "No valid teacher book limit (must be an integer)!\n";
			}
		}
		
		if(tTimeField == null || tTimeField.getText().length() == 0) 
			errorMessage += "No valid teacher time limit!\n";
		else{
			try{
				Integer.parseInt(tTimeField.getText()); //tests if field is integer
			}
			catch (NumberFormatException e){
				errorMessage += "No valid teacher time limit (must be an integer)!\n";
			}
		}
		
		if(tFinesField == null || tFinesField.getText().length() == 0)
			errorMessage += "No valid teacher fine amount!\n";
		else{
			try{
				Integer.parseInt(tFinesField.getText()); //tests if field is integer
			}
			catch (NumberFormatException e){
				errorMessage += "No valid teacher fine amouont (must be an integer)!\n";
			}
		}

		//tests if amount owed is inputted and is an integer
		
		//if there is no error message, returns true
		if (errorMessage.length() == 0) {
			return true;
		} else { //if there is an error message, create alert with error messages printed
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
