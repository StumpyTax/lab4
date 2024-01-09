package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

public class NelderMeed {


    public static Vector findMin(Double alpha, Double beta, Double gamma,
                                 Double itter, BiFunction<Double,Double,Double> f){
        var points=new Vector[]{
                new Vector(new Double[]{0d,0d}),
                new Vector(new Double[]{1d,0d}),
                new Vector(new Double[]{0d,1d})};
        Vector res=points[0];
        Comparator<Vector> comparator=new Comparator<Vector>() {
            @Override
            public int compare(Vector o1, Vector o2) {
                return Double.compare(f.apply(o1.get(0),o1.get(1)),f.apply(o2.get(0),o2.get(1)));
            }
        };
        var sortedPoints=points;
        for(int i=0;i<itter;i++){
             sortedPoints=Arrays.stream(sortedPoints).sorted(comparator).toList().toArray(new Vector[]{});

            var mid=sortedPoints[0].sum(sortedPoints[1]).multiply(0.5);

            var xr=mid.sum(mid.diff(sortedPoints[2]).multiply(alpha));
            if(f.apply(xr.get(0),xr.get(1))<f.apply(sortedPoints[1].get(0),sortedPoints[1].get(1))){
                sortedPoints[2]=xr;
            }
            else{
                if(f.apply(xr.get(0),xr.get(1))<f.apply(sortedPoints[2].get(0),sortedPoints[2].get(1))){
                    sortedPoints[2]=xr;
                }
                var c=sortedPoints[2].sum(mid).multiply(0.5);
                if(f.apply(c.get(0),c.get(1))<f.apply(sortedPoints[2].get(0),sortedPoints[2].get(1))){
                    sortedPoints[2]=c;
                }
            }
            if(f.apply(xr.get(0),xr.get(1))<f.apply(sortedPoints[0].get(0),sortedPoints[0].get(1)))
            {
                var xe=mid.sum(xr.diff(mid).multiply(gamma));
                if(f.apply(xe.get(0),xe.get(1))<f.apply(xr.get(0),xr.get(1)))
                    sortedPoints[2]=xe;
                else
                    sortedPoints[2]=xr;
            }
            if(f.apply(xr.get(0),xr.get(1))>f.apply(sortedPoints[1].get(0),sortedPoints[1].get(1)))
            {
                var xc=mid.sum(sortedPoints[2].diff(mid).multiply(beta));
                if(f.apply(xc.get(0),xc.get(1))<f.apply(sortedPoints[2].get(0),sortedPoints[2].get(1)))
                    sortedPoints[2]=xc;
            }
            res=sortedPoints[0];
        }
        return res;
    }
}
