package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class RecursiveLinearSearch implements ArraySearch
{
    @Override
    public <E extends Comparable> int search(E[] array, E item)
    {
        return recLinearSearch(array, item, 0);
    }

    private <E extends Comparable> int recLinearSearch(E[] array, E item, int index)
    {
        if(array == null || array.length == 0 || item == null || index >= array.length)
        {
            return -1;
        }
        if(array[index] == item)
        {
            return -1
        }
    }
}
