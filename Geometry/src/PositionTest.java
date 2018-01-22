import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void r1InsideR2()
    {
        Rectangle rect1 = new Rectangle(new Point(3,3), new Point(17,3), new Point(3,30), new Point(17,30));
        Rectangle rect2 = new Rectangle(new Point(0,0), new Point(20,0), new Point(0,50), new Point(20,50));

        assertTrue(Position.R1InsideR2(rect2,rect1));
    }


    @Test
    void r1TangencyR2()
    {
        Rectangle rect1 = new Rectangle(new Point(0,0), new Point(20,0), new Point(0,50), new Point(20,50));
        Rectangle rect2 = new Rectangle(new Point(20,0), new Point(40,0), new Point(20,20), new Point(40,20));

        assertTrue(Position.R1TangencyR2(rect1,rect2));
    }

    @Test
    void r1SharePointsR2()
    {
        Rectangle rect1 = new Rectangle(new Point(0,0), new Point(20,0), new Point(0,50), new Point(20,50));
        Rectangle rect2 = new Rectangle(new Point(19,0), new Point(40,0), new Point(19,20), new Point(40,20));

        assertTrue(Position.R1SharePointsR2(rect1,rect2));
    }
}