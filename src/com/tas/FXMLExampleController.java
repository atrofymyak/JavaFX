package com.tas;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import constants.StringConstants;
import db.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import myti.MyTiCard;
import myti.User;

public class FXMLExampleController {
	
	private DBService dbService = DBService.getInstance();
	@FXML private Label cardValue;	
	@FXML private ListView<String> manageCard;
	@FXML private TextField newCredit;
	@FXML private TextField addedCredit;
	@FXML private TextField userId;
	@FXML private TextField userName;
	@FXML private TextField userMail;
	@FXML private ListView<String> userCard;
	@FXML private TextField addedUser;

	@FXML
	@SuppressWarnings("unchecked")
	protected void openManageUserWindow(ActionEvent event) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("managecu.fxml"));

		//
		Stage stage = new Stage();
		stage.setTitle("Manage Cars/Users");
		Scene scene = new Scene(root, 750, 450);
		stage.setScene(scene);		
		root.setStyle("-fx-background-color: #f0f7fc;");

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
		ListView<String> cardListView = (ListView<String>) scene.lookup("#manageCard");
		ListView<String> userCard = (ListView<String>) scene.lookup("#userCard"); 
		List<String> cardItems = new ArrayList<String>();
		List<String> userCards = new ArrayList<String>();

		for (MyTiCard card : dbService.getCards()) {
			String cardInfo = card.getCardId();

			if (!card.getUserId().isEmpty()) {
				cardInfo += StringConstants.SPACE + StringConstants.MINUS + StringConstants.SPACE + card.getUserId();
			}

			userCards.add(card.getCardId());
			cardItems.add(cardInfo);
		}

		cardListView.setItems(new ObservableListWrapper<String>(cardItems));
		userCard.setItems(new ObservableListWrapper<String>(userCards));
		
		stage.show();
	}
	
		
	@FXML
	protected void selectCardValue(MouseEvent event) throws Exception {	
				
		String selectedItem = (String) manageCard.getSelectionModel().getSelectedItem();
		
		List<MyTiCard> cards = dbService.getCards();
		for(MyTiCard card : cards){
			String cardInfo = card.getCardId();

			if (!card.getUserId().isEmpty()) {
				cardInfo += StringConstants.SPACE + StringConstants.MINUS + StringConstants.SPACE + card.getUserId();
			}
			
			if(selectedItem.equals(cardInfo)){
				cardValue.setText("Credit for " + cardInfo + StringConstants.COLON + " $" + card.getCredit());
				break;
			}
		}				
	}
	
	@FXML
	protected void quit(ActionEvent event) throws Exception {
		System.exit(0);
	}
	
	@FXML
	protected void createUser(ActionEvent event) throws Exception {
		User user = new User();
		user.setUserId(userId.getText());
		user.setName(userName.getText());
		user.setEmail(userMail.getText());
		user.setCards(userCard.getItems());
		
		addedUser.setText("User " + user.getUserId() + ":" + user.getName() + " was added");
		
		dbService.addUser(user);
	}
	
	@FXML
	protected void addCredit(ActionEvent event) throws Exception {				
		
		double val = Double.valueOf(newCredit.getText());
		
		String selectedItem = (String) manageCard.getSelectionModel().getSelectedItem();
		
		List<MyTiCard> updatedCards = new ArrayList<MyTiCard>();
		
		List<MyTiCard> cards = dbService.getCards();
		for(MyTiCard card : cards){
			String cardInfo = card.getCardId();

			if (!card.getUserId().isEmpty()) {
				cardInfo += StringConstants.SPACE + StringConstants.MINUS + StringConstants.SPACE + card.getUserId();
			}
			
			if(selectedItem.equals(cardInfo)){
				card.setCredit(card.getCredit() + val);
				cardValue.setText("Credit for " + cardInfo + StringConstants.COLON + " $" + card.getCredit());
				addedCredit.setText("$" + val + " added to " + cardInfo);
			}
			
			updatedCards.add(card);
		}
		
		dbService.setCards(updatedCards);
		
		newCredit.setText("");
	}
}

