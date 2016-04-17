/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.LinkedList;

public class AI_Assignment1 {

	public static final byte[] G8 = {1, 2, 3, 4, 5, 6, 7, 8, 0}; //{0,1,2,3,4,5,6,7,8}
	public static final byte[] P8 = {6, 4, 7, 8, 5, 0, 3, 2, 1}; //{6,4,7,8,5,0,3,2,1} {1,4,2,3,0,5,6,7,8} {3,1,2,6,8,4,7,0,5}
	public static final byte[] G15 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0};
	public static final byte[] P15 = {1, 2, 3, 5, 11, 6, 4, 13, 9, 8, 0, 12, 7, 10, 14};

	public static void main(String[] args) {
		PuzzleState goal = new PuzzleState(G8, (byte) 3, (byte) 3);
		PuzzleState start = new PuzzleState(P8, (byte) 3, (byte) 3);
		PuzzleState goal15 = new PuzzleState(G15, (byte) 3, (byte) 5);
		PuzzleState start15 = new PuzzleState(P15, (byte) 3, (byte) 5);
		PuzzleGenerator<PuzzleState> gen = new PuzzleGenerator<>();
		LinkedList<FinderAgent<PuzzleState>> testPuzzles = gen.generatePuzzles(9, 1, goal, start);
		LinkedList<SearchAlgorithm<Node<PuzzleState>>> searchToDo = new LinkedList<>();
		searchToDo.add(new BFS(EnumHeursitic.Uninformed));
//		searchToDo.add(new DFS(EnumHeursitic.Uninformed));
		searchToDo.add(new IDDFS(EnumHeursitic.Uninformed));

		searchToDo.add(new AStar(EnumHeursitic.MissplacedTiles));
		searchToDo.add(new GBFS(EnumHeursitic.MissplacedTiles));
		searchToDo.add(new IDAStar(EnumHeursitic.MissplacedTiles));

		searchToDo.add(new AStar(EnumHeursitic.ManhattanDistance));
		searchToDo.add(new GBFS(EnumHeursitic.ManhattanDistance));
		searchToDo.add(new IDAStar(EnumHeursitic.ManhattanDistance));

		searchToDo.add(new AStar(EnumHeursitic.MissplacedManhattanCombo));
		searchToDo.add(new GBFS(EnumHeursitic.MissplacedManhattanCombo));
		searchToDo.add(new IDAStar(EnumHeursitic.MissplacedManhattanCombo));

		searchToDo.add(new AStar(EnumHeursitic.EuclideanDistance));
		searchToDo.add(new GBFS(EnumHeursitic.EuclideanDistance));
		searchToDo.add(new IDAStar(EnumHeursitic.EuclideanDistance));

		LinkedList<Node<PuzzleState>> path;
		for (int i = 0; i < testPuzzles.size(); i++) {
			//Need to add in timing
			System.out.println("Generic Version Test - " + i + ": with " + testPuzzles.get(i).getMaxNodes() + " different states. " + testPuzzles.get(i).getStart().grid());
			System.out.println("");
			for (SearchAlgorithm<Node<PuzzleState>> Algo : searchToDo) {
				path = testPuzzles.get(i).algoSearch(Algo);
				System.out.println(Algo.name() + ", With " + Algo.getHeursitic().name() + " Heursitic: " + testPuzzles.get(i).getNumNodes() + " nodes to solution. ");
				System.out.println(path);
				path.clear();

				System.out.println("");
			}
			System.out.println("");
		}
	}
}
