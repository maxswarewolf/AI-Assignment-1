/*
 * Author: Adrian Pellegrino.
 * Student ID: 2054442.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author Adrian
 */
public final class IDDFS implements SearchAlgorithm<Node<PuzzleState>> {

	private final EnumHeursitic hue;
	private int limit = 0;
	private Node<PuzzleState> current;
	private final Stack<Node<PuzzleState>> open = new Stack<>();
	private final PriorityQueue<Node<PuzzleState>> exceded;
	private final HashSet<Integer> closed = new HashSet<>();

	public IDDFS(EnumHeursitic hue) {
		this.hue = hue;
		exceded = new PriorityQueue<>(16, getComparator());
	}

	@Override
	public String name() {
		return "Iteravtive Depth Depth-First-Search";
	}

	@Override
	public EnumHeursitic getHeursitic() {
		return this.hue;
	}

	@Override
	public int getNodeCost(Node<PuzzleState> t) {
		return 0;
	}

	@Override
	public Comparator<Node<PuzzleState>> getComparator() {
		return (Node<PuzzleState> node1, Node<PuzzleState> node2) -> {
			return (node1.getDISTANCE_COST() == node2.getDISTANCE_COST())
					? (node1.getDirection().getValue() > node2.getDirection().getValue()) ? 1
							: (node1.getDirection().getValue() < node2.getDirection().getValue()) ? -1 : 0
					: (node1.getDISTANCE_COST() > node2.getDISTANCE_COST()) ? -1
							: (node1.getDISTANCE_COST() < node2.getDISTANCE_COST()) ? 1 : 0;
		};
	}

	@Override
	public int getLimit() {
		return this.limit;
	}

	@Override
	public void setLimit(Node<PuzzleState> origin, Node<PuzzleState> goal) {
		int previous = this.limit;
		this.limit = hue.value(origin.getData(), goal.getData());
		while (this.limit <= previous) {
			this.limit++;
		}
	}

	@Override
	public void setLimit(int a) {
		this.limit = a;
	}

	@Override
	public boolean limitReached(Node<PuzzleState> a) {
		return (a.getDISTANCE_COST() >= this.limit);
	}

	private boolean DLS(Node<PuzzleState> start, Node<PuzzleState> goal, int depth) {
		open.add(start);
		while (!open.isEmpty()) {
			Node<PuzzleState> origin = open.pop();

			if (closed.contains(origin.hashCode())) {
				continue;
			}
			closed.add(origin.hashCode());

			if (origin.getDISTANCE_COST() > depth) {
				exceded.add(origin);
				continue;
			}

			if (origin.isGoal(goal)) {
				this.current = origin;
				return true;
			}

			for (byte i = 0; i < origin.getNumNeighbours(); i++) {
				Node<PuzzleState> neighbour = origin.genNeighbour(i);
				if (neighbour != null) {
					open.push(neighbour);
				}
			}
		}
		this.current = start;
		return false;
	}

	@Override
	public Node<PuzzleState> search(Node<PuzzleState> origin, Node<PuzzleState> goal) {
		int threshold = 0;
		long maxNodes = hue.factorial(origin.getData().getData().length);
		while (!DLS(origin, goal, threshold)) {
			threshold = (!exceded.isEmpty()) ? exceded.poll().getDISTANCE_COST() : threshold + 1;

			if (threshold > maxNodes) {
				System.out.println("");
				System.out.println("Something went wrong: " + threshold);
				return origin;
			}
			exceded.clear();
			open.clear();
			closed.clear();
		}
		return current;
	}
}
