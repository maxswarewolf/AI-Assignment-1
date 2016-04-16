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

    IDAStar(EnumHeursitic hue) {
        this.hue = hue;
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
    public void setLimit(Node<PuzzleState> current, Node<PuzzleState> end) {
        int previous = this.limit;
        this.limit = hue.value(current.getData(), end.getData());
        while (this.limit <= previous) {
            this.limit++;
        }
    }

    @Override
    public boolean limitReached(Node<PuzzleState> a) {
        return (this.HAS_DEPTH && (a.getFINAL_COST() > getLimit()));
    }

    @Override
    public Node<PuzzleState> search(Node<PuzzleState> a, Node<PuzzleState> b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLimit(int a) {
        this.limit = a;
    }
}
