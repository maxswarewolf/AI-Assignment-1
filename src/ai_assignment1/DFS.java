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
public class DFS implements SearchAlgorithm<PuzzleNode>{
    private final EnumHeursitic hue;
    DFS(EnumHeursitic hue){
        this.hue = hue;
    }
    @Override
    public Comparator<PuzzleNode> getComparator(){
        return new Comparator(){
            private int compare(PuzzleNode node1, PuzzleNode node2){
                if (node1.getDISTANCE_COST() == node2.getDISTANCE_COST()){
                    if (node1.getDirection().getValue() > node2.getDirection().getValue()){ return 1;}
                    if (node1.getDirection().getValue() < node2.getDirection().getValue()){ return -1;}
                    return 0;
                } else{
                    if (node1.getDISTANCE_COST() > node2.getDISTANCE_COST()){ return -1;}
                    if (node1.getDISTANCE_COST() < node2.getDISTANCE_COST()){ return 1;}
                    return 0;
                }
            }
            @Override
            public int compare(Object o1, Object o2) {
                return compare((PuzzleNode)o1, (PuzzleNode)o2);
            }
        };
    }
    @Override
    public int getNodeCost(PuzzleNode node){
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

}
