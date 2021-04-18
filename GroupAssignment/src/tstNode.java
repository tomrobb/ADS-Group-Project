// Class to represent a node in the TST
public class tstNode {

	// The Data that each node contains:
	public char data;
	public boolean isEnd;
	public tstNode left, middle, right;

	// Constructor
	public tstNode(char c) {
		this.data = c;
	}
}
