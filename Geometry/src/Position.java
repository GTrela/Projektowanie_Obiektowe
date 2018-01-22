import java.awt.*;

public class Position
{
	public static boolean R1InsideR2(Rectangle rectangle1, Rectangle rectangle2)
	{
		if (Point1Between2Points(rectangle2.getTopLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& (Point1Between2Points(rectangle2.getBottomRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean R1ContainsR2(Rectangle rectangle1, Rectangle rectangle2)
	{
		if (PointInsideSquareBetween2Points(rectangle2.getTopLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& (PointInsideSquareBetween2Points(rectangle2.getBottomRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean R1TangencyR2(Rectangle rectangle1, Rectangle rectangle2)
	{
		Point topLeftPoint = new Point (rectangle1.getTopLeft().x-1,rectangle1.getTopLeft().y-1);
		Point bottomRightPoint = new Point (rectangle1.getBottomRight().x+1,rectangle1.getBottomRight().y+1);
		// test for Top Left Point
		if (PointInsideSquareBetween2Points(rectangle2.getTopLeft(), topLeftPoint, bottomRightPoint)
				&& !(Point1Between2Points(rectangle2.getTopLeft(), topLeftPoint, bottomRightPoint)))
		{
			return true;
		}
		//test for Top Right Point
		if (PointInsideSquareBetween2Points(rectangle2.getTopRight(), topLeftPoint, bottomRightPoint)
				&& !(Point1Between2Points(rectangle2.getTopRight(), topLeftPoint, bottomRightPoint)))
		{
			return true;
		}
		//test for Bottom Left Point
		if (PointInsideSquareBetween2Points(rectangle2.getBottomLeft(), topLeftPoint, bottomRightPoint)
				&& !(Point1Between2Points(rectangle2.getBottomLeft(), topLeftPoint, bottomRightPoint)))
		{
			return true;
		}
		//test for Bottom Right Point
		if (PointInsideSquareBetween2Points(rectangle2.getBottomRight(), topLeftPoint, bottomRightPoint)
				&& !(Point1Between2Points(rectangle2.getBottomRight(), topLeftPoint, bottomRightPoint)))
		{
			return true;
		}

		return false;
	}

	public static boolean R1SharePointsR2(Rectangle rectangle1, Rectangle rectangle2)
	{
		if (R1InsideR2(rectangle1,rectangle2) || R1InsideR2(rectangle2,rectangle1))
		{
			return false;
		}
		if ((rectangle1.getTopLeft().x > rectangle2.getBottomRight().x) ||
				(rectangle1.getBottomRight().x  < rectangle2.getTopLeft().x) ||
				(rectangle1.getTopLeft().y > rectangle2.getBottomRight().y) ||
				(rectangle1.getBottomRight().y < rectangle2.getTopLeft().y))
		{
			return false;
		}
		else
		{
			return true;
		}

	}


	static boolean Point1Between2Points(Point point1, Point topLeftPoint, Point bottomRightPoint)
	{
		if (point1 != null && topLeftPoint != null && bottomRightPoint != null
				&& topLeftPoint.x < point1.x && point1.x < bottomRightPoint.x
				&& topLeftPoint.y < point1.y && point1.y < bottomRightPoint.y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	static boolean PointInsideSquareBetween2Points(Point point, Point topLeftPoint, Point bottomRightPoint)
	{
		if (point != null && topLeftPoint != null && bottomRightPoint != null
				&& topLeftPoint.x <= point.x && point.x <= bottomRightPoint.x
				&& topLeftPoint.y <= point.y && point.y <= bottomRightPoint.y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
