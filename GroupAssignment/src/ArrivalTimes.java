import java.io.*;
import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class ArrivalTimes {

// converts file to arraylist	
	public static ArrayList<String> fileToArrayList(String filename) throws FileNotFoundException, IOException {

		ArrayList<String> result = new ArrayList<>();

		try (BufferedReader f = new BufferedReader(new FileReader(filename))) {
			while (f.ready()) {
				result.add(f.readLine());
			}
		}
		return result;
	}

	/*
	 * param: user input of hh:mm:ss returns Arraylist of information
	 */
	public static ArrayList<String> tripInfo(String timeOfArrival) throws FileNotFoundException, IOException {

		ArrayList<String> results = new ArrayList<String>();
		// LocalTime time = LocalTime.parse(timeOfArrival);

		ArrayList<String> fullList = fileToArrayList("stop_times.txt");

		// removing invalid hours
		for (int i = 1; i < fullList.size(); i++) {
			try {
				String count = fullList.get(i);
				StopTimes StopTimeobject = new StopTimes(count);
				LocalTime checkTime = StopTimeobject.getArrivalTime();
				int hour = checkTime.getHour();

				if (hour > 24) {
					fullList.remove(i);
				}
			} catch (Exception e) {
			}
			;
		}

		// looping list to find arrival times
		for (int i = 1; i < fullList.size(); i++) {

			String index = fullList.get(i);
			StopTimes StopTimeobject = new StopTimes(index);
			// LocalTime busTime =StopTimeobject.getArrivalTime();
			String busTime = StopTimeobject.getArrivalTimeString();

			try {
				if (timeOfArrival.equalsIgnoreCase(busTime)) {

					// LocalTime dep =StopTimeobject.getDepartureTime();
					// String depTime =dep.toString();
					String depTime = StopTimeobject.getDepartureTimeString();

					String info = ""
							+ Integer.toString(StopTimeobject.getTripId())
							+ "\n | Stop Name: "+ Main.returnStopName(Integer.toString(StopTimeobject.getStopId())) 
							+ "\n | Stop id: "+ Integer.toString(StopTimeobject.getStopId()) 
							+ "\n | Departure time: " + depTime 
							+ "\n | Stop Sequence: "+ Integer.toString(StopTimeobject.getStopSequence()) 
							+ "\n | Pick-up type:  "+ Integer.toString(StopTimeobject.getPickUpType()) 
							+ "\n | Drop-off type: "+ Integer.toString(StopTimeobject.getDropOffType()) 
							+ "\n | Distance: "+ Double.toString(StopTimeobject.getDistance());

					results.add(info);
				} else {

				}

			} catch (Exception e) {
			}
			;
		}
		Collections.sort(results);
		return results;
	}

}