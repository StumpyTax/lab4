import org.example.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixTests {

    @Test
    public void TestOnMultipply(){
        var a=new Matrix(3,2);
        a.setRow(0,new double[]{1,2});
        a.setRow(1,new double[]{2,1});
        a.setRow(2,new double[]{3,4});
        var b=new Matrix(2,3);
        b.setRow(0,new double[]{3,2,1});
        b.setRow(1,new double[]{4,5,6});
        Matrix res=a.multiply(b);
        Assertions.assertArrayEquals(new double[][]{
                new double[]{11,12,13},
                new double[]{10,9,8},
                new double[]{25,26,27}
        },res.getValues());
    }
    @Test
    public void TestDeterminant(){
        var a=new Matrix(3,3);
        a.setRow(0,new double[]{1,2,3});
        a.setRow(1,new double[]{3,2,1});
        a.setRow(2,new double[]{3,4,6});

        Assertions.assertEquals(-4,a.determinant());
    }
}
