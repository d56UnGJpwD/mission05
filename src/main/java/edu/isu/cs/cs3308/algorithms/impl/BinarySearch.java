package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class BinarySearch implements ArraySearch
{
    @Override
    public <E extends Comparable> int search(E[] array, E item)
    {
        if(array == null || array.length == 0 || item == null)
        {
            return -1;
        }

        int floor = 0;
        int ceiling = array.length - 1;

        while(floor <= ceiling)
        {
            int index = (floor + ceiling)/2;
            if(array[index] == item)
            {
                return index;
            }
            else if((Integer)item < (Integer)array[index])
            {
                ceiling = index - 1;
            }
            else
            {
                floor = index + 1;
            }
        }
        return -1;
    }
}
