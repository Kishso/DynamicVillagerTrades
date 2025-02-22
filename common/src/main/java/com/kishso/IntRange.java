package com.kishso;

public class IntRange {

    private int low;
    private int high;

    public IntRange(int low, int high){
        this.low = low;
        this.high = high;
    }

    public boolean contains(int number){
        return (number >= low && number <= high);
    }
}
