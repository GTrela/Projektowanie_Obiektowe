import java.awt.*;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

public class EllipseRecognition
{
    List<Ellipse> detectedEllipses = new ArrayList<>();

    public Point detectTopEdgeLeftPoint(Raster imageRaster, int startX, int startY, ArrayList<Point> skipPoints)
    {
        for (int y = startY; y < imageRaster.getHeight(); y++)
        {
            for (int x = startX; x < imageRaster.getWidth(); x++)
            {
                for (Point skipPoint: skipPoints)
                {
                    if (x == skipPoint.x && y == skipPoint.y)
                    {
                        x++;
                    }
                }

                if ((imageRaster.getSample(x,y,0) == 1) && (imageRaster.getSample(x-1,y+1, 0) == 0)
                     && (imageRaster.getSample(x,y+1, 0) == 0) && (imageRaster.getSample(x+1,y, 0) == 0)
                     && (imageRaster.getSample(x+2,y, 0) == 0))
                {
                    Point detectedTopEdgeLeftPoint = new Point(x, y);
                    return detectedTopEdgeLeftPoint;
                }
            }
        }

        return null;
    }

    public ArrayList<Point> detectTopEdgeRightPoints(Raster imageRaster, int TopEdgeLeftX, int TopEdgeLeftY)
    {
        ArrayList<Point> TopEdgeRightPoints = new ArrayList<>();

        for (int x = TopEdgeLeftX + 1; x < imageRaster.getWidth(); x++)
        {
            if ((imageRaster.getSample(x,TopEdgeLeftY,0) == 1) && (imageRaster.getSample(x-2,TopEdgeLeftY, 0) == 0)
                    && (imageRaster.getSample(x-1,TopEdgeLeftY, 0) == 0) && (imageRaster.getSample(x,TopEdgeLeftY+1, 0) == 0)
                    && (imageRaster.getSample(x+1,TopEdgeLeftY+1, 0) == 0))
            {
                Point detectedTopEdgeRightPoint = new Point(x, TopEdgeLeftY);
                TopEdgeRightPoints.add(detectedTopEdgeRightPoint);
            }
        }

        return TopEdgeRightPoints;
    }

    public boolean checkContinuity(Raster imageRaster, Point p1, Point p2)
    {
        if (p1.x > p2.x)
        {
            Point temp = p1;
            p1 = p2;
            p2 = temp;
        }

        for (int x = p1.x+1; x < p2.x; x++)
        {
            if (imageRaster.getSample(x,p1.y,0) == 1)
            {
                return false;
            }
        }

        return true;
    }

    public ArrayList<EllipseVerticalEdgePair> findVerticalEdgePairs(Raster imageRaster, int startX, int startY, ArrayList<Point> skipPoints)
    {
        Point TopEdgeLeft;
        ArrayList<EllipseVerticalEdgePair> verticalEdgePairs = new ArrayList<>();
        ArrayList<Point> TopEdgeRightPoints = new ArrayList<>();

        TopEdgeLeft = detectTopEdgeLeftPoint(imageRaster, startX+2, startY, skipPoints);
        TopEdgeRightPoints = detectTopEdgeRightPoints(imageRaster, TopEdgeLeft.x, TopEdgeLeft.y);

        while (TopEdgeLeft != null && !TopEdgeRightPoints.isEmpty())
        {
            for (Point pt : TopEdgeRightPoints)
            {
                if (checkContinuity(imageRaster, TopEdgeLeft, pt))
                {
                    for (int y = TopEdgeLeft.y; y < imageRaster.getHeight(); y++)
                    {
                        Point p1 = null;
                        Point p2 = null;

                        if ((imageRaster.getSample(TopEdgeLeft.x,y,0) == 1) && (imageRaster.getSample(TopEdgeLeft.x-1,y-1, 0) == 0)
                                && (imageRaster.getSample(TopEdgeLeft.x,y-1, 0) == 0) && (imageRaster.getSample(TopEdgeLeft.x+1,y, 0) == 0)
                                && (imageRaster.getSample(TopEdgeLeft.x+2,y, 0) == 0))
                        {
                            p1 = new Point(TopEdgeLeft.x,y);
                        }

                        if ((imageRaster.getSample(pt.x,y,0) == 1) && (imageRaster.getSample(pt.x-2,y, 0) == 0)
                                && (imageRaster.getSample(pt.x-1,y, 0) == 0) && (imageRaster.getSample(pt.x,y-1, 0) == 0)
                                && (imageRaster.getSample(pt.x+1,y-1, 0) == 0))
                        {
                            p2 = new Point(pt.x,y);
                        }

                        if (p1 != null && p2 != null)
                        {
                            if (checkContinuity(imageRaster, p1, p2))
                            {
                                verticalEdgePairs.add(new EllipseVerticalEdgePair(TopEdgeLeft, pt, p1, p2));
                            }
                        }
                    }
                }
            }

            if (!verticalEdgePairs.isEmpty())
            {
                skipPoints.add(TopEdgeLeft);
            }

            TopEdgeLeft = detectTopEdgeLeftPoint(imageRaster, startX+2, startY, skipPoints);
            TopEdgeRightPoints = detectTopEdgeRightPoints(imageRaster, TopEdgeLeft.x, TopEdgeLeft.y);
        }

        return verticalEdgePairs;
    }
}
