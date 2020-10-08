package core;

import java.util.ArrayList;

import mst.CS2004;
import mst.MST;
//Simulated Annealing
public class SA {
	private static ArrayList<Integer> oldSol = new ArrayList<Integer>();
	private static ArrayList<Integer> newSol = new ArrayList<Integer>();
	//Empty both arrays
	public static void clearArrays() {	
		newSol.clear();
		oldSol.clear();		
	}
	//Code for Simulated Annealing
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> setSA (){
		clearArrays();//Empty both arrays
		//Gets all the values from the class variables
		int iterations = Variables.iterations;
		double T = Variables.temperatureSA;
		double Tmin = Variables.temperatureMin;

		double coolingRate = Math.pow((Tmin/T), (1.0/iterations));//Calculates the cooling rate

		oldSol = (ArrayList<Integer>) Paths.newPath(Variables.size).clone();//Generates a new random path which is copied into oldSol

		for (int i = 0; i < iterations - 1; i++) {

			newSol = (ArrayList<Integer>) Paths.smallChange(oldSol).clone();//Applies a small change to the generated path 		

			double fitnessSol = Fitness.TSPFitnessFunction(newSol);//calculates the fitness of the old and the new solution
			double fitnessoldSol = Fitness.TSPFitnessFunction(oldSol);
			//Compares if the new solution is worst than the old one
			if (fitnessSol > fitnessoldSol) {//if the solution is worst then...
				double p = Math.exp(((fitnessoldSol - fitnessSol))/ T); //Calculate the probability
				if (p > CS2004.UR(0.0, 1.0)) {//if the probability is greater than a random generated between 0 and 1
					oldSol = (ArrayList<Integer>) newSol.clone();//Copy the new solution into the old one
				}
			}else {//if the new solution is NOT worst then...
				oldSol = (ArrayList<Integer>) newSol.clone();//Copy the new solution into the old one
			}
			T = T*coolingRate;//Reduce the temperature using the cooling rate 
		}
		Paths.store(oldSol, "SA");//Stores the result path with the name of the method;
		return oldSol;//return the value in oldSol which is the most efficient path
	}
	//Runs the algorithm 15 times and calculates the average
	public static double getFitness() {
		double sum = 0.0;
		for ( int i = 0; i < 15; i++) {	
			sum = sum + Fitness.TSPFitnessFunction(setSA());
		}
		sum = sum/15;
		return sum;
	}
	//Calculates the best initial temperature
	public static double getTemperature() {
		double max = TSP.getMaxDistance();//Gets the maximum distance in the matrix
		double mst = MST.getMST();//Gets the minimum spanning tree
		int size = Variables.size;//Size of the data set
		
		double X = Math.abs(mst - (size * max));//Refer to the report for more information about this formula
		int N = scaleDown(X);//A number is generated to scale down the solution 
		return X/N;
	}
	//Generates a number like 10, 100m 1000.... to scale down the previous solution
	private static int scaleDown(double X) {
		String strX = String.valueOf(Double.valueOf(X).intValue());//converts the double into integer to eliminate decimals and then into a String
		int numSize = (strX.length())/2;//Calculate the size of the string then divided by two

		String num = "1";//Initialise the string with a "1"
		for(int i = 1; i < numSize; i++) {
			num = num + "0";//add zero to the string until it reach the size of numSize
		}
		return Integer.parseInt(num);//Convert the string into an Integer and return it 
	}

}
