/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Adrian
 */
public class DFS implements SearchAlgorithm<Node<PuzzleState>> {

    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    private Stack<Node<PuzzleState>> open = new Stack<>();
    private Map<Integer, Node<PuzzleState>> closed = new HashMap<>();

    DFS(EnumHeursitic hue) {
        this.hue = hue;
    }

    @Override
    public Comparator<Node<PuzzleState>> getComparator() {
        return new Comparator() {
            private int compare(Node node1, Node node2) {
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
            }

            @Override
            public int compare(Object o1, Object o2) {
                return compare((Node) o1, (Node) o2);
            }
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
    public void setLimit(Node<PuzzleState> b, Node<PuzzleState> a) {
        this.limit = hue.value(b.getData(), a.getData());
    }

    @Override
    public boolean limitReached(Node<PuzzleState> a) {
        return (this.HAS_DEPTH && (a.getFINAL_COST() > getLimit()));
    }

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> start, Node<PuzzleState> end) {
        closed.put(start.hashCode(), start);
        Node<PuzzleState> neighbours[] = start.genNeighbours();
        for (byte i = 0; i < neighbours.length; i++) {
            if (closed.containsKey(neighbours[i].hashCode())) {
                return search(neighbours[i], end);
            }
        }
        return start;
    }
}
