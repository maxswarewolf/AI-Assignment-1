/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;

/**
 *
 * @author Adrian
 */
public final class IDAStar implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = true;
    private Node<PuzzleState> current;

    IDAStar(EnumHeursitic hue) {
        this.hue = hue;
    }

    @Override
    public Comparator<Node<PuzzleState>> getComparator() {
        return (Node<PuzzleState> node1, Node<PuzzleState> node2) -> {
            return (node1.getFINAL_COST() == node2.getFINAL_COST())
                    ? (node1.getDISTANCE_COST() > node2.getDISTANCE_COST()) ? 1
                            : (node1.getDISTANCE_COST() < node2.getDISTANCE_COST()) ? -1 : 0
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

    private boolean DLS(Node<PuzzleState> origin, Node<PuzzleState> goal, int threshold) {
        origin.setHEURSITIC_COST(hue.value(origin.getData(), goal.getData()));
        if (origin.isGoal(goal)) {
            this.current = new Node<>(origin);
            return true;
        }
        if (origin.getFINAL_COST() > threshold) {
            return false;
        }
        for (byte i = 0; i < origin.getNumNeighbours(); i++) {
            Node<PuzzleState> neighbour = origin.genNeighbour(i);
            if ((neighbour != null) ? DLS(neighbour, goal, threshold) : false) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> origin, Node<PuzzleState> goal) {
        int threshold = hue.value(origin.getData(), goal.getData());
        while (!DLS(origin, goal, threshold)) {
            threshold++;
        }
        return current;
    }

    @Override
    public void setLimit(int a) {
        this.limit = a;
    }
}
