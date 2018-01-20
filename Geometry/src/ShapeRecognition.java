import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.helper.opencv_objdetect;
import org.bytedeco.javacpp.indexer.UByteRawIndexer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.CV_8UC;
import static org.bytedeco.javacpp.opencv_core.CV_8UC3;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

public class ShapeRecognition
{
	List<Rectangle> detectedRectangles = new ArrayList<>();

	public static Raster loadImageRaster(String file_path) throws IOException
	{
		File input = new File(file_path);
		BufferedImage buf_image = ImageIO.read(input);

		buf_image = binarizeImage(buf_image);
		return buf_image.getData(); //return raster

	}

	public static BufferedImage binarizeImage(BufferedImage img_param)
	{
		//to binary
		BufferedImage image = new BufferedImage(img_param.getWidth(), img_param.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		Graphics g = image.getGraphics();
		g.drawImage(img_param, 0, 0, null);
		g.dispose();

		return image;
	}

	public static void printRasterImage(Raster imageRaster)
	{
		//represent images as 0 and 1s array
		for (int y = 0; y < imageRaster.getHeight(); y++)
		{
			for (int x = 0; x < imageRaster.getWidth(); x++)
			{
				System.out.print(imageRaster.getSample(x, y, 0));
			}
			System.out.println();
		}
	}

	public boolean detectRectangle(Raster imageRaster, int tolerance, int startX, int startY, ArrayList<Point> skipPoints)
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

	public static Point detectTopLeftRectCorner(Raster imageRaster, int tolerance, int startX, int startY, ArrayList<Point> skipPoints)
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

	public static ArrayList<Point> detectTopRightRectCorner(Raster imageRaster, int tolerance, int TopLeftX, int TopLeftY)
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

	public static ArrayList<Point> detectBottomLeftRectCorner(Raster imageRaster, int tolerance, int TopLeftX, int TopLeftY)
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

	public static Point checkBottomRightPoint(Raster imageRaster, Point bottomLeft, Point topRight)
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

	public opencv_core.Mat bufferedImageToMat(BufferedImage bi)
	{
		opencv_core.Mat mat = new opencv_core.Mat(bi.getHeight(), bi.getWidth(), CV_8UC(3));

		int r, g, b;
		try (UByteRawIndexer indexer = mat.createIndexer()) {
			for (int y = 0; y < bi.getHeight(); y++) {
				for (int x = 0; x < bi.getWidth(); x++) {
					int rgb = bi.getRGB(x, y);

					r = (byte) ((rgb >> 0) & 0xFF);
					g = (byte) ((rgb >> 8) & 0xFF);
					b = (byte) ((rgb >> 16) & 0xFF);

					indexer.put(y, x, 0, r);
					indexer.put(y, x, 1, g);
					indexer.put(y, x, 2, b);
				}
			}
			indexer.release();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return mat;
	}

	public static void show(opencv_core.Mat mat, String title)
	{
		OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
		CanvasFrame canvas = new CanvasFrame(title, 1);
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.showImage(converter.convert(mat));
	}

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.out.println("Add file path as argument");
			System.exit(0);
		}
		else
		{
			String imageFilePath = args[0];
			try
			{
				ShapeRecognition sr = new ShapeRecognition();
				Raster imageRaster = loadImageRaster(imageFilePath);
				printRasterImage(imageRaster);

				ArrayList<Point> testPoints = new ArrayList<>();

				//detect every Rectangle
				sr.detectRectangle(imageRaster, 4, imageRaster.getMinX(), imageRaster.getMinY(), testPoints);

				WritableRaster wr = Raster.createWritableRaster(imageRaster.getSampleModel(), imageRaster.getDataBuffer(), null);

				System.out.println("\n\n");

				// delete detected rectangles from the Raster
				for (Rectangle rect : sr.detectedRectangles)
				{
					for (Point pt : rect.getPoints())
					{
						wr.setSample(pt.x, pt.y, 0, 1);
					}
				}

				printRasterImage(wr);

				// OpenCV
				// Preload the opencv_objdetect module to work around a known bug.
				Loader.load(opencv_objdetect.class);

				BufferedImage ellipsesImage = new BufferedImage(wr.getWidth(), wr.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
				ellipsesImage.setData(wr);

				opencv_core.Mat src = sr.bufferedImageToMat(ellipsesImage);

				show(src, "Step 1");
			}

			catch (IOException e)
			{
				System.out.println("Couldn't open file:" + args[0]);
				e.printStackTrace();
			}
		}
	}
}
