package org.example;

public class CalculateAverage implements Runnable{

    private double average;
    private final int[] numbers;
    private boolean done;

    public CalculateAverage(int...numbers) {
        if(numbers == null) throw new IllegalArgumentException();
        this.numbers = numbers;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting average calculation..");
        double sum = 0;
        for(int i : numbers){
            sum+=i;
        }
        if(numbers.length != 0){
            average = sum / numbers.length;
        }else {
            average = 0;
        }
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    public double getAverage() {
        return average;
    }
}
