import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleRecognitionTest {

    @Test
    void detectTopLeftRectCorner()
    {
        ShapeRecognition sr = new ShapeRecognition();
        RectangleRecognition rr = new RectangleRecognition();
        File currentDir = new File("").getAbsoluteFile();
        Point expectedTopLeft = new Point(51,96);

        try
        {
            Raster imageRaster = sr.loadImageRaster("test6.jpg");
            ArrayList<Point> skipPoints = new ArrayList<>();
            Point detectedTopLeft = rr.detectTopLeftRectCorner(imageRaster, 4, imageRaster.getMinX(), imageRaster.getMinY(), skipPoints);
            assertEquals(expectedTopLeft,detectedTopLeft);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void detectTopRightRectCorner()
    {
        ShapeRecognition sr = new ShapeRecognition();
        RectangleRecognition rr = new RectangleRecognition();
        File currentDir = new File("").getAbsoluteFile();
        Point expectedTopRight = new Point(218,96);

        try
        {
            Raster imageRaster = sr.loadImageRaster("test6.jpg");
            ArrayList<Point> skipPoints = new ArrayList<>();
            Point detectedTopLeft = rr.detectTopLeftRectCorner(imageRaster, 4, imageRaster.getMinX(), imageRaster.getMinY(), skipPoints);
            List<Point> detectedTopRight = rr.detectTopRightRectCorner(imageRaster,4,detectedTopLeft.x,detectedTopLeft.y);
            assertEquals(expectedTopRight,detectedTopRight.get(0));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}