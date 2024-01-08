package org.example;

public class Main {
    public static void main(String[] args) {

        var m=new Matrix(3,2);
        m.setRow(0,new double[]{1,1});

        m.print();
    }
}