/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Adrian
 */
public class DFS implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    private final Stack<Node<PuzzleState>> open = new Stack<>();
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
    public Node<PuzzleState> search(Node<PuzzleState> start, Node<PuzzleState> goal) {
        open.add(start);
        while (!open.isEmpty()) {
            Node<PuzzleState> origin = open.pop();
            if (closed.contains(origin.hashCode())) {
                continue;
            }
            closed.add(origin.hashCode());

            if (origin.isGoal(goal)) {
                return origin;
            }

            for (byte i = origin.getNumNeighbours(); i > 0; i++) {
                Node<PuzzleState> neighbour = origin.genNeighbour(i - 1);
                if (neighbour != null) {
                    open.push(neighbour);
                }
            }
        }
        return start;
//        closed.add(origin.hashCode());
//        if (origin.isGoal(goal)) {
//            return origin;
//        }
//        for (byte i = 0; i < origin.getNumNeighbours(); i++) {
//            Node<PuzzleState> neighbour = origin.genNeighbour(i);
//            if (neighbour != null) {
//                if (closed.contains(neighbour.hashCode())) {
//                    return search(neighbour, goal);
//                }
//            }
//        }
//        return origin;
    }

    @Override
    public void setLimit(int a) {
        this.limit = a;
    }
}
