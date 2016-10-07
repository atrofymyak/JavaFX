package com.tas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import constants.StringConstants;
import db.DBService;
import fileio.FileService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import myti.MyTiCard;
import myti.MyTiPass;
import myti.Station;

public class Main extends Application {

	private DBService dbService = DBService.getInstance();

	public static void main(String[] args) {
		FileService.getInstance().readFile(new File("input.txt"));

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));

		Scene scene = new Scene(root, 900, 400);

		root.setStyle("-fx-background-color: #f0f7fc;");

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		@SuppressWarnings("unchecked")
		ListView<String> fromListView = (ListView<String>) scene.lookup(StringConstants.FROM);		
		List<String> items = new ArrayList<String>();

		for (Station station : dbService.getStations()) {
			if (station.getType() == 1) {
				items.add(station.getName());
			}
		}

		fromListView.setItems(new ObservableListWrapper<String>(items));

		@SuppressWarnings("unchecked")
		ListView<String> toListView = (ListView<String>) scene.lookup(StringConstants.TO);
		List<String> toItems = new ArrayList<String>();
		for (Station station : dbService.getStations()) {
			if (station.getType() == 2) {
				toItems.add(station.getName());
			}
		}
		toListView.setItems(new ObservableListWrapper<String>(toItems));

		@SuppressWarnings("unchecked")
		ListView<String> cardListView = (ListView<String>) scene.lookup(StringConstants.CARD2);
		List<String> cardItems = new ArrayList<String>();

		for (MyTiCard card : dbService.getCards()) {
			String cardInfo = card.getCardId();

			if (!card.getUserId().isEmpty()) {
				cardInfo += StringConstants.SPACE + StringConstants.MINUS + StringConstants.SPACE + card.getUserId();
			}

			cardItems.add(cardInfo);
		}

		cardListView.setItems(new ObservableListWrapper<String>(cardItems));

		List<MyTiPass> passes = dbService.getPasses();

		@SuppressWarnings("unchecked")
		ListView<String> passListView = (ListView<String>) scene.lookup(StringConstants.PASS_LENGTH);
		List<String> passItems = new ArrayList<String>();
		List<String> passTypeItems = new ArrayList<String>();

		for (MyTiPass pass : passes) {
			if (!passItems.contains(pass.getLength())) {
				passItems.add(pass.getLength());
			}
			if (!passTypeItems.contains(pass.getZone())) {
				passTypeItems.add(pass.getZone());
			}
		}

		passListView.setItems(new ObservableListWrapper<String>(passItems));

		@SuppressWarnings("unchecked")
		ListView<String> passTypeListView = (ListView<String>) scene.lookup(StringConstants.PASS_TYPE);
		passTypeListView.setItems(new ObservableListWrapper<String>(passTypeItems));

		@SuppressWarnings("unchecked")
		ListView<String> dayListView = (ListView<String>) (ListView<String>) scene.lookup(StringConstants.DAY);
		List<String> dayItems = new ArrayList<String>();
		dayItems.add("Mon");
		dayItems.add("Tue");
		dayItems.add("Wed");
		dayItems.add("Thu");
		dayItems.add("Fri");
		dayItems.add("Sat");
		dayItems.add("Sun");
		dayListView.setItems(new ObservableListWrapper<String>(dayItems));

		@SuppressWarnings("unchecked")
		ListView<String> startListView = (ListView<String>) scene.lookup(StringConstants.START);
		List<String> startItems = new ArrayList<String>();
		startItems.add("0:00");
		startListView.setEditable(true);
		startListView.setItems(new ObservableListWrapper<String>(startItems));

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();

	}
}
