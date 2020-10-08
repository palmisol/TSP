package core;
import java.util.ArrayList;
//Random Restart Hill Climber
public class RRHC {
	private static ArrayList<Integer> oldSol = new ArrayList<Integer>();
	private static ArrayList<Integer> newSol = new ArrayList<Integer>();
	//Empty both arrays
	private static void clearArrays() {	
		newSol.clear();
		oldSol.clear();		
	}
	//Code for the Random Restart Hill Climber
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> setRRHC() {
		clearArrays();//Empty both arrays
		
		int[] allIter = new int[2];//Create an array of integers that will contain the iterations for the random restart and the method itself
		allIter = calculateIter(Variables.iterations);//Splits the total number of iterations into two numbers which are stored in index "0" and "1".
													/*1,000,000 -> 1,000 and 1,000
													 *100,000 -> 100 and 1,000
													 *10,000 -> 100 and 100
													 *1,000 -> 10 and 100
													 *100 -> 10 and 10
													  */												
		int innerIter = allIter[0];//This represent the number of small changes applied to the random generated path. 
		int outerIter = allIter[1];//Number of times the Random Mutation Hill Climber will be run. 
		
		oldSol = (ArrayList<Integer>) Paths.newPath(Variables.size).clone();//Generates a new random path which is copied into oldSol
		
		for(int i = 0; i < outerIter; i++) {
			newSol = (ArrayList<Integer>) RMHC.setRMHC(innerIter).clone();//Uses Random Mutation Hill Climber, with the specific number of iterations previously generated, to create a new path

			double fitnessSol = Fitness.TSPFitnessFunction(newSol);//Calculates the fitness of every solution 
			double fitnessOldSol = Fitness.TSPFitnessFunction(oldSol);
			//Compare if the new solution is more efficient that the old solution. 
			if( fitnessSol < fitnessOldSol) {
				oldSol = (ArrayList<Integer>) newSol.clone();//If the new solution is better, it is copied into the old solution
			}
		}
		Paths.store(oldSol, "RRHC");//Stores the result path with the name of the method;
		return oldSol;//return the value in oldSol which is the most efficient path
	}
	//Calculates the inner and outer iterations
	public static int[] calculateIter(int iterations) {

		int[] allIter = new int[2];
		int numLength = (Integer.toString(iterations).length() - 1)/2;
		String numStr = "1";

		for(int i = 0; i < numLength; i++) {
			numStr = numStr + '0';	
		}

		allIter[0] = Integer.parseInt(numStr);//Inner Iterations
		allIter[1] = iterations/allIter[0];//Outer Iterations

		return allIter;
	}
	//Runs the algorithm 15 times and calculates the average
	public static double getFitness() {
		double sum = 0.0;
		for ( int i = 0; i < 15; i++) {	
			sum = sum + Fitness.TSPFitnessFunction(setRRHC());
		}
		sum = sum/15;
		return sum;	
	}

}
