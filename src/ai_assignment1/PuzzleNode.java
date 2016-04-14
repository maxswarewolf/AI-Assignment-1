package ai_assignment1;

import java.util.Arrays;

public class PuzzleNode{
    private final PuzzleState puzzleState; //the puzzle it holds
    private final PuzzleNode origin; //used for path-finding
    private final int DISTANCE_COST; //TOTAL DISTANCE COST TO THIS POINT
    private final EnumDir direction;
    private int HEURSITIC_COST; //TOTAL COST OF THIS PUZZLE
    private int FINAL_COST;
    
    //Default Constructor
    public PuzzleNode(){
        this.puzzleState = null;
        this.origin = null;
        this.DISTANCE_COST = 0;
        this.HEURSITIC_COST = 0;
        this.FINAL_COST = 0;
        this.direction = EnumDir.ROOT;
    }
    //Constructor
    public PuzzleNode(PuzzleState puzzle){
        this.puzzleState = puzzle;
        this.origin = null;
        this.DISTANCE_COST = 0;
        this.HEURSITIC_COST = 0;
        this.FINAL_COST = 0;
        this.direction = EnumDir.ROOT;
    }
    public PuzzleNode(PuzzleState puzzle, PuzzleNode root, int dist, EnumDir dir){
        this.puzzleState = puzzle;
        this.origin = root;
        this.DISTANCE_COST = dist;
        this.HEURSITIC_COST = 0;
        this.FINAL_COST = 0;
        this.direction = dir;
    }
    //Copy Constructor
    public PuzzleNode(PuzzleNode copyNode){
        this.puzzleState = new PuzzleState(copyNode.getPuzzleState());
        if (copyNode.getOrigin() != null) {
            this.origin = new PuzzleNode(copyNode.getOrigin());
        } else {
            this.origin = null;
        }
        this.DISTANCE_COST = copyNode.getDISTANCE_COST();
        this.HEURSITIC_COST = copyNode.getHEURSITIC_COST();
        this.FINAL_COST = copyNode.getFINAL_COST(); 
        this.direction = copyNode.getDirection();
    }
    
    //Getters / Setters
    public PuzzleState getPuzzleState(){ return puzzleState;}    
    public PuzzleNode getOrigin(){ return origin;}
    public int getDISTANCE_COST(){ return DISTANCE_COST;}
    public int getHEURSITIC_COST(){ return HEURSITIC_COST;}
    public int getFINAL_COST(){ return FINAL_COST;}
    public EnumDir getDirection(){ return direction;}
    
    public void setHEURSITIC_COST(int HEURSITIC_COST){
        this.HEURSITIC_COST = HEURSITIC_COST;
        this.FINAL_COST = this.HEURSITIC_COST + this.DISTANCE_COST;
    }

    @Override
    public String toString(){
        return this.direction.name();
    }
    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == PuzzleNode.class){
            PuzzleNode Node = (PuzzleNode)obj;
            PuzzleState temp = Node.getPuzzleState();
            return (Arrays.equals(puzzleState.getPUZZLE(), temp.getPUZZLE()));
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.puzzleState.hashCode();
    }
    
    public PuzzleNode[] genNeighbours(){
        byte index = puzzleState.getZERO_INDEX();
        byte M = puzzleState.getColumns();
        byte N = puzzleState.getRows();
        PuzzleNode neighbours[] = new PuzzleNode[4];
        
        //UP DIRECTION
        if (!(index < M)){
            neighbours[EnumDir.UP.getValue()] = new PuzzleNode(new PuzzleState(puzzleState), this, this.DISTANCE_COST + 1, EnumDir.UP);
            neighbours[EnumDir.UP.getValue()].getPuzzleState().swap(index, (byte) (index - 3));
        } else {
            neighbours[EnumDir.UP.getValue()] = null;
        }
        //LEFT DIRECTION
        if (!(index % M == 0)){
            neighbours[EnumDir.LEFT.getValue()] = new PuzzleNode(new PuzzleState(puzzleState), this, this.DISTANCE_COST + 1, EnumDir.LEFT);
            neighbours[EnumDir.LEFT.getValue()].getPuzzleState().swap(index, (byte) (index - 1));
        } else {
            neighbours[EnumDir.LEFT.getValue()] = null;
        }
        //DOWN DIRECTION
        if (!(index >= (M * N - M))){
            neighbours[EnumDir.DOWN.getValue()] = new PuzzleNode(new PuzzleState(puzzleState), this, this.DISTANCE_COST + 1, EnumDir.DOWN);
            neighbours[EnumDir.DOWN.getValue()].getPuzzleState().swap(index, (byte) (index + 3));
        } else {
            neighbours[EnumDir.DOWN.getValue()] = null;
        }
        //RIGHT DIRECTION
        if (!(index % M == (M - 1))){
            neighbours[EnumDir.RIGHT.getValue()] = new PuzzleNode(new PuzzleState(puzzleState), this, this.DISTANCE_COST + 1, EnumDir.RIGHT);
            neighbours[EnumDir.RIGHT.getValue()].getPuzzleState().swap(index, (byte) (index + 1));
        } else {
            neighbours[EnumDir.RIGHT.getValue()] = null;
        }
        
        return neighbours;
    }
    public boolean isGoal(PuzzleState end){
        return puzzleState.equals(end);
    }
}
