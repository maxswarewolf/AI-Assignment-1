/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Adrian
 */
public final class AStar implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    private final PriorityQueue<Node<PuzzleState>> open;
    private final Map<Integer, Node<PuzzleState>> closed = new HashMap<>();

    AStar(EnumHeursitic hue) {
        this.hue = hue;
        this.open = new PriorityQueue<>(16, getComparator());
    }

    @Override
    public Comparator<Node<PuzzleState>> getComparator() {
        return (Node<PuzzleState> node1, Node<PuzzleState> node2) -> {
            if (node1.getFINAL_COST() == node2.getFINAL_COST()) {
                if (node1.getDISTANCE_COST() > node2.getDISTANCE_COST()) {
                    return 1;
                }
                if (node1.getDISTANCE_COST() < node2.getDISTANCE_COST()) {
                    return -1;
                }
                return 0;
            } else {
                if (node1.getFINAL_COST() > node2.getFINAL_COST()) {
                    return 1;
                }
                if (node1.getFINAL_COST() < node2.getFINAL_COST()) {
                    return -1;
                }
                return 0;
            }
        };
    }

    @Override
    public int getNodeCost(Node<PuzzleState> node) {
        return node.getFINAL_COST();
    }

    @Override
    public String name() {
        return "A Star Search";
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
    public void setLimit(Node<PuzzleState> b, Node<PuzzleState> a) {
        this.limit = hue.value(b.getData(), a.getData());
    }

    @Override
    public boolean limitReached(Node<PuzzleState> a) {
        return (this.HAS_DEPTH && (a.getFINAL_COST() > getLimit()));
    }

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> start, Node<PuzzleState> end) {
        start.setHEURSITIC_COST(hue.value(start.getData(), end.getData()));
        open.add(start);
        while (!open.isEmpty()) {
            Node<PuzzleState> origin = open.poll();
            if (closed.containsKey(origin.hashCode() + origin.getFINAL_COST())) {
                continue;
            }
            closed.put(origin.hashCode() + origin.getFINAL_COST(), origin);

            if (origin.isGoal(end.getData())) {
                return origin;
            }

            Node<PuzzleState> neighbours[] = origin.genNeighbours();
            for (byte i = 0; i < neighbours.length; i++) {
                if (neighbours[i] != null) {
                    open.add(neighbours[i]);
                }
            }
        }
        return start;
    }
}
