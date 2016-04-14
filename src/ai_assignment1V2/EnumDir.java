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
public enum EnumDir {
    ROOT((byte)-1),
    UP((byte)0),
    LEFT((byte)1),
    DOWN((byte)2),
    RIGHT((byte)3);
    private final byte value;
    EnumDir(final byte newValue){this.value = newValue;}
    public byte getValue(){ return value;}
}
