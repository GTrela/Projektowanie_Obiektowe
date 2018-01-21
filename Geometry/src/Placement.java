import java.awt.*;
import java.util.ArrayList;

public class Placement
{
	public static boolean R1ContatinsR2(Rectangle rectangle1, Rectangle rectangle2)
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

	public static boolean R1InsideR2(Rectangle rectangle1, Rectangle rectangle2)
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
		// test for Top Left Point
		if (PointInsideSquareBetween2Points(rectangle2.getTopLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& (Point1Between2Points(rectangle2.getTopLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}
		//test for Top Right Point
		if (PointInsideSquareBetween2Points(rectangle2.getTopRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& !(Point1Between2Points(rectangle2.getTopRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}
		//test for Bottom Left Point
		if (PointInsideSquareBetween2Points(rectangle2.getBottomLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& !(Point1Between2Points(rectangle2.getBottomLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}
		//test for Bottom Right Point
		if (PointInsideSquareBetween2Points(rectangle2.getBottomRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& !(Point1Between2Points(rectangle2.getBottomRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}

		return false;
	}

	public static boolean R1SharePointsR2(Rectangle rectangle1, Rectangle rectangle2)
	{
		if (R1InsideR2(rectangle1,rectangle2))
		{
			return false;
		}
		else
		{
			if (PointInsideSquareBetween2Points(rectangle2.getTopLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
					|| PointInsideSquareBetween2Points(rectangle2.getTopRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
					|| (PointInsideSquareBetween2Points(rectangle2.getBottomRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight()))
					|| (PointInsideSquareBetween2Points(rectangle2.getBottomLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
			{
				return true;
			}
			return false;
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
