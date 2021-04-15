// Stops class, which holds the values for each stop, from stops.txt
public class Stops {

	// there are other values in stops.txt but they're empty, like stop_url and parent_station,
	// so we dont include them.
	int stop_id;
	int stop_code;
	String stop_name;
	String stop_desc;
	double stop_lat;
	double stop_lon;
	String zone_id;

	// Constructor for Stops
	// input:
	// stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station
	public Stops(String inputLine) {

		
		
		String[] inputValues = inputLine.split(",", 0);
		
		// There are try/catch loops around each parse instruction, because not all lines of stops.txt
		// have valid values, and it causes errors if we try to parse a string with no numbers, as a
		// double or integer.
		
		
		// if the String can not be parsed as an integer, we set stop_id to be = -1;
		try {
			this.stop_id = Integer.parseInt(inputValues[0]);
		}catch (Exception e){
			this.stop_id = -1;
		}

		// if the String can not be parsed as an integer, we set stop_code to be = -1;
		try {
			this.stop_code = Integer.parseInt(inputValues[1]);
		} catch (Exception e) {
			this.stop_code = -1;
		}

		// moving the keywords to the end of the stop name, to save the hassle later.
		this.stop_name = moveKeywords(inputValues[2]);
		
		this.stop_desc = inputValues[3];

		// if the String can not be parsed as an Double, we set stop_lat to be = -1;
		try {
			this.stop_lat = Double.parseDouble(inputValues[4]);
		} catch (Exception e) {
			this.stop_id = -1;
		}
		// if the String can not be parsed as an Double, we set stop_lon to be = -1;
		try {
			this.stop_lon = Double.parseDouble(inputValues[5]);
		} catch (Exception e) {
			this.stop_id = -1;
		}

		this.zone_id = inputValues[6];

		
		// These print statements are only for checking that the values are assigned correctly
		System.out.println("Done Adding the Values for Stops");
		System.out.println("Stop ID: " + stop_id);
		System.out.println("Stop code: " + stop_code);
		System.out.println("Stop Name: " + stop_name);
		System.out.println("Stop Description: " + stop_desc);
		System.out.println("Stop Latitude: " + stop_lat);
		System.out.println("Stop Longitude: " + stop_lon);
		System.out.println("Zone ID: " + zone_id + "\n\n");

	}

	// move keywords: wb, nb, sb, eb from start of the names to the end of the names
	// of the stops
	// note: substring(3) gets rid of the first 3 characters in a string
	private String moveKeywords(String input) {

		// for keyword WB
		if (input.charAt(0) == 'W' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " WB";
		}
		// for keyword NB
		if (input.charAt(0) == 'N' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " NB";
		}
		// for keyword WB
		if (input.charAt(0) == 'S' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " SB";
		}
		// for keyword WB
		if (input.charAt(0) == 'E' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " EB";
		} else
			return input;

	}

}
