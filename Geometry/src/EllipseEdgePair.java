import java.awt.*;

class EllipseVerticalEdgePair
{
    Point TopEdgeLeft;
    Point TopEdgeRight;
    Point BottomEdgeLeft;
    Point BottomEdgeRight;

    public EllipseVerticalEdgePair(Point TopEdgeLeft, Point TopEdgeRight, Point BottomEdgeLeft, Point BottomEdgeRight)
    {
        this.TopEdgeLeft = TopEdgeLeft;
        this.TopEdgeRight = TopEdgeRight;
        this.BottomEdgeLeft = BottomEdgeLeft;
        this.BottomEdgeRight = BottomEdgeRight;
    }

    public Point getTopEdgeLeft()
    {
        return TopEdgeLeft;
    }

    public Point getTopEdgeRight()
    {
        return TopEdgeRight;
    }

    public Point getBottomEdgeLeft()
    {
        return BottomEdgeLeft;
    }

    public Point getBottomEdgeRight()
    {
        return BottomEdgeRight;
    }
}

class EllipseHorizontalEdgePair
{
    Point LeftEdgeTop;
    Point LeftEdgeBottom;
    Point RightEdgeTop;
    Point RightEdgeBottom;

    public EllipseHorizontalEdgePair(Point LeftEdgeTop, Point LeftEdgeBottom, Point RightEdgeTop, Point RightEdgeBottom)
    {
        this.LeftEdgeTop = LeftEdgeTop;
        this.LeftEdgeBottom = LeftEdgeBottom;
        this.RightEdgeTop = RightEdgeTop;
        this.RightEdgeBottom = RightEdgeBottom;
    }

    public Point getLeftEdgeTop()
    {
        return LeftEdgeTop;
    }

    public Point getLeftEdgeBottom()
    {
        return LeftEdgeBottom;
    }

    public Point getRightEdgeTop()
    {
        return RightEdgeTop;
    }

    public Point getRightEdgeBottom()
    {
        return RightEdgeBottom;
    }
}
