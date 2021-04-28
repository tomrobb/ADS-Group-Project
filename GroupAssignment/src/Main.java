import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


	public static ArrayList<Stops> stops = new ArrayList<Stops>();
	public static ArrayList<String> listOfResults = new ArrayList<String>();
	public static ArrayList<Transfer> transfers = new ArrayList<Transfer>();
	public static ArrayList<StopTimes> stopTimes = new ArrayList<StopTimes>();
	public static TST tst = new TST();
	
	
//	public static ArrivalTimes ATs = new ArrivalTimes();

	public static void main(String[] args) throws FileNotFoundException, IOException 

	{

		try {
			init_stops();
			initStopTimes();
			initTransfer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		populate_TST();







		Scanner inputScanner = new Scanner(System.in);
		boolean exit=false;
		while(exit==false)
		{
			System.out.println("Please select the functions you want to use by typing their corresponding Number. The available options are:");
			System.out.println("1. Shortest Path between 2 Stops ");
			System.out.println("2. Search for a Bus Stop ");
			System.out.println("3. Search for All Trips ");
			System.out.println("4. Exit the Program");
			System.out.println("Type your Answer here:");
			//			Scanner inputScanner = new Scanner(System.in);


			if(inputScanner.hasNextLine())
			{
				String inputFunction = inputScanner.nextLine();

				if(inputFunction.equals("4"))//Exit the Program
				{
					exit=true;
					System.out.println("Program is now exiting.");
					//					inputScanner.close(); 
					break;
				}
				else if(inputFunction.equals("1"))//Shortest Paths between 2 Stops
				{




					try
					{

						System.out.println("Input the unique stop Id for the starting destination, press enter and then do the same for the end destination");
						String start=inputScanner.nextLine();;
						String stop=inputScanner.nextLine();;


						int startInt =Integer.parseInt(start);
						int stopInt = Integer.parseInt(stop);
						ShortestPath SP = new ShortestPath();
						Stops startStop = null;
						Stops stopStop=null;
						boolean errorOccurred=false;




						for(int i=0;i<stops.size();i++)
						{
							if(stops.get(i).stop_id == startInt)
							{
								System.out.println("Got Start");
								startStop=stops.get(i);
							}
							if(stops.get(i).stop_id == stopInt)
							{
								System.out.println("Got Stop");
								stopStop=stops.get(i);
							}

						}

						BusGraph bus= new BusGraph(stops, transfers, stopTimes);
						SP=bus.getShortestPath(startStop, stopStop);

						if(SP.getWeight() <0)
						{
							errorOccurred=true; 
						}



						if(SP.getWeight()==-1 &&errorOccurred==false)
						{
							System.out.println("Shortest Path does not exist");
						}
						else
						{
							System.out.println("Shortest Path between these two is of length:"+SP.getWeight());
							System.out.println("Journey takes the following Path, beginning at the start stop:");



							String[] output = new String[7];

							for(int j=SP.stops.size()-1;j>=0;j--)
							{

								System.out.println("\n Moves to:\n");
								output[0] = "Name: " + toTitleCase(SP.stops.get(j).stop_name);
								output[1] = "  -Description: " + toTitleCase(SP.stops.get(j).stop_desc);
								output[2] = "  -Stop ID: " +SP.stops.get(j).stop_id;
								output[3] = "  -Stop Code: " + SP.stops.get(j).stop_code;
								output[4] = "  -Latitude: " + SP.stops.get(j).stop_lat;
								output[5] = "  -Longitude: " + SP.stops.get(j).stop_lon;
								output[6] = "  -Zone ID: " + SP.stops.get(j).zone_id;


								for (int k = 0; k < output.length; k++)
								{
									System.out.println(output[k]);
								}
							}

							System.out.println("\n=-=-=-=\n");
							System.out.println("Final Destination:");

							output[0] = "Name: " + toTitleCase(stopStop.stop_name);
							output[1] = "  -Description: " + toTitleCase(stopStop.stop_desc);
							output[2] = "  -Stop ID: " +stopStop.stop_id;
							output[3] = "  -Stop Code: " + stopStop.stop_code;
							output[4] = "  -Latitude: " + stopStop.stop_lat;
							output[5] = "  -Longitude: " + stopStop.stop_lon;
							output[6] = "  -Zone ID: " + stopStop.zone_id;


							for (int k = 0; k < output.length; k++)
							{
								System.out.println(output[k]);
							}

						}



					} catch ( java.lang.NumberFormatException e) {
						System.out.println("Error occurred due to incorrect Input");
					}
					catch(java.lang.NullPointerException e)
					{
						System.out.println("Error occurred due to incorrect Input");
					}
				}

				else if(inputFunction.equals("2"))//Search for a Bus Stop
				{

					System.out.print("Enter the stop to search for: ");
					String searchKey = inputScanner.nextLine();

					System.out.println("\nSearching for: " + searchKey);

					// Autocompletes the given input string, results are stored in the ArrayList
					listOfResults = tst.autocomplete(searchKey.toUpperCase());
					// Printing out the # of results found
					if (listOfResults != null) {
						System.out.println("Results Found: " + listOfResults.size() + "\n");
					} else
						System.out.println("No Results found.");

					String[] currentData;
					// if the arraylist is not empty:
					if (listOfResults != null) {

						// for every result in the ArrayList of Search Results, we want to print it's data
						for (int i = 0; i < listOfResults.size(); i++) {

							System.out.println("\n=-=-=-=\n");
							System.out.println((i + 1) + ".) " + listOfResults.get(i));

							// getting raw data from the stop name
							currentData = returnStopData(listOfResults.get(i));
							// formatting that data to look pretty
							currentData = formatData(currentData);

							// printing all of the formatted data for a stop
							for (int k = 0; k < currentData.length; k++) {
								System.out.println(currentData[k]);
							}
						}
					}
				}
				else if(inputFunction.equals("3"))//Search for All Trips
				{
					
					System.out.println("Input the specific time you want to search for in the format: hh:mm:ss   An example would be: 5:25:50");
					String inputTime=inputScanner.nextLine();;
					
					ArrayList<String> arrivalTimesList = new ArrayList<String>();
					arrivalTimesList=ArrivalTimes.tripInfo(inputTime);
					
					for (int i = 0; i < arrivalTimesList.size(); i++) {

						System.out.println(arrivalTimesList.get(i));
					}

				}
				else
				{
					System.out.println("This is not a valid number input");
				}

			}
			
			 
		}
		inputScanner.close(); 

		System.out.println("Program has now exited");
	}


	// Function to initialise the Stops class, using the input file "stops.txt"
	//
	//
	public static void init_stops() throws FileNotFoundException {
		try {
			File stopsFile = new File("stops.txt");
			Scanner scanner = new Scanner(stopsFile);
			// Skipping the first line, since it's a header and has no data
			scanner.nextLine();

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				stops.add(new Stops(data));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}
	}

	/**
	 * Initialise the transfer array list
	 */
	public static void initTransfer() {
		try {
			File transferFile = new File("transfers.txt");
			Scanner fileScanner = new Scanner(transferFile);
			// skip the first line
			fileScanner.nextLine();
			while (fileScanner.hasNextLine()) {
				String newLine = fileScanner.nextLine();
				transfers.add(new Transfer(newLine));
			}
			fileScanner.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found.");
		}
	}

	/**
	 * Initialise the stop times array list
	 */
	public static void initStopTimes() {
		try {
			File timesFile = new File("stop_times.txt");
			Scanner fileScanner = new Scanner(timesFile);
			fileScanner.nextLine();
			while (fileScanner.hasNextLine()) {
				String newLine = fileScanner.nextLine();
				stopTimes.add(new StopTimes(newLine));
			}
			fileScanner.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found.");
		}
	}




	// Function for populating the Ternary Search Tree
	//
	//
	public static void populate_TST() {
		// Goes through every Stop class...
		for (int i = 0; i < stops.size(); i++) {
			// ... and inserts the name of every stop into the TST, so we can use
			// autocomplete later.
			tst.insert(stops.get(i).getName());

		}
	}



	// Function to return the Stop data, given the Stop name
	// 	parameter: The Exact Name of the Stop
	//	return: An array of Strings of length 7, with the various data stored in each
	//
	//
	public static String[] returnStopData(String name) {
		// The string we will be outputting
		String[] outputData = new String[7];

		for (int i = 0; i < stops.size(); i++) {

			if (name.equalsIgnoreCase(stops.get(i).getName())) {
				outputData[0] = stops.get(i).getID();
				outputData[1] = stops.get(i).getCode();
				outputData[2] = stops.get(i).getName();
				outputData[3] = stops.get(i).getDesc();
				outputData[4] = stops.get(i).getLat();
				outputData[5] = stops.get(i).getLon();
				outputData[6] = stops.get(i).getZone();
			}

		}
		return outputData;
	}



	// Function to format the Array of raw stop data
	// it basically just reads in the returned stop data, reorders and formats it, so that
	// it can be displayed in the console.
	//	parameter: Array of Strings, from returnStopData
	// 	return: A rearranged, formatted Array of Strings ready to be displayed.
	//
	//
	public static String[] formatData(String[] stopData) {
		String[] output = new String[7];

		// changing the order of the outputs to look cleaner together
		output[0] = "Name: " + toTitleCase(stopData[2]);
		output[1] = "  -Description: " + toTitleCase(stopData[3]);
		output[2] = "  -Stop ID: " + stopData[0];
		output[3] = "  -Stop Code: " + stopData[1];
		output[4] = "  -Latitude: " + stopData[4];
		output[5] = "  -Longitude: " + stopData[5];
		output[6] = "  -Zone ID: " + stopData[6];
		return output;
	}


	// Function which makes every word start with a capital letter
	//	parameter: 	string to be converted into title case
	//	return:			String Which Is Now Capitalised.
	//
	//
	public static String toTitleCase(String name) {
		if (name != null) {

			name = name.toLowerCase(); // because this function only works on lower case
			char[] charArray = name.toCharArray();
			boolean foundSpace = true;

			for (int i = 0; i < charArray.length; i++) {
				// if the array element is a letter
				if (Character.isLetter(charArray[i])) {
					// check space is present before the letter
					if (foundSpace) {
						// change the letter into uppercase
						charArray[i] = Character.toUpperCase(charArray[i]);
						foundSpace = false;
					}
				}
				else {
					// if the new character is not character
					foundSpace = true;
				}
			}
			// convert the char array to the string
			name = String.valueOf(charArray);
			return name;
		} else
			return name;
	}

}








