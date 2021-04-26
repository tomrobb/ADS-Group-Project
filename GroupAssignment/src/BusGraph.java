
import java.util.*;

public class BusGraph {
    // adjacency list for the graph
    ArrayList<AdjacencyListNode> adjacencyList;

    /**
     * Constructor for BusGraph
     * @param stopList: list of all the stops.
     * @param transferList: list of all the transfers.
     * @param timesList: list of all the stop times
     */
    public BusGraph(ArrayList<Stops> stopList, ArrayList<Transfer> transferList, ArrayList<StopTimes> timesList){
        adjacencyList = new ArrayList<AdjacencyListNode>();
        // initialise the adjacency list
        for(int i = 0; i < stopList.size(); i++){
            adjacencyList.add(new AdjacencyListNode(stopList.get(i), stopList.get(i).stop_id));
        }
        // sort the adjacency list by stop_id
        adjacencyList.sort(new AdjacencyComparator());
        timesList.sort(new StopTimesComparator());
        // add edges to the adjacency list
        addStopTimes(timesList);
        addTransfers(transferList);
    }

    /**
     * Uses Dijkstra's algorithm to find the shortest path between two stops.
     * @param start: The first stop in the path
     * @param destination: The final stop in the path
     * @return Returns a ShortestPath, if one exists, that contains an ordered list of the stops, where the destination is at index 0, and the weight of
     * the path. Otherwise, returns null.
     */
    public ShortestPath getShortestPath(Stops start, Stops destination){
        double[] distTo = new double[adjacencyList.size()];
        boolean[] relaxed = new boolean[adjacencyList.size()];
        Stops[] edgeTo = new Stops[adjacencyList.size()];
        // find the index of the start stop
        int src = Collections.binarySearch(adjacencyList, new AdjacencyListNode(start, start.stop_id), new AdjacencyComparator());
        // initialise the arrays
        for(int i = 0; i < distTo.length; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
            relaxed[i] = false;
            edgeTo[i] = null;
        }
        distTo[src] = 0;
        // Dijkstra's algorithm
        for(int i = 0; i < distTo.length - 1; i++){
            int minIndex = -1;
            boolean cont = false;
            double minDist = Double.POSITIVE_INFINITY;
            // find the unrelaxed edge with smallest distance
            for(int j = 0; j < distTo.length; j++) {
                if(distTo[j] < minDist && !relaxed[j]) {
                    minDist = distTo[j];
                    minIndex = j;
                    cont = true;
                }
            }
            if(cont){
                relaxed[minIndex] = true;
                LinkedList<LinkedListNode> getList = adjacencyList.get(minIndex).edges;
                // check all edges adjacent to the stop
                for(LinkedListNode j = getList.pollFirst(); j != null; j = getList.pollFirst()) {
                    int nodeIndex = Collections.binarySearch(adjacencyList, new AdjacencyListNode(null, j.getStopId()),
                            new AdjacencyComparator());
                    // update shortest path
                    if((distTo[minIndex] + j.getWeight()) < distTo[nodeIndex]) {
                        distTo[nodeIndex] = distTo[minIndex] + j.weight;
                        edgeTo[nodeIndex] = adjacencyList.get(minIndex).stop;
                    }
                }
            }
        }
        return findShortestPath(destination, distTo, edgeTo);
    }

    /**
     * Adds the edges created by the stop times to the adjacency list.
     * @param timesList: list of all the stop times
     */
    private void addStopTimes(ArrayList<StopTimes> timesList){
        int currentTrip = -1;
        int previousStop = -1;
        for(int i = 0; i < timesList.size(); i++){
            StopTimes newStopTime = timesList.get(i);
            // if this stop is on the same route as the previous stop, then a new edge is created between them
            if(newStopTime.getTripId() == currentTrip){
                int index = Collections.binarySearch(adjacencyList, new AdjacencyListNode(null,previousStop),
                        new AdjacencyComparator());
                AdjacencyListNode addEdge = adjacencyList.get(index);
                addEdge.addEdge(newStopTime.getStopId(), 1);
                adjacencyList.set(index, addEdge);
            }
            // otherwise, we update the current trip
            else{
                currentTrip = newStopTime.getTripId();
            }
            previousStop = newStopTime.getStopId();
        }
    }

    /**
     * Adds the edges created by transfers to the adjacency list.
     * @param transferList: A list of transfers.
     */
    private void addTransfers(ArrayList<Transfer> transferList){
        for(int i = 0; i < transferList.size(); i++){
            Transfer newTransfer = transferList.get(i);
            int index = Collections.binarySearch(adjacencyList, new AdjacencyListNode(null, newTransfer.getFromStopId()),
                    new AdjacencyComparator());
            AdjacencyListNode addEdge = adjacencyList.get(index);
            // if the transfer is type 2, then make the weight 2
            if(newTransfer.getTransferType()==0){
                addEdge.addEdge(newTransfer.getToStopId(), 2);
            }
            // otherwise, calculate the weight from the transfer time
            else addEdge.addEdge(newTransfer.getToStopId(), newTransfer.getTransferTime()/100);
            adjacencyList.set(index, addEdge);
        }
    }

    /**
     *
     * @param destination: The final stop in the path
     * @param distTo: array of the distances from the source to all other stops
     * @param edgeTo: array of the previous stops on the path
     * @return: returns ShortestPath, that contains an ordered list of the stops, where the destination is at index 0, and the weight of
     * the path
     */
    private ShortestPath findShortestPath(Stops destination, double[] distTo, Stops[] edgeTo){
        int destIndex = Collections.binarySearch(adjacencyList, new AdjacencyListNode(destination,destination.stop_id),
                new AdjacencyComparator());
        // if there is a path between the source and the destination.
        if(distTo[destIndex] != Double.POSITIVE_INFINITY){
            ShortestPath shortestPath = new ShortestPath();
            shortestPath.setWeight(distTo[destIndex]);
            int pathIndex = destIndex;
            // adds the stops on the path to the ShortestPath
            while(edgeTo[pathIndex] != null){
                shortestPath.stops.add(edgeTo[pathIndex]);
                pathIndex = Collections.binarySearch(adjacencyList, new AdjacencyListNode(edgeTo[pathIndex], edgeTo[pathIndex].stop_id),
                        new AdjacencyComparator());
            }
            return shortestPath;
        }
        // returns null if no path exists
        else return null;
    }


    // nodes for the linked list in the AdjacencyListNode. These represent edges in the graph
    private class LinkedListNode {
        private int stopId;
        private double weight;

        /**
         * Constructor for LinkedListNode.
         * @param stopId: the stop_id of the stop the edge goes to
         * @param weight: the weight of the edge
         */
        public LinkedListNode(int stopId, double weight){
            this.stopId = stopId;
            this.weight = weight;
        }

        // getters for the stored data
        public int getStopId() {
            return stopId;
        }

        public double getWeight() {
            return weight;
        }
    }

    // nodes for the adjacency list. These contain the stop, stop_id, and the list of edges going from the node
    private class AdjacencyListNode{
        private int stopId;
        private Stops stop;
        LinkedList<LinkedListNode> edges;

        /**
         * Constructor for AdjacencyListNodes
         * @param stop: the stop
         * @param stopId: the stop id
         */
        public AdjacencyListNode(Stops stop, int stopId){
            this.stop = stop;
            this.stopId = stopId;
            edges = new LinkedList<LinkedListNode>();
        }

        //getters and setters for the stored data.
        public Stops getStop() {
            return stop;
        }

        public int getStopId() {
            return stopId;
        }

        public void addEdge(int stopId, double weight){
            edges.add(new LinkedListNode(stopId, weight));
        }
    }

    // Comparator class that allows us to perform search and sort operations on the adjacency list.
    private class AdjacencyComparator implements Comparator<AdjacencyListNode>{

        @Override
        public int compare(AdjacencyListNode node1, AdjacencyListNode node2) {
            return node1.getStopId() - node2.getStopId();
        }
    }
    
    private class StopTimesComparator implements Comparator<StopTimes>{
        @Override
        public int compare(StopTimes time1, StopTimes time2){
            if(time1.getTripId() != time2.getTripId()){
                return time1.getTripId() - time2.getTripId();
            }
            else return time1.getStopSequence() - time2.getStopSequence();
        }
    }
}
