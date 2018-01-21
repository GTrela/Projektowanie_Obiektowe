import java.awt.*;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

public class EllipseRecognition
{
	List<Ellipse> detectedEllipses = new ArrayList<>();

	public boolean isSkipPoint(Point p, ArrayList<Point> skipPoints)
	{
		for (Point skipPoint : skipPoints)
		{
			if (skipPoint.x == p.x && skipPoint.y == p.y)
			{
				return true;
			}
		}

		return false;
	}

	public Point detectTopEdgeLeftPoint(Raster imageRaster, int startX, int startY, ArrayList<Point> skipPoints)
	{
		for (int y = startY; y + 1 < imageRaster.getHeight(); y++)
		{
			for (int x = startX; x + 2 < imageRaster.getWidth(); x++)
			{
				if (!isSkipPoint(new Point(x, y), skipPoints))
				{
					if ((imageRaster.getSample(x, y, 0) == 1)
							&& (imageRaster.getSample(x - 1, y + 1, 0) == 0)
							&& (imageRaster.getSample(x, y + 1, 0) == 0) && (imageRaster.getSample(x + 1, y, 0) == 0)
							&& (imageRaster.getSample(x + 2, y, 0) == 0))
					{
						Point detectedTopEdgeLeftPoint = new Point(x, y);
						skipPoints.add(detectedTopEdgeLeftPoint);
						return detectedTopEdgeLeftPoint;
					}
				}
			}
		}

		return null;
	}

	public Point detectTopEdgeRightPoint(Raster imageRaster, int TopEdgeLeftX, int TopEdgeLeftY)
	{
		for (int x = TopEdgeLeftX + 1; x + 3 < imageRaster.getWidth(); x++)
		{
			if (imageRaster.getSample(x, TopEdgeLeftY, 0) == 0)
			{
				if ((imageRaster.getSample(x + 1, TopEdgeLeftY, 0) == 0)
						&& (imageRaster.getSample(x + 2, TopEdgeLeftY, 0) == 1)
						&& (imageRaster.getSample(x + 2, TopEdgeLeftY + 1, 0) == 0)
						&& (imageRaster.getSample(x + 3, TopEdgeLeftY + 1, 0) == 0))
				{
					Point detectedTopEdgeRightPoint = new Point(x + 2, TopEdgeLeftY);
					return detectedTopEdgeRightPoint;
				}
			}
			else
			{
				break;
			}
		}

		return null;
	}

	public boolean checkHorizontalContinuity(Raster imageRaster, Point p1, Point p2)
	{
		if (p1.x > p2.x)
		{
			Point temp = p1;
			p1 = p2;
			p2 = temp;
		}

		for (int x = p1.x + 1; x < p2.x; x++)
		{
			if (imageRaster.getSample(x, p1.y, 0) == 1)
			{
				return false;
			}
		}

		return true;
	}

	public ArrayList<EllipseVerticalEdgePair> findVerticalEdgePairs(Raster imageRaster, int startX, int startY, ArrayList<Point> skipPoints)
	{
		Point TopEdgeLeft, TopEdgeRightPoint;
		ArrayList<EllipseVerticalEdgePair> verticalEdgePairs = new ArrayList<>();

		// find potential top left edge ellipse point
		TopEdgeLeft = detectTopEdgeLeftPoint(imageRaster, startX + 2, startY, skipPoints);

		while (TopEdgeLeft != null)
		{
			// search for potential top right edge points
			TopEdgeRightPoint = detectTopEdgeRightPoint(imageRaster, TopEdgeLeft.x, TopEdgeLeft.y);

			if (TopEdgeRightPoint != null)
			{

				for (int y = TopEdgeLeft.y; y < imageRaster.getHeight(); y++)
				{
					Point p1 = null;
					Point p2 = null;

					if ((imageRaster.getSample(TopEdgeLeft.x, y, 0) == 1) && (imageRaster.getSample(TopEdgeLeft.x - 1, y - 1, 0) == 0)
							&& (imageRaster.getSample(TopEdgeLeft.x, y - 1, 0) == 0) && (imageRaster.getSample(TopEdgeLeft.x + 1, y, 0) == 0)
							&& (imageRaster.getSample(TopEdgeLeft.x + 2, y, 0) == 0))
					{
						p1 = new Point(TopEdgeLeft.x, y);
					}

					if ((imageRaster.getSample(TopEdgeRightPoint.x, y, 0) == 1) && (imageRaster.getSample(TopEdgeRightPoint.x - 2, y, 0) == 0)
							&& (imageRaster.getSample(TopEdgeRightPoint.x - 1, y, 0) == 0) && (imageRaster.getSample(TopEdgeRightPoint.x, y - 1, 0) == 0)
							&& (imageRaster.getSample(TopEdgeRightPoint.x + 1, y - 1, 0) == 0))
					{
						p2 = new Point(TopEdgeRightPoint.x, y);
					}

					if (p1 != null && p2 != null)
					{
						if (checkHorizontalContinuity(imageRaster, p1, p2))
						{
							verticalEdgePairs.add(new EllipseVerticalEdgePair(TopEdgeLeft, TopEdgeRightPoint, p1, p2));
						}
					}
				}
			}

			TopEdgeLeft = detectTopEdgeLeftPoint(imageRaster, startX + 2, startY, skipPoints);
		}

		return verticalEdgePairs;
	}

	public Point detectLeftEdgeBottomPoint(Raster imageRaster, int startX, int startY, ArrayList<Point> skipPoints)
	{
		for (int x = startX; x + 1 < imageRaster.getWidth(); x++)
		{
			for (int y = startY; y - 2 > 0; y--)
			{
				if (!isSkipPoint(new Point(x, y), skipPoints))
				{
					if ((imageRaster.getSample(x, y, 0) == 1)
							&& (imageRaster.getSample(x, y - 1, 0) == 0)
							&& (imageRaster.getSample(x, y - 2, 0) == 0) && (imageRaster.getSample(x + 1, y, 0) == 0)
							&& (imageRaster.getSample(x + 1, y + 1, 0) == 0))
					{
						Point detectLeftEdgeBottomPoint = new Point(x, y);
						skipPoints.add(detectLeftEdgeBottomPoint);
						return detectLeftEdgeBottomPoint;
					}
				}
			}
		}

		return null;
	}

	public Point detectLeftEdgeTopPoint(Raster imageRaster, int LeftEdgeBottomX, int LeftEdgeBottomY)
	{
		for (int y = LeftEdgeBottomY - 1; y - 3 > 0; y--)
		{
			if (imageRaster.getSample(LeftEdgeBottomX, y, 0) == 0)
			{
				if ((imageRaster.getSample(LeftEdgeBottomX, y - 1, 0) == 0)
						&& (imageRaster.getSample(LeftEdgeBottomX, y - 2, 0) == 1)
						&& (imageRaster.getSample(LeftEdgeBottomX + 1, y - 2, 0) == 0)
						&& (imageRaster.getSample(LeftEdgeBottomX + 1, y - 3, 0) == 0))
				{
					Point detectedLeftEdgeTopPoint = new Point(LeftEdgeBottomX, y - 2);
					return detectedLeftEdgeTopPoint;
				}
			}
			else
			{
				break;
			}
		}

		return null;
	}


	public boolean checkVerticalContinuity(Raster imageRaster, Point p1, Point p2)
	{
		if (p1.y > p2.y)
		{
			Point temp = p1;
			p1 = p2;
			p2 = temp;
		}

		for (int y = p1.y + 1; y < p2.y; y++)
		{
			if (imageRaster.getSample(p1.x, y, 0) == 1)
			{
				return false;
			}
		}

		return true;
	}

	public ArrayList<EllipseHorizontalEdgePair> findHortizontalEdgePairs(Raster imageRaster, int startX, int startY, ArrayList<Point> skipPoints)
	{
		Point LeftEdgeBottom;
		Point LeftEdgeTop;
		ArrayList<EllipseHorizontalEdgePair> horizontalEdgePairs = new ArrayList<>();

		LeftEdgeBottom = detectLeftEdgeBottomPoint(imageRaster, startX, startY - 2, skipPoints);

		while (LeftEdgeBottom != null)
		{
			// search for potential top right edge points
			LeftEdgeTop = detectLeftEdgeTopPoint(imageRaster, LeftEdgeBottom.x, LeftEdgeBottom.y);

			if (LeftEdgeTop != null)
			{
				for (int x = LeftEdgeBottom.x; x < imageRaster.getWidth(); x++)
				{
					Point RightEdgeBottom = null;
					Point RightEdgeTop = null;

					if ((imageRaster.getSample(x, LeftEdgeBottom.y, 0) == 1) && (imageRaster.getSample(x, LeftEdgeBottom.y - 1, 0) == 0)
							&& (imageRaster.getSample(x, LeftEdgeBottom.y - 2, 0) == 0) && (imageRaster.getSample(x - 1, LeftEdgeBottom.y, 0) == 0)
							&& (imageRaster.getSample(x - 1, LeftEdgeBottom.y + 1, 0) == 0))
					{
						RightEdgeBottom = new Point(x, LeftEdgeBottom.y);
					}

					if ((imageRaster.getSample(x, LeftEdgeTop.y, 0) == 1) && (imageRaster.getSample(x, LeftEdgeTop.y + 1, 0) == 0)
							&& (imageRaster.getSample(x, LeftEdgeTop.y + 2, 0) == 0) && (imageRaster.getSample(x - 1, LeftEdgeTop.y, 0) == 0)
							&& (imageRaster.getSample(x - 1, LeftEdgeTop.y - 1, 0) == 0))
					{
						RightEdgeTop = new Point(x, LeftEdgeTop.y);
					}

					if (RightEdgeBottom != null && RightEdgeTop != null)
					{
						if (checkVerticalContinuity(imageRaster, RightEdgeBottom, RightEdgeTop))
						{
							horizontalEdgePairs.add(new EllipseHorizontalEdgePair(LeftEdgeTop, LeftEdgeBottom, RightEdgeTop, RightEdgeBottom));
						}
					}
				}
			}

			LeftEdgeBottom = detectLeftEdgeBottomPoint(imageRaster, startX, startY - 2, skipPoints);
		}

		return horizontalEdgePairs;
	}

	public boolean detectEllipse(Raster imageRaster)
    {
        boolean detectFlag = false;

        ArrayList<Point> skipPoints = new ArrayList<>();

        ArrayList<EllipseVerticalEdgePair> ellipseVerticalEdgePairs = findVerticalEdgePairs(imageRaster, imageRaster.getMinX(), imageRaster.getMinY(), skipPoints);

        skipPoints = new ArrayList<>();

        ArrayList<EllipseHorizontalEdgePair> ellipseHorizontalEdgePairs = findHortizontalEdgePairs(imageRaster, imageRaster.getMinX(), imageRaster.getHeight(), skipPoints);;

        for (EllipseVerticalEdgePair verticalEdgePair : ellipseVerticalEdgePairs)
        {
            for (EllipseHorizontalEdgePair horizontalEdgePair : ellipseHorizontalEdgePairs)
            {
                Point center = new Point(((int) (verticalEdgePair.getTopEdgeLeft().x + verticalEdgePair.getTopEdgeRight().x))/2,
                            (int)(verticalEdgePair.getTopEdgeLeft().y + verticalEdgePair.getBottomEdgeLeft().y)/2);

                Point centerLeftEdge = new Point( (int) (horizontalEdgePair.getLeftEdgeBottom().x + horizontalEdgePair.getLeftEdgeTop().x)/2,
                                (int) (horizontalEdgePair.getLeftEdgeBottom().y + horizontalEdgePair.getLeftEdgeTop().y)/2);

                double d1 = Math.sqrt(  (center.x - horizontalEdgePair.getLeftEdgeTop().x)*(center.x - horizontalEdgePair.getLeftEdgeTop().x) +
                        (center.y - horizontalEdgePair.getLeftEdgeTop().y)*(center.y - horizontalEdgePair.getLeftEdgeTop().y) );

                double d2 = Math.sqrt( (horizontalEdgePair.getRightEdgeTop().x - center.x)*(horizontalEdgePair.getRightEdgeTop().x - center.x) +
                        (horizontalEdgePair.getRightEdgeTop().y - center.y)*(horizontalEdgePair.getRightEdgeTop().y - center.y) );

                double d_left1 = Math.sqrt ( (centerLeftEdge.x - verticalEdgePair.getTopEdgeLeft().x)*(centerLeftEdge.x - verticalEdgePair.getTopEdgeLeft().x) +
                        (centerLeftEdge.y - verticalEdgePair.getTopEdgeLeft().y)*(centerLeftEdge.y - verticalEdgePair.getTopEdgeLeft().y));

                double d_left2 = Math.sqrt( (centerLeftEdge.x - verticalEdgePair.getBottomEdgeLeft().x)*(centerLeftEdge.x - verticalEdgePair.getBottomEdgeLeft().x) +
                        (centerLeftEdge.y - verticalEdgePair.getBottomEdgeLeft().y)*(centerLeftEdge.y - verticalEdgePair.getBottomEdgeLeft().y));

                if (Math.abs(d1-d2) <= 2 && Math.abs(d_left1-d_left2) <=2)
                {
                    detectFlag = true;

                    detectedEllipses.add(new Ellipse(verticalEdgePair.getTopEdgeLeft(), verticalEdgePair.getTopEdgeRight(), verticalEdgePair.getBottomEdgeLeft(), verticalEdgePair.getBottomEdgeRight(),
                                        horizontalEdgePair.getLeftEdgeTop(), horizontalEdgePair.getLeftEdgeBottom(), horizontalEdgePair.getRightEdgeTop(), horizontalEdgePair.getRightEdgeBottom()));
                }
            }
        }

        return detectFlag;
    }
}
