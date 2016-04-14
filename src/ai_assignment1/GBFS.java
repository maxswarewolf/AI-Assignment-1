/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

import java.util.Comparator;

/**
 *
 * @author Adrian
 */
public class GBFS implements SearchAlgorithm<PuzzleNode>{
    private final EnumHeursitic hue;
    GBFS(EnumHeursitic hue){
        this.hue = hue;
    }
    @Override
    public Comparator<PuzzleNode> getComparator(){
        return new Comparator(){
            private int compare(PuzzleNode node1, PuzzleNode node2){
                if (node1.getHEURSITIC_COST() > node2.getHEURSITIC_COST()){ return 1;}
                if (node1.getHEURSITIC_COST() < node2.getHEURSITIC_COST()){ return -1;}
                return 0;
            }
            @Override
            public int compare(Object o1, Object o2) {
                return compare((PuzzleNode)o1, (PuzzleNode)o2);
            }
        };
    }
    @Override
    public int getNodeCost(PuzzleNode node){
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

}
