package com.tas;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));

		Scene scene = new Scene(root, 900, 400);

		root.setStyle("-fx-background-color: #f0f7fc;");

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		@SuppressWarnings("unchecked")
		ListView<String> fromListView = (ListView<String>) scene.lookup("#from");
		scene.lookup("#from");
		List<String> items = new ArrayList<String>();
		items.add("Central");
		items.add("Flagstaff");
		items.add("Richmond");
		fromListView.setItems(new ObservableListWrapper<String>(items));

		@SuppressWarnings("unchecked")
		ListView<String> toListView = (ListView<String>) scene.lookup("#to");
		List<String> toItems = new ArrayList<String>();
		toItems.add("Lilydale");
		toItems.add("Epping");
		toListView.setItems(new ObservableListWrapper<String>(toItems));

		@SuppressWarnings("unchecked")
		ListView<String> cardListView = (ListView<String>) scene.lookup("#card");
		List<String> cardItems = new ArrayList<String>();
		cardItems.add("x1-lc");
		cardItems.add("x2");
		cardListView.setItems(new ObservableListWrapper<String>(cardItems));

		@SuppressWarnings("unchecked")
		ListView<String> passListView = (ListView<String>) scene.lookup("#passLength");
		List<String> passItems = new ArrayList<String>();
		passItems.add("2Hour");
		passItems.add("All Day");
		passListView.setItems(new ObservableListWrapper<String>(passItems));

		@SuppressWarnings("unchecked")
		ListView<String> passTypeListView = (ListView<String>) scene.lookup("#passType");
		List<String> passTypeItems = new ArrayList<String>();
		passTypeItems.add("Zone1");
		passTypeItems.add("Zone12");
		passTypeListView.setItems(new ObservableListWrapper<String>(passTypeItems));

		@SuppressWarnings("unchecked")
		ListView<String> dayListView = (ListView<String>) (ListView<String>) scene.lookup("#day");
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
		ListView<String> startListView = (ListView<String>) scene.lookup("#start");
		List<String> startItems = new ArrayList<String>();
		startItems.add("9:00");
		startListView.setEditable(true);
		startListView.setItems(new ObservableListWrapper<String>(startItems));

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();

	}
}
