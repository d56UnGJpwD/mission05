package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class RecursiveBinarySearch implements ArraySearch
{
    @Override
    public <E extends Comparable> int search(E[] array, E item)
    {
        if(array == null || array.length == 0 || item == null)
        {
            return -1;
        }
        return recBinarySearch(array, item, 0, array.length - 1);
    }

    public <E extends Comparable> int recBinarySearch(E[] array, E item, int floor, int ceiling)
    {
        int index = (floor + ceiling)/2;

        if(floor >= ceiling)
        {
            return -1;
        }
        if(array[index] == item)
        {
            return index;
        }
        if((Integer)item < (Integer)array[index])
        {
            return recBinarySearch(array, item, floor, index -1);
        }
        else
        {
            return recBinarySearch(array, item, index+1, ceiling);
        }
    }
}
