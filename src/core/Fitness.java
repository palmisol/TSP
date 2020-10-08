package core;

import java.util.ArrayList;
import mst.MST;

public class Fitness {

	public static double TSPFitnessFunction(ArrayList<Integer> listPlaces) {

		int size = Variables.size;
		double[][] matrixPlaces = Variables.matrixPlaces;

		//number of cities to visit 
		ArrayList<Integer> t = listPlaces; //A tour (list of integers of size)
		// An N by N matrix containing each d(i,j)
		double s = 0;

		for (int i = 0; i < size - 1; i++){

			int a = t.get(i);
			int b = t.get(i+1);

			s = s + matrixPlaces[a][b];
		}

		int end_city = t.get(size - 1);
		int start_city = t.get(0);	

		s = s + matrixPlaces[end_city][start_city];

		return s;
	}
	//Return the efficiency
	public static double getEfficiency(double MST, double TSP) {
		return (MST/TSP)*100;
	}
	//Calculates the Fitness of the optimal values
	public static double getOptimal() {
		return TSPFitnessFunction(TSP.ReadIntegerFile("src/TSPdata/TSP_"+Variables.size+"_OPT.txt"));
	}
	//Returns both efficiencies in a double array. The first one the efficiency using the minimum spanning tree fitness and the second one using the optimal fitness (use in the GUI)
	public static double[] getEfficiencies(double tsp) {		
		double[] F = new double[2];//This array will storage both values 

		F[0] = Fitness.getEfficiency(MST.getMST(), tsp);//Calculate the efficiency using the minimum spanning tree
		//Check the index of the drop down menu. Just the 8 first values have a optimum path. 
		if (Variables.boxIndex < 8) {//If the user has selected any of the first 8 item then 
			F[1] = Fitness.getEfficiency(getOptimal(), tsp);
		}else {//If the user has selected any item above index 8, the efficiency will be 0
			F[1] = 0.0;
		}
		return F;
	}
}
