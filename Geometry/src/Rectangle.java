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

    public void translateByVector (Point vector)
    {

	    this.TopLeft.x += vector.x;
	    this.TopRight.x += vector.x;
	    this.BottomLeft.x += vector.x;
	    this.BottomRight.x += vector.x;

	    this.TopLeft.y += vector.y;
	    this.TopRight.y += vector.y;
	    this.BottomLeft.y += vector.y;
	    this.BottomRight.y += vector.y;
    }

	public Point getTopLeft()
	{
		return TopLeft;
	}

	public Point getTopRight()
	{
		return TopRight;
	}

	public Point getBottomLeft()
	{
		return BottomLeft;
	}

	public Point getBottomRight()
	{
		return BottomRight;
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

	@Override
	public String toString()
	{
		return "Prostokąt {" +
				"\nLewy górny róg = (" + TopLeft.x + ", " + TopLeft.y + ")" +
				"\nPrawy góry róg = (" + TopRight.x + ", " + TopRight.y + ")" +
				"\nLewy dolny róg = (" + BottomLeft.x + ", " + BottomLeft.y + ")" +
				"\nPrawy dolny róg = (" + BottomRight.x + ", " + BottomRight.y + ")" +
				" }\n";
	}
}
