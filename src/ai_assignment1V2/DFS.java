/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashSet;

/**
 *
 * @author Adrian
 */
public class DFS implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    private final HashSet<Integer> closed = new HashSet<>();

    DFS(EnumHeursitic hue) {
        this.hue = hue;
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
    public int getNodeCost(Node<PuzzleState> node) {
        return 0;
    }

    @Override
    public String name() {
        return "Depth First Search";
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

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> start, Node<PuzzleState> end) {
        closed.add(start.hashCode());
        if (start.getData().hashCode() == end.getData().hashCode()) {
            return start;
        }
        for (byte i = 0; i < start.getNumNeighbours(); i++) {
            Node<PuzzleState> neighbour = start.genNeighbour(i);
            if (closed.contains(neighbour.hashCode())) {
                return search(neighbour, end);
            }
        }
        return start;
    }

    @Override
    public void setLimit(int a) {
        this.limit = a;
    }
}
