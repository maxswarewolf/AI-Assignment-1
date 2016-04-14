package ai_assignment1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class FinderAgent {
    //PRIVATE VARIABLES
    private final PuzzleNode start;
    private final PuzzleState end;
    private final int MAX_NODES;
    private int NumNodes = 0;
    private int NumExploredStates = 0;
    private int NumGeneratedNodes = 0;
   
    //PRIVATE FUNCTIONS
    private LinkedList<PuzzleNode> constructPath(PuzzleNode node){
        LinkedList finalPath = new LinkedList<>();
        PuzzleNode pathNode = node;
        NumNodes = pathNode.getDISTANCE_COST();
        while (pathNode.getOrigin() != null){
            finalPath.addFirst(pathNode);
            pathNode = pathNode.getOrigin();
        }
        finalPath.addFirst(start);
        //NumNodes = finalPath.size();
        return finalPath;
    }
    private long factorial(long number) {
      if (number <= 1) {
          return 1;
      } else {
          return number * factorial(number - 1);
      }
    }
    
    //CONSTRUCTOR
    public FinderAgent(PuzzleState start, PuzzleState end){
        this.start = new PuzzleNode(start, null, 0, EnumDir.ROOT);
        this.end = new PuzzleState(end);
        this.MAX_NODES = (int) factorial(this.start.getPuzzleState().getColumns() * this.start.getPuzzleState().getRows());
    }
    //COPY CONSTRUCTOR
    public FinderAgent(FinderAgent copy){
        this.start = new PuzzleNode(copy.getStart());
        this.end = new PuzzleState(copy.getEnd());
        this.MAX_NODES = copy.getMaxNodes();
    }
    
    //GETTERS
    public PuzzleNode getStart() { return new PuzzleNode(start);}
    public PuzzleState getEnd(){ return new PuzzleState(end);}
    public int getNumNodes(){ return NumNodes;}
    public int getMaxNodes(){ return MAX_NODES;}
    public int getNumExploredStates(){ return NumExploredStates;}
    public int getNumGeneratedNodes(){ return NumGeneratedNodes;}
    
    @Override
    public String toString(){
        return "Intial Puzzle State: " + Arrays.toString(start.getPuzzleState().getPUZZLE()) + " Goal Puzzle State: " + Arrays.toString(end.getPUZZLE());
    }
    
    //PUBLIC FUNCTION REQUIRED FOR SEARCH    
    //Search Algorithms using a priority Queue and a heursitic function      
    public LinkedList<PuzzleNode> search(SearchAlgorithm algo, EnumHeursitic heu){
        Map<Integer, PuzzleNode> closed = new HashMap<>();
        PriorityQueue<PuzzleNode> open = new PriorityQueue<>(16, algo.getComparator());
        int tempGeneratedNodes = 0;
        
        start.setHEURSITIC_COST(heu.value(start.getPuzzleState(), end));
        open.add(start);  
        while (!open.isEmpty()){
            PuzzleNode origin = open.poll();
            if (closed.containsKey(origin.hashCode() + algo.getNodeCost(origin))){ //based on total node getNodeCost
                continue;
            }
            
            closed.put(origin.hashCode() + algo.getNodeCost(origin), origin);
            if (origin.isGoal(end)) { //early exit once path found
                NumExploredStates = closed.size();
                NumGeneratedNodes = tempGeneratedNodes;
                return constructPath(origin);
            }
            
            PuzzleNode neighbours[] = origin.genNeighbours();
            for (int index = 0; index < neighbours.length; index++){
                if (neighbours[index] != null) {
                    neighbours[index].setHEURSITIC_COST(heu.value(neighbours[index].getPuzzleState(), end));
                    open.add(neighbours[index]);
                    tempGeneratedNodes++;
                }
            }
        }
        
        return constructPath(start); 
    }
    //CUSTOM SEARCHING ALGORHYTHIMS
    
}
