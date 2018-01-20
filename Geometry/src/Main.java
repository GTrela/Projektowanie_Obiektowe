import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main
{
	static int count = 0;

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

	public static boolean detectRectangle(Raster imageRaster, int tolerance, int startX, int startY, ArrayList<Point> skipPoints)
	{
		Point TopLeft, BottomRight;
		ArrayList<Point> BottomLeft, TopRight;

		//detect first top left Rectangle corner
		TopLeft = detectTopLeftRectCorner(imageRaster, tolerance, startX, startY, skipPoints);


		//detect rest of corners base on found top left corner
		if (TopLeft != null)
		{
			TopRight = detectTopRightRectCorner(imageRaster, tolerance, TopLeft.x, TopLeft.y);

			BottomLeft = detectBottomLeftRectCorner(imageRaster, tolerance, TopLeft.x, TopLeft.y);


			if (TopRight.size() > 0 || BottomLeft.size() > 0)
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
							count++;
							System.out.printf("Found Rectangle at:\n%s\n%s\n%s\n%s\n",
									TopLeft.toString(), TopRight.get(topRightIndex), BottomLeft.get(bottomLeftIndex), BottomRight.toString());
						}
					}
				}
				return true;
			}
		}

		return false;
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
						//System.out.println("Found Top Left Rectangle Corner at x = " + x + ", y = " + y);
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
					//System.out.println("Found Top Right Rectangle Corner at x = " + x + ", y = " + TopLeftY);
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
					//System.out.println("Found Bottom Left Rectangle Corner at x = " + TopLeftX + ", y = " + y);
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
				Raster imageRaster = loadImageRaster(imageFilePath);
				printRasterImage(imageRaster);

				ArrayList<Point> testPoints = new ArrayList<>();

				//detect every Rectangle
				while (detectRectangle(imageRaster, 4, imageRaster.getMinX(), imageRaster.getMinY(), testPoints))
				{
				}
				System.out.println("Rectangles found = " + count);
			}

			catch (IOException e)
			{
				System.out.println("Couldn't open file:" + args[0]);
				e.printStackTrace();
			}
		}
	}
}
