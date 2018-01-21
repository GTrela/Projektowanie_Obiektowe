import java.awt.*;

public class Ellipse
{
    // Top Edge
    private final Point TopEdgeLeft;
    private final Point TopEdgeRight;

    // Bottom Edge
    private final Point BottomEdgeLeft;
    private final Point BottomEdgeRight;

    // Left Edge
    private final Point LeftEdgeTop;
    private final Point LeftEdgeBottom;

    // Right Edge
    private final Point RightEdgeTop;
    private final Point RightEdgeBottom;

    public Ellipse(Point TopEdgeLeft,
                   Point TopEdgeRight,
                   Point BottomEdgeLeft,
                   Point BottomEdgeRight,
                   Point LeftEdgeTop,
                   Point LeftEdgeBottom,
                   Point RightEdgeTop,
                   Point RightEdgeBottom)
    {
        this.TopEdgeLeft = TopEdgeLeft;
        this.TopEdgeRight = TopEdgeRight;
        this.BottomEdgeLeft = BottomEdgeLeft;
        this.BottomEdgeRight = BottomEdgeRight;
        this.LeftEdgeTop = LeftEdgeTop;
        this.LeftEdgeBottom = LeftEdgeBottom;
        this.RightEdgeTop = RightEdgeTop;
        this.RightEdgeBottom = RightEdgeBottom;
    }

    public Rectangle getFramingRectangle()
    {
        Point TopLeft = new Point(LeftEdgeTop.x, TopEdgeLeft.y);
        Point TopRight = new Point(RightEdgeTop.x, TopEdgeRight.y);
        Point BottomLeft = new Point(LeftEdgeBottom.x, BottomEdgeLeft.y);
        Point BottomRight = new Point(RightEdgeBottom.x, BottomEdgeRight.y);

        return new Rectangle(TopLeft, TopRight, BottomLeft, BottomRight);
    }

    public void translateByVector(Point vector)
    {
        this.TopEdgeLeft.x += vector.x;
        this.TopEdgeRight.x += vector.x;
        this.BottomEdgeLeft.x += vector.x;
        this.BottomEdgeRight.x += vector.x;
        this.LeftEdgeTop.x += vector.x;
        this.LeftEdgeBottom.x += vector.x;
        this.RightEdgeTop.x += vector.x;
        this.RightEdgeBottom.x += vector.x;

        this.TopEdgeLeft.y += vector.y;
        this.TopEdgeRight.y += vector.y;
        this.BottomEdgeLeft.y += vector.y;
        this.BottomEdgeRight.y += vector.y;
        this.LeftEdgeTop.y += vector.y;
        this.LeftEdgeBottom.y += vector.y;
        this.RightEdgeTop.y += vector.y;
        this.RightEdgeBottom.y += vector.y;
    }

    public double distanceBetweenTwoPoints(Point p1, Point p2)
    {
        return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
    }

    @Override
    public String toString()
    {
        Point ellipseCenter = new Point(((int) (TopEdgeLeft.x + TopEdgeRight.x))/2,
                (int)(TopEdgeLeft.y + BottomEdgeLeft.y)/2);

        Point centerTop = new Point((int)((TopEdgeLeft.x + TopEdgeRight.x)/2), (int)((TopEdgeLeft.y + TopEdgeRight.y)/2));
        Point centerLeft = new Point((int)((LeftEdgeBottom.x + LeftEdgeTop.x)/2), (int)((LeftEdgeBottom.y + LeftEdgeTop.y)/2));

        double a = distanceBetweenTwoPoints(centerTop, ellipseCenter);
        double b = distanceBetweenTwoPoints(centerLeft, ellipseCenter);

        return "{ Środek = (" + ellipseCenter.x + ", " + ellipseCenter.y + ")" +
                ", a = " + a +
                ", b = " + b +
                " }\n";
    }

    public static class EllipseBuilder
    {
        // Top Edge
        private Point NestedTopEdgeLeft;
        private Point NestedTopEdgeRight;

        // Bottom Edge
        private Point NestedBottomEdgeLeft;
        private Point NestedBottomEdgeRight;

        // Left Edge
        private Point NestedLeftEdgeTop;
        private Point NestedLeftEdgeBottom;

        // Right Edge
        private Point NestedRightEdgeTop;
        private Point NestedRightEdgeBottom;

        public EllipseBuilder(){}

        public EllipseBuilder TopEdgeLeftPoint(Point TopEdgeLeft)
        {
            this.NestedTopEdgeLeft = TopEdgeLeft;
            return this;
        }

        public EllipseBuilder TopEdgeRightPoint(Point TopEdgeRight)
        {
            this.NestedTopEdgeRight = TopEdgeRight;
            return this;
        }

        public EllipseBuilder BottomEdgeLeftPoint(Point BottomEdgeLeft)
        {
            this.NestedBottomEdgeLeft = BottomEdgeLeft;
            return this;
        }

        public EllipseBuilder BottomEdgeRightPoint(Point BottomEdgeRight)
        {
            this.NestedBottomEdgeRight = BottomEdgeRight;
            return this;
        }

        public EllipseBuilder LeftEdgeTopPoint(Point LeftEdgeTop)
        {
            this.NestedLeftEdgeTop = LeftEdgeTop;
            return this;
        }

        public EllipseBuilder LeftEdgeBottomPoint(Point LeftEdgeBottom)
        {
            this.NestedLeftEdgeBottom = LeftEdgeBottom;
            return this;
        }

        public EllipseBuilder RightEdgeTopPoint(Point RightEdgeTop)
        {
            this.NestedRightEdgeTop = RightEdgeTop;
            return this;
        }

        public EllipseBuilder RightEdgeBottomPoint(Point RightEdgeBottom)
        {
            this.NestedRightEdgeBottom = RightEdgeBottom;
            return this;
        }

        public Ellipse createEllipse()
        {
            return new Ellipse(
                    NestedTopEdgeLeft, NestedTopEdgeRight, NestedBottomEdgeLeft, NestedBottomEdgeRight,
                    NestedLeftEdgeTop, NestedLeftEdgeBottom, NestedRightEdgeTop, NestedRightEdgeBottom);
        }
    }

}
