import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Rectangle
{
    Point TopLeft;
    Point TopRight;
    Point BottomLeft;
    Point BottomRight;

    public Rectangle(Point TopLeft, Point TopRight, Point BottomLeft, Point BottomRight)
    {
        this.TopLeft = TopLeft;
        this.TopRight = TopRight;
        this.BottomLeft = BottomLeft;
        this.BottomRight = BottomRight;
    }

    public List<Point> getPoints()
    {
        List<Point> rectanglePoints = new ArrayList<>();

        rectanglePoints.add(this.TopLeft);
        rectanglePoints.add(this.TopRight);
        rectanglePoints.add(this.BottomLeft);
        rectanglePoints.add(this.BottomRight);

        for (int heightIndex = TopLeft.y+1; heightIndex < BottomLeft.y; heightIndex++)
        {
            rectanglePoints.add(new Point(this.TopLeft.x, heightIndex));
            rectanglePoints.add(new Point(this.TopRight.x, heightIndex));
        }

        for (int lengthIndex = TopLeft.x+1; lengthIndex < TopRight.x; lengthIndex++)
        {
            rectanglePoints.add(new Point(lengthIndex, this.TopLeft.y));
            rectanglePoints.add(new Point(lengthIndex, this.BottomLeft.y));
        }

        return rectanglePoints;
    }
}
