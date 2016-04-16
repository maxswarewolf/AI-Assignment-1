/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Adrian
 */
public class BFS implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    private final Queue<Node<PuzzleState>> open = new LinkedList<>();
    private final HashSet<Integer> closed = new HashSet<>();

    BFS(EnumHeursitic hue) {
        this.hue = hue;
    }

    @Override
    public Comparator<Node<PuzzleState>> getComparator() {
        return (Node<PuzzleState> node1, Node<PuzzleState> node2) -> {
            return (node1.getDISTANCE_COST() == node2.getDISTANCE_COST())
                    ? (node1.getRoot().getDirection().getValue() == node2.getRoot().getDirection().getValue())
                            ? (node1.getDirection().getValue() > node2.getDirection().getValue()) ? 1
                                    : (node1.getDirection().getValue() < node2.getDirection().getValue()) ? -1 : 0
                            : (node1.getRoot().getDirection().getValue() > node2.getRoot().getDirection().getValue()) ? 1
                                    : (node1.getRoot().getDirection().getValue() < node2.getRoot().getDirection().getValue()) ? -1 : 0
                    : (node1.getDISTANCE_COST() > node2.getDISTANCE_COST()) ? 1
                            : (node1.getDISTANCE_COST() < node2.getDISTANCE_COST()) ? -1 : 0;
        };
    }

    @Override
    public int getNodeCost(Node<PuzzleState> node) {
        return 0;
    }

    @Override
    public String name() {
        return "Breadth First Search";
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
        open.add(start);
        while (!open.isEmpty()) {
            Node<PuzzleState> origin = open.poll();
            if (closed.contains(origin.hashCode())) {
                continue;
            }
            closed.add(origin.hashCode());

            if (origin.isGoal(end)) {
                return origin;
            }

            for (byte i = 0; i < origin.getNumNeighbours(); i++) {
                Node<PuzzleState> neighbour = origin.genNeighbour(i);
                if (neighbour != null) {
                    open.add(neighbour);
                }
            }
        }
        return start;
    }

    @Override
    public void setLimit(int a) {
        this.limit = a;
    }
}
