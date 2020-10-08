package core;
import java.util.ArrayList;
//Random Mutation Hill Climber
public class RMHC {
	private static ArrayList<Integer> oldSol = new ArrayList<Integer>();
	private static ArrayList<Integer> newSol = new ArrayList<Integer>();
	//Empty both arrays
	public static void clearArrays() {	
		newSol.clear();
		oldSol.clear();		
	}
	//Code for the Random Mutation Hill Climber
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> setRMHC(int iterations){
		clearArrays();//Empty both arrays
			
		oldSol = (ArrayList<Integer>) Paths.newPath(Variables.size).clone();//Generates a new random path which is copied into oldSol

		for(int i = 0; i < iterations ; i++) {

			newSol = (ArrayList<Integer>) Paths.smallChange(oldSol).clone();//Applies a small change to the generated path 

			double fitnessSol = Fitness.TSPFitnessFunction(newSol);//calculates the fitness of the old and the new solution
			double fitnessoldSol = Fitness.TSPFitnessFunction(oldSol);
			//Compare if the new solution is more efficient that the old solution. 
			if( fitnessSol < fitnessoldSol) {
				oldSol = (ArrayList<Integer>) newSol.clone();//If the new solution is better, it is copied into the old solution
			}
		}
		Paths.store(oldSol, "RMHC");//Stores the result path with the name of the method;
		return oldSol;//return the value in oldSol which is the most efficient path
	}
	//Runs the algorithm 15 times and calculates the average
	public static double getFitness() {
		double sum = 0.0;
		for ( int i = 0; i < 15; i++) {	
			sum = sum + Fitness.TSPFitnessFunction(setRMHC(Variables.iterations));
		}
		sum = sum/15;
		return sum;
	}
}
