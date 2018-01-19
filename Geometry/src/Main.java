import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class Main {

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

	public static void printRasterImage (Raster imageRaster)
	{
		//represent images as 0 and 1s array
		for (int i = 0; i < imageRaster.getHeight(); i++)
		{
			for (int j = 0; j < imageRaster.getWidth(); j++)
			{
				System.out.print(imageRaster.getSample(j, i, 0));
			}
			System.out.println();
		}
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
		    }

		    catch (IOException e)
		    {
			    System.out.println("Couldn't open file:" + args[0]);
			    e.printStackTrace();
		    }
	    }
    }
}
