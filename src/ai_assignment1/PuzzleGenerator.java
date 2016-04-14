/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Adrian
 */
public class PuzzleGenerator {
  /**
    *   A list of states that can be uses as well a some goal states 
    *   public final byte[] P1 = {1,4,2,3,0,5,6,7,8};    
    *   public final byte[] P2 = {4,1,5,3,6,2,0,8,7};    
    *   public final byte[] P3 = {4,1,5,3,6,2,0,8,7};    
    *   public final byte[] P4 = {6,4,7,8,5,0,3,2,1};   
    *   public final byte[] P5 = {8,6,7,2,5,4,3,0,1};
    * 
    *   public final byte[] G1 = {0,1,2,3,4,5,6,7,8}; //8 - Puzzle - 3x3
    *   public final byte[] G2 = {0,1,2,3,4,5,6,7,8,9,10,11}; //11 - Puzzle - 3x4
    *   public final byte[] G3 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}; //15 - Puzzle - 
    *   public final byte[] G4 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17}; //17 - Puzzle
    *   //21 - puzzle
    *   //24 - puzzle
    * 
    *   PuzzleState T1 = new PuzzleState(P1, (byte)3);
    * 
    *   PuzzleState T2 = new PuzzleState(P2, (byte)3);     
    *   PuzzleState T3 = new PuzzleState(P3, (byte)3);
    *   PuzzleState T4 = new PuzzleState(P4, (byte)3);
    *   PuzzleState T5 = new PuzzleState(P5, (byte)3);
    * 
    *   PuzzleState GT_1 = new PuzzleState(G1, (byte)3);
    * 
    *   FinderAgent TEST_1 = new FinderAgent(T1,GT_1);
    *   FinderAgent TEST_2 = new FinderAgent(T2,GT_1);
    *   FinderAgent TEST_3 = new FinderAgent(T3,GT_1);
    *   FinderAgent TEST_4 = new FinderAgent(T4,GT_1);
    *   FinderAgent TEST_5 = new FinderAgent(T5,GT_1);
    */
    
    private long factorial(long number) {
      if (number <= 1) {
          return 1;
      } else {
          return number * factorial(number - 1);
      }
    }
    //Really long function
    public LinkedList<FinderAgent> generatePuzzles(int numPuzzleCells, int maxNumPuzzles, byte numColumns, PuzzleState goal, PuzzleState start){
        LinkedList<FinderAgent> temp = new LinkedList<>();
        int MaxNodes = (int)factorial(numPuzzleCells); //Exit Condition
        Map<Integer, PuzzleNode> closed = new HashMap<>(); //Generated States
        Queue<PuzzleNode> open = new LinkedList<>(); //States still to look at
        int tempMaxNumPuzzles = maxNumPuzzles; //User selected number of puzzles to generate
        int tempGeneratedNodes = 0; //Exit variable
        if (maxNumPuzzles < 0 || maxNumPuzzles > MaxNodes){
            tempMaxNumPuzzles = MaxNodes;
        }
        
        open.add(new PuzzleNode(start));  
        while (!open.isEmpty() && (MaxNodes / 2) != tempGeneratedNodes && tempMaxNumPuzzles != temp.size()){ //Exit conditions
            PuzzleNode origin = open.poll();
            if (closed.containsKey(origin.hashCode())) { //Secondary Loop condition
                continue;
            }
            
            //Adding a State to be tested
            closed.put(origin.hashCode(), origin);
            PuzzleState tempState =  new PuzzleState(origin.getPuzzleState().getPUZZLE(), numColumns);
            FinderAgent tempAgent = new FinderAgent(tempState, goal);
            temp.add(tempAgent); 
            
            //Generating new States for the program to test
            PuzzleNode neighbours[] = origin.genNeighbours();
            for (int index = 0; index < neighbours.length; index++){
                if (neighbours[index] != null) {
                    open.add(neighbours[index]);
                    tempGeneratedNodes++;
                }
            }
        }
        return temp;
    }
}
