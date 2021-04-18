import java.util.*;

public class TST {

	private tstNode root;

	// when we first construct the TST, the root is null because we're at the start of
	// the tree.
	//
	public TST() {
		root = null;
	}


	// Method to add nodes to the TST
	// parameter:		a string, in this case, will be the names of the stops.
	//
	//
	public void insert(String word) {
		tstNode tmp = insert(root, word);
		if (root == null)
			root = tmp;
	}


	// Function to add nodes to the TST
	// parameters:	the current node of the tree
	//							the word we are inserting
	//
	private tstNode insert(tstNode current, String word) {
		if (current == null) {
			current = new tstNode(word.charAt(0));
		}
		if (word.charAt(0) < current.data)
			current.left = insert(current.left, word);
		else if (word.charAt(0) > current.data)
			current.right = insert(current.right, word);
		else {
			if (word.length() == 1) {
				current.isEnd = true;
				return current;
			}
			current.middle = insert(current.middle, word.substring(1));
		}
		return current;

	}


	// Function for finding the last node of a word
	//	parameter:		The word we are finding the last node for
	//	return:				the last node
	//
	//
	private tstNode findLastNode(String word) {
		if (word.length() == 0)
			return root;
		tstNode current = root;
		int i = 0;
		while (current != null) {
			if (word.charAt(i) == current.data) {
				if (i == word.length() - 1)
					return current;
				++i;
				current = current.middle;
			} else if (word.charAt(i) < current.data) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return null;
	}


	// function which autocompletes a given prefix
	// 	parameter:  prefix which we will search a solution for
	// 	return: 		An arraylist of search results, which is null if no results found
	//
	//
	public ArrayList<String> autocomplete(String prefix) {

		ArrayList<String> words = new ArrayList<String>();
		if (prefix.length() == 0)
			inOrder(root, words, prefix);
		else {
			tstNode tmp = findLastNode(prefix);
			if (tmp == null)
				return null;
			if (tmp.isEnd == true)
				words.add(prefix);
			inOrder(tmp.middle, words, prefix);
		}
		return words;
	}


	// Function which recursively adds the nodes in order
	//	parameters:	current Node in the tree
	//							Arraylist of Strings, which will be the output in autocomplete
	//							A String which stores a word
	//
	private void inOrder(tstNode current, ArrayList<String> words, String word) {
		if (current == null)
			return;
		inOrder(current.left, words, word);
		if (current.isEnd == true)
			words.add(word + current.data);
		inOrder(current.middle, words, word + current.data);
		inOrder(current.right, words, word);
	}

}
