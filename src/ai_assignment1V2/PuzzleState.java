package ai_assignment1V2;

import java.util.Arrays;

public class PuzzleState implements Puzzle {

    //PRIVATE VARIABLES
    private byte[] DATA; //int[ROWS][COLUMNS]
    private byte M;
    private byte N;
    private byte ZERO_INDEX; //ZERO POSITION

    //Construstor
    public PuzzleState(byte[] puzzle, byte nColumns, byte nRows) {
        this.M = nColumns;
        this.N = nRows;
        this.DATA = new byte[M * N];
        for (byte i = 0; i < this.DATA.length; i++) {
            this.DATA[i] = puzzle[i];
            if (this.DATA[i] == 0) {
                this.ZERO_INDEX = i;
            }
        }
    }

    public PuzzleState(byte[][] puzzle) {
        this.M = (byte) puzzle[0].length;
        this.N = (byte) puzzle.length;
        byte index = 0;
        this.DATA = new byte[M * N];
        for (byte n = 0; n < N; n++) {
            for (byte m = 0; m < M; m++) {
                this.DATA[index] = puzzle[n][m];
                if (this.DATA[index] == 0) {
                    this.ZERO_INDEX = index;
                }
                index++;
            }
        }
    }

    /**
     * Copy Constructor of the class
     *
     * @param copy
     */
    public PuzzleState(PuzzleState copy) {
        this.M = copy.getColumns();
        this.N = copy.getRows();
        this.DATA = new byte[M * N];
        byte[] puzzle = copy.getData(); //shallow copy
        for (byte i = 0; i < this.DATA.length; i++) { //deep copy
            this.DATA[i] = puzzle[i];
            if (this.DATA[i] == 0) {
                this.ZERO_INDEX = i;
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public byte getColumns() {
        return M;
    }

    /**
     *
     * @return
     */
    @Override
    public byte getRows() {
        return N;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < DATA.length; i++) {
            if (i % getColumns() == 0) {
                temp += "\n";
            }
            temp += Byte.toString(DATA[i]) + " ";
        }
        return temp;
    }

    /**
     *
     * @param indexStart
     * @param indexEnd
     */
    @Override
    public void swap(byte indexStart, byte indexEnd) {
        byte temp = DATA[indexStart];
        DATA[indexStart] = DATA[indexEnd];
        DATA[indexEnd] = temp;
        if (DATA[indexStart] == 0) {
            this.ZERO_INDEX = indexStart;
        } else {
            this.ZERO_INDEX = indexEnd;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == PuzzleState.class) {
            PuzzleState temp = (PuzzleState) obj;
            return (Arrays.equals(DATA, temp.getData()));
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.M;
        hash = 67 * hash + this.N;
        hash = 67 * hash + this.ZERO_INDEX;
        for (int i = 0; i < this.DATA.length; i++) {
            hash = (this.DATA.length - i) * hash + this.DATA[i];
        }
        return hash;
    }

    /**
     *
     * @return
     */
    @Override
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public byte[] getData() {
        return this.DATA;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public byte getIndex(byte item) {
        return this.ZERO_INDEX;
    }

    /**
     *
     * @param copy
     */
    @Override
    public void deepCopy(Puzzle copy) {
        this.M = copy.getColumns();
        this.N = copy.getRows();
        this.DATA = new byte[M * N];
        byte[] puzzle = copy.getData(); //shallow copy
        for (byte i = 0; i < this.DATA.length; i++) { //deep copy
            this.DATA[i] = puzzle[i];
            if (this.DATA[i] == 0) {
                this.ZERO_INDEX = i;
            }
        }

    }

    /**
     *
     * @return
     */
    @Override
    public Puzzle returnDeepCopy() {
        return new PuzzleState(this);
    }

}
