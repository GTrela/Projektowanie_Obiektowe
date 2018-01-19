import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_BYTE_BINARY;

public class Main
{

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

	public static boolean detectRectangle (Raster imageRaster, int tolerance, int startX, int startY)
	{
		Point TopLeft,BottomLeft,BottomRight,TopRight;

		//detect first top left Rectangle corner
		TopLeft = detectTopLeftRectCorner(imageRaster,tolerance,startX,startY);

		//detect rest of corners base on found top left corner
		if (TopLeft != null)
		{
			TopRight = detectTopRightRectCorner(imageRaster,tolerance,TopLeft.x, TopLeft.y);
			BottomLeft = detectBottomLeftRectCorner(imageRaster, tolerance, TopLeft.x, TopLeft.y);

			if (TopRight != null || BottomLeft != null)
			{
				BottomRight = detectBottomRightRectCorner(imageRaster,tolerance,BottomLeft.x,BottomLeft.y);
				if (BottomRight != null)
				{
					System.out.printf("Found Rectangle at:\n%s\n%s\n%s\n%s",
							TopLeft.toString(), TopRight.toString() ,BottomLeft.toString(),BottomRight.toString());
				}
			}
		}
		return false;
	}

	public static Point detectTopLeftRectCorner(Raster imageRaster, int tolerance, int startX, int startY)
	{
		for (int y = startY; y < imageRaster.getHeight(); y++)
		{
			for (int x = startX; x < imageRaster.getWidth(); x++)
			{
				// search for top left rectangle corner base on minimal lenght
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
						System.out.println("Found Top Left Rectangle Corner at x = " + x + ", y = " + y);
						return new Point(x,y);
					}
				}
			}
		}
		return null;
	}

	public static Point detectTopRightRectCorner(Raster imageRaster, int tolerance, int TopLeftX, int TopLeftY)
	{
		for (TopLeftX = TopLeftX + 1; TopLeftX < imageRaster.getWidth(); TopLeftX++)
		{
			if (imageRaster.getSample(TopLeftX, TopLeftY, 0) == 0)
			{
				boolean detectFlag = true;

				for (int i = 1; i <= tolerance; i++)
				{
					if ((imageRaster.getSample(TopLeftX, TopLeftY + i, 0) == 1))
					{
						detectFlag = false;
					}
				}

				if (detectFlag)
				{
					System.out.println("Found Top Right Rectangle Corner at x = " + TopLeftX + ", y = " + TopLeftY);
					return new Point(TopLeftX,TopLeftY);
				}
			}
		}
		return null;
	}

	public static Point detectBottomLeftRectCorner(Raster imageRaster, int tolerance, int TopLeftX, int TopLeftY)
	{

		//detect bottom left rectangle corner base on founded top left corner
		for (TopLeftY = TopLeftY + 1; TopLeftY < imageRaster.getHeight(); TopLeftY++)
		{
			if (imageRaster.getSample(TopLeftX, TopLeftY, 0) == 0)
			{
				boolean detectFlag = true;

				for (int i = 1; i <= tolerance; i++)
				{
					if ((imageRaster.getSample(TopLeftX + i, TopLeftY, 0) == 1))
					{
						detectFlag = false;
					}
				}

				if (detectFlag)
				{
					System.out.println("Found Bottom Left Rectangle Corner at x = " + TopLeftX + ", y = " + TopLeftY);
					return new Point(TopLeftX,TopLeftY);
				}
			}
		}

		return null;
	}

	public static Point detectBottomRightRectCorner(Raster imageRaster, int tolerance, int BottomLeftX, int BottomLeftY)
	{
		for (BottomLeftX = BottomLeftX + 1; BottomLeftX < imageRaster.getWidth(); BottomLeftX++)
		{
			if (imageRaster.getSample(BottomLeftX, BottomLeftY, 0) == 0)
			{
				boolean detectFlag = true;

				for (int i = 1; i <= tolerance; i++)
				{
					if ((imageRaster.getSample(BottomLeftX, BottomLeftY - i, 0) == 1))
					{
						detectFlag = false;
					}
				}

				if (detectFlag)
				{
					System.out.println("Found Bottom Right Rectangle Corner at x = " + BottomLeftX + ", y = " + BottomLeftY);
					return new Point(BottomLeftX,BottomLeftY);
				}
			}
		}

		return null;
	}

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.out.println("Add file path as argument");
			System.exit(0);
		} else
		{
			String imageFilePath = args[0];
			try
			{
				Raster imageRaster = loadImageRaster(imageFilePath);
				printRasterImage(imageRaster);
				//detect first Rectangle
				detectRectangle(imageRaster, 4,imageRaster.getMinX(),imageRaster.getMinY());
			}

			catch (IOException e)
			{
				System.out.println("Couldn't open file:" + args[0]);
				e.printStackTrace();
			}
		}
	}
}
