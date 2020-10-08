package core;
import java.util.ArrayList;
import mst.CS2004;
import mst.MST;
//Stochastic Hill Climber
public class SHC {
	private static ArrayList<Integer> oldSol = new ArrayList<Integer>();
	private static ArrayList<Integer> newSol = new ArrayList<Integer>();
	//Empty both arrays
	private static void clearArrays() {	
		newSol.clear();
		oldSol.clear();		
	}
	//Code for the Stochastic Hill Climber
	@SuppressWarnings("unchecked")
	private static ArrayList<Integer> setSHC() {
		clearArrays();//Empty both arrays
		double Pr;
		//Gets all the values from the class variables
		int iterations = Variables.iterations;
		double T = Variables.temperatureSHC;

		oldSol = (ArrayList<Integer>) Paths.newPath(Variables.size).clone();//Generates a new random path which is copied into oldSol

		for(int i = 0; i < iterations ; i++) {

			newSol = (ArrayList<Integer>) Paths.smallChange(oldSol).clone();//Applies a small change to the generated path 

			double fitnessSol = Fitness.TSPFitnessFunction(newSol);//calculates the fitness of the old and the new solution
			double fitnessoldSol = Fitness.TSPFitnessFunction(oldSol);

			Pr = 1/(1+Math.exp((fitnessSol - fitnessoldSol)/T));//Probability is calculated

			if (Pr > CS2004.UR(0.0, 1.0)) {//Generate a random number between 0 and 1
				oldSol = (ArrayList<Integer>) newSol.clone();//If the new solution is better, it is copied into the old solution
			}
		}
		Paths.store(oldSol, "SHC");//Stores the result path with the name of the method;
		return oldSol;//return the value in oldSol which is the most efficient path
	}
	//Runs the algorithm 15 times and calculates the average	
	public static double getFitness() {
		double sum = 0.0;
		for ( int i = 0; i < 15; i++) {	
			sum = sum + Fitness.TSPFitnessFunction(setSHC());
		}
		sum = sum/15;
		return sum;
	}
	//Calculates the best temperature
	public static double getTemperature() {
		double K = 1.63 * Math.pow(10, -3); //1.63*10^-3 is constant I calculated (Refer to the report for more information)
		return (MST.getMST() * K);
	}
}
