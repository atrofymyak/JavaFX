package db;

import java.util.ArrayList;
import java.util.List;

import myti.MyTiCard;
import myti.MyTiPass;
import myti.Station;
import myti.User;

public class DBService {

	private List<MyTiCard> cards = new ArrayList<MyTiCard>();
	private List<User> users = new ArrayList<User>();
	private List<Station> stations = new ArrayList<Station>();
	private List<MyTiPass> passes = new ArrayList<MyTiPass>();
	
	public static final DBService INSTANCE = new DBService();

	private DBService() {
	}

	public static DBService getInstance() {
		return INSTANCE;
	}

	public List<MyTiCard> getCards() {
		return cards;
	}

	public void setCards(List<MyTiCard> cards) {
		this.cards = cards;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public void addCard(MyTiCard card) {
		this.cards.add(card);
	}

	public void addUser(User user) {
		this.users.add(user);
	}

	public void addStation(Station station) {
		this.stations.add(station);
	}

	public List<MyTiPass> getPasses() {
		return passes;
	}

	public void setPasses(List<MyTiPass> passes) {
		this.passes = passes;
	}
	
	public void addPass(MyTiPass pass) {
		this.passes.add(pass);
	}
	
}
