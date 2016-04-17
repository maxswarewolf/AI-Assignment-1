package ai_assignment1V2;

import java.util.Arrays;
import java.util.LinkedList;

public class FinderAgent<T extends Puzzle> {

	//PRIVATE VARIABLES
	private final Node<T> start;
	private final T end;
	private final LinkedList<SearchAlgorithm<T>> algoToRun = new LinkedList<>();
	private final int MAX_NODES;

	//PRIVATE FUNCTIONS
	private LinkedList<Node<T>> constructPath(Node<T> node) {
		LinkedList<Node<T>> finalPath = new LinkedList<>();
		Node<T> pathNode = node;
		while (pathNode.getRoot() != null) {
			finalPath.addFirst(pathNode);
			pathNode = pathNode.getRoot();
		}
		finalPath.addFirst(start);
		return finalPath;
	}

	//CONSTRUCTOR
	@SuppressWarnings("unchecked")
	public FinderAgent(T start, T end) {
		this.start = new Node<>(start, null, 0, EnumDir.ROOT);
		this.end = (T) end.returnDeepCopy();
		this.MAX_NODES = (int) EnumHeursitic.Factorial(start.getColumns() * start.getRows());
	}

	//COPY CONSTRUCTOR
	@SuppressWarnings("unchecked")
	public FinderAgent(FinderAgent<T> copy) {
		this.start = new Node<>(copy.getStart());
		this.end = (T) copy.getEnd().returnDeepCopy();
		this.MAX_NODES = copy.getMaxNodes();
	}

	//GETTERS
	public Node<T> getStart() {
		return new Node<>(start);
	}

	@SuppressWarnings("unchecked")
	public T getEnd() {
		return (T) end.returnDeepCopy();
	}

	public int getMaxNodes() {
		return MAX_NODES;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "Intial Puzzle State: " + Arrays.toString(start.getData().getData()) + " Goal Puzzle State: " + Arrays.toString(end.getData());
	}

	public void listAlgoSearch(LinkedList<SearchAlgorithm<Node<T>>> searchToDo) {
		LinkedList<Node<T>> path;
		for (SearchAlgorithm<Node<T>> Algo : searchToDo) {
			path = search(Algo);
			System.out.println(Algo.name() + ", With " + Algo.getHeursitic().name() + " Heursitic: " + (path.size() - 1) + " nodes to solution. ");
			System.out.println(path);
			path.clear();
			System.out.println("");
		}
	}

	/**
	 *
	 * @param algo
	 * @return
	 */
	public LinkedList<Node<T>> search(SearchAlgorithm<Node<T>> algo) {
		Node<T> temp = (start.getData().getColumns() == end.getColumns()
				&& start.getData().getRows() == end.getRows())
						? algo.search(start, new Node<>(end)) : null;
		return (temp != null) ? constructPath(temp) : constructPath(start);
	}
}
