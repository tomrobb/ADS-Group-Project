import java.util.ArrayList;

public class ShortestPath {
    // The list of stops on the route
    ArrayList<Stops> stops;
    // The total weight of the path.
    private double weight;

    /**
     *  Constructor for ShortestPath
     */
    public ShortestPath(){
        stops = new ArrayList<Stops>();
        weight = -1;
    }

    // Getter and setter for weight

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
