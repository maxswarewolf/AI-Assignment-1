package ai_assignment1V2;

public class Node<T extends Puzzle>{
    private final T data; //the puzzle it holds
    private final Node root; //used for path-finding
    private final int DISTANCE_COST; //TOTAL DISTANCE COST TO THIS POINT
    private final EnumDir direction;
    private int HEURSITIC_COST; //TOTAL COST OF THIS PUZZLE
    private int FINAL_COST;
    
    //Default Constructor
    public Node(){
        this.data = null;
        this.root = null;
        this.DISTANCE_COST = 0;
        this.HEURSITIC_COST = 0;
        this.FINAL_COST = 0;
        this.direction = EnumDir.ROOT;
    }
    //Constructor
    public Node(T puzzle){
        this.data = puzzle;
        this.root = null;
        this.DISTANCE_COST = 0;
        this.HEURSITIC_COST = 0;
        this.FINAL_COST = 0;
        this.direction = EnumDir.ROOT;
    }
    public Node(T puzzle, Node<T> root, int dist, EnumDir dir){
        this.data = puzzle;
        this.root = root;
        this.DISTANCE_COST = dist;
        this.HEURSITIC_COST = 0;
        this.FINAL_COST = 0;
        this.direction = dir;
    }
    //Copy Constructor
    public Node(Node<T> copyNode){
        this.data = (T)copyNode.getData().returnDeepCopy();
        if (copyNode.getRoot() != null) {
            this.root = new Node(copyNode.getRoot());
        } else {
            this.root = null;
        }
        this.DISTANCE_COST = copyNode.getDISTANCE_COST();
        this.HEURSITIC_COST = copyNode.getHEURSITIC_COST();
        this.FINAL_COST = copyNode.getFINAL_COST(); 
        this.direction = copyNode.getDirection();
    }
    
    //Getters / Setters
    public T getData(){ return data;}    
    public Node getRoot(){ return root;}
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
        return this.root.getDirection() + " " + this.getDirection() + " " + this.getDISTANCE_COST();
        //return this.data.toString() + "\n" + this.direction.name()+ "\n";
    }
    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == Node.class){
            Node tempNode = (Node)obj;
            return data.equals(tempNode.getData());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.data.hashCode();
    }
    
    public Node[] genNeighbours(){
        byte index = data.getIndex((byte)0);     
        byte Col = data.getColumns();
        byte Rows = data.getRows();
        Node<T> neighbours[] = new Node[4];
        
        //UP DIRECTION
        if (!(index < Col)){
            neighbours[EnumDir.UP.getValue()] = new Node<>((T)data.returnDeepCopy(), this, this.DISTANCE_COST + 1, EnumDir.UP);
            neighbours[EnumDir.UP.getValue()].getData().swap(index, (byte) (index - 3));
        } else {
            neighbours[EnumDir.UP.getValue()] = null;
        }
        //LEFT DIRECTION
        if (!(index % Col == 0)){
            neighbours[EnumDir.LEFT.getValue()] = new Node<>((T)data.returnDeepCopy(), this, this.DISTANCE_COST + 1, EnumDir.LEFT);
            neighbours[EnumDir.LEFT.getValue()].getData().swap(index, (byte) (index - 1));
        } else {
            neighbours[EnumDir.LEFT.getValue()] = null;
        }
        //DOWN DIRECTION
        if (!(index >= (Col * Rows - Col))){
            neighbours[EnumDir.DOWN.getValue()] = new Node<>((T)data.returnDeepCopy(), this, this.DISTANCE_COST + 1, EnumDir.DOWN);
            neighbours[EnumDir.DOWN.getValue()].getData().swap(index, (byte) (index + 3));
        } else {
            neighbours[EnumDir.DOWN.getValue()] = null;
        }
        //RIGHT DIRECTION
        if (!(index % Col == (Col - 1))){
            neighbours[EnumDir.RIGHT.getValue()] = new Node<>((T)data.returnDeepCopy(), this, this.DISTANCE_COST + 1, EnumDir.RIGHT);
            neighbours[EnumDir.RIGHT.getValue()].getData().swap(index, (byte) (index + 1));
        } else {
            neighbours[EnumDir.RIGHT.getValue()] = null;
        }
        
        return neighbours;
    }
    public boolean isGoal(T end){
        return data.equals(end);
    }
}
