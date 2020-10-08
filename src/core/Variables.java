package core;
import java.util.ArrayList;
//This class contains the main variables 
public class Variables {
	public static int size;//Size of the data set
	public static double[][] matrixPlaces = new double [size][size];//Double array which contains all the distances 
	public static double temperatureSHC;//Temperature of Stochastic Hill Climber
	public static double temperatureSA;//Initial Simulated Annealing Temperature
	public static double temperatureMin;//Minimum Temperature Simulated Annealing
	public static int iterations;//Number of repetitions
	public static int boxIndex;//Drop down menu index (used for when calculating Efficiency using the optimal path)
	public static ArrayList<Integer> bestPath = new ArrayList<Integer>();//The most efficient path
	public static String bestMethod = "";//Initials of the method which performed the bestPath
}
