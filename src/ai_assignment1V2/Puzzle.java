/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

/**
 *
 * @author Adrian
 */
public interface Puzzle {
    byte[] getData();
    byte getIndex(byte item);
    byte getColumns();
    byte getRows();
    void swap(byte a, byte b);
    void deepCopy(Puzzle copy);
    Puzzle returnDeepCopy();
    @Override
    String toString();
    @Override
    boolean equals(Object obj);
    @Override
    int hashCode();
    
}
