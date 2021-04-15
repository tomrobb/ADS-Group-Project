import java.util.*;
import java.io.*;

public class Main {
	
	// creating an arraylist of Stops, that holds the data from stops.txt
	public static ArrayList<Stops> stops = new ArrayList<Stops>();
	

	
	// main is currently only used to check that init_stops works
	public static void main(String[] args) {

		try {
			init_stops();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.print("Total Stops: "+ stops.size());
		

	}

	// function to initialise and populate the stops arraylist,
	public static void init_stops() throws FileNotFoundException {

		try {
			File stopsFile = new File("stops.txt");
			Scanner scanner = new Scanner(stopsFile);
			scanner.nextLine(); // skipping the "header" line

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				stops.add(new Stops(data));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred, File 'stops.txt' not found.");
		}

	}
}