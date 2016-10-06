package myti;

import java.util.Scanner;

/**
 * Created by Prashant on 06/09/16.
 */
public class Journey {
    private Scanner keyboard = new Scanner(System.in);
    //Holds the day of journey eg monday , tuesday
    private String journeyDay;
    // holds the time to start journey
    private double journeyStartTime;
    // holds the time to end the journey
    private double journeyEndTime;
    // holds the name of the  start station
    private String startStation;
    // holds the name of the  end station
    private String endStation;
    // checks whether concession is entitled
    private boolean consessionFlag = false;
    // checks whether the day is sunday
    private boolean sundayCheck = false;
    // if travel pass is updated this holds the updated zone value
    private String updatedZone = null;
    // if travel pass is updated this holds the updated Length value
    private String updatedLength = null;

    /**
     * these variables hold the counter for stations
     * each station has a journey started and a journey ended counter
     */
    private int centralStartCounter =0;
    private int flagstaffStartCounter =0;
    private int richmondStartCounter =0;
    private int lilydaleStartCounter =0;
    private int eppingStartCounter =0;
    private int centralEndCounter =0;
    private int flagstaffEndCounter =0;
    private int richmondEndCounter =0;
    private int lilydaleEndCounter =0;
    private int eppingEndCounter =0;


    /**
     * Empty constructor to create object in MyTiSystem Class
     */
    public Journey() {
    }

    /**
     * Constructor to add object
     * @param journeyDate
     * @param journeyStartTime
     * @param startStation
     * @param endStation
     */
    public Journey(String journeyDate, double journeyStartTime, String startStation, String endStation) {
        this.journeyDay = journeyDate;
        this.journeyStartTime = journeyStartTime;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    /**
     * gets journeyDay
     * @return String
     */
    public String getJourneyDate() {
        return journeyDay;
    }

    /**
     * returns journey start time
     * @return double
     */
    public double getJourneyStartTime() {
        return journeyStartTime;
    }

    /**
     * returns journey end time
     * @return double
     */
    public double getJourneyEndTime() {
        return journeyEndTime;
    }

    /**
     * returns journey start station
     * @return String
     */
    public String getStartStation() {
        return startStation;
    }

    /**
     * returns journey end station
     * @return String
     */
    public String getEndStation() {
        return endStation;
    }

    /**
     * returns counter for journeys started in central station
     * @return int
     */
    public int getCentralStartCounter() {
        return centralStartCounter;
    }

    /**
     * eturns counter for journeys started in flagstaff station
     * @return int
     */
    public int getFlagstaffStartCounter() {
        return flagstaffStartCounter;
    }

    /**
     * eturns counter for journeys started in richmond station
     * @return int
     */
    public int getRichmondStartCounter() {
        return richmondStartCounter;
    }

    /**
     * eturns counter for journeys started in lilydale station
     * @return int
     */
    public int getLilydaleStartCounter() {
        return lilydaleStartCounter;
    }

    /**
     * eturns counter for journeys started in epping station
     * @return int
     */
    public int getEppingStartCounter() {
        return eppingStartCounter;
    }

    /**
     * eturns counter for journeys ending in central station
     * @return int
     */
    public int getCentralEndCounter() {
        return centralEndCounter;
    }

    /**
     * eturns counter for journeys ending in flagstaff station
     * @return int
     */
    public int getFlagstaffEndCounter() {
        return flagstaffEndCounter;
    }

    /**
     * eturns counter for journeys ending in richmond station
     * @return int
     */
    public int getRichmondEndCounter() {
        return richmondEndCounter;
    }

    /**
     * eturns counter for journeys ending in lilydale station
     * @return int
     */
    public int getLilydaleEndCounter() {
        return lilydaleEndCounter;
    }

    /**
     * eturns counter for journeys ending in epping station
     * @return int
     */
    public int getEppingEndCounter() {
        return eppingEndCounter;
    }

    /**
     * returns the updated zone value if changed
     * @return String
     */
    public String getUpdatedZone() {
        return updatedZone;
    }

    /**
     * returns the updated length value if changed
     * @return String
     */
    public String getUpdatedLength() {
        return updatedLength;
    }


    /**
     * This method Creates a journey
     * Values are passed and checked corrospondingly
     * if journey is validated it will be made
     * if journey needs a different travel pass , logic will update pass to next best and cheapest
     * if updated travel pass can be purchased journey will be made else not
     * concession for seniors and juniors is calculated and returned
     * full fare cost is returned for adults
     * allows senior card holders to take free journeys on sunday
     * -1 will be returned if no condition is met, failing to make a journey
     * @param myTiPassZone
     * @param myTiPasslength
     * @param seniorCheck
     * @return double (cost of journey)
     */
    public double createJourney(String myTiPassZone, String myTiPasslength, String seniorCheck) {
        if(seniorCheck.contains("Senior")) {
            // if the day is sunday this is set true
            sundayCheck = true;
        }
        if((seniorCheck.contains("Senior")) || seniorCheck.contains("Junior")) {
           // if concession is applicable this is set true;
            consessionFlag=true;
        }
        updatedZone = myTiPassZone;
        updatedLength = myTiPasslength;
        clearBuffer();
        // input from user
        System.out.println("****************");
        System.out.println("Please Enter the details for your journey below:- ");
        System.out.println("Please enter the day you wish to travel\n" +
                "Type Day From (Monday - Sunday)\n");
        this.journeyDay = keyboard.nextLine();
        if (!((getJourneyDate().toLowerCase().contains("monday")) ||
                (getJourneyDate().toLowerCase().contains("tuesday")) ||
                (getJourneyDate().toLowerCase().contains("wednesday")) ||
                (getJourneyDate().toLowerCase().contains("thursday")) ||
                (getJourneyDate().toLowerCase().contains("friday")) ||
                (getJourneyDate().toLowerCase().contains("saturday")) ||
                (getJourneyDate().toLowerCase().contains("sunday"))))
        {
            System.out.println("Invalid Input, Please Start again!");
            return -1;
        }

        System.out.println("Please enter the Start time for your journey\n" +
                "Type Hours From 0.0 - 23.59 \n");
        this.journeyStartTime = keyboard.nextDouble();
        if (journeyStartTime < 0.0 || journeyStartTime > 23.59) {
            return -1;
        }

        System.out.println("Please enter the End time for your journey\n" +
                "Type Hours From 0.0 - 23.59 \n");
        this.journeyEndTime = keyboard.nextDouble();
        if (journeyEndTime < 0.0 || journeyEndTime > 23.59) {
            return -1;
        }

        System.out.println("Please choose a Starting station option from the following options");
        this.startStation = stationChoice();
        System.out.println("Please choose your Ending station option from the following options");
        this.endStation = stationChoice();

        // variables to check with
        double journeytimer = 0;
        int endZone = 0;
        String setZoneOne = "Zone 1";
        String setZoneOneAndTwo = "Zone 1 and 2";
        String setLength;
        double exrtaCost = 0.0;

        String startZoneCheck = zoneCheck(startStation);
        String endZoneCheck = zoneCheck(endStation);
        journeytimer = journeyEndTime-journeyStartTime;
        if((journeyDay.contains("sunday")) && (sundayCheck == true)) {
            // if traveling on a sunday a senior citizen is given free travel
            System.out.println("Your journey Today is Free Of Cost Since you are a Senior Citizen");
            return 0;
        }
        // method checks all possible values for a 2 Hour pass for Zone 1 only
        // if update is required the method will automatically to the next best cheapest tiket
        // concession will be aplied if applicable
        if(myTiPasslength.equals("2 Hour Pass")) {
            if(myTiPassZone.equals("Zone 1")) {
                if((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer>2)) {
                    System.out.println("This journey cannot be made on a 2 hour Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to an All Day travel pass for Zone 1");
                    exrtaCost = 1.50;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedLength = "All Day Pass";
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer<=2)) {
                    System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to a 2 Hour travel pass for Zone 1 and 2");
                    exrtaCost = 3.30;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedZone = setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer>2)) {
                    System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to a All Day travel pass for Zone 1 and 2");
                    exrtaCost = 6.30;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedLength = "All Day Pass";
                    updatedZone =setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to a 2 Hour travel pass for Zone 1 and 2");
                    exrtaCost = 3.30;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedZone =setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer>2)) {
                    System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to a All Day travel pass for Zone 1 and 2");
                    exrtaCost = 6.30;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedLength = "All Day Pass";
                    updatedZone =setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
            }

        }
        // Checks all possible values for an All day pass for Zone 1 only
        // if update is required the method will automatically to the next best cheapest tiket
        // concession will be aplied if applicable
        if (myTiPasslength.equals("All Day Pass")) {
            if(myTiPassZone.equals("Zone 1")) {
                if((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if(myTiPassZone.equals("Zone 1")) {
                    if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer > 2)) {
                        System.out.println("Your journey has been Validated on your current myTi TravelPass");
                        return 0;
                    }
                }
                else if(myTiPassZone.equals("Zone 1")) {
                    if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer <=2)) {
                        System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                        System.out.println("Your Travel pass is autamatically being updated to a 2 Hour travel pass for Zone 1 and 2");
                        exrtaCost = 1.90;
                        if (consessionFlag == true) {
                            exrtaCost = exrtaCost/2;
                        }
                        updatedZone =setZoneOneAndTwo;
                        System.out.println("Automatic update Deduction : $" + exrtaCost);
                        return exrtaCost;
                    }
                }
                else if(myTiPassZone.equals("Zone 1")) {
                    if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer >2)) {
                        System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                        System.out.println("Your Travel pass is autamatically being updated to a All Day travel pass for Zone 1 and 2");
                        exrtaCost = 4.80;
                        if (consessionFlag == true) {
                            exrtaCost = exrtaCost/2;
                        }
                        updatedLength = "All Day Pass";
                        updatedZone =setZoneOneAndTwo;
                        System.out.println("Automatic update Deduction : $" + exrtaCost);
                        return exrtaCost;
                    }
                }
                else if(myTiPassZone.equals("Zone 1")) {
                    if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer <=2)) {
                        System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                        System.out.println("Your Travel pass is autamatically being updated to a 2 Hour travel pass for Zone 1 and 2");
                        exrtaCost = 1.90;
                        if (consessionFlag == true) {
                            exrtaCost = exrtaCost/2;
                        }
                        updatedZone =setZoneOneAndTwo;
                        System.out.println("Automatic update Deduction : $" + exrtaCost);
                        return exrtaCost;
                    }
                }
                else if(myTiPassZone.equals("Zone 1")) {
                    if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer >2)) {
                        System.out.println("This journey cannot be made on a Zone 1 Travel Pass");
                        System.out.println("Your Travel pass is autamatically being updated to a All Day travel pass for Zone 1 and 2");
                        exrtaCost = 4.80;
                        if (consessionFlag == true) {
                            exrtaCost = exrtaCost/2;
                        }
                        updatedLength = "All Day Pass";
                        updatedZone =setZoneOneAndTwo;
                        System.out.println("Automatic update Deduction : $" + exrtaCost);
                        return exrtaCost;
                    }
                }

            }

        }
        // checks all possible values for a 2 hour pass for zone 1 and 2 only
        // if update is required the method will automatically to the next best cheapest tiket
        // concession will be aplied if applicable
        if (myTiPasslength.equals("2 Hour Pass")) {
            if(myTiPassZone.equals("Zone 1 and 2")) {
                if((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer>2)) {
                    System.out.println("This journey cannot be made on a 2 hour Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to an All Day travel pass for Zone 1 and 2");
                    exrtaCost = 2.90;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedLength = "All Day Pass";
                    updatedZone =setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer>2)) {
                    System.out.println("Your Travel pass is autamatically being updated to an All Day travel pass for Zone 1 and 2");
                    updatedLength = "All Day Pass";
                    updatedZone =setZoneOneAndTwo;
                    exrtaCost = 2.90;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer>2)) {
                    System.out.println("This journey cannot be made on a 2 hour Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to an All Day travel pass for Zone 1 and 2");
                    exrtaCost = 2.90;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedLength = "All Day Pass";
                    updatedZone =setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);
                    return exrtaCost;
                }
                else if((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 2")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 2")) && (journeytimer>2)) {
                    System.out.println("This journey cannot be made on a 2 hour Travel Pass");
                    System.out.println("Your Travel pass is autamatically being updated to an All Day travel pass for Zone 1 and 2");
                    exrtaCost = 2.90;
                    if (consessionFlag == true) {
                        exrtaCost = exrtaCost/2;
                    }
                    updatedLength = "All Day Pass";
                    updatedZone =setZoneOneAndTwo;
                    System.out.println("Automatic update Deduction : $" + exrtaCost);

                    return exrtaCost;
                }
            }else {
                return -1;
            }
        }
        // checks all possible values for an All day  pass for zone 1 and 2 only
        if (myTiPasslength.equals("All Day Pass")) {
            if(myTiPassZone.equals("Zone 1 and 2")) {
                if((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 1")) && (journeytimer>2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 1")) && (endZoneCheck.equals("Zone 2")) && (journeytimer>2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 1")) && (journeytimer>2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 2")) && (journeytimer<=2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
                else if ((startZoneCheck.equals("Zone 2")) && (endZoneCheck.equals("Zone 2")) && (journeytimer>2)) {
                    System.out.println("Your journey has been Validated on your current myTi TravelPass");
                    return 0;
                }
            }else {
                // if journey cannot be made or input error -1 will be returned to the calling method
                return -1;
            }
        }
        // if journey cannot be made or input error -1 will be returned to the calling method
    return -1;
    }


    /**
     * Private method
     * Allows user to choose station from a list rather than input name
     * returns equivalent string to compensate for errors
     * @return String
     */
    private String stationChoice(){
        String stationChoice = null;
        System.out.println  ("List of Stations \n" +
                "Choose a number (1-5) for your station choice\n" +
                "Station 1: Central     Zone: 1\n" +
                "Station 2: Flagstaff   Zone: 1\n" +
                "Station 3: Richmond    Zone: 1\n" +
                "Station 4: Lilydale    Zone: 2\n" +
                "Station 5: Epping      Zone: 2\n");
        int choice = keyboard.nextInt();
        switch (choice) {
            case 0:
                System.out.println("Invalid Option! please select an option from 1 to 5");
                stationChoice();
                break;
            case 1:
                stationChoice = "Central";
                break;
            case 2:
                stationChoice = "Flagstaff";
                break;
            case 3:
                stationChoice = "Richmond";
                break;
            case 4:
                stationChoice = "Lilydale";
                break;
            case 5:
                stationChoice = "Epping";
                break;
            default:
                System.out.println("Invalid Option! please select an option from 1 to 5");
                stationChoice();
                break;

        }
        return stationChoice;
    }

    /**
     * Private Method
     * checks which zone the input station belongs to
     * returns a string eg, zone 1 , zone 1 and 2
     * @param stationName
     * @return String
     */
    private String zoneCheck(String stationName) {
        String station1 = "Central";
        String station2 = "Flagstaff";
        String station3 = "Richmond";
        String station4 = "Lilydale";
        String station5 = "Epping";
        String corrospondingZone= null;
        if(stationName.equals(station1)) {
            corrospondingZone = "Zone 1";
        }
        else if (stationName.equals(station2)) {
            corrospondingZone = "Zone 1";
        }
        else if (stationName.equals(station3)) {
            corrospondingZone = "Zone 1";
        }
        else if (stationName.equals(station4)) {
            corrospondingZone = "Zone 2";
        }
        else if (stationName.equals(station5)) {
            corrospondingZone = "Zone 2";
        }
        return corrospondingZone;
    }

    /**
     * Checks the station counter increments each time a journey is started or ended from a satation.
     * @param startingStation
     * @param endingStation
     */
    public void counterCheck(String startingStation, String endingStation) {
        if(startingStation.contains("Central"))
            this.centralStartCounter++;
        if(startingStation.contains("Flagstaff"))
            this.flagstaffStartCounter++;
        if(startingStation.contains("Richmond"))
            this.richmondStartCounter++;
        if(startingStation.contains("Lilydale"))
            this.lilydaleStartCounter++;
        if(startingStation.contains("Epping"))
            this.eppingStartCounter++;
        if(endingStation.contains("Central"))
            this.centralEndCounter++;
        if(endingStation.contains("Flagstaff"))
            this.flagstaffEndCounter++;
        if(endingStation.contains("Richmond"))
            this.richmondEndCounter++;
        if(endingStation.contains("Lilydale"))
            this.lilydaleEndCounter++;
        if(endingStation.contains("Epping"))
            this.eppingEndCounter++;
    }

    /**
     * Private Method
     * clears the input buffer
     */
    private void clearBuffer () {
        keyboard = new Scanner(System.in);
    }
}


