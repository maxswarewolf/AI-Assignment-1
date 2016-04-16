/*
 * Author: Adrian Pellegrino.
 * Student ID: 2054442.
 */
package ai_assignment1V2;

import java.util.Comparator;

/**
 *
 * @author Adrian
 */
public final class IDDFS implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private Node<PuzzleState> current;

    public IDDFS(EnumHeursitic hue) {
        this.hue = hue;
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

    private boolean DLS(Node<PuzzleState> origin, Node<PuzzleState> goal, int depth) {
        if ((depth <= 0) && (origin.isGoal(goal))) {
            this.current = new Node<>(origin);
            return true;
        }
        if (depth > 0) {
            for (byte index = 0; index < origin.getNumNeighbours(); index++) {
                Node<PuzzleState> neighbour = origin.genNeighbour(index);
                if ((neighbour != null) ? DLS(neighbour, goal, depth - 1) : false) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> start, Node<PuzzleState> end) {
        boolean done = true;
        for (int depth = 0; depth < EnumHeursitic.factorial(start.getData().getData().length); depth++) {
            if (DLS(start, end, depth)) {
                break;
            }
        }
        return (done) ? this.current : start;
    }

}
