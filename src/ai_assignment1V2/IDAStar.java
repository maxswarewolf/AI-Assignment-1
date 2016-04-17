/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author Adrian
 */
public final class IDAStar implements SearchAlgorithm<Node<PuzzleState>> {

	private final EnumHeursitic hue;
	private int limit = 0;
	private final boolean HAS_DEPTH = true;
	private Node<PuzzleState> current;
	private final PriorityQueue<Node<PuzzleState>> exceded;
	private final PriorityQueue<Node<PuzzleState>> open;
	private final HashSet<Integer> closed = new HashSet<>();

	IDAStar(EnumHeursitic hue) {
		this.hue = hue;
		exceded = new PriorityQueue<>(16, getComparator());
		open = new PriorityQueue<>(16, getComparator());
	}

	@Override
	public Comparator<Node<PuzzleState>> getComparator() {
		return (Node<PuzzleState> node1, Node<PuzzleState> node2) -> {
			return (node1.getFINAL_COST() == node2.getFINAL_COST())
					? (node1.getDirection().getValue() > node2.getDirection().getValue()) ? 1
							: (node1.getDirection().getValue() < node2.getDirection().getValue()) ? -1 : 0
					: (node1.getFINAL_COST() > node2.getFINAL_COST()) ? 1
							: (node1.getFINAL_COST() < node2.getFINAL_COST()) ? -1 : 0;
		};
	}

	@Override
	public int getNodeCost(Node<PuzzleState> node) {
		return node.getFINAL_COST();
	}

	@Override
	public String name() {
		return "Iteravtive Depth A Star";
	}

	@Override
	public EnumHeursitic getHeursitic() {
		return this.hue;
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
	public boolean limitReached(Node<PuzzleState> a) {
		return (this.HAS_DEPTH && (a.getFINAL_COST() > getLimit()));
	}

	private boolean DLS(Node<PuzzleState> start, Node<PuzzleState> goal, int threshold) {
		start.setHEURSITIC_COST(hue.value(start.getData(), goal.getData()));
		open.add(start);
		while (!open.isEmpty()) {
			Node<PuzzleState> origin = open.poll();

			if (closed.contains(origin.hashCode() + origin.getFINAL_COST())) {
				continue;
			}
			closed.add(origin.hashCode() + origin.getFINAL_COST());

			if (origin.getFINAL_COST() > threshold) {
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
					neighbour.setHEURSITIC_COST(hue.value(origin.getData(), goal.getData()));
					open.add(neighbour);
				}
			}
		}
		this.current = start;
		return false;
	}

	@Override
	public Node<PuzzleState> search(Node<PuzzleState> origin, Node<PuzzleState> goal) {
		int threshold = hue.value(origin.getData(), goal.getData());
		long maxNodes = hue.factorial(origin.getData().getData().length);
		while (!DLS(origin, goal, threshold)) {
			threshold = (!exceded.isEmpty()) ? exceded.poll().getFINAL_COST() : threshold + 1;

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

	@Override
	public void setLimit(int a) {
		this.limit = a;
	}
}
