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

					if ((imageRaster.getSample(TopEdgeLeft.x - 1, y - 1, 0) == 0)
							&& (imageRaster.getSample(TopEdgeLeft.x, y - 1, 0) == 0) && (imageRaster.getSample(TopEdgeLeft.x + 1, y, 0) == 0)
							&& (imageRaster.getSample(TopEdgeLeft.x + 2, y, 0) == 0))
					{
						p1 = new Point(TopEdgeLeft.x, y);
					}

					if ((imageRaster.getSample(TopEdgeRightPoint.x - 2, y, 0) == 0)
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
					if ((imageRaster.getSample(x, y - 1, 0) == 0)
							&& (imageRaster.getSample(x, y - 2, 0) == 0) && (imageRaster.getSample(x + 1, y, 0) == 0)
							&& (imageRaster.getSample(x + 1, y + 1, 0) == 0))
					{
						Point detectLeftEdgeBottomPoint = new Point(x, y);
						return detectLeftEdgeBottomPoint;
					}
				}
			}
		}

		return null;
	}

	public ArrayList<Point> detectLeftEdgeTopPoints(Raster imageRaster, int LeftEdgeBottomX, int LeftEdgeBottomY)
	{
		ArrayList<Point> LeftEdgeTopPoints = new ArrayList<>();

		for (int y = LeftEdgeBottomY + 1; y - 1 > 0; y--)
		{
			if ((imageRaster.getSample(LeftEdgeBottomX, y + 1, 0) == 0)
					&& (imageRaster.getSample(LeftEdgeBottomX, y + 2, 0) == 0) && (imageRaster.getSample(LeftEdgeBottomX + 1, y, 0) == 0)
					&& (imageRaster.getSample(LeftEdgeBottomX + 1, y - 1, 0) == 0))
			{
				Point detectedLeftEdgeBottomPoint = new Point(LeftEdgeBottomX, y);
				LeftEdgeTopPoints.add(detectedLeftEdgeBottomPoint);
			}
		}

		return LeftEdgeTopPoints;
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
		ArrayList<EllipseHorizontalEdgePair> horizontalEdgePairs = new ArrayList<>();
		ArrayList<Point> LeftEdgeTopPoints = new ArrayList<>();

		LeftEdgeBottom = detectLeftEdgeBottomPoint(imageRaster, startX, startY - 2, skipPoints);
		LeftEdgeTopPoints = detectLeftEdgeTopPoints(imageRaster, LeftEdgeBottom.x, LeftEdgeBottom.y);

		while (LeftEdgeBottom != null && !LeftEdgeTopPoints.isEmpty())
		{
			for (Point pt : LeftEdgeTopPoints)
			{
				if (checkVerticalContinuity(imageRaster, LeftEdgeBottom, pt))
				{
					for (int x = LeftEdgeBottom.x; x < imageRaster.getWidth(); x++)
					{
						Point p1 = null;
						Point p2 = null;

						if ((imageRaster.getSample(x - 1, LeftEdgeBottom.y, 0) == 0)
								&& (imageRaster.getSample(x - 1, LeftEdgeBottom.y - 1, 0) == 0) && (imageRaster.getSample(x, LeftEdgeBottom.y + 1, 0) == 0)
								&& (imageRaster.getSample(x, LeftEdgeBottom.y + 2, 0) == 0))
						{
							p1 = new Point(x, LeftEdgeBottom.y);
						}

						if ((imageRaster.getSample(x, pt.y - 1, 0) == 0)
								&& (imageRaster.getSample(x, pt.y - 2, 0) == 0) && (imageRaster.getSample(x - 1, pt.y, 0) == 0)
								&& (imageRaster.getSample(x - 1, pt.y + 1, 0) == 0))
						{
							p2 = new Point(x, pt.y);
						}

						if (p1 != null && p2 != null)
						{
							if (checkVerticalContinuity(imageRaster, p1, p2))
							{
								horizontalEdgePairs.add(new EllipseHorizontalEdgePair(pt, LeftEdgeBottom, p2, p1));
							}
						}
					}
				}
			}

			if (!LeftEdgeTopPoints.isEmpty())
			{
				skipPoints.add(LeftEdgeBottom);
			}

			LeftEdgeBottom = detectLeftEdgeBottomPoint(imageRaster, startX, startY - 2, skipPoints);
			LeftEdgeTopPoints = detectLeftEdgeTopPoints(imageRaster, LeftEdgeBottom.x, LeftEdgeBottom.y);

		}

		return horizontalEdgePairs;
	}
}
