import java.time.LocalTime;


public class StopTimes {

    // The data stored from stop_times.txt
    private int tripId;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private String arrivalTimeString;
    private String departureTimeString;
    private int stopId;
    private int stopSequence;
    private int pickUpType;
    private int dropOffType;
    private double distance;

    /**
     * Constructor for StopTimes
     * @param inputLine: A line from the stop_times.txt file. trip_id,arrival_time,departure_time,stop_id,stop_sequence,
     *                 stop_headsign,pickup_type,drop_off_type,shape_dist_traveled
     */
    public StopTimes(String inputLine){
        // split the line into its individual parameters.
        String[] inputArray = inputLine.split(",");

        // assign the data to variables
        try{
            this.arrivalTimeString =inputArray[1];
        }
        catch (Exception e){
            
        }
        
        try{
            this.departureTimeString =inputArray[2];
        }
        catch (Exception e){
            
        }
        
        
        
         try{
             this.tripId = Integer.parseInt(inputArray[0]);
         }
         catch (Exception e){
             this.tripId = -1;
         }

        try{
            this.arrivalTime = LocalTime.parse(inputArray[1]);
        }
        catch (Exception e){
            this.arrivalTime = null;
        }

        try{
            this.departureTime = LocalTime.parse(inputArray[2]);
        }
        catch (Exception e){
            this.departureTime = null;
        }

        try{
            this.stopId = Integer.parseInt(inputArray[3]);
        }
        catch (Exception e){
            this.stopId = -1;
        }

        try{
            this.stopSequence = Integer.parseInt(inputArray[4]);
        }
        catch (Exception e){
            this.stopSequence = -1;
        }

        try{
            this.pickUpType = Integer.parseInt(inputArray[6]);
        }
        catch (Exception e){
            this.pickUpType = -1;
        }

        try{
            this.dropOffType = Integer.parseInt(inputArray[7]);
        }
        catch (Exception e){
            this.dropOffType = -1;
        }

        try{
            this.distance = Double.parseDouble(inputArray[8]);
        }
        catch (Exception e){
            this.distance = -1;
        }


    }

    // Getters for the data

    public int getTripId() {
        return tripId;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public int getStopId() {
        return stopId;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public int getPickUpType() {
        return pickUpType;
    }

    public int getDropOffType() {
        return dropOffType;
    }

    public double getDistance() {
        return distance;
    }
    
    public String getArrivalTimeString() {
    	return arrivalTimeString;
    }
    
    public String getDepartureTimeString() {
    	return departureTimeString;
    }

}
