/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1V2;

import java.util.Comparator;

/**
 *
 * @author Adrian
 * @param <T>
 */
public interface SearchAlgorithm<T> {

    int numExploredStates = 0;
    int numGeneratedNodes = 0;

    String name();

    EnumHeursitic getHeursitic();

    int getNodeCost(T t);

    Comparator<T> getComparator();

    int getLimit();

    void setLimit(T b, T a);

    void setLimit(int a);

    boolean limitReached(T a);

    T search(T a, T b);
}
