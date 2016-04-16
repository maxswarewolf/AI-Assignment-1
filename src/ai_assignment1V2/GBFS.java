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
public final class GBFS implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    private final PriorityQueue<Node<PuzzleState>> open;
    private final HashSet<Integer> closed = new HashSet<>();

    GBFS(EnumHeursitic hue) {
        this.hue = hue;
        this.open = new PriorityQueue<>(16, getComparator());
    }

    @Override
    public Comparator<Node<PuzzleState>> getComparator() {
        return (Node<PuzzleState> node1, Node<PuzzleState> node2) -> {
            return (node1.getHEURSITIC_COST() > node2.getHEURSITIC_COST()) ? 1
                    : (node1.getHEURSITIC_COST() < node2.getHEURSITIC_COST()) ? -1 : 0;
        };
    }

    @Override
    public int getNodeCost(Node<PuzzleState> node) {
        return node.getHEURSITIC_COST();
    }

    @Override
    public String name() {
        return "Greedy Best First Search";
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
        start.setHEURSITIC_COST(hue.value(start.getData(), goal.getData()));
        open.add(start);
        while (!open.isEmpty()) {
            Node<PuzzleState> origin = open.poll();
            if (closed.contains(origin.hashCode() + origin.getFINAL_COST())) {
                continue;
            }
            closed.add(origin.hashCode() + origin.getFINAL_COST());

            if (origin.isGoal(goal)) {
                return origin;
            }

            for (byte i = 0; i < origin.getNumNeighbours(); i++) {
                Node<PuzzleState> neighbour = origin.genNeighbour(i);
                if (neighbour != null) {
                    neighbour.setHEURSITIC_COST(hue.value(start.getData(), goal.getData()));
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
