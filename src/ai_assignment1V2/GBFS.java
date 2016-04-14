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
public class GBFS implements SearchAlgorithm<Node<PuzzleState>>{
    private final EnumHeursitic hue;
    private int limit = 0;
    private final boolean HAS_DEPTH = false;
    GBFS(EnumHeursitic hue){
        this.hue = hue;
    }
    @Override
    public Comparator<Node<PuzzleState>> getComparator(){
        return new Comparator(){
            private int compare(Node node1, Node node2){
                if (node1.getHEURSITIC_COST() > node2.getHEURSITIC_COST()){ return 1;}
                if (node1.getHEURSITIC_COST() < node2.getHEURSITIC_COST()){ return -1;}
                return 0;
            }
            @Override
            public int compare(Object o1, Object o2) {
                return compare((Node)o1, (Node)o2);
            }
        };
    }
    @Override
    public int getNodeCost(Node node){
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
    public void setLimit(Node<PuzzleState> b, Node<PuzzleState> a) {
        this.limit = hue.value(b.getData(), a.getData());    
    }

    @Override
    public boolean limitReached(Node<PuzzleState> a) {
        return (this.HAS_DEPTH && (a.getFINAL_COST() > getLimit()));
    }
}
