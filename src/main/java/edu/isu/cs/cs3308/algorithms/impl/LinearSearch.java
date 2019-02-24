package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class LinearSearch implements ArraySearch
{
    @Override
    public <E extends Comparable> int search(E[] array, E item)
    {
        if(array == null || array.length == 0 || item == null)
        {
            return -1;
        }
        for(int i = 0; i < array.length - 1; i++)
        {
            if(array[i] == item)
            {
                return i;
            }
        }
        return -1;
    }
}
