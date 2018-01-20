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
