
public class Transfer {

	// data stored from transfers.txt
	private int fromStopId;
	private int toStopId;
	private int transferType;
	private int transferTime;

	/**
	 *
	 * @param inputLine: single line from transfers.txt.
	 *                   from_stop_id,to_stop_id,transfer_type,min_transfer_time
	 */
	public Transfer(String inputLine) {
		// split the line into its individual parameters.
		String[] inputArray = inputLine.split(",");

		// assign the data to variables
		try {
			this.fromStopId = Integer.parseInt(inputArray[0]);
		} catch (Exception e) {
			this.fromStopId = -1;
		}

		try {
			this.toStopId = Integer.parseInt(inputArray[1]);
		} catch (Exception e) {
			this.fromStopId = -1;
		}

		try {
			this.transferType = Integer.parseInt(inputArray[2]);
		} catch (Exception e) {
			this.transferType = -1;
		}

		try {
			this.transferTime = Integer.parseInt(inputArray[3]);
		} catch (Exception e) {
			this.transferTime = -1;
		}

	}

	// Getter for the stored data.

	public int getFromStopId() {
		return fromStopId;
	}

	public int getToStopId() {
		return toStopId;
	}

	public int getTransferTime() {
		return transferTime;
	}

	public int getTransferType() {
		return transferType;
	}

}
