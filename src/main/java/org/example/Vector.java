package org.example;

import java.util.Objects;
import java.util.function.BiFunction;

public class Vector {
    private final Double[] x;

    public Vector(Double[] x) {
        this.x = x;
    }

    public Double get(int index) {
        return x[index];
    }

    public Vector sum(Vector other) {
        if (!Objects.equals(getOrder(), other.getOrder()))
            throw new RuntimeException();
        return forEach(other, (x, y) -> x + y);
    }

    public Vector diff(Vector other) {
        if (!Objects.equals(getOrder(), other.getOrder()))
            throw new RuntimeException();
        return forEach(other, (x, y) -> x - y);
    }

    public Double scalarMultiplication(Vector other) {
        if (!Objects.equals(getOrder(), other.getOrder()))
            throw new RuntimeException();
        double result = 0d;
        for (var i = 0; i < getOrder(); i++) {
            result += get(i) * other.get(i);
        }
        return result;
    }

    public Vector multiply(Double x) {
        Double[] result = new Double[getOrder()];
        for (var i = 0; i < getOrder(); i++) {
            result[i] = get(i) * x;
        }
        return new Vector(result);
    }

    public Double length() {
        double result = 0d;
        for (var i = 0; i < getOrder(); i++) {
            result += get(i) * get(i);
        }
        return Math.sqrt(result);
    }

    public Vector normalize() {
        double len = length();
        Double[] result = new Double[getOrder()];
        for (var i = 0; i < getOrder(); i++) {
            result[i] = get(i) / len;
        }
        return new Vector(result);
    }

    private Vector forEach(Vector a, BiFunction<Double, Double, Double> f) {
        Double[] nList = new Double[a.getOrder()];
        if (this.x.length != a.x.length) throw new RuntimeException();
        for (var i = 0; i < this.x.length; i++) {
            nList[i] = f.apply(this.x[i], a.x[i]);
        }
        return new Vector(nList);
    }

    public Integer getOrder() {
        return x.length;
    }
}
