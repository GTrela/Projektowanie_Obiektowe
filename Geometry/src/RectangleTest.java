import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void translateByVector()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));

        rect1.translateByVector(new Point(1,1));

        assertEquals(new Point(4,4), rect1.getTopLeft());
        assertEquals(new Point(18,4), rect1.getTopRight());
        assertEquals(new Point(4,31), rect1.getBottomLeft());
        assertEquals(new Point(18,31), rect1.getBottomRight());
    }

    @Test
    void getTopLeft()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));

        assertEquals(new Point(3,3), rect1.getTopLeft());
    }

    @Test
    void getTopRight()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));

        assertEquals(new Point(17,3), rect1.getTopRight());
    }

    @Test
    void getBottomLeft()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));

        assertEquals(new Point(3,30), rect1.getBottomLeft());
    }

    @Test
    void getBottomRight()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));

        assertEquals(new Point(17,30), rect1.getBottomRight());
    }

    @Test
    void toStringTest()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));

        String test = "{ Lewy górny róg = (3, 3)\n" +
                "Prawy góry róg = (17, 3)\n" +
                "Lewy dolny róg = (3, 30)\n" +
                "Prawy dolny róg = (17, 30) }\n";

        assertEquals(test, rect1.toString());
    }
}