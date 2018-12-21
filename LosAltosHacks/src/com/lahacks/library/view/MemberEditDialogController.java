package com.lahacks.library.view;

import java.awt.Label;

import com.lahacks.library.Main;
import com.lahacks.library.model.Member;

import javafx.collections.FXCollections;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberEditDialogController extends Main {

	// FXML text fields for entering information into a new member
	@FXML private TextField fNameField;
	@FXML private TextField lNameField;
	@FXML private ChoiceBox<String> statusChoiceBox;
	private ObservableList<String> status = FXCollections.observableArrayList();
	private Stage dialogStage; // the stage for the dialog
	private boolean okClicked = false; //whether or not ok was clicked
	private Member member;
	private Main main;
	@FXML public javafx.scene.control.Label memberLabel;

	/**
	 * Initializes layout. Adds options to grade statusChoiceBox.
	 */
	@FXML private void initialize(){
		status.add("Student");
		status.add("Teacher");
		statusChoiceBox.setItems(status);
	}

	

	/**
	 * Sets the stage
	 * @param dialogStage
	 */
	public void setDialog(Stage dialogStage){
		this.dialogStage = dialogStage;
		this.dialogStage.setResizable(false);
		this.dialogStage.getIcons().add(new Image("file:resources/edit-icon.png")); //sets stage icon
	}

	/**
	 * Sets a member to a given member
	 * @param member
	 */
	public void setMember(Member member){ //Sets text fields to member's fields
		this.member = member;
		fNameField.setText(member.getFirstName());
		lNameField.setText(member.getLastName());
		statusChoiceBox.setValue(member.getStatus());
		
	}

	/**
	 * 
	 * @return whether or not ok is clicked
	 */
	public boolean isOkClicked(){
		return okClicked;
	}

	/**
	 * When Ok is clicked, sets new member to entered values
	 */
	@FXML private void handleOk(){
		if(isInputValid()){
			//makes sure input is valid
			// sets member's fields to given fields
			
			
				for (int i = 0; i < getBooksOutData().size(); i++) {
					
				if (getBooksOutData().get(i).getMemberFirstName().equalsIgnoreCase(member.getFirstName()) && getBooksOutData().get(i).getMemberLastName().equalsIgnoreCase(member.getLastName())) {
					
					
						getBooksOutData().get(i).setFirstName(fNameField.getText());
						getBooksOutData().get(i).setLastName(lNameField.getText());
						getBooksOutData().get(i).setStatus(statusChoiceBox.getValue());
						if (statusChoiceBox.getValue().equalsIgnoreCase("student"))
							getBooksOutData().get(i).setTimeLimit(Integer.parseInt(main.member.getSTimeLimitString()));
						else 
							getBooksOutData().get(i).setTimeLimit(Integer.parseInt(main.member.getTTimeLimitString()));
					
						
						
					
				}
				}
				
			
			
			member.setFirstName(fNameField.getText());
			member.setLastName(lNameField.getText());
			member.setStatus(statusChoiceBox.getValue());
			

			//sets okclicked to true and closes dialog
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Closes dialog when cancel is clicked
	 */
	@FXML private void handleCancel(){
		dialogStage.close();
	}

	/**
	 * Tests if the input is valid
	 * @return true if input is valid, false otherwise
	 */
	private boolean isInputValid(){
		String errorMessage = "";


		//tests if first name is inputted
		if(fNameField.getText() == null || fNameField.getText().length() == 0)
			errorMessage += "No valid first name!\n";

		//tests if last name is inputted
		if(lNameField.getText() == null || lNameField.getText().length() == 0)
			errorMessage += "No valid last name!\n";
		if(statusChoiceBox.getValue() == null || statusChoiceBox.getValue().length() == 0)
			errorMessage += "No valid member type selected! \n";
		else{
			try{
				
			} catch (NumberFormatException e){
			}
		}

		
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