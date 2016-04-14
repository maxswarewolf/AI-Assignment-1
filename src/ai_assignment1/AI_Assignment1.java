/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

import java.util.LinkedList;


public class AI_Assignment1 {
    public static final byte[] G1 = {0,1,2,3,4,5,6,7,8}; //{0,1,2,3,4,5,6,7,8}
    public static final byte[] P1 = {6,4,7,8,5,0,3,2,1}; //{6,4,7,8,5,0,3,2,1} {1,4,2,3,0,5,6,7,8} {3,1,2,6,8,4,7,0,5}
    public static void main(String[] args) {
        PuzzleState goal = new PuzzleState(G1, (byte)3);
        PuzzleState start = new PuzzleState(P1, (byte)3);
        PuzzleGenerator gen = new PuzzleGenerator();
        LinkedList<FinderAgent> testPuzzles = gen.generatePuzzles(9, 1, (byte)3, goal, start);
        LinkedList<SearchAlgorithm<PuzzleNode>> searchToDo = new LinkedList<>();
//        searchToDo.add(new BFS(EnumHeursitic.Uninformed));
//        searchToDo.add(new DFS(EnumHeursitic.Uninformed));
        
//        searchToDo.add(new AStar(EnumHeursitic.MissplacedTiles));
        searchToDo.add(new AStar(EnumHeursitic.ManhattanDistance));
//        searchToDo.add(new AStar(EnumHeursitic.MissplacedManhattanCombo));
//        searchToDo.add(new AStar(EnumHeursitic.EuclideanDistance));
        
//        searchToDo.add(new GBFS(EnumHeursitic.MissplacedTiles));
//        searchToDo.add(new GBFS(EnumHeursitic.ManhattanDistance));
//        searchToDo.add(new GBFS(EnumHeursitic.MissplacedManhattanCombo));
//        searchToDo.add(new GBFS(EnumHeursitic.EuclideanDistance));
        
        LinkedList<PuzzleNode> path;
        for (int i = 0; i < testPuzzles.size(); i++) {
            //Need to add in timing
            System.out.println("Test - " + i + ": with " + testPuzzles.get(i).getMaxNodes() + " different states. " + testPuzzles.get(i).getStart().getPuzzleState());
            System.out.println("");
            for (SearchAlgorithm<PuzzleNode> Algo : searchToDo){
                path = testPuzzles.get(i).search(Algo, Algo.getHeursitic());
                System.out.println(Algo.name() +", With " + Algo.getHeursitic().name() + " Heursitic: " + testPuzzles.get(i).getNumNodes() + " nodes to solution. " + testPuzzles.get(i).getNumExploredStates() + " Explored. " + testPuzzles.get(i).getNumGeneratedNodes() + " generated.");
                System.out.println(path);
                System.out.println("");
            }
            System.out.println("");
        }
    }   
}
