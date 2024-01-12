package a1;

public class DrivingCostCalculator {

	public static void main(String[] args) {
		// Christopher Drake 
		// u1219632
		// CS1410 Assignment 1: Driving Cost Calculator
		
		
		double driveDistance = 700; 
		double milesPerGal = 24.2; 
		double gasCost = 3.10; 
		double elecKWh = 3.8; 
		double elecCost = 0.106;
		
		//Begin program, print banner to console
		displayBanner();
		
		//Print summary of electric and gas trip costs, using values stored in program.
		System.out.print("The cost of a " + driveDistance + " mile trip by gas is: $" + String.format("%.2f", calculateGasTripCost(driveDistance, milesPerGal, gasCost)));  
		System.out.println(" and by electric is: $" + String.format("%.2f", calculateElectricTripCost(driveDistance, elecKWh, elecCost)));
	}
	/**
	 * Calculates and returns cost of trip for an gas vehicle
	 * @param milesToDrive : total distance of trip in miles
	 * @param milesPerGallon : them amount of fuel in gallons the vehicle consumes per hour
	 * @param dollarsPerGallon : cost of fuel per gallon
	 * @return : total cost of tip for gas vehicle
	 */
	public static double calculateGasTripCost(double milesToDrive, double milesPerGallon, double dollarsPerGallon) {
		// Cost of trip = number of gallons * dollars per gallon
		
		double gallonsUsed = milesToDrive / milesPerGallon; 
		double tripCost = gallonsUsed * dollarsPerGallon; 
	 
		return tripCost; 
	}
	
	/**
	 * Calculates and returns cost of trip for an electric vehicle
	 * @param milesToDrive : total distance of trip in miles
	 * @param milesPerKWh : the miles per KWh rating of the electric car
	 * @param dollarsPerKWh : cost per KWh of electricity
	 * @return : total cost of trip for electric vehicle
	 */
	public static double calculateElectricTripCost(double milesToDrive, double milesPerKWh, double 	dollarsPerKWh) {
		double elecUsed = milesToDrive / milesPerKWh; 
		double tripCost = elecUsed * dollarsPerKWh; 
		
		return tripCost; 
	}
	
	/**
	 * Prints program banner to console
	 */
	public static void displayBanner() {
		System.out.println("=============================="); 
		System.out.println("---- Trip Cost Calculator ----"); 
		System.out.println("=============================="); 

	}

	
}
