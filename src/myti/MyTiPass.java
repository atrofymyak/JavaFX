package myti;

import java.sql.Time;
import java.time.LocalTime;

import constants.StringConstants;


/**
 * Created by Prashant on 03/09/16.
 */

/**
 * This class is the class responsible for the creation of a MyTi travel Pass
 */
public class MyTiPass {

    /**
     * Final Variables For Zones allow easier Modification to the Ticketing Prices
     */
    //2 Hour Zone 1 Travel Pass on this MyKi card
    final static double TRAVEL_PASS_TYPE_1 = 3.50;
    //2 Hour Zone 2 Travel Pass on this MyKi card
    final static double TRAVEL_PASS_TYPE_2 = 5.00;
    //All day Zone 1 Travel Pass on this MyKi card
    final static double TRAVEL_PASS_TYPE_3 = 6.90;
    //All day Zone Travel Pass on this MyKi card
    final static double TRAVEL_PASS_TYPE_4 = 9.80;
    //discount rate for juniours and seniors
    final static double DISCOUNT_RATE = 2;

    /**
     * Private String that holds the Zone Name, initialized to null
     */
    private String zone = null;
    /**
     * Private String that holds the Length of Pass, initialized to null
     */
    private String length = null;
    /**
     * Private Time Class object that holds the current Clock Time.
     */
    private Time startTime;
    
    private double price;
    
    private String dayOfWeek;

    /**
     * Empty constructor to initialize the class in the Main System
     */
    public MyTiPass() {
    }

    /**
     * Constructor passing Zone and Length To create an Object of this class.
     *
     * @param zone
     * @param length
     */
    public MyTiPass(String zone, String length) {
        this.zone = zone;
        this.length = length;
    }

    /**
     * public method to get The start time of the travel pass
     *
     * @return
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * public method to get The Zone of the travel pass
     *
     * @return
     */
    public String getZone() {
        return zone;
    }

    /**
     * public method to get The Length of the travel pass
     *
     * @return
     */
    public String getLength() {
        return length;
    }


    /**
     * this method Checks the Zone, length and the pass type being input.
     * When a section is made an appropriate cost is calculated for the journey
     * If the Card holder is a junior or senior then a discounted price is applied.
     * A final cost of type double is returned from this method.
     *
     * @param zone
     * @param length
     * @param typeOfPass
     * @return double: cost of travel
     */
    public double calculateCostofPass(String zone, String length, String typeOfPass) {
        if ((length.equals("a")) && (zone.equals("a"))) {
            this.zone = "Zone 1";
            this.length = "2 Hour Pass";
            this.startTime = Time.valueOf(LocalTime.now());
            if ((typeOfPass.contains("Junior")) || (typeOfPass.contains("Senior"))) {
                double costOftravel = TRAVEL_PASS_TYPE_1;
                costOftravel /= DISCOUNT_RATE;
                return costOftravel;
            } else {
                double costOftravel = TRAVEL_PASS_TYPE_1;
                return costOftravel;

            }

        } else if ((length.equals("a")) && (zone.equals("b"))) {
            this.zone = "Zone 1";
            this.length = "All Day Pass";
            this.startTime = Time.valueOf(LocalTime.now());
            if ((typeOfPass.contains("Junior")) || (typeOfPass.contains("Senior"))) {
                double costOftravel = TRAVEL_PASS_TYPE_2;
                costOftravel /= DISCOUNT_RATE;
                return costOftravel;
            } else {
                double costOftravel = TRAVEL_PASS_TYPE_2;
                return costOftravel;
            }

        } else if ((length.equals("b")) && (zone.equals("a"))) {
            this.zone = "Zone 1 and 2";
            this.length = "2 Hour Pass";
            this.startTime = Time.valueOf(LocalTime.now());
            if ((typeOfPass.contains("Junior")) || (typeOfPass.contains("Senior"))) {
                double costOftravel = TRAVEL_PASS_TYPE_3;
                costOftravel /= DISCOUNT_RATE;
                return costOftravel;
            } else {
                double costOftravel = TRAVEL_PASS_TYPE_3;
                return costOftravel;
            }

        } else if ((length.equals("b")) && (zone.equals("b"))) {
            this.zone = "Zone 1 and 2";
            this.length = "All Day Pass";
            this.startTime = Time.valueOf(LocalTime.now());
            if ((typeOfPass.contains("Junior")) || (typeOfPass.contains("Senior"))) {
                double costOftravel = TRAVEL_PASS_TYPE_4;
                costOftravel /= DISCOUNT_RATE;
                return costOftravel;
            } else {
                double costOftravel = TRAVEL_PASS_TYPE_4;
                return costOftravel;
            }
        } else
            return 0.0;
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
    
	public String toString(){
		String result = this.getLength() + StringConstants.COLON + this.getZone() + StringConstants.COLON + this.getPrice();
		
		return result;
	}

}
