package core;

import mst.MST;

public class ManualRun {
	//This class is used to test the code through a variety of data sizes and iterations

	static int[] iterationsArray = {10, 100, 1000, 10000, 100000, 1000000}; 
	//static int[] sizes = {48,76,124,130,150,159,299,442};
	static int[] sizes = {48,51,52,70,76,100,101,105,107,124,125,130,136,144,150,152,159,198,200,226,262,299,318,400,417,439,442};
	static int[] optSizes = {48,51,52,70,76,100,442};
	static double optFitness = 0.0;

	public static void main(String[] args) {

		Variables.iterations = 1000000; // Comment iterations and uncomment test iterations to test the efficiency in various data set at different iterations

		//efficiencyOPT(); // Uncomment to test efficiency using fitness from the optimal path
		//efficiencyMST(); //Uncomment to test efficiency using fitness from MST
		//averageDistance(); //Calculate the average distance from every dataset
		//testIterations(); 
		//path(); //Display the best path
	}
	private static void setOptData(int i) {

		Variables.size = optSizes[i];
		Variables.matrixPlaces = TSP.ReadArrayFile("src/TSPdata/TSP_"+optSizes[i]+".txt", " ");
		optFitness = Fitness.TSPFitnessFunction(TSP.ReadIntegerFile("src/TSPdata/TSP_"+optSizes[i]+"_OPT.txt"));

		setTemperature();
	}
	private static void setData(int i){

		Variables.size = sizes[i];
		Variables.matrixPlaces = TSP.ReadArrayFile("src/TSPdata/TSP_"+sizes[i]+".txt", " ");	

		setTemperature();
	}
	private static void setTemperature() {

		Variables.temperatureSHC = SHC.getTemperature();

		//		Variables.iterations = 1000000;

		Variables.temperatureMin = 0.00001;
		Variables.temperatureSA = SA.getTemperature();

	}
	private static void efficiencyMST(){

		for ( int i = 0; i < sizes.length; i++) {
			setData(i);
		
			System.out.println("______________________________________________________");
			System.out.println("Size " + Variables.size);
			System.out.println(Fitness.getEfficiency(MST.getMST(), SA.getFitness()));
			System.out.println(Fitness.getEfficiency(MST.getMST(), SHC.getFitness()));
			System.out.println(Fitness.getEfficiency(MST.getMST(), RMHC.getFitness()));
			System.out.println(Fitness.getEfficiency(MST.getMST(), RRHC.getFitness()));
			System.out.println("______________________________________________________");

		}
	}
	private static void efficiencyOPT(){

		for ( int i = 0; i < optSizes.length; i++) {

			setOptData(i);

			System.out.println("______________________________________________________");
			System.out.println("Size " + Variables.size);
			System.out.println(Fitness.getEfficiency(optFitness, SA.getFitness()));
			System.out.println(Fitness.getEfficiency(optFitness, SHC.getFitness()));
			System.out.println(Fitness.getEfficiency(optFitness, RMHC.getFitness()));
			System.out.println(Fitness.getEfficiency(optFitness, RRHC.getFitness()));
			System.out.println("______________________________________________________");

		}
	}
	private static void averageDistance(){
		for ( int i = 0; i < sizes.length; i++) {
			setData(i);
			System.out.println(TSP.getAverageDistance());
		}
	}
	private static void testIterations() {

		for ( int i = 0; i < sizes.length; i++) {
			System.out.println(sizes[i]+"--------------------------");
			for ( int j = 0; j < iterationsArray.length; j++) {
				setData(i);
				Variables.iterations = iterationsArray[j];
				
				System.out.println("______________________________________________________");
				System.out.println(Variables.iterations);
				System.out.println(Fitness.getEfficiency(MST.getMST(), SA.getFitness()));
				System.out.println(Fitness.getEfficiency(MST.getMST(), SHC.getFitness()));
				System.out.println(Fitness.getEfficiency(MST.getMST(), RMHC.getFitness()));
				System.out.println(Fitness.getEfficiency(MST.getMST(), RRHC.getFitness()));
				System.out.println("______________________________________________________");
			}
		}
	}
	private static void path() {

		setData(0);
		Variables.iterations = 1000000;

		System.out.println("RMHC = "+RRHC.getFitness());
		System.out.println(RMHC.getFitness());
		System.out.println(SHC.getFitness());
		System.out.println(SA.getFitness());
		System.out.println("----------------------------------");
		System.out.println(Variables.bestPath.toString());
		System.out.println(Fitness.TSPFitnessFunction(Variables.bestPath));
		System.out.println(Variables.bestMethod);

	}
}
