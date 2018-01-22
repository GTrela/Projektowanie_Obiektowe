import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EllipseHorizontalEdgePairTest
{
    Point LeftEdgeTop = new Point(0,0);
    Point LeftEdgeBottom = new Point(2,0);
    Point RightEdgeTop = new Point(0,20);
    Point RightEdgeBottom = new Point(2,20);

    @Test
    void getLeftEdgeTop()
    {
        EllipseHorizontalEdgePair pair = new EllipseHorizontalEdgePair(LeftEdgeTop,LeftEdgeBottom,RightEdgeTop,RightEdgeBottom);

        assertEquals(LeftEdgeTop, pair.getLeftEdgeTop());
    }

    @Test
    void getLeftEdgeBottom()
    {
        EllipseHorizontalEdgePair pair = new EllipseHorizontalEdgePair(LeftEdgeTop,LeftEdgeBottom,RightEdgeTop,RightEdgeBottom);

        assertEquals(LeftEdgeBottom, pair.getLeftEdgeBottom());
    }

    @Test
    void getRightEdgeTop()
    {
        EllipseHorizontalEdgePair pair = new EllipseHorizontalEdgePair(LeftEdgeTop,LeftEdgeBottom,RightEdgeTop,RightEdgeBottom);

        assertEquals(RightEdgeTop, pair.getRightEdgeTop());
    }

    @Test
    void getRightEdgeBottom()
    {
        EllipseHorizontalEdgePair pair = new EllipseHorizontalEdgePair(LeftEdgeTop,LeftEdgeBottom,RightEdgeTop,RightEdgeBottom);

        assertEquals(RightEdgeBottom, pair.getRightEdgeBottom());
    }
}