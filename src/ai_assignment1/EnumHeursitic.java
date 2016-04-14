/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

/**
 *
 * @author Adrian
 */
public enum EnumHeursitic {
    Uninformed {
        @Override
        public int value(PuzzleState current, PuzzleState end){
            return 0;
        }
    },
    MissplacedTiles {
        @Override
        public int value(PuzzleState current, PuzzleState end){
            int difference = 0;
            int length = current.getPUZZLE().length;
            for (int i = 0; i < length; i++){
                if ((current.getPUZZLE()[i] != end.getPUZZLE()[i]) && (current.getPUZZLE()[i] != 0)){
                    difference++;
                }
            }
            return difference;
        }
    },
    ManhattanDistance {
        @Override
        public int value(PuzzleState current, PuzzleState end){
            int manDist = 0;
            for( byte col = 1; col <= current.getColumns(); col++){
                for( byte row = 1; row <= current.getRows(); row++){

                    byte index = (byte) ((col * row) - 1);
                    if ((current.getPUZZLE()[index] != end.getPUZZLE()[index]) && (current.getPUZZLE()[index] != 0)){

                        for( byte gCol = 1; gCol <= end.getColumns(); gCol++){
                            for( byte gRow = 1; gRow <= end.getRows(); gRow++){

                                if (current.getPUZZLE()[index] == end.getPUZZLE()[(gCol*gRow) - 1]){
                                    manDist += Math.abs(gCol - col) + Math.abs(gRow - row);
                                }
                            }
                        }
                    } 
                }
            }
            return manDist;
        }
    },
    MissplacedManhattanCombo {
        @Override
        public int value(PuzzleState current, PuzzleState end){
            return MissplacedTiles.value(current, end) + ManhattanDistance.value(current, end);
        }
    },
    EuclideanDistance{
        @Override
        public int value(PuzzleState current, PuzzleState end){
            int euclideanDist = 0;
            for( byte col = 1; col <= current.getColumns(); col++){
                for( byte row = 1; row <= current.getRows(); row++){

                    byte index = (byte) ((col * row) - 1);
                    if ((current.getPUZZLE()[index] != end.getPUZZLE()[index]) && (current.getPUZZLE()[index] != 0)){

                        for( byte gCol = 1; gCol <= end.getColumns(); gCol++){
                            for( byte gRow = 1; gRow <= end.getRows(); gRow++){

                                if (current.getPUZZLE()[index] == end.getPUZZLE()[(gCol*gRow) - 1]){
                                    euclideanDist += Math.abs(Math.sqrt((col - gCol)*(col - gCol) + (row - gRow)*(row - gRow)));
                                    //euclideanDist += Math.abs(col - gCol) + Math.abs(row - gRow);
                                }
                            }
                        }
                    } 
                }
            }
            return euclideanDist;
        }
    
    };
    
    public abstract int value(PuzzleState current, PuzzleState end);
}
