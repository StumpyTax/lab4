import org.example.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class VectorTests {

        @Test
        public void LengthTest()
        {
            var v1 = new Vector(new Double[]{1d, 2d, -1d});
            var v2 = new Vector(new Double[]{3d, 4d});
            var v3 = new Vector(new Double[]{0d, 0d});

            Assertions.assertEquals(v2.length(), 5d);
            Assertions.assertEquals(v3.length(), 0d);
        }

        @Test
        public void SumTest()
        {
            var v1 = new Vector(new Double[]{0d, 0d});
            var v2 = new Vector(new Double[]{2d, 2d});
            var v3 = new Vector(new Double[]{1d, 2d});
            var v4 = new Vector(new Double[]{3d, 4d});

            Assertions.assertEquals(v1.sum(v4).length(), 5d);
            Assertions.assertEquals(v2.sum(v3).length(), 5d);
        }
        @Test
        public void ScalarMultipTest()
        {
                  var v1 = new Vector(new Double[]{0d, 0d});
            var v2 = new Vector(new Double[]{2d, 2d});

            Assertions.assertEquals(v1.scalarMultiplication(v2), 0d);
            Assertions.assertEquals(v2.scalarMultiplication(v2), 8d);

        }
        @Test
        public void NormalizeTest()
        {
            System.out.println();
            var v1 = new Vector(new Double[]{0d, 2d});
            var normal = v1.normalize();

            Assertions.assertEquals(normal.length(), 1d);
            Assertions.assertEquals(normal.multiply(2d).length(), v1.length());

        }

    }

