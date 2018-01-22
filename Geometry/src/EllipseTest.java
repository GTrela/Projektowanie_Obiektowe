import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTest {

    Point TopEdgeLeft = new Point(73,40);
    Point TopEdgeRight = new Point(86,40);
    Point BottomEdgeLeft = new Point(73,177);
    Point BottomEdgeRight = new Point(86,177);
    Point LeftEdgeTop = new Point(33,98);
    Point LeftEdgeBottom = new Point(33,119);
    Point RightEdgeTop = new Point(126,98);
    Point RightEdgeBottom = new Point(126,119);

    @org.junit.jupiter.api.Test
    void getFramingRectangle()
    {
        Ellipse ellipse = new Ellipse.EllipseBuilder()
                .TopEdgeLeftPoint(TopEdgeLeft)
                .TopEdgeRightPoint(TopEdgeRight)
                .BottomEdgeLeftPoint(BottomEdgeLeft)
                .BottomEdgeRightPoint(BottomEdgeRight)
                .LeftEdgeTopPoint(LeftEdgeTop)
                .LeftEdgeBottomPoint(LeftEdgeBottom)
                .RightEdgeTopPoint(RightEdgeTop)
                .RightEdgeBottomPoint(RightEdgeBottom).createEllipse();

        Rectangle expectedRectangle = new Rectangle(new Point(33,40), new Point(126,40), new Point(33,177), new Point(126,177));
        assertEquals(expectedRectangle.getTopLeft(), ellipse.getFramingRectangle().getTopLeft());
        assertEquals(expectedRectangle.getTopRight(), ellipse.getFramingRectangle().getTopRight());
        assertEquals(expectedRectangle.getBottomLeft(), ellipse.getFramingRectangle().getBottomLeft());
        assertEquals(expectedRectangle.getBottomRight(), ellipse.getFramingRectangle().getBottomRight());
    }

    @org.junit.jupiter.api.Test
    void translateByVector()
    {
        Point vector = new Point(2,4);

        Ellipse ellipse = new Ellipse.EllipseBuilder()
                .TopEdgeLeftPoint(TopEdgeLeft)
                .TopEdgeRightPoint(TopEdgeRight)
                .BottomEdgeLeftPoint(BottomEdgeLeft)
                .BottomEdgeRightPoint(BottomEdgeRight)
                .LeftEdgeTopPoint(LeftEdgeTop)
                .LeftEdgeBottomPoint(LeftEdgeBottom)
                .RightEdgeTopPoint(RightEdgeTop)
                .RightEdgeBottomPoint(RightEdgeBottom).createEllipse();

        Point ExpectedTopEdgeLeft = new Point(75,44);
        Point ExpectedTopEdgeRight = new Point(88,44);
        Point ExpectedBottomEdgeLeft = new Point(75,181);
        Point ExpectedBottomEdgeRight = new Point(88,181);
        Point ExpectedLeftEdgeTop = new Point(35,102);
        Point ExpectedLeftEdgeBottom = new Point(35,123);
        Point ExpectedRightEdgeTop = new Point(128,102);
        Point ExpectedRightEdgeBottom = new Point(128,123);

        ellipse.translateByVector(vector);

        assertEquals(ExpectedTopEdgeLeft, ellipse.getTopEdgeLeft());
        assertEquals(ExpectedTopEdgeRight, ellipse.getTopEdgeRight());
        assertEquals(ExpectedBottomEdgeLeft, ellipse.getBottomEdgeLeft());
        assertEquals(ExpectedBottomEdgeRight, ellipse.getBottomEdgeRight());
        assertEquals(ExpectedLeftEdgeTop, ellipse.getLeftEdgeTop());
        assertEquals(ExpectedLeftEdgeBottom, ellipse.getLeftEdgeBottom());
        assertEquals(ExpectedRightEdgeTop, ellipse.getRightEdgeTop());
        assertEquals(ExpectedRightEdgeBottom, ellipse.getRightEdgeBottom());
    }

    @org.junit.jupiter.api.Test
    void distanceBetweenTwoPoints()
    {
        Ellipse ellipse = new Ellipse.EllipseBuilder()
                .TopEdgeLeftPoint(TopEdgeLeft)
                .TopEdgeRightPoint(TopEdgeRight)
                .BottomEdgeLeftPoint(BottomEdgeLeft)
                .BottomEdgeRightPoint(BottomEdgeRight)
                .LeftEdgeTopPoint(LeftEdgeTop)
                .LeftEdgeBottomPoint(LeftEdgeBottom)
                .RightEdgeTopPoint(RightEdgeTop)
                .RightEdgeBottomPoint(RightEdgeBottom).createEllipse();

        Point p1 = new Point(2,3);
        Point p2 = new Point(10,9);

        assertEquals(10, ellipse.distanceBetweenTwoPoints(p1,p2));
    }

    @org.junit.jupiter.api.Test
    void toStringTest()
    {
        Ellipse ellipse = new Ellipse.EllipseBuilder()
                .TopEdgeLeftPoint(TopEdgeLeft)
                .TopEdgeRightPoint(TopEdgeRight)
                .BottomEdgeLeftPoint(BottomEdgeLeft)
                .BottomEdgeRightPoint(BottomEdgeRight)
                .LeftEdgeTopPoint(LeftEdgeTop)
                .LeftEdgeBottomPoint(LeftEdgeBottom)
                .RightEdgeTopPoint(RightEdgeTop)
                .RightEdgeBottomPoint(RightEdgeBottom).createEllipse();

        assertEquals("{ Środek = (79, 108), a = 68.0, b = 46.0 }\n", ellipse.toString());
    }
}