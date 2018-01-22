import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EllipseVerticalEdgePairTest {

    Point TopEdgeLeft = new Point(0,0);
    Point TopEdgeRight = new Point(2,0);
    Point BottomEdgeLeft = new Point(0,20);
    Point BottomEdgeRight = new Point(2,20);

    @Test
    void getTopEdgeLeft()
    {
        EllipseVerticalEdgePair pair = new EllipseVerticalEdgePair(TopEdgeLeft, TopEdgeRight, BottomEdgeLeft, BottomEdgeRight);

        assertEquals(TopEdgeLeft, pair.getTopEdgeLeft());
    }

    @Test
    void getTopEdgeRight()
    {
        EllipseVerticalEdgePair pair = new EllipseVerticalEdgePair(TopEdgeLeft, TopEdgeRight, BottomEdgeLeft, BottomEdgeRight);

        assertEquals(TopEdgeRight, pair.getTopEdgeRight());
    }

    @Test
    void getBottomEdgeLeft()
    {
        EllipseVerticalEdgePair pair = new EllipseVerticalEdgePair(TopEdgeLeft, TopEdgeRight, BottomEdgeLeft, BottomEdgeRight);

        assertEquals(BottomEdgeLeft, pair.getBottomEdgeLeft());
    }

    @Test
    void getBottomEdgeRight()
    {
        EllipseVerticalEdgePair pair = new EllipseVerticalEdgePair(TopEdgeLeft, TopEdgeRight, BottomEdgeLeft, BottomEdgeRight);

        assertEquals(BottomEdgeRight, pair.getBottomEdgeRight());
    }
}