package core;
import java.util.ArrayList;
import java.util.Collections;

import mst.CS2004;
//This class creates new Paths, shuffle existing paths and stores the most efficient path 
public class Paths {
	static ArrayList<Integer> listPlaces = new ArrayList<>();//Initialise an array list to store the path
	//Creates a new Path of size N
	public static ArrayList<Integer> newPath(int N) {

		listPlaces.clear();//Wipes all the content inside the array

		//This loop creates a list of numbers from 0 to the size of N which is the size of the dataset
		for (int j = 0 ; j < N; j++) {
			listPlaces.add(j);//Add every value to the array
		}
		return Shuffle(listPlaces);//Change the order of the values in the array
	}
	//Change the position of two values in the arrayList
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> smallChange (ArrayList<Integer> arrayPlaces) {	
		listPlaces.clear();//Wipes all the content inside the array
		listPlaces = (ArrayList<Integer>) arrayPlaces.clone();//Copy the arrayPlaces into listPlaces

		int temp;
		int i = 0;
		int j = 0;

		while(i == j) {
			i = CS2004.UI(1, listPlaces.size()-1);//Look for a random number between 1 and the size of the array
			j = CS2004.UI(1, listPlaces.size()-1);
		}
		temp = listPlaces.get(i);//Assign temp the number in index i 
		listPlaces.set(i, listPlaces.get(j));//change the number in index "i" for the number in index "j"
		listPlaces.set(j, temp);//Set temp as the number in index "j"
		return listPlaces;
	} 
	public static ArrayList<Integer> Shuffle (ArrayList<Integer> arrayPlaces){
		//arrayList.remove(0); Uncomment to create paths that start from 0
		Collections.shuffle(arrayPlaces); //Change the order of the values in the array
		//arrayList.add(0, 0);
		return arrayPlaces;
	}
	//Stores the best Fitness and its Path
	@SuppressWarnings("unchecked")
	public static void store(ArrayList<Integer> newSol, String method){

		ArrayList<Integer> oldSol = new ArrayList<Integer>();

		//During the first run oldSol is empty therefore is filled with newSol
		if(oldSol.isEmpty() == true) { 
			oldSol = (ArrayList<Integer>) newSol.clone();//Copy newSol into oldSol
		}else {
			double oldFitness = Fitness.TSPFitnessFunction(oldSol);//Calculate the fitness of both solutions
			double newFitness = Fitness.TSPFitnessFunction(newSol);
			//Compare which solution is better 
			//Store the best solution into oldSol
			if( newFitness < oldFitness) {
				oldSol = (ArrayList<Integer>) newSol.clone();//Copy the best solution	into oldSol
			}
		}	
		//Transfer both solutions to the variables class so any other class can access to them
		Variables.bestMethod = method;//Copies the Initial of the method
		Variables.bestPath = (ArrayList<Integer>) oldSol.clone();//Copies the path
	}
}
