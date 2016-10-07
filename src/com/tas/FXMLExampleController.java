package com.tas;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import constants.StringConstants;
import db.DBService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myti.MyTiCard;
import myti.User;
import validation.ValidationService;

public class FXMLExampleController {

	private DBService dbService = DBService.getInstance();
	@FXML
	private Label cardValue;
	@FXML
	private ListView<String> manageCard;
	@FXML
	private TextField newCredit;
	@FXML
	private TextField addedCredit;
	@FXML
	private TextField userId;
	@FXML
	private TextField userName;
	@FXML
	private TextField userMail;
	@FXML
	private ListView<String> userCard;
	@FXML
	private TextField addedUser;
	@FXML
	private TextArea journey;
	@FXML
	private ListView<String> from;
	@FXML
	private ListView<String> to;
	@FXML
	private ListView<String> card;
	@FXML
	private ListView<String> day;
	@FXML
	private ListView<String> start;

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
		for (MyTiCard card : cards) {
			String cardInfo = card.getCardId();

			if (!card.getUserId().isEmpty()) {
				cardInfo += StringConstants.SPACE + StringConstants.MINUS + StringConstants.SPACE + card.getUserId();
			}

			if (selectedItem.equals(cardInfo)) {
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
	protected void addJourney(ActionEvent event) throws Exception {
		String journeyText = "Added journey from " + from.getSelectionModel().getSelectedItem() + " to "
				+ to.getSelectionModel().getSelectedItem() + " on " + day.getSelectionModel().getSelectedItem() + " at "
				+ start.getSelectionModel().getSelectedItem();

		journey.setText(journeyText);
	}

	@FXML
	protected void createUser(ActionEvent event) throws Exception {

		String error = ValidationService.validateUser(userId.getText(), userName.getText(), userMail.getText(),
				userCard.getSelectionModel().getSelectedItems());

		if (error.isEmpty()) {
			User user = new User();
			user.setUserId(userId.getText());
			user.setName(userName.getText());
			user.setEmail(userMail.getText());
			user.setCards(userCard.getSelectionModel().getSelectedItems());

			addedUser.setText("User " + user.getUserId() + ":" + user.getName() + " was added");

			dbService.addUser(user);
		} else {
			popupErrorMsg(error);
		}
	}

	@FXML
	protected void addCredit(ActionEvent event) throws Exception {
		String selectedItem = (String) manageCard.getSelectionModel().getSelectedItem();

		String error = ValidationService.validateCredit(selectedItem, newCredit.getText());

		if (error.isEmpty()) {
			double val = Double.valueOf(newCredit.getText());

			List<MyTiCard> updatedCards = new ArrayList<MyTiCard>();

			List<MyTiCard> cards = dbService.getCards();
			for (MyTiCard card : cards) {
				String cardInfo = card.getCardId();

				if (!card.getUserId().isEmpty()) {
					cardInfo += StringConstants.SPACE + StringConstants.MINUS + StringConstants.SPACE
							+ card.getUserId();
				}

				if (selectedItem.equals(cardInfo)) {
					card.setCredit(card.getCredit() + val);
					cardValue.setText("Credit for " + cardInfo + StringConstants.COLON + " $" + card.getCredit());
					addedCredit.setText("$" + val + " added to " + cardInfo);
				}

				updatedCards.add(card);
			}

			dbService.setCards(updatedCards);

			newCredit.setText("");
		} else {
			popupErrorMsg(error);
		}

	}

	@SuppressWarnings("deprecation")
	private void popupErrorMsg(String msg) {
		final Stage myDialog = new Stage();
		myDialog.initModality(Modality.APPLICATION_MODAL);
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				myDialog.close();
			}
		});
		Scene myDialogScene = new Scene(VBoxBuilder.create().children(new Text(msg), okButton).spacing(30)
				.alignment(Pos.CENTER).padding(new Insets(10)).build(), 200, 200);
		myDialog.setTitle("Error message");

		myDialog.setScene(myDialogScene);
		myDialog.show();
	}
}
