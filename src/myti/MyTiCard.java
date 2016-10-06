package myti;

import java.util.ArrayList;
/**
 * Created by Prashant on 06/09/16.
 */


/**
 * This is The MyTi Card Class.
 * Not only does it hold It's own parameters
 * but also Initializes the array Lists for Classes : MyTiPass, Journey & User
 * by initializing it in the constructer Every new Myti Card will generate it's own ArrayLists.
 */
public class MyTiCard {


    /**
     * Constant Variable Credit limit is the maximum allowed credit
     * Constant Variable Legal Multiple is a multiple that we can re-charge by
     */
    final static double CREDIT_LIMIT = 100.0;
    final static int LEGAL_MULTIPLE = 5;
    private final static int MAX_PURCHASES = 10;
    /**
     * Creating ArrayLists for Appropriate Classes
     * 1. Arraylist for MytiPass, holds the MyTiPass objects.
     * 2. Arraylist for Journey, holds the Journey objects.
     * 3. Arraylist for User, holds 'only one ' User object. because a MyTi card can only have exactly one user.
     */
    ArrayList<MyTiPass> myTiPasses = new ArrayList<MyTiPass>();
    ArrayList<Journey> journeys = new ArrayList<Journey>();
    ArrayList<User> userCards = new ArrayList<User>(1);
    
    private String cardId;
    private String userId;    


    /**
     * A Constructor to Initialize the array Lists for Classes : MyTiPass, Journey & User
     * initializing the constructer here Allows Every new MytiCard object to generate it's own ArrayLists.
     */
    public MyTiCard() {
        this.myTiPasses = new ArrayList<MyTiPass>();
        this.journeys = new ArrayList<Journey>();
        this.userCards = new ArrayList<User>();
    }


    // we need to keep track of all (successful) purchases made

    // create a structure to keep track of travel passes purchased


    /**
     * String holding the credit for a card.
     */
    private double credit;

    /**
     * Constructor passing nothing but making sure that the credit for every card is set to 0.0;
     *
     * @param myTiId
     */
    public MyTiCard(String myTiId) {
        this.credit = 0.0;
    }

    /**
     * Public getter to get the credit for an Instance of a MyTi Card.
     *
     * @return
     */
    public double getCredit() {
        return credit;
    }


    /**
     * A method to Recharge a MyTiCard
     * Checks LEGAL_MULTIPLE validation of the amount being a multiple of 5
     * Checks CREDIT_LIMIT validation of the credit not exceeding $100
     * Passing of the Two Checks will add amount to this instance of Credit
     * Failing the Checks wil lresult in an appropriate Fail message.
     *
     * @param amount
     * @return true/false
     */
    public boolean chargeMytiCard(double amount) {
        if (!(amount % LEGAL_MULTIPLE == 0)) {
            System.out.println("Recharge Failed! The Amount you can charge with must be a multiple of '5'.");
            return false;
        } else {
            if (amount + this.getCredit() > CREDIT_LIMIT) {
                System.out.println("Recharge Failed! Your MyTi Credit cannot exceed $100. Please choose a smaller amount");
                return false;
            } else {
                this.credit += amount;
                System.out.println("Your MyTi card was recharged with $" + amount);
                System.out.println("Your current credit is $" + this.getCredit());
                return true;
            }
        }

    }

    /**
     * This method is called when A myTiPass is being purchased
     * it Takes this instance of the credit and deducts the amount from it.
     *
     * @param amount
     * @return
     */
    public boolean purchasePass(double amount) {
        this.credit -= amount;
        return true;
    }


    /**
     * This Method prints the journeys Taken on a MytiCard
     */
    public void printJourney() {
        Journey tempJourney;
        for (int i = 0; i < journeys.size(); i++) {
            tempJourney = journeys.get(i);
            System.out.println("Journey " + (i + 1) + ". " + tempJourney.getStartStation() + " to " + tempJourney.getEndStation() +
                    " at " + tempJourney.getJourneyStartTime() + " Hours");
        }

    }

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}


}