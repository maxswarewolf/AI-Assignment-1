package ai_assignment1;

import java.util.Arrays;

public class PuzzleState{
    //PRIVATE VARIABLES
    private final byte[] PUZZLE; //int[ROWS][COLUMNS]
    private final byte M;
    private final byte N;
    private byte ZERO_INDEX; //ZERO POSITION 
    
    //Construstor 
    public PuzzleState(byte[] puzzle, byte nColumns){
        this.M = nColumns;
        this.N = (byte) (puzzle.length / nColumns);
        this.PUZZLE = new byte[M*N];
        for(byte i = 0; i < this.PUZZLE.length; i++){
            this.PUZZLE[i] = puzzle[i];
            if (this.PUZZLE[i] == 0) {
                this.ZERO_INDEX = i;
            }
        }
    }
    public PuzzleState(byte[][] puzzle){
        this.M = (byte) puzzle[0].length;
        this.N = (byte) puzzle.length;
        byte index = 0;
        this.PUZZLE = new byte[M*N];
        for (byte n = 0; n < N; n++){
            for (byte m = 0; m < M; m++){
                this.PUZZLE[index] = puzzle[n][m];
                if (this.PUZZLE[index] == 0) {
                    this.ZERO_INDEX = index;
                }
                index++;
            }
        }
    }
    public PuzzleState(PuzzleState copy){
        this.M = copy.getColumns();
        this.N = copy.getRows();
        this.PUZZLE = new byte[M*N];
        byte[] puzzle = copy.getPUZZLE(); //shallow copy
        for(byte i = 0; i < this.PUZZLE.length; i++){ //deep copy
            this.PUZZLE[i] = puzzle[i];
            if (this.PUZZLE[i] == 0) {
                this.ZERO_INDEX = i;
            }
        }
    }
    
    //GETTERS
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public byte[] getPUZZLE(){ return PUZZLE;}  
    public byte getZERO_INDEX(){ return ZERO_INDEX;}
    public byte getColumns(){ return M;}
    public byte getRows(){ return N;}
    //FUNCTIONS
    @Override
    public String toString(){
        String temp = "[";
        for(byte x : PUZZLE){
            temp += Byte.toString(x) + " ";
        }
        return temp + "]";
    }
    public void swap(byte indexStart, byte indexEnd){
        byte temp = PUZZLE[indexStart];
        PUZZLE[indexStart] = PUZZLE[indexEnd];
        PUZZLE[indexEnd] = temp;
        if (PUZZLE[indexStart] == 0){
            this.ZERO_INDEX = indexStart;
        } else { 
            this.ZERO_INDEX = indexEnd;
        }
    }
    @Override
    public boolean equals(Object obj){
        if (obj.getClass() == PuzzleState.class){
            PuzzleState temp = (PuzzleState)obj;
            return (Arrays.equals(PUZZLE, temp.getPUZZLE()));
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        for (int i = 0; i < this.PUZZLE.length; i++){
            hash = (this.PUZZLE.length - i) * hash + this.PUZZLE[i];
        }
        hash = 67 * hash + this.M;
        hash = 67 * hash + this.N;
        hash = 67 * hash + this.ZERO_INDEX;
        return hash;
    }
}