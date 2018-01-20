import java.awt.*;

public class Placement
{
	static boolean R1ContatinsR2(Rectangle rectangle1, Rectangle rectangle2)
	{
		if ( Point1Between2Points(rectangle2.getTopLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight())
				&& (Point1Between2Points(rectangle2.getBottomLeft(), rectangle1.getTopLeft(), rectangle1.getBottomRight()))
				&& (Point1Between2Points(rectangle2.getTopRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight()))
				&& (Point1Between2Points(rectangle2.getBottomRight(), rectangle1.getTopLeft(), rectangle1.getBottomRight())))
		{
			return true;
		}
		else
		{
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
}
