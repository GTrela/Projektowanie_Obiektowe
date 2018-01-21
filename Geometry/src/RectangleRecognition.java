import java.awt.*;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

public class RectangleRecognition
{
    List<Rectangle> detectedRectangles = new ArrayList<>();

    public boolean detectRectangles(Raster imageRaster, int tolerance, int startX, int startY, ArrayList<Point> skipPoints)
    {
        Point TopLeft, BottomRight;
        ArrayList<Point> BottomLeft, TopRight;

        boolean detectFlag = false;

        //detect first top left Rectangle corner
        TopLeft = detectTopLeftRectCorner(imageRaster, tolerance, startX, startY, skipPoints);

        //while there is unchecked top left corner search for rectangles
        while (TopLeft != null)
        {
            TopRight = detectTopRightRectCorner(imageRaster, tolerance, TopLeft.x, TopLeft.y);
            BottomLeft = detectBottomLeftRectCorner(imageRaster, tolerance, TopLeft.x, TopLeft.y);

            if (TopRight.size() > 0 && BottomLeft.size() > 0)
            {
                for (int bottomLeftIndex = 0; bottomLeftIndex < BottomLeft.size(); bottomLeftIndex++)
                {
                    for (int topRightIndex = 0; topRightIndex < TopRight.size(); topRightIndex++)
                    {
                        // check if found bottomRight corner
                        BottomRight = checkBottomRightPoint(imageRaster, BottomLeft.get(bottomLeftIndex), TopRight.get(topRightIndex));

                        //if found return rectangle
                        if (BottomRight != null)
                        {
                            detectedRectangles.add(new Rectangle(TopLeft, TopRight.get(topRightIndex), BottomLeft.get(bottomLeftIndex), BottomRight));
                            detectFlag = true;
                        }
                    }
                }
            }

            TopLeft = detectTopLeftRectCorner(imageRaster, tolerance, startX, startY, skipPoints);
        }

        if (detectFlag)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Point detectTopLeftRectCorner(Raster imageRaster, int tolerance, int startX, int startY, ArrayList<Point> skipPoints)
    {
        for (int y = startY; y < imageRaster.getHeight(); y++)
        {
            for (int x = startX; x < imageRaster.getWidth(); x++)
            {
                // skip earlier detected Top Left Rectangle Corner Points
                for (int i = 0; i < skipPoints.size(); i++)
                {
                    if (x == skipPoints.get(i).x && y == skipPoints.get(i).y)
                    {
                        x++;
                    }
                }

                // search for top left rectangle corner base on minimal length
                if (x + tolerance < imageRaster.getWidth() && y + tolerance < imageRaster.getHeight()
                        && imageRaster.getSample(x, y, 0) == 0)
                {
                    boolean detectFlag = true;

                    for (int i = 1; i <= tolerance; i++)
                    {
                        if ((imageRaster.getSample(x + i, y, 0) == 1) ||
                                (imageRaster.getSample(x, y + i, 0) == 1))
                        {
                            detectFlag = false;
                        }
                    }

                    if (detectFlag)
                    {
                        // create and add to detected Top Left Corner Points
                        Point detectedTopLeftCorner = new Point(x, y);
                        skipPoints.add(detectedTopLeftCorner);

                        return detectedTopLeftCorner;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Point> detectTopRightRectCorner(Raster imageRaster, int tolerance, int TopLeftX, int TopLeftY)
    {
        ArrayList<Point> detectedTopRightPoints = new ArrayList<>();

        for (int x = TopLeftX + 1; x < imageRaster.getWidth(); x++)
        {
            // check if we have straight line of points
            if (imageRaster.getSample(x, TopLeftY, 0) == 0)
            {
                boolean detectFlag = true;

                //check if we have detect corner
                for (int i = 1; i <= tolerance; i++)
                {
                    if ((imageRaster.getSample(x, TopLeftY + i, 0) == 1))
                    {
                        detectFlag = false;
                    }
                }

                if (detectFlag)
                {
                    Point detectedTopRightPoint = new Point(x, TopLeftY);
                    detectedTopRightPoints.add(detectedTopRightPoint);
                }
            }
            else
            {
                break;
            }
        }

        return detectedTopRightPoints;
    }

    public ArrayList<Point> detectBottomLeftRectCorner(Raster imageRaster, int tolerance, int TopLeftX, int TopLeftY)
    {
        ArrayList<Point> detectedBottomLeftPoints = new ArrayList<>();

        //detect bottom left rectangle corner base on founded top left corner
        for (int y = TopLeftY + 1; y < imageRaster.getHeight(); y++)
        {
            if (imageRaster.getSample(TopLeftX, y, 0) == 0)
            {
                boolean detectFlag = true;

                for (int i = 1; i <= tolerance; i++)
                {
                    if ((imageRaster.getSample(TopLeftX + i, y, 0) == 1))
                        detectFlag = false;
                }

                if (detectFlag)
                {
                    Point detectedBottomLeftPoint = new Point(TopLeftX, y);
                    detectedBottomLeftPoints.add(detectedBottomLeftPoint);
                }
            }
            else
            {
                break;
            }
        }
        return detectedBottomLeftPoints;
    }

    public Point checkBottomRightPoint(Raster imageRaster, Point bottomLeft, Point topRight)
    {
        // check if this point exist's
        if ((imageRaster.getSample(topRight.x, bottomLeft.y, 0) != 0))
        {
            return null;
        }

        //check if there is bottom edge
        for (int x = bottomLeft.x + 1; x < topRight.x; x++)
        {
            if (imageRaster.getSample(x, bottomLeft.y, 0) != 0)
            {
                return null;
            }
        }

        //check if there is right edge
        for (int y = topRight.y + 1; y < bottomLeft.y; y++)
        {
            if (imageRaster.getSample(topRight.x, y, 0) != 0)
            {
                return null;
            }
        }

        return new Point(topRight.x, bottomLeft.y);
    }

    public List<Rectangle> getDetectedRectangles()
    {
        return detectedRectangles;
    }
}
