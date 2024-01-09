package org.example;

public class Main {
    public static void main(String[] args) {
        var res= NelderMeed.findMin(1d,0.5,2d,10d,(x, y)->(y-x*x)*(y-x*x)+100*(1-x)*(1-x));
        System.out.println(res.get(0)+" "+res.get(1));
    }
}