package myti;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Prashant on 06/09/16.
 */

/**
 * the following Class associates itself to thee MyTiCard Class.
 * It Holds 3 parameters in it's object.
 */

public class User {

    Scanner keyboard = new Scanner(System.in);

    private String userId;
    //Holds the Owner Name on the Card
    private String name;
    //Holds the Owner Email on the card
    private String email;
    //Holds the Type (junior, Adult Senior)
    private String type;
    //HOlds the Card name that the user wishes to input.
    private List<String> cards = new ArrayList<String>();


    /**
     * Constructor Passing 3 parameters to create an object
     * will come to use when the user chooses 'No' to Enter a card name
     *
     * @param name
     * @param email
     * @param type
     */
    public User(String name, String email, String type) {
        this.name = name;
        this.email = email;
        this.type = type;
    }


    /**
     * Empty Constructor to create an object Instance in the MytiSystem Class
     */
    public User() {
    }


    /**
     * Constructor passing 4 parameters to create an object
     * will come to use when the user chooses 'yes' to Enter a card name
     *
     * @param name
     * @param email
     * @param cardId
     * @param type
     */
    public User(String name, String email, List<String> cards, String type) {

        this.name = name;
        this.email = email;
        this.cards = cards;
        this.type = type;
    }


    /**
     * Returns the name for This Instance
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * returns the email for this Instance
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * returns the card Id for this instance
     *
     * @return
     */
    public List<String> getCards() {
        return cards;
    }

    /**
     * returns the Card type for this instance
     *
     * @return
     */
    public String getType() {
        return type;
    }


    /**
     * Creates a new object of the class user (with name, email and type ) only
     * returns the User object created
     *
     * @return
     */
    public User addUserToMyTiCard() {
        clearBuffer();
        System.out.println("Enter your User Name: ");
        this.name = keyboard.nextLine();
        System.out.println("Enter your email address: ");
        this.email = keyboard.nextLine();
        this.type = typeOfCard();
        User userDetails = new User(this.name, this.email, this.type);
        return userDetails;

    }


    /**
     * Takes the input for CardId and creates a new object of the User class
     * with input ( name, email, cardId and Type )
     * returns the User object Created.
     *
     * @param cardIdInput
     * @return
     */
//    public User addUserWithFourParamters(String cardIdInput) {
//        this.cardId = cardIdInput;
//        User finalUser = new User(this.name, this.email, this.cardId, this.type);
//        return finalUser;
//    }


    /**
     * Returns a String that holds the Type of Card chosen by the User.
     * Switch Case returns the String so that the String is comparable to other methods.
     * Invalid option will break the method.
     *
     * @return
     */
    private String typeOfCard() {
        clearBuffer();
        String cardType = null;
        System.out.println("Type Of Cards\n" +
                "Please Choose an Appropriate Card:-\n" +
                "Enter (1-3) to make your choice\n" +
                "Type 1: Junior\n" +
                "Type 2: Adult\n" +
                "Type 3: Senior\n");
        int choice = keyboard.nextInt();
        switch (choice) {
            case 0:
                System.out.println("Invalid Option! please select an option from 1 to 3");
                typeOfCard();
                break;
            case 1:
                cardType = "Junior";
                break;
            case 2:
                cardType = "Adult";
                break;
            case 3:
                cardType = "Senior";
                break;
            default:
                System.out.println("Invalid Option! please select an option from 1 to 5");
                typeOfCard();
                break;

        }
        return cardType;
    }


    /**
     * A method to clear the input buffer initializing it.
     */
    private void clearBuffer() {
        keyboard = new Scanner(System.in);
    }


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setCards(List<String> cards) {
		this.cards = cards;
	}

    
    
}


