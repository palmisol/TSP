package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


import core.Fitness;
import core.RMHC;
import core.RRHC;
import core.SA;
import core.SHC;
import core.TSP;
import core.Variables;
import mst.MST;


import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.SystemColor;

public class GUI {

	private JFrame frame;
	private JButton btnRMHC, btnRRHC, btnSHC, btnSA, btnStart;

	private JSpinner spinnerIter;

	private JComboBox<?> filesBox;
	String[] files = { "48", "51", "52" ,"70","76", "100", "105", "442", "No Optimal Solution", "101", "107", "124", "125", "130", "136", "144", "150"
			, "152", "159", "198", "200", "226", "262", "264", "299", "318", "400", "417", "439", "493", "532", "574"}; // Sizes of the data set

	int clicks = 0;
	int clicks1 = 0;
	int clicks2 = 0;
	int clicks3 = 0;
	
	//Result Labels
	private JLabel lblRmhc, lblRmhc_1, lblRmhc_2, lblRmhc_3, lblRmhc_4, lblRrhc, lblRrhc_1, lblRrhc_2, lblRrhc_3, lblRrhc_4,lblShc, lblShc_1,lblShc_2,lblShc_3,lblShc_4,lblSa,lblSa_1,lblSa_2,lblSa_3,lblSa_4;
	
	private JLabel lblT_1, lblTmin; //Labels Simulated Annealing
	private JLabel lblT; //Label Stochastic Hill Climber
	private JLabel lblIterations, lblSize;
	private JLabel lblMstresult;
	private JLabel lblOptresult;

	private JTextField tempSHC;
	private JTextField tempSA;
	private JTextField tempMinSA;


	private static DecimalFormat fitness = new DecimalFormat("#.0000"); 
	private static DecimalFormat efficiency = new DecimalFormat("#.00"); 
	private static DecimalFormat temperature = new DecimalFormat("#.00"); 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GUI() {

		initialize();
		labels();
		clearLabels();
		otherComponents();
		buttons();

		setInitialValues();

	}
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1143, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// The four lines that separate the four methods 
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 194, 507, 12);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(596, 421, 507, 12);
		frame.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(30, 421, 507, 12);
		frame.getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(596, 194, 507, 12);
		frame.getContentPane().add(separator_3);

	}
	//Set all the initial Values
	private void setInitialValues() {

		filesBox.setSelectedIndex(0);
		spinnerIter.setValue(100);

		setTemperature();

		tempMinSA.setText("0.00001");

	}
	//Read the values in the different components
	private void setValues() {


		Variables.size = Integer.parseInt(filesBox.getSelectedItem().toString());
		Variables.matrixPlaces = (TSP.ReadArrayFile("src/TSPdata/TSP_"+Variables.size+".txt", " "));

		Variables.iterations = (Integer) spinnerIter.getValue();

		Variables.temperatureSHC = Double.parseDouble(tempSHC.getText());

		Variables.temperatureSA = Double.parseDouble(tempSA.getText());
		Variables.temperatureMin = Double.parseDouble(tempMinSA.getText());


	}
	private void setTemperature() {

		tempSHC.setText(temperature.format(SHC.getTemperature()));
		Variables.temperatureSHC = SHC.getTemperature();

		tempSA.setText(temperature.format(SA.getTemperature()));
		Variables.temperatureSA = SA.getTemperature();

	}
	//Gathers all labels
	private void labels() {

		lblSize = new JLabel("Size");
		lblSize.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblSize.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblSize.setBounds(-10, 35, 61, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblSize);//add the label to the panel

		//These labels display the five results under every method. 
		////////////////RMHC/////////////////////////
		lblRmhc = new JLabel("RMHC1");
		lblRmhc.setFont(new Font("Lucida Grande", Font.PLAIN, 11)); //Text preferences
		lblRmhc.setBounds(30, 259, 520, 16); //(X, Y, Width, Height)
		frame.getContentPane().add(lblRmhc);//add the label to the panel

		lblRmhc_1 = new JLabel("RMHC2");
		lblRmhc_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRmhc_1.setBounds(30, 287, 520, 16);
		frame.getContentPane().add(lblRmhc_1);

		lblRmhc_2 = new JLabel("RMHC3");
		lblRmhc_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRmhc_2.setBounds(30, 315, 520, 16);
		frame.getContentPane().add(lblRmhc_2);

		lblRmhc_3 = new JLabel("RMHC4");
		lblRmhc_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRmhc_3.setBounds(30, 343, 520, 16);
		frame.getContentPane().add(lblRmhc_3);

		lblRmhc_4 = new JLabel("RMHC5");
		lblRmhc_4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRmhc_4.setBounds(30, 371, 520, 16);
		frame.getContentPane().add(lblRmhc_4);

		////////////////RRHC/////////////////////////
		lblRrhc = new JLabel("RRHC1");
		lblRrhc.setFont(new Font("Lucida Grande", Font.PLAIN, 11));//Text preferences
		lblRrhc.setBounds(596, 259, 507, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblRrhc);//add the label to the panel

		lblRrhc_1 = new JLabel("RRHC2");
		lblRrhc_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRrhc_1.setBounds(596, 287, 507, 16);
		frame.getContentPane().add(lblRrhc_1);

		lblRrhc_2 = new JLabel("RRHC3");
		lblRrhc_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRrhc_2.setBounds(596, 315, 507, 16);
		frame.getContentPane().add(lblRrhc_2);

		lblRrhc_3 = new JLabel("RRHC4");
		lblRrhc_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRrhc_3.setBounds(596, 343, 507, 16);
		frame.getContentPane().add(lblRrhc_3);

		lblRrhc_4 = new JLabel("RRHC5");
		lblRrhc_4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblRrhc_4.setBounds(596, 371, 507, 16);
		frame.getContentPane().add(lblRrhc_4);

		////////////////SHC/////////////////////////
		lblShc = new JLabel("SHC1");
		lblShc.setFont(new Font("Lucida Grande", Font.PLAIN, 11));//Text preferences
		lblShc.setBounds(596, 482, 507, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblShc);//add the label to the panel

		lblShc_1 = new JLabel("SHC2");
		lblShc_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblShc_1.setBounds(596, 510, 507, 16);
		frame.getContentPane().add(lblShc_1);

		lblShc_2 = new JLabel("SHC3");
		lblShc_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblShc_2.setBounds(596, 538, 507, 16);
		frame.getContentPane().add(lblShc_2);

		lblShc_3 = new JLabel("SHC4");
		lblShc_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblShc_3.setBounds(596, 566, 507, 16);
		frame.getContentPane().add(lblShc_3);

		lblShc_4 = new JLabel("SHC5");
		lblShc_4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblShc_4.setBounds(596, 594, 507, 16);
		frame.getContentPane().add(lblShc_4);

		////////////////SA/////////////////////////
		lblSa = new JLabel("SA1");
		lblSa.setFont(new Font("Lucida Grande", Font.PLAIN, 11));//Text preferences
		lblSa.setBounds(30, 482, 507, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblSa);//add the label to the panel

		lblSa_1 = new JLabel("SA2");
		lblSa_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSa_1.setBounds(30, 510, 507, 16);
		frame.getContentPane().add(lblSa_1);

		lblSa_2 = new JLabel("SA3");
		lblSa_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSa_2.setBounds(30, 538, 507, 16);
		frame.getContentPane().add(lblSa_2);

		lblSa_3 = new JLabel("SA4");
		lblSa_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSa_3.setBounds(30, 566, 507, 16);
		frame.getContentPane().add(lblSa_3);

		lblSa_4 = new JLabel("SA5");
		lblSa_4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblSa_4.setBounds(30, 594, 507, 16);
		frame.getContentPane().add(lblSa_4);

		//////////////////Other//////////////////////
		lblIterations = new JLabel("Iterations");
		lblIterations.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblIterations.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblIterations.setBounds(834, 35, 61, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblIterations);//add the label to the panel

		lblT = new JLabel("Temperature");
		lblT.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblT.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblT.setBounds(939, 441, 75, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblT);//add the label to the panel

		lblTmin = new JLabel("Min Temp");
		lblTmin.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblTmin.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblTmin.setBounds(388, 441, 61, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblTmin);//add the label to the panel

		lblT_1 = new JLabel("Temperature");
		lblT_1.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblT_1.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblT_1.setBounds(205, 441, 82, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblT_1);//add the label to the panel

		JLabel lblMst = new JLabel("MST");
		lblMst.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblMst.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblMst.setBounds(503, 35, 61, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblMst);

		JLabel lblOptimalSolution = new JLabel("Optimal Solution");
		lblOptimalSolution.setFont(new Font("Roboto", Font.PLAIN, 12));//Text preferences
		lblOptimalSolution.setHorizontalAlignment(SwingConstants.RIGHT);//Text preferences, alignment
		lblOptimalSolution.setBounds(447, 63, 117, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblOptimalSolution);//add the label to the panel

		lblMstresult = new JLabel("MSTresult");
		lblMstresult.setBounds(576, 35, 132, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblMstresult);//add the label to the panel

		lblOptresult = new JLabel("OPTresult");
		lblOptresult.setBounds(576, 63, 132, 16);//(X, Y, Width, Height)
		frame.getContentPane().add(lblOptresult);//add the label to the panel
	}
	//Gathers all buttons
	private void buttons() {

		btnStart = new JButton("START"); // Set text to be displayed 
		btnStart.setFont(new Font("Lucida Grande", Font.PLAIN, 17)); //Text preferences 
		btnStart.setForeground(SystemColor.blue); //Text colour
		btnStart.addActionListener(new ActionListener() { //Action to be performed when clicked
			public void actionPerformed(ActionEvent e) {

				System.out.println("Start button pressed"); // Print Start button
				clearLabels(); // Clear all labels 
				setValues(); // Set Values
				printValues(); // Print the values set

				lblRmhc.setText(RMHCFitness()); // Calculate every fitness and efficiency
				lblRrhc.setText(RRHCFitness());
				lblShc.setText(SHCFitness());
				lblSa.setText(SAFitness());

				printBestPath(); //Print the best path
			}
		});
		btnStart.setBorderPainted(false); // Deactivate borders
		btnStart.setBounds(514, 123, 117, 29); //(X, Y, Width, Height)
		frame.getContentPane().add(btnStart); //add the button to the panel 


		btnRMHC = new JButton("Random Mutation Hill Climber"); // Set text to be displayed 
		btnRMHC.setHorizontalAlignment(SwingConstants.LEFT); //Text preferences, alignment 
		btnRMHC.setForeground(SystemColor.blue);//Text colour
		btnRMHC.addActionListener(new ActionListener() { //Action to be performed when clicked
			public void actionPerformed(ActionEvent e) {

				System.out.println("RMHC button pressed"); // Check the button has been pressed
				setValues(); // Set Values
				printValues(); // Print the values set

				clicks++; //Counts the number of clicks

				switch (clicks){
				case 1:
					lblRmhc_1.setText(RMHCFitness()); //Displays the results in the following label 
					printBestPath(); //Print the best path in the console 
					break;
				case 2:
					lblRmhc_2.setText(RMHCFitness());
					printBestPath();
					break;
				case 3:	
					lblRmhc_3.setText(RMHCFitness());
					printBestPath();
					break;
				case 4:
					lblRmhc_4.setText(RMHCFitness());
					printBestPath();
					break;
				case 5:
					lblRmhc.setText(RMHCFitness());
					printBestPath();
					clicks = 0;
					break;
				default:
					clicks = 0;
					break;
				}

			}
		});
		btnRMHC.setBorderPainted(false); //Deactivate borders
		btnRMHC.setBounds(30, 218, 242, 29); //(X, Y, Width, Height)
		frame.getContentPane().add(btnRMHC); //add the button to the panel 

		btnRRHC = new JButton("Random Restart Hill Climber");// Set text to be displayed 
		btnRRHC.setHorizontalAlignment(SwingConstants.LEFT);//Text preferences, alignment 
		btnRRHC.setForeground(SystemColor.blue);//Text colour
		btnRRHC.addActionListener(new ActionListener() {//Action to be performed when clicked
			public void actionPerformed(ActionEvent e) {

				System.out.println("RRHC button pressed");// Check the button has been pressed
				setValues();// Set Values
				printValues();// Print the values set

				clicks1++;//Counts the number of clicks

				switch (clicks1){
				case 1:
					lblRrhc_1.setText(RRHCFitness());//Displays the results in the following label 
					printBestPath();//Print the best path in the console 
					break;
				case 2:
					lblRrhc_2.setText(RRHCFitness());
					printBestPath();
					break;
				case 3:	
					lblRrhc_3.setText(RRHCFitness());
					printBestPath();
					break;
				case 4:
					lblRrhc_4.setText(RRHCFitness());
					printBestPath();
					break;
				case 5:
					lblRrhc.setText(RRHCFitness());
					printBestPath();
					clicks1 = 0;
					break;
				default:
					clicks1 = 0;
					break;
				}


			}
		});
		btnRRHC.setBorderPainted(false);//Deactivate borders
		btnRRHC.setBounds(596, 218, 242, 29);//(X, Y, Width, Height)
		frame.getContentPane().add(btnRRHC);//add the button to the panel 

		btnSHC = new JButton("Stochastic Hill Climber");// Set text to be displayed 
		btnSHC.setHorizontalAlignment(SwingConstants.LEFT);//Text preferences, alignment 
		btnSHC.setForeground(SystemColor.blue);//Text colour
		btnSHC.addActionListener(new ActionListener() {//Action to be performed when clicked
			public void actionPerformed(ActionEvent e) {

				System.out.println("SHC button pressed");// Check the button has been pressed
				setValues();// Set Values
				printValues();// Print the values set

				clicks2++;//Counts the number of clicks

				switch (clicks2){
				case 1:
					lblShc_1.setText(SHCFitness());//Displays the results in the following label 
					printBestPath();//Print the best path in the console 
					break;
				case 2:
					lblShc_2.setText(SHCFitness());
					printBestPath();
					break;
				case 3:	
					lblShc_3.setText(SHCFitness());
					printBestPath();
					break;
				case 4:
					lblShc_4.setText(SHCFitness());
					printBestPath();
					break;
				case 5:
					lblShc.setText(SHCFitness());
					printBestPath();
					clicks2 = 0;
					break;
				default:
					clicks2 = 0;
					break;

				}
			}
		});
		btnSHC.setBorderPainted(false);//Deactivate borders
		btnSHC.setBounds(596, 441, 188, 29);//(X, Y, Width, Height)
		frame.getContentPane().add(btnSHC);//add the button to the panel 

		btnSA = new JButton("Simulated Annealing");// Set text to be displayed 
		btnSA.setHorizontalAlignment(SwingConstants.LEFT);//Text preferences, alignment
		btnSA.setForeground(SystemColor.blue);//Text colour
		btnSA.addActionListener(new ActionListener() {//Action to be performed when clicked
			public void actionPerformed(ActionEvent e) {

				System.out.println("SA button pressed");// Check the button has been pressed
				setValues();// Set Values
				printValues();// Print the values set

				clicks3++;//Counts the number of clicks

				switch (clicks3){
				case 1:
					lblSa_1.setText(SAFitness());//Displays the results in the following label 
					printBestPath();//Print the best path in the console 
					break;
				case 2:
					lblSa_2.setText(SAFitness());
					printBestPath();
					break;
				case 3:	
					lblSa_3.setText(SAFitness());
					printBestPath();
					break;
				case 4:
					lblSa_4.setText(SAFitness());
					printBestPath();
					break;
				case 5:
					lblSa.setText(SAFitness());
					printBestPath();
					clicks3 = 0;
					break;
				default:
					clicks3 = 0;
					break;
				}
			}
		});
		btnSA.setBorderPainted(false);//Deactivate borders
		btnSA.setBounds(30, 441, 179, 29);//(X, Y, Width, Height)
		frame.getContentPane().add(btnSA);//add the button to the panel
	}
	//JText, JComboBox and JSpinner 
	private void otherComponents(){	

		spinnerIter = new JSpinner();//Contains the number of Iterations
		spinnerIter.setBounds(907, 32, 196, 26);//(X, Y, Width, Height)
		frame.getContentPane().add(spinnerIter);

		filesBox = new JComboBox<Object>(files);//Drop-down menu with all the datasets
		filesBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//The 10th item does not contain anything therefore if selected, the app resets all the values
				//The 10th value is "No Optimal Solution"
				if (filesBox.getSelectedIndex() == 10) {
					setInitialValues();//Set initial values
				}
				//Else.....

				Variables.size = Integer.parseInt(filesBox.getSelectedItem().toString());//Set the size to the selected number in the drop down menu
				Variables.matrixPlaces = (TSP.ReadArrayFile("src/TSPdata/TSP_"+Variables.size+".txt", " "));//Load the data set into the double array
				Variables.boxIndex = filesBox.getSelectedIndex();//Save the selected index

				setTemperature(); // Calculate the best temperature
				showMSTandOPT(); // Display the Minimum Spanning tree and the Optimal Fitness
			}
		});
		filesBox.setBounds(63, 31, 209, 27);//(X, Y, Width, Height)
		frame.getContentPane().add(filesBox);

		//JtextFiedls are used to input temperatures so the user can input any type of number
		tempSHC = new JTextField();
		tempSHC.setBounds(1026, 436, 76, 26);//(X, Y, Width, Height)
		frame.getContentPane().add(tempSHC);
		tempSHC.setColumns(10);

		tempSA = new JTextField();
		tempSA.setBounds(297, 436, 76, 26);//(X, Y, Width, Height)
		frame.getContentPane().add(tempSA);
		tempSA.setColumns(10);

		tempMinSA = new JTextField();
		tempMinSA.setBounds(461, 436, 76, 26);//(X, Y, Width, Height)
		frame.getContentPane().add(tempMinSA);
		tempMinSA.setColumns(10);
	}
	//Display the MST Fitness and the Optimal Fitness
	private void showMSTandOPT(){
		//Check that the selected item in the drop down menu is of index less than 8 due to those values have an optimal path
		if (filesBox.getSelectedIndex() < 8) {
			lblOptresult.setText(fitness.format(Fitness.getOptimal()));//Calculate the optimal fitness
		}else {
			lblOptresult.setText("Not Found");//If index greater than 8 display "Not found"
		}

		double resultMST = MST.getMST(); //Calculate the minimum Spanning tree
		lblMstresult.setText(fitness.format(resultMST));//Display the value and format it to four decimal places
	}
	//These methods calculate the fitness and efficiency and put them into a string which will be displayed in a label. 
	private String RRHCFitness() {

		double F = RRHC.getFitness();//Get the average fitness	
		double[] E = Fitness.getEfficiencies(F);//Get both efficiencies
		//Return a string in the formal - X.000 • MST= Y.00% OPT= Z.00%
		return fitness.format(F) + " | " +"MST= "+ efficiency.format(E[0])+"%"+" OPT= "+efficiency.format(E[1])+"%";
	}
	private String RMHCFitness() {

		double F = RMHC.getFitness();//Get the average fitness	
		double[] E = Fitness.getEfficiencies(F);//Get both efficiencies
		//Return a string in the formal - X.000 • MST= Y.00% OPT= Z.00%
		return fitness.format(F) + " | " +"MST= "+ efficiency.format(E[0])+"%"+" OPT= "+efficiency.format(E[1])+"%";
	}
	private String SHCFitness() {

		double F = SHC.getFitness();//Get the average fitness	
		double[] E = Fitness.getEfficiencies(F);//Get both efficiencies
		double T = Variables.temperatureSHC;//Get the temperature from the variables class (previously set during setValues())
		//Return a string in the formal - X.000 • MST= Y.00% OPT= Z.00% T= 0.00˚
		return fitness.format(F) + " | " +" MST= "+ efficiency.format(E[0])+"%"+" OPT= "+efficiency.format(E[1])+"%"+" T= "+temperature.format(T);
	}
	private String SAFitness() {

		double F = SA.getFitness();//Get the average fitness	
		double[] E = Fitness.getEfficiencies(F);//Get both efficiencies
		double T = Variables.temperatureSA;//Get the temperature from the variables class (previously set during setValues())
		double Tmin = Variables.temperatureMin;//Get the temperature from the variables class (previously set during setValues())
		//Return a string in the formal - X.000 • MST= Y.00% OPT= Z.00% T= 0.00˚ Tmin= 0.00
		return fitness.format(F) + " | " +"MST= "+ efficiency.format(E[0])+"%"+" OPT= "+efficiency.format(E[1])+"%"+" T= "+temperature.format(T)+" Tmin= "+Tmin;
	}
	//Set a "-" symbol in every label	
	private void clearLabels() {

		lblRmhc.setText("-"); 
		lblRmhc_1.setText("-");  
		lblRmhc_2.setText("-");  
		lblRmhc_3.setText("-");  
		lblRmhc_4.setText("-"); 
		lblRrhc.setText("-");  
		lblRrhc_1.setText("-");  
		lblRrhc_2.setText("-");  
		lblRrhc_3.setText("-");  
		lblRrhc_4.setText("-"); 
		lblShc.setText("-"); 
		lblShc_1.setText("-"); 
		lblShc_2.setText("-"); 
		lblShc_3.setText("-");
		lblShc_4.setText("-"); 
		lblSa.setText("-"); 
		lblSa_1.setText("-"); 
		lblSa_2.setText("-"); 
		lblSa_3.setText("-"); 
		lblSa_4.setText("-"); 

	}
	//Print Values 
	private void printValues() {
		System.out.println("------------------------------------------------");
		System.out.println("Temperature SHC = "+Variables.temperatureSHC);
		System.out.println("Temperature SA = "+Variables.temperatureSA);
		System.out.println("Min Temperature SA = "+Variables.temperatureMin);
		System.out.println("------------------------------------------------");
	}
	private void printBestPath() {

		System.out.println("------------------------------------------------");
		System.out.println("Best Path");
		System.out.println(Variables.bestPath.toString());
		System.out.println("Calculated by "+Variables.bestMethod+" with Fitness= "+ Fitness.TSPFitnessFunction(Variables.bestPath));
		System.out.println("------------------------------------------------");

	}
}
