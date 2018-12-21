package com.lahacks.library.view;

import java.io.BufferedWriter;


import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import com.lahacks.library.Main;
import com.lahacks.library.model.BooksOut;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WeeklyBooksReportController {
	//Table of the report
	@FXML private TableView<BooksOut> table;
	@FXML private TableColumn<BooksOut, String> IDColumn;
	@FXML private TableColumn<BooksOut, String> bookNameColumn;
	@FXML private TableColumn<BooksOut, String> authorColumn;
	@FXML private TableColumn<BooksOut, String> checkedOutColumn;
	@FXML private TableColumn<BooksOut, String> memberTypeColumn;
	@FXML private TableColumn<BooksOut, String> memberNameColumn;
	@FXML private TableColumn<BooksOut, String> daysTillColumn;
	@FXML private TableColumn<BooksOut, String> finesColumn;
	private Main main; //the main
	private ObservableList<BooksOut> booksOut = FXCollections.observableArrayList(); //books list
	private Stage dialogStage; //the main stage
	private boolean cancelClicked; //if cancel is clicked
	private static int numBooks = 0;
	
	

	/**
	 * Creates a new WeeklyBooksReportController. Sets cancelClicked to false.
	 */
	public WeeklyBooksReportController(){
		cancelClicked = false;
	}

	/**
	 * Initializes the table.
	 */
	@FXML private void initialize(){
		
		//Initializes columns so that it displays correct information.
		IDColumn.setCellValueFactory(cellData -> cellData.getValue().getIDProperty());
		bookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorProperty());
		checkedOutColumn.setCellValueFactory(cellData -> cellData.getValue().getMemberProperty());
		memberTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getMemberTypeProperty());
		memberNameColumn.setCellValueFactory(cellData -> cellData.getValue().getMemberNameProperty());
		finesColumn.setCellValueFactory(cellData -> cellData.getValue().getAmountOwedProperty());
		daysTillColumn.setCellValueFactory(cellData -> cellData.getValue().getDaysTillReturnProperty(main.booksOut.getMemberTypeProperty()));
		
			}
	
	

	/**
	 * Sets main to the main
	 * @param main
	 */
	public void setMain(Main main){
		
		this.main = main; //sets main to the main
		if (numBooks == 0)
		numBooks = main.getBooksOutData().size(); //calculates size of the data
		
		for(int i = 0; i < numBooks; i++){ //loops through, copies over books to avoid passing by reference
			booksOut.add(main.getBooksOutData().get(i)); //so data is not directly affected
		}
		numBooks = booksOut.size();
		for(int i = numBooks-1; i >= 0; i--){ //runs a loop through all books
			if(booksOut.get(i).getDaysTillReturn(main.getBooksOutData().get(i).getMemberTypeProperty()) > 7) 
				booksOut.remove(i); // removes a book if it is not due in the current week
			else if(booksOut.get(i).getMemberFirstName().equalsIgnoreCase("-"))
				booksOut.remove(i); // removes a book if it is not checked out
		}
		
		
		table.setItems(booksOut); //sets table to these books
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

	/**
	 * Handles printing for report
	 */
	@FXML private void handlePrint(){
		//Takes the report page anchorpane from main and prints it
		AnchorPane page = Main.page;
		print(page);
	}

	/**
	 * Prints a given node.
	 * @param node the inputted node
	 */
	private void print(Node node) {
		System.out.println("Creating a printer job...");

		//Creates new printerjob, makes sure it isn't null. Then prints.
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {
			System.out.println(job.jobStatusProperty().asString());

			boolean printed = job.printPage(node);
			if (printed)job.endJob(); //Tests for printing success/fail
			else System.out.println("Printing failed.");
		}
		else System.out.println("Could not create a printer job."); //Printerjob is null.
	}
	
	/**
	 * Handles exporting the table information as an excel .xls file. Export path is the desktop.
	 * @throws Exception
	 */
	
	@FXML public void handleExport() throws Exception {
		Writer writer = null;
		try {
			//Creates new file at path Desktop
				File file = new File(System.getProperty("user.home") + "/Desktop/", "report_output.xls");
				if(!file.exists()){
					file.createNewFile(); //If file does not exist, creates new file
				}
				writer = new BufferedWriter(new FileWriter(file)); //Writes file to desktop
				
				String text = "ID \t Book Name \t Checked Out \t Member Type \t Member Name \t Days Till Return \t Fines Owed\n"; //Adds first line title
				writer.write(text);
				
				//Adds books to the xls file
				for (BooksOut booksOut : booksOut) {
					writer.write(booksOut.getIDString()+ "\t" +booksOut.getTitle() + "\t" + booksOut.getMember() + "\t" + booksOut.getMemberType() + "\t" + booksOut.getMemberName() + "\t" + (booksOut.getDaysTillReturn(main.booksOut.getMemberTypeProperty())+"") + "\t" + booksOut.getAmountOwedProperty().getValue() + "\n");
				}
				writer.write(text);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			Alert alert = new Alert(AlertType.INFORMATION); //Creates success alert
			alert.setTitle("Success!");
			alert.setHeaderText("Export successful");
			alert.setContentText("Saved xls file to user.home/Desktop/\n Named \"report_output.xls\"");
			alert.showAndWait();
			
			writer.flush(); //Closes BufferedWriter
			writer.close();
		} 
	}
	
}
