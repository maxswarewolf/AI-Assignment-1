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
            if (node1.getDISTANCE_COST() == node2.getDISTANCE_COST()) {
                if (node1.getDirection().getValue() > node2.getDirection().getValue()) {
                    return 1;
                }
                if (node1.getDirection().getValue() < node2.getDirection().getValue()) {
                    return -1;
                }
                return 0;
            } else {
                if (node1.getDISTANCE_COST() > node2.getDISTANCE_COST()) {
                    return -1;
                }
                if (node1.getDISTANCE_COST() < node2.getDISTANCE_COST()) {
                    return 1;
                }
                return 0;
            }
        };
    }

    @Override
    public int getLimit() {
        return this.limit;
    }

    @Override
    public void setLimit(Node<PuzzleState> current, Node<PuzzleState> end) {
        int previous = this.limit;
        this.limit = hue.value(current.getData(), end.getData());
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

    private Node<PuzzleState> DLS(Node<PuzzleState> origin, Node<PuzzleState> goal, int depth) {
        if ((depth <= 0) && (origin.isGoal(goal.getData()))) {
            return origin;
        }
        if (depth > 0) {
            Node<PuzzleState> neighbours[] = origin.genNeighbours();
            for (byte index = 0; index < neighbours.length; index++) {
                if (neighbours[index] != null) {
                    Node<PuzzleState> found = DLS(neighbours[index], goal, depth - 1);
                    if (found != null) {
                        return found;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> start, Node<PuzzleState> end) {
        for (int depth = 0; depth < this.limit; depth++) {
            Node<PuzzleState> found = DLS(start, end, depth);
            if (found != null) {
                return found;
            }
        }
        return start;
    }

}
