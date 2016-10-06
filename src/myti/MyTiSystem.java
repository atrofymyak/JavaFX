package myti;

import java.util.HashMap;
import java.util.Scanner;


/**
 * Implementation of basic MyTi system outline.
 * A MyTi card can be used to purchase travel passes:
 * 2Hour or All Day, Zone 1 or Zones 1+2.
 * Each type of travel pass has different prices.
 * This program loops through options of purchasing a pass,
 * topping up the MyTi card, or viewing current credit.
 *
 * @author lcavedon
 */
public class MyTiSystem {

    /**
     * create a new Scanner from standard input
     */
    static Scanner input = new Scanner(System.in);
    /**
     * Creates a Hashmap with a string(User Name) and MyTiCard object
     * to store all the MytiCards Created in the system.
     */
    static HashMap<String, MyTiCard> mytiCardMap = new HashMap<String, MyTiCard>();
    /**
     * Creates a mytiPass object to allow class Access
     */
    static MyTiPass myTiPass = new MyTiPass();
    /**
     * Creates a Journey object to allow class Access
     */
    static Journey journey = new Journey();
    /**
     * Creates a User object to allow class Access
     */
    static User user = new User();

    static int userCounter = 0;
    /**
     * Boolean to CHeck whether a user Decides to put a name to their MytICard.
     */
    static boolean ownerCheck;

    /**
     * main program: this contains the main menu loop
     */
    public static void main(String[] args) {

        /**
         * Creating a default Instance of a MytiCard for testing Purpose
         * Every new User will have to Register First through (add new user ) option
         * to access the system.
         */
        MyTiCard card1 = new MyTiCard("111");
        mytiCardMap.put("111", card1);


        // main menu loop: print menu, then do something depending on selection
        int option;
        do {
            printMainMenu();
            option = input.nextInt();
            System.out.println();

            // perform correct action, depending on selection
            switch (option) {
                case 1:
                    purchasePass();
                    break;
                case 2:
                    recharge();
                    break;
                case 3:
                    showCredit();
                    break;
                case 4:
                    checkAndPurchaseJourney();
                    break;
                case 5:
                    printAllJourneys();
                    break;
                case 6:
                    stationStatistics();
                    break;
                case 7:
                    addNewUser();
                    break;
                case 0:
                    System.out.println("Goodbye!");  // will quit---do nothing
                    break;
                default:
                    // if no legal option selected, just go round and choose again
                    System.out.println("Invalid option!");
            }

        } while (option != 0);

        // finishing processing ... close the input stream
        input.close();
    }

    /*
     * Print the main menu.
     */
    static void printMainMenu() {
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1. Purchase a travel pass");
        System.out.println("2. Recharge your MyTi card");
        System.out.println("3. Show remaining MyTi credit");
        System.out.println("4. Take a journey using MyTi Card");
        System.out.println("5. Print all journeys made using all MyTi Cards");
        System.out.println("6. Show station statistics");
        System.out.println("7. Add a New User");
        System.out.println("0. Quit");
        System.out.print("Your option: ");
    }

    /*
     * Purchase a travel pass using MyTi credit
     */

    static void purchasePass() {

        /*
        Validate MytiCard if null return back to menu
        if validated create a new Instance of a Myti Card object and retrieve the index.
         */
        System.out.println();
        System.out.println("What is the id of the MyTi pass:");
        String id = input.next();
        MyTiCard myticard = null;
        if (!mytiCardMap.containsKey(id)) {
            System.out.println("No MyTi Card matching that id was found ...");
            return;
        } else {
            myticard = mytiCardMap.get(id);
            User checkUser = myticard.userCards.get(0);
            String typeOfCard = checkUser.getType();
            String typeOfCardToPrint = null;

            // Matching Id Found...
            // print time options
            System.out.println();
            System.out.println("How long do you need a pass for:");
            System.out.println("a. 2 Hours");
            System.out.println("b. All Day");
            System.out.println("c. cancel");
            System.out.println("Your selection: ");
            String length = input.next();
            if (length.equals("c")) return;  // cancel

            System.out.println();
            System.out.println("For which zones:");
            System.out.println("a. Zone 1");
            System.out.println("b. Zones 1 and 2");
            System.out.println("c. cancel");
            System.out.println("Your selection: ");
            String zones = input.next();
            if (zones.equals("c")) return;    // cancel

            System.out.println();

            // first check if valid options were selected
            if ((!length.equals("a") && !length.equals("b"))
                    || (!zones.equals("a") && !zones.equals("b"))) {
                System.out.println("You have selected an illegal option. Please try again.");
                // if not, then re-try purchasing a pass
                purchasePass();
            } else {
                // checking the credit.
                double credit = myticard.getCredit();
                // checking the cost of travel
                // if Type = adult then card is full fare
                // if Type = senior, junior then card is concession.
                double costOfTravel = myTiPass.calculateCostofPass(length, zones, typeOfCard);
                if (typeOfCard.contains("Adult")) {
                    typeOfCardToPrint = "Full Fare";
                }
                if ((typeOfCard.contains("Junior")) || (typeOfCard.contains("Senior"))) {
                    typeOfCardToPrint = "Concession";
                }
                //Checking to see the credit is sufficient to purchase this journey
                if (credit >= costOfTravel) {
                    // if sufficient
                    // create a new pass and store it in the array list
                    // print details of pass.
                    myticard.purchasePass(costOfTravel);
                    MyTiPass currentPass;
                    currentPass = new MyTiPass(myTiPass.getZone(), myTiPass.getLength());
                    myticard.myTiPasses.add(currentPass);
                    System.out.println("____________________");
                    System.out.println("You have successfully purchased a " + typeOfCardToPrint + " MyTi Travel Pass.\n" + "Details:- \n" +
                            "********************\n" + myTiPass.getLength() + " Valid For " + myTiPass.getZone() + "\n" +
                            "Start Time for Travel pass is " + myTiPass.getStartTime() + "\n" +
                            "A charge of $" + costOfTravel + " has been deducted from your MyTi Card \n" +
                            "Your remaining balance is $" + myticard.getCredit() + "\n" + "*******************");
                    System.out.println("____________________");
                } else {
                    // if insufficient print message.
                    System.out.println("Purcase Pass Failed: The credit was insufficient");
                }
            }

        }
    }

    /*
     * Recharge a MyTi card
     */
    static void recharge() {
        // Validating User
        // if no match print message and return.
        System.out.println("What is the id of the MyTi pass:");
        String id = input.next();
        MyTiCard myticard = null;
        if (!mytiCardMap.containsKey(id)) {
            System.out.println("No MyTi Card matching that id was found ...");
            return;
            //if match found create a new object
        } else {
            myticard = mytiCardMap.get(id);
            System.out.print("How much credit do you want to add: ");
            // read charge amount from input Scanner
            double amount = input.nextDouble();
            // call method to recharge myti card
            myticard.chargeMytiCard(amount);
        }

    }

    /*
     * Display the remaining credit on MyTi card
     */
    static void showCredit() {
        // Validating User
        // if no match print message and return.
        System.out.println("What is the id of the MyTi pass:");
        String id = input.next();
        MyTiCard myticard = null;
        if (!mytiCardMap.containsKey(id)) {
            System.out.println("No MyTi Card matching that id was found ...");
            return;
            //if match found create a new object
        } else {
            // print balance
            myticard = mytiCardMap.get(id);
            System.out.println("Your current balance is $" + myticard.getCredit());
        }

    }

    static void checkAndPurchaseJourney() {
        // clear input buffer
        // Validating User
        // if no match print message and return.
        clearBuffer();
        System.out.println("What is the id of the MyTi pass:");
        String id = input.next();
        MyTiCard myticard = null;
        if (!mytiCardMap.containsKey(id)) {
            System.out.println("No MyTi Card matching that id was found ...");
            return;
            //if match found create a new object
        } else {
            // check Myti Pass arrayList size
            // if size greater than 1, allow user to choose which pass to make a journey on
            int choice = 0;
            myticard = mytiCardMap.get(id);
            if (!myticard.myTiPasses.isEmpty()) {
                if (myticard.myTiPasses.size() > 1) {
                    System.out.println("Which MyTi travel pass would you like to make a journey on?");

                    for (int j = 0; j < myticard.myTiPasses.size(); j++) {
                        MyTiPass tempPass = myticard.myTiPasses.get(j);
                        String tempPassZone = tempPass.getZone();
                        String tempPassLength = tempPass.getLength();
                        System.out.println("Travel Pass" + "'" + (j + 1) + "'. " + tempPassZone + " Duration : " + tempPassLength);
                    }
                    // take user choice
                    System.out.println("Enter your choice : 1,2,3,etc)");
                    choice = input.nextInt();
                    choice = choice - 1;
                }

                // create a new Card based on the choice input
                // get the object input by user.
                MyTiPass currentPass = myticard.myTiPasses.get(choice);
                // get corrosponding zone on Travel pass
                String passZone = currentPass.getZone();
                // get corrosponding Length of time on Travel pass
                String passLength = currentPass.getLength();
                // get the corrosponding type of card to check if its a senior card.
                String seniorCheck = user.getType();
                // calculate extra cost to make journey
                double extraCost = journey.createJourney(passZone, passLength, seniorCheck);
                // if extra cost is 0 = journey can be made on the travel pass
                // if extra cost is > 0 = journet needs extra charge ( automatic system)
                // if extra cost -1; invalid options selected in method.
                if (extraCost >= 0) {
                    // calculating whether the pass can be updated on extra cost.
                    if (myticard.getCredit() > extraCost) {
                        // deduct extra cost
                        myticard.purchasePass(extraCost);
                        // update and print journey
                        journey.counterCheck(journey.getStartStation(), journey.getEndStation());
                        String finalZone = journey.getUpdatedZone();
                        String finalLength = journey.getUpdatedLength();
                        MyTiPass updatedPass = new MyTiPass(finalZone, finalLength);
                        myticard.myTiPasses.set(choice, updatedPass);
                        System.out.println("Journey Successfully Made");
                        System.out.println("____________________");
                        System.out.println("Here is your Journey Pass:-\n" +
                                "Journey start Date :" + journey.getJourneyDate() + "\n" +
                                "Journey start Time :" + journey.getJourneyStartTime() + "\n" +
                                "Journey End Time :" + journey.getJourneyEndTime() + "\n" +
                                "Journey start Station :" + journey.getStartStation() + "\n" +
                                "Journey End Station: :" + journey.getEndStation() + "\n");
                        System.out.println("____________________");
                        Journey currentjourney;
                        currentjourney = (new Journey(journey.getJourneyDate(), journey.getJourneyStartTime(),
                                journey.getStartStation(), journey.getEndStation()));
                        //System.out.println(currentjourney);
                        myticard.journeys.add(currentjourney);
                    } else {
                        // if credit is insuffieicent print this message
                        System.out.println("You have insufficient balance to purchase this journey");
                        System.out.println("Please recharge your MyTi card and try again.");
                    }

                } else {
                    extraCost = journey.createJourney(passZone, passLength, seniorCheck);
                }


            } else {
                //if no myTi travel Pass exists , prompts to purchase a Myti travel Pass first
                System.out.println("****************");
                System.out.println("Sorry! You need to Purchase A MyTi Travel Pass before you can make a journey.");
            }
        }
    }

    /**
     * Print the list of all journeys made on the Myti Card
     */
    static void printAllJourneys() {
        // validate Card
        // if no validation print message
        System.out.println("What is the id of the MyTi pass:");
        String id = input.next();
        MyTiCard myticard = null;
        if (!mytiCardMap.containsKey(id)) {
            System.out.println("No MyTi Card matching that id was found ...");
            return;

        } else {
            //if validated get MytiCard id and call print journey method
            myticard = mytiCardMap.get(id);
            myticard.printJourney();
        }


    }


    /**
     * Adding a new User
     */
    static void addNewUser() {
        //clear input buffer
        clearBuffer();
        // choose an id (Unique constraint)
        System.out.println("Choose an ID: ");
        String choiceId = input.next();
        // if id exists print message
        //recall method
        if (mytiCardMap.containsKey(choiceId)) {
            System.out.println("Sorry, that id is already in use...");
            System.out.println("please Enter another Id:");
            addNewUser();
        } else {
            // if id is unique
            // add new Card with Id
            MyTiCard card1 = new MyTiCard(choiceId);
            mytiCardMap.put(choiceId, card1);
            User currentUserInfo = user.addUserToMyTiCard();
            input.nextLine();
            // attach another Myti Card
            System.out.println("Would you like to Attach a MyTi card?  (y/n): ");
            String choices = input.nextLine();
            // validation
            System.out.println("The caharacter you choose was " + choices);
            //check whether anyone owns that card(unique constraint)
            ownerCheck = card1.userCards.isEmpty();
            if (choices.contains("y")) {
                if (ownerCheck) {
                    // if yes enter the name of the card
                    System.out.println("Enter a name for the card: ");
                    String cardId = input.nextLine();
                    //currentUserInfo = user.addUserWithFourParamters(cardId);
                    card1.userCards.add(currentUserInfo);
                    //print card with details
                    System.out.println("________________________");
                    System.out.println("Here are Your Card Details :-\n" +
                            "Login ID : " + choiceId + "\n" +
                            "Registered Name : " + currentUserInfo.getName() + "\n" +
                            "Registered Email : " + currentUserInfo.getEmail() + "\n" +
                            "Type : " + currentUserInfo.getType() + "\n" /*+
                            "Card ID : " + currentUserInfo.getCardId() + "\n"*/);
                    System.out.println("________________________");
                }
            }
            //if anyone already owns that card print appropriate message
            if (!ownerCheck) {
                System.out.println("Sorry, that card already has a user; only 1 user per card is allowed.");
                addNewUser();

            }
            // if hoice was no
            if (choices.contains("n")) {
                card1.userCards.add(currentUserInfo);
                // add a new Card without cardName
                // print details
                System.out.println("________________________");
                System.out.println("Here are Your Card Details :-\n" +
                        "Login ID : " + choiceId + "\n" +
                        "Registered Name : " + currentUserInfo.getName() + "\n" +
                        "Registered Email : " + currentUserInfo.getEmail() + "\n" +
                        "Type : " + currentUserInfo.getType() + "\n");
                System.out.println("________________________");
            }

        }

    }

    /**
     * Printing station Statistics
     */
    static void stationStatistics() {
        // validate user
        // if not validate print message
        System.out.println("What is the id of the MyTi pass:");
        String id = input.next();
        MyTiCard myticard = null;
        if (!mytiCardMap.containsKey(id)) {
            System.out.println("No MyTi Card matching that id was found ...");
            return;

        } else {
            // if validated create a new instance and retrieve object from id
            myticard = mytiCardMap.get(id);
            if (!myticard.myTiPasses.isEmpty()) {
                // if journeys are not empty, print journey list
                System.out.println("____________________________");
                System.out.println("Station travel statistics:\n" +
                        "Central: " + journey.getCentralStartCounter() + " journeys started here, "
                        + journey.getCentralEndCounter() + " journeys ended here.\n" +
                        "Flagstaff: " + journey.getFlagstaffStartCounter() + " journeys started here, "
                        + journey.getFlagstaffEndCounter() + " journeys ended here.\n" +
                        "Richmond: " + journey.getRichmondStartCounter() + " journeys started here, "
                        + journey.getRichmondEndCounter() + " journeys ended here.\n" +
                        "Lilydale: " + journey.getLilydaleStartCounter() + " journeys started here, "
                        + journey.getLilydaleEndCounter() + " journeys ended here.\n" +
                        "Epping: " + journey.getEppingStartCounter() + " journeys started here, "
                        + journey.getEppingEndCounter() + " journeys ended here\n.");
                System.out.println("____________________________");
            }
        }

    }

    /**
     * Clears the input buffer by initializing it.
     */
    private static void clearBuffer() {
        input = new Scanner(System.in);

    }

}