package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Console;
import java.util.Arrays;

@Getter
public class Matrix {
    private double[][] values;
    private final int rows;
    private final int columns;

    public Matrix(int rows,int columns){
        this.rows=rows;
        this.columns=columns;
        values=new double[rows][columns];
    }
    public double get(int row,int column){
        if(column>=columns|| row>=rows)
            throw new IndexOutOfBoundsException();
        return values[row][column];
    }
    public void setValue(int row,int column,double value){
        if(column>=columns|| row>=rows)
            throw new IndexOutOfBoundsException();
        values[row][column]=value;
    }

    public void setRow(int row,double[] value){
        if(row>=rows)
            throw new IndexOutOfBoundsException();
        if(value.length!=columns)
            throw new RuntimeException("Wrong size of the row");
        values[row]=value;
    }
    public Matrix sum(Matrix b){
        if(b.rows!=this.rows || b.columns!=this.columns)
            throw new RuntimeException("Matrix have dif size");
        for(int i=0;i<rows;i++)
            for (int j=0;j<columns;j++){
                values[i][j]+=b.get(i,j);
            }
        return this;
    }
    public Matrix multiply(Matrix b){
        if(this.columns!=b.rows)
            throw new RuntimeException("Wrong sizes");
        Matrix res=new Matrix(this.rows,b.columns);
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < b.columns; j++) {
                double tmp = 0d;
                for(int m=0;m<columns;m++){
                    tmp+=values[i][m]*b.values[m][j];
                }
                res.setValue(i,j,tmp);
            }
        }
        return res;
    }
    public Matrix multiply(double b){
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < columns; j++) {
                values[i][j]*=b;
            }
        }
        return this;
    }
    public Matrix transponse(){
        Matrix res=new Matrix(this.columns,this.rows);
        for(int i=0;i<this.columns;i++){
            for (int j=0;j<this.rows;j++){
                res.setValue(i,j,values[i][j]);
            }
        }
        return res;
    }
    private void determinant_(Matrix subMinor,double elemParentMinor,Res summ){
        if (subMinor.values.length > 1){
            Matrix tmpMinor = new Matrix(subMinor.rows - 1,subMinor.columns-1);
            for (int c = 0; c < subMinor.columns; c++) {
                for (int i = 1; i < subMinor.rows; i++) {
                    for (int j = 0; j < subMinor.columns; j++) {
                        if (j < c)
                            tmpMinor.setValue(i - 1,j,subMinor.values[i][j]);
                        else if (j > c)
                            tmpMinor.setValue(i - 1,j - 1,subMinor.values[i][j]);
                    }
                }
                double paramForSub = Math.pow(-1,c+2)*subMinor.values[0][c]*elemParentMinor;
                determinant_(tmpMinor, paramForSub,summ);
            }
        }
        else
            summ.res += elemParentMinor * subMinor.values[0][0];
    }
    @NoArgsConstructor
    private class Res{
        public double res=0;

    }
    public double determinant(){
        Res res=new Res();
        determinant_(this,1,res);
        return res.res;
    }
    public Matrix getInverse (){
        return transponse().multiply(1/determinant());
    }
    public void print(){
        for(var rows:values){
            System.out.println(Arrays.toString(rows));
        }
    }

}
