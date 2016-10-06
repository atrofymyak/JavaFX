package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import constants.StringConstants;
import db.DBService;
import myti.MyTiCard;
import myti.MyTiPass;
import myti.Station;
import myti.User;

public class FileService {

	private DBService dbService;

	private FileService() {
		dbService = DBService.getInstance();
	}

	public static FileService getInstance() {
		return new FileService();
	}

	public void readFile(File inputFile) {

		boolean isCard = false;
		boolean isUser = false;
		boolean isPrice = false;
		boolean isStation = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String line = null;
			while ((line = reader.readLine()) != null) {

				
				if (line.startsWith("#cards ")) {
					isCard = true;
					isUser = false;
					isPrice = false;
					isStation = false;
					continue;
				}else if(line.startsWith("#users ")){
					isCard = false;
					isUser = true;
					isPrice = false;
					isStation = false;
					continue;
				}else if(line.startsWith("#prices")){
					isCard = false;
					isUser = false;
					isPrice = true;
					isStation = false;
					continue;
				}else if(line.startsWith("#stations")){
					isCard = false;
					isUser = false;
					isPrice = false;
					isStation = true;
					continue;
				}
				
				if (isCard) {
					populateCards(line);
				}
				else if(isUser){
					populateUsers(line);
				}else if(isStation){					
					populateStations(line);
				}else if(isPrice){
					populatePasses(line);
				}
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	private void populatePasses(String line) {
		String[] tokens = line.split(StringConstants.COLON);
		
		MyTiPass pass = new MyTiPass(tokens[1], tokens[0]);
		pass.setPrice(Double.valueOf(tokens[2]));
		
		dbService.addPass(pass);
	}

	private void populateStations(String line) {
		String[] tokens = line.split(StringConstants.COLON);
		
		for(String token : tokens){
			Station station = new Station();
			
			station.setName(token.split(StringConstants.COMMA)[0]);
			station.setType(Integer.valueOf(token.split(StringConstants.COMMA)[1]));
			
			dbService.addStation(station);
		}
	}

	private void populateUsers(String line) {
		String[] tokens = line.split(StringConstants.COLON);
		
		User user = new User();
		user.setUserId(tokens[0]);
		user.setName(tokens[1]);
		user.setEmail(tokens[2]);
		if(tokens.length >= 5){					
			user.setType(tokens[4]);
		}
							
		List<String> cards = new ArrayList<String>();
		if(tokens.length >= 4){
			String[] cardIds = tokens[3].split(StringConstants.COMMA);
			for(String cardId : cardIds){
				cards.add(cardId);
			}
		}
		
		user.setCards(cards);
		
		dbService.addUser(user);
	}

	private void populateCards(String line) {
		String[] tokens = line.split(StringConstants.COLON);
		
		MyTiCard card = new MyTiCard();
		card.setCardId(tokens[0]);
		card.setUserId(tokens[1]);
		card.setCredit(Double.valueOf(tokens[2]));
		
		dbService.addCard(card);
	}	
	
}
