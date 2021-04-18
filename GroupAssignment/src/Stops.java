// Stops class, which holds the values for each stop, from stops.txt
public class Stops {

	// The Data that we're storing from stops.txt
	// note: There are more columns of data, but they seem to be unused, like "stop_url"
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

		//  splitting the input line since the data is comma seperated
		String[] inputValues = inputLine.split(",");

		// There are try/catch loops around each parse instruction, because not all lines of stops.txt
		// have valid entries, and it causes errors if we try to parse a string with no numbers, as a
		// double or integer.
		//
		// if the String can not be parsed as an integer, we set stop_id to be = -1;
		try {
			this.stop_id = Integer.parseInt(inputValues[0]);
		} catch (Exception e){
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

	}

	// move keywords: wb, nb, sb, eb from start of the names to the end of the names
	// of the stops
	// note: substring(3) gets rid of the first 3 characters in a string
	private String moveKeywords(String input) {

		// for the word "FLAGSTOP "
		if (input.charAt(0) == 'F' && input.charAt(1) == 'L' && input.charAt(2) == 'A' && input.charAt(3) == 'G'
		&& input.charAt(4) == 'S' && input.charAt(5) == 'T' && input.charAt(6) == 'O' && input.charAt(7) == 'P'
		&& input.charAt(8) == ' ') {
			// we don't return the string, since flagstop can be followed by WB, NB, SB or EB too
			input = input.substring(8) + " FLAGSTOP";

		}
		// for keyword WB
		if (input.charAt(0) == 'W' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " WB";
		}
		// for keyword NB
		else if (input.charAt(0) == 'N' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " NB";
		}
		// for keyword WB
		else if (input.charAt(0) == 'S' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " SB";
		}
		// for keyword WB
		else if (input.charAt(0) == 'E' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " EB";
		}
		else
		return input;

	}


	// Public functions for returning specific Data
	//
	//
	public String getID() {
		if(stop_id == -1) {
			return "N/A";
		}
		else return "" + this.stop_id;
	}
	public String getCode() {
		if(stop_code == -1) {
			return "N/A";
		}
		else return "" + this.stop_code;
	}
	public String getName() {
		return this.stop_name;
	}
	public String getDesc() {
		return this.stop_desc;
	}
	public String getLat() {
		if(stop_lat == -1) {
			return "N/A";
		}
		else return "" + this.stop_lat;
	}
	public String getLon() {
		if(stop_lon == -1) {
			return "N/A";
		}
		else return "" + this.stop_lon;
	}
	public String getZone() {
		return this.zone_id;
	}

}
