package com.lahacks.library;

import java.io.File;
import java.sql.*;
import java.util.logging.*;



import java.io.IOException;
import java.util.prefs.Preferences;

import com.lahacks.library.model.BooksOut;
import com.lahacks.library.model.Member;
import com.lahacks.library.view.BooksReportController;
import com.lahacks.library.view.MemberEditDialogController;
import com.lahacks.library.view.MemberLimitsController;
import com.lahacks.library.view.MembersInformationController;
import com.lahacks.library.view.MenuBarController;
import com.lahacks.library.view.WeeklyBooksReportController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Samrudh Shenoy
 *
 */
public class Main extends Application {


	private Stage primaryStage; //Primary stage used
	private BorderPane layout; //BorderPane used in stage
	private ObservableList<Member> memberData = FXCollections.observableArrayList(); //List of members
	private static ObservableList<BooksOut> booksOutData = FXCollections.observableArrayList(); //List of books
	public static Member member = new Member(); //creating new member
	public static BooksOut booksOut = new BooksOut(); //creating new booksOut

	public static AnchorPane page;

	public Main(){ //Default, dummy data and database connection
		
		  try (Connection con = getConnection()) { //tries connecting to database

		      if (!schemaExists(con)) {

		        createSchema(con);

		      
		      }


		    } catch (SQLException | ClassNotFoundException ex) { //catches exception and prints

		     System.out.println("Database Creation Error");

		    }
		
		memberData.add(new Member("Andrew", "Johnson", "Student"));
		memberData.add(new Member("John", "Smith", "Student"));
		memberData.add(new Member("Samantha", "Worthington", "Student"));
		memberData.add(new Member("Peter", "Brown", "Teacher"));
		memberData.add(new Member("Bob", "Anderson", "Student"));
		memberData.add(new Member("Matthew", "Thompson", "Teacher"));
		memberData.add(new Member("Billy", "Allen", "Student"));
		memberData.add(new Member("Angela", "Wright", "Student"));
		memberData.add(new Member("Lilly", "White", "Teacher"));
		memberData.add(new Member("Daniel", "Brown", "Student"));
		
		
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "One With You", "Sylvia Day", 125));	
		booksOutData.add(new BooksOut("John", "Smith", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "The Whistler", "John Grisham", 104));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "The 5 Love Languages", "Gary Chapman", 118));	
		booksOutData.add(new BooksOut("Bob", "Anderson", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "Alexander Hamilton", "Ron Chernow", 110));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "The Girl in the Ice", "Robert Bryndza", 120));	
		booksOutData.add(new BooksOut("Samantha", "Worthington", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "The Nightingale", "Kristin Hannah", 106));
		booksOutData.add(new BooksOut("Matthew", "Thompson", "Teacher", member.booksOutLimit("teacher"), member.booksOutTime("teacher"), member.booksOutOwed("teacher"), "StrengthsFinder 2.0", "Tom Rath", 112));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "Night School", "Lee Child", 115));
		booksOutData.add(new BooksOut("Andrew", "Johnson", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "Me Before You", "Jojo Moyes", 102));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "Born to Run", "Bruce Springsteen", 121));	
		booksOutData.add(new BooksOut("Andrew", "Johnson", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "The Girl on the Train", "Paula Hawkins", 101));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "First 100 Words", "Roger Priddy", 116));
		booksOutData.add(new BooksOut("John", "Smith", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "Miss Peregrine’s Home for Peculiar Children", "Ransom Riggs", 103));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "No Man's Land", "David Baldacci", 124));
		booksOutData.add(new BooksOut("Samantha", "Worthington", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "A Man Called Ove", "Fredrik Backman", 105));
		booksOutData.add(new BooksOut("Peter", "Brown", "Teacher", member.booksOutLimit("teacher"), member.booksOutTime("teacher"), member.booksOutOwed("teacher"), "When Breath Becomes Air", "Paul Kalanithi", 107));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "Milk and Honey ", "Rupi Kaur", 117));	
		booksOutData.add(new BooksOut("Matthew", "Thompson", "Teacher", member.booksOutLimit("teacher"), member.booksOutTime("teacher"), member.booksOutOwed("teacher"), "All the Light We Cannot See", "Anthony Doerr", 111));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "Rogue Lawyer", "John Grisham", 122));	
		booksOutData.add(new BooksOut("Billy", "Allen", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "Hillbilly Elegy", "J.D. Vance", 113));
		booksOutData.add(new BooksOut("Peter", "Brown", "Teacher", member.booksOutLimit("teacher"), member.booksOutTime("teacher"), member.booksOutOwed("teacher"), "The Life-Changing Magic of Tidying Up", "Marie Kondo", 108));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "To Kill a Mockingbird", "Harper Lee", 119));	
		booksOutData.add(new BooksOut("Bob", "Anderson", "Student", member.booksOutLimit("student"), member.booksOutTime("student"), member.booksOutOwed("student"), "After You", "Jojo Moyes", 109));
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "The Wrong Side of Goodbye", "Michael Connelly", 123));	
		booksOutData.add(new BooksOut("-", "", "-", 0, 0, 0, "Two by Two", "Nicholas Sparks", 114));
		
		
	}

	/**
	 *
	 * @return the hard-coded memberData 
	 */
	public ObservableList<Member> getPersonData(){
		return memberData;
	}
	
	/**
	 *
	 * @return the hard-coded booksOutData 
	 */
	public ObservableList<BooksOut> getBooksOutData() {
		return booksOutData;
	}
	
	/**
	 *
	 * Gets database connection, links to imported jar file with database
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {

		System.out.println("Getting a database connection");

		Class.forName("org.h2.Driver");

		return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

	}

	/**
	 *
	 * creates schema
	 */
	private void createSchema(Connection con) throws SQLException {

		System.out.println("Creating schema");

		Statement st = con.createStatement();

		String table = "create table employee(id integer, name varchar(64))";
		st.executeUpdate(table);
		System.out.println("Created schema");
	}

	/**
	 *
	 * Checks if schema exists
	 */
	private boolean schemaExists(Connection con) {
		System.out.println("Checking for Schema existence");
		try {
			Statement st = con.createStatement();
			st.executeQuery("select count(*) from employee");
			System.out.println("Schema exists");
		} catch (SQLException ex) {
			System.out.println("Existing DB not found will create a new one");
			return false;
		}
		return true;
	}

	/**
	 * Runs when program starts. Sets up stage and initializes the layouts.
	 */
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		//Sets stage name to HHS Library Database App
		this.primaryStage.setTitle("Homestead High School Library Database App");
		//Sets stage image, top bar
		this.primaryStage.getIcons().add(new Image("file:resources/main-icon.png"));

		initializeLayout();
		showMembersInformation();
		}
	

	/**
	 * Initializes the layout. Creates the menubar at the top.
	 */
	public void initializeLayout() {
		try {
			// Load root layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MenuBar.fxml"));
			layout = (BorderPane) loader.load();

			// Show the scene containing the root layout
			Scene scene = new Scene(layout);
			primaryStage.setScene(scene);

			// Loads menu bar controller
			MenuBarController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showMembersInformation() {
		try {
			// Loads membersinformation from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MembersInformation.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Sets up the info controller
			MembersInformationController controller = loader.getController();
			controller.setMain(this);
			controller.activateFilter(); //Activates filter bar
			layout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows a new window to edit members from the database.
	 * @param member the member to show the edit dialog
	 * @return boolean true if ok is clicked, false if it is not
	 */
	public boolean showPersonEditDialog(Member member) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MemberEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Member"); //Sets title to Edit Member
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Loads controller
			MemberEditDialogController controller = loader.getController();
			controller.setDialog(dialogStage);
			controller.memberLabel.setText("Edit Member");
			controller.setMember(member);
			dialogStage.showAndWait(); // Waits until ok is clicked, then returns
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Shows a new window to add members from the database.
	 * @param member the member to show the new dialog
	 * @return boolean true if ok is clicked, false if it is not
	 */
	public boolean showPersonNewDialog(Member member) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MemberEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("New Member"); //Sets title to Edit Member
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Loads controller
			MemberEditDialogController controller = loader.getController();
			controller.setDialog(dialogStage);
			controller.memberLabel.setText("New Member");
			controller.setMember(member);
			dialogStage.showAndWait(); // Waits until ok is clicked, then returns
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Shows a new window to view and edit member limits
	 */
	public void showMembersLimits() {
		try{
			// Load the fxml file and create a new stage for the members limits window
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MemberLimits.fxml"));
			page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Members Limits"); //Sets title to Members Limits
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Loads controller
			MemberLimitsController controller = loader.getController();

			//Sets stage and main to controller class
			controller.setDialog(dialogStage);
			controller.setMain(this);

			dialogStage.showAndWait();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the full books report with all of the books
	 */
	public void showBooks() {
		try{
			// Load the fxml file and create a new stage for the full books report
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BooksReport.fxml"));
			page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Full Books Report"); //Sets title
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Loads controller
			BooksReportController controller = loader.getController();

			//Sets stage and main to controller class
			controller.setDialog(dialogStage);
			controller.setMain(this);
			dialogStage.showAndWait();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * Creates the weekly books report with the returns coming up in the
	 * following week, as well as non-returned books
	 */
	public void showWeeklyBooks() {
		try{
			// Load the fxml file and create a new stage for the weekly books report
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/WeeklyBooksReport.fxml"));
			page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Weekly Books Report"); //Sets title
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Loads controller
			WeeklyBooksReportController controller = loader.getController();

			//Sets stage and main to controller class
			controller.setDialog(dialogStage);
			controller.setMain(this);
			dialogStage.showAndWait();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return the primary stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}