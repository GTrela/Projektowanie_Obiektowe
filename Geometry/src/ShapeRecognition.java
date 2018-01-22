import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShapeRecognition
{
	public Raster loadImageRaster(String file_path) throws IOException
	{
		File input = new File(file_path);
		BufferedImage buf_image = ImageIO.read(input);

		buf_image = binarizeImage(buf_image);
		return buf_image.getData(); //return raster
	}

	public BufferedImage binarizeImage(BufferedImage img_param)
	{
		//to binary
		BufferedImage image = new BufferedImage(img_param.getWidth(), img_param.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		Graphics g = image.getGraphics();
		g.drawImage(img_param, 0, 0, null);
		g.dispose();

		return image;
	}

	public void printRasterImage(Raster imageRaster)
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

	public void printRectRectRelation(RectangleRecognition rr)
	{
		// rectangle vs rectangle relations
		for (int i = 0; i < rr.detectedRectangles.size(); i++)
		{
			for (int j = 0; j < rr.detectedRectangles.size(); j++)
			{
				// if rectangle is the same object
				if (rr.detectedRectangles.get(i) == rr.detectedRectangles.get(j))
				{
					continue;
				}
				else
				{
					System.out.printf("Relacja prostokątów nr %d i %d:\n", i, j);

					boolean r1ContainsR2;
					boolean r1TangencyR2;
					boolean r1SharePointsR2;

					if (Position.R1InsideR2(rr.detectedRectangles.get(j), rr.detectedRectangles.get(i)))
					{
						System.out.printf("Prostokąt %d znajduje się wewnątrz prostokąta %d\n", i, j);
					}

					if (r1ContainsR2 = Position.R1ContainsR2(rr.detectedRectangles.get(j), rr.detectedRectangles.get(i)))
					{
						System.out.printf("Prostokąt %d zawiera się w prostokącie %d\n", i, j);
					}

					if (r1TangencyR2 = Position.R1TangencyR2(rr.detectedRectangles.get(j), rr.detectedRectangles.get(i)))
					{
						System.out.printf("Prostokąt %d jest styczny do prostokąta %d\n", i, j);
					}

					if (r1SharePointsR2 = Position.R1SharePointsR2(rr.detectedRectangles.get(j), rr.detectedRectangles.get(i)))
					{
						System.out.printf("Prostokąt %d ma część wspólną z prostokątem %d\n", i, j);
					}

					if (!r1ContainsR2 && !r1TangencyR2 && !r1SharePointsR2)
					{
						System.out.printf("Prostokąt %d jest rozłączna względem prostokąta %d\n", i, j);
					}

					System.out.println();
				}
			}
		}
	}

	public void printElipseElipseRelation(EllipseRecognition er)
	{
		//ellipse vs ellipse relations
		for (int i = 0; i < er.detectedEllipses.size(); i++)
		{
			for (int j = 0; j < er.detectedEllipses.size(); j++)
			{
				// if elipse is the same object
				if (er.detectedEllipses.get(i) == er.detectedEllipses.get(j))
				{
					continue;
				}
				else
				{
					System.out.printf("Relacja elipsy nr %d i %d:\n", i, j);

					boolean r1ContainsR2;
					boolean r1TangencyR2;
					boolean r1SharePointsR2;

					if (Position.R1InsideR2(er.detectedEllipses.get(j).getFramingRectangle(), er.detectedEllipses.get(i).getFramingRectangle()))
					{
						System.out.printf("Elipsa %d znajduje się wewnątrz elipsy %d\n", i, j);
					}

					if (r1ContainsR2 = Position.R1ContainsR2(er.detectedEllipses.get(j).getFramingRectangle(), er.detectedEllipses.get(i).getFramingRectangle()))
					{
						System.out.printf("Elipsa %d zawiera się w elipsie %d\n", i, j);
					}

					if (r1TangencyR2 = Position.R1TangencyR2(er.detectedEllipses.get(j).getFramingRectangle(), er.detectedEllipses.get(i).getFramingRectangle()))
					{
						System.out.printf("Elipsa %d jest styczna do elipsy %d\n", i, j);
					}

					if (r1SharePointsR2 = Position.R1SharePointsR2(er.detectedEllipses.get(j).getFramingRectangle(), er.detectedEllipses.get(i).getFramingRectangle()))
					{
						System.out.printf("Elipsa %d ma część wspólną z elipsą %d\n", i, j);
					}

					if (!r1ContainsR2 && !r1TangencyR2 && !r1SharePointsR2)
					{
						System.out.printf("Elipsa %d jest rozłączna względem elipsy %d\n", i, j);
					}

					System.out.println();
				}
			}
		}
	}


	public void printRectangleElipseRelation(RectangleRecognition rr, EllipseRecognition er)
	{
		//rectangle vs ellipse relations
		for (int i = 0; i < rr.detectedRectangles.size(); i++)
		{
			for (int j = 0; j < er.detectedEllipses.size(); j++)
			{

				System.out.printf("Relacja prostokąta nr %d i elipsy %d:\n", i, j);

				boolean r1ContainsR2;
				boolean r1TangencyR2;
				boolean r1SharePointsR2;

				if (Position.R1InsideR2(er.detectedEllipses.get(j).getFramingRectangle(), rr.detectedRectangles.get(i)))
				{
					System.out.printf("Prostokąt %d znajduje się wewnątrz elipsy %d\n", i, j);
				}

				if (r1ContainsR2 = Position.R1ContainsR2(er.detectedEllipses.get(j).getFramingRectangle(), rr.detectedRectangles.get(i)))
				{
					System.out.printf("Prostokąt %d zawiera się w elipsie %d\n", i, j);
				}

				if (r1TangencyR2 = Position.R1TangencyR2(er.detectedEllipses.get(j).getFramingRectangle(), rr.detectedRectangles.get(i)))
				{
					System.out.printf("Prostokąt %d jest styczny do elipsy %d\n", i, j);
				}

				if (r1SharePointsR2 = Position.R1SharePointsR2(er.detectedEllipses.get(j).getFramingRectangle(), rr.detectedRectangles.get(i)))
				{
					System.out.printf("Prostokąt %d ma część wspólną z elispą %d\n", i, j);
				}

				if (!r1ContainsR2 && !r1TangencyR2 && !r1SharePointsR2)
				{
					System.out.printf("Prostokąt %d jest rozłączna względem elipsy %d\n", i, j);
				}

				System.out.println();

			}
		}
	}


	public void printElipseRectangleRelation(EllipseRecognition er, RectangleRecognition rr)
	{
		for (int i = 0; i < er.detectedEllipses.size(); i++)
		{
			for (int j = 0; j < rr.detectedRectangles.size(); j++)
			{
				System.out.printf("Relacja elipsy nr %d i prostokąta %d:\n", i, j);

				boolean r1ContainsR2;
				boolean r1TangencyR2;
				boolean r1SharePointsR2;

				if (Position.R1InsideR2(rr.detectedRectangles.get(j), er.detectedEllipses.get(i).getFramingRectangle()))
				{
					System.out.printf("Elipsa %d znajduje się wewnątrz prostokąta %d\n", i, j);
				}

				if (r1ContainsR2 = Position.R1ContainsR2(rr.detectedRectangles.get(j), er.detectedEllipses.get(i).getFramingRectangle()))
				{
					System.out.printf("Elipsa %d zawiera się w prostokącie %d\n", i, j);
				}

				if (r1TangencyR2 = Position.R1TangencyR2(rr.detectedRectangles.get(j), er.detectedEllipses.get(i).getFramingRectangle()))
				{
					System.out.printf("Elipsa %d jest styczny do prostokąta %d\n", i, j);
				}

				if (r1SharePointsR2 = Position.R1SharePointsR2(rr.detectedRectangles.get(j), er.detectedEllipses.get(i).getFramingRectangle()))
				{
					System.out.printf("Elipsa %d ma część wspólną z prostokątem %d\n", i, j);
				}

				if (!r1ContainsR2 && !r1TangencyR2 && !r1SharePointsR2)
				{
					System.out.printf("Elipsa %d jest rozłączna względem prostokąta %d\n", i, j);
				}

				System.out.println();
			}
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
				ShapeRecognition sr = new ShapeRecognition();
				RectangleRecognition rr = new RectangleRecognition();
				EllipseRecognition er = new EllipseRecognition();

				Raster imageRaster = sr.loadImageRaster(imageFilePath);

				ArrayList<Point> testPoints = new ArrayList<>();

				//detect every Rectangle
				rr.detectRectangles(imageRaster, 4, imageRaster.getMinX(), imageRaster.getMinY(), testPoints);

				//detect every Ellipse
				er.detectEllipses(imageRaster);

				System.out.printf("Wykryto %d prostokąty:\n", rr.detectedRectangles.size());
				for (int i = 0; i < rr.detectedRectangles.size(); i++)
				{
					System.out.printf("Prostokąt nr: %d\n%s\n", i, rr.detectedRectangles.get(i));
				}

				System.out.printf("Wykryto %d elipsy:\n", er.detectedEllipses.size());
				for (int i = 0; i < er.detectedEllipses.size(); i++)
				{
					System.out.printf("Elipsa nr: %d\n%s\n", i, er.detectedEllipses.get(i));
				}

				sr.printRectRectRelation(rr);
				sr.printElipseElipseRelation(er);
				sr.printRectangleElipseRelation(rr, er);
				sr.printElipseRectangleRelation(er,rr);
			}

			catch (IOException e)
			{
				System.out.println("Couldn't open file:" + args[0]);
				e.printStackTrace();
			}
		}
	}
}
