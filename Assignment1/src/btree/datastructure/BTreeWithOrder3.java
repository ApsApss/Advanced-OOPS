package datastructure;

/**
 * this interface shows the structure of BTree with order 3, each node in BTree
 * holds student object as a key.
 */
public interface BTreeWithOrder3 {
	// root element of the tree
	public Node root = new Node();
	public int size = 0;

	// inserts student elements
	public void insert(Student studentElement);

	// prints details of Kth element from the tree
	public void getKthElementInTree(int k);

	// prints redId of probation students
	public void printProbationStudents();

	// prints student names with GPA 4.0
	public void printStudentsWithGPA4();

	// returns the size of the tree
	public int getSize();
}
