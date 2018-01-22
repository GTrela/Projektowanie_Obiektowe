import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EllipseRecognitionTest
{
    @Test
    void isSkipPoint()
    {
        ArrayList<Point> skipPoints = new ArrayList<>();
        Point testPoint = new Point(1,1);

        skipPoints.add(testPoint);
        skipPoints.add(new Point(2,4));
        skipPoints.add(new Point(1,2));

        EllipseRecognition er = new EllipseRecognition();

        assertTrue(er.isSkipPoint(testPoint,skipPoints));
    }

    @Test
    void detectTopEdgeLeftPoint()
    {
        ShapeRecognition sr = new ShapeRecognition();
        EllipseRecognition er = new EllipseRecognition();
        File currentDir = new File("").getAbsoluteFile();
        Point expectedTopEdgeLeft = new Point(367,95);

        try
        {
            Raster imageRaster = sr.loadImageRaster("test5.jpg");
            ArrayList<Point> skipPoints = new ArrayList<>();
            Point detectedTopEdgeLeft = er.detectTopEdgeLeftPoint(imageRaster,imageRaster.getMinX()+2,imageRaster.getMinY(),skipPoints);
            assertEquals(expectedTopEdgeLeft,detectedTopEdgeLeft);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void detectTopEdgeRightPoint()
    {
        ShapeRecognition sr = new ShapeRecognition();
        EllipseRecognition er = new EllipseRecognition();
        File currentDir = new File("").getAbsoluteFile();
        Point expectedTopEdgeRight = new Point(378,95);

        try
        {
            Raster imageRaster = sr.loadImageRaster("test5.jpg");
            ArrayList<Point> skipPoints = new ArrayList<>();
            Point detectedTopEdgeLeft = er.detectTopEdgeLeftPoint(imageRaster,imageRaster.getMinX()+2,imageRaster.getMinY(),skipPoints);
            Point detectedTopEdgeRight = er.detectTopEdgeRightPoint(imageRaster,detectedTopEdgeLeft.x, detectedTopEdgeLeft.y);

            assertEquals(expectedTopEdgeRight,detectedTopEdgeRight);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void checkHorizontalContinuity()
    {
        ShapeRecognition sr = new ShapeRecognition();
        EllipseRecognition er = new EllipseRecognition();
        File currentDir = new File("").getAbsoluteFile();

        try
        {
            Raster imageRaster = sr.loadImageRaster("test7.jpg");

            assertTrue(er.checkHorizontalContinuity(imageRaster,new Point(0,0),new Point(16,0)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void findVerticalEdgePairs()
    {
        ShapeRecognition sr = new ShapeRecognition();
        EllipseRecognition er = new EllipseRecognition();
        File currentDir = new File("").getAbsoluteFile();

        EllipseVerticalEdgePair pair1 = new EllipseVerticalEdgePair(new Point(73,40), new Point(86,40), new Point(73,177), new Point(86,177));

        try
        {
            Raster imageRaster = sr.loadImageRaster("test6.jpg");
            ArrayList<Point> skipPoints = new ArrayList<>();
            ArrayList<EllipseVerticalEdgePair> ellipseVerticalEdgePairs = er.findVerticalEdgePairs(imageRaster, imageRaster.getMinX(), imageRaster.getMinY(), skipPoints);
            assertEquals(pair1.getTopEdgeLeft(), ellipseVerticalEdgePairs.get(0).getTopEdgeLeft());
            assertEquals(pair1.getTopEdgeRight(), ellipseVerticalEdgePairs.get(0).getTopEdgeRight());
            assertEquals(pair1.getBottomEdgeLeft(), ellipseVerticalEdgePairs.get(0).getBottomEdgeLeft());
            assertEquals(pair1.getBottomEdgeRight(), ellipseVerticalEdgePairs.get(0).getBottomEdgeRight());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}