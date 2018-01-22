import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
					System.out.printf("-- Relacja prostokątów nr %d i %d --\n", i, j);

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
						System.out.printf("Prostokąt %d jest rozłączny względem prostokąta %d\n", i, j);
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
					System.out.printf("-- Relacja elipsy nr %d i %d --\n", i, j);

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

				System.out.printf("-- Relacja prostokąta nr %d i elipsy %d --\n", i, j);

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
					System.out.printf("Prostokąt %d ma część wspólną z elipsą %d\n", i, j);
				}

				if (!r1ContainsR2 && !r1TangencyR2 && !r1SharePointsR2)
				{
					System.out.printf("Prostokąt %d jest rozłączny względem elipsy %d\n", i, j);
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
				System.out.printf("-- Relacja elipsy nr %d i prostokąta %d --\n", i, j);

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
					System.out.printf("Elipsa %d jest styczna do prostokąta %d\n", i, j);
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
			System.out.println("Podaj nazwę pliku jako argument");
			System.exit(0);
		}
		else
		{
			String imageFilePath = args[0];
			try
			{
				Scanner scanner = new Scanner(System.in);
				ShapeRecognition sr = new ShapeRecognition();
				RectangleRecognition rr = new RectangleRecognition();
				EllipseRecognition er = new EllipseRecognition();

				Raster imageRaster = sr.loadImageRaster(imageFilePath);

				ArrayList<Point> testPoints = new ArrayList<>();

				//detect every Rectangle
				rr.detectRectangles(imageRaster, 4, imageRaster.getMinX(), imageRaster.getMinY(), testPoints);

				//detect every Ellipse
				er.detectEllipses(imageRaster);

				System.out.println();

				boolean exitFlag = false;

				while(!exitFlag)
				{
					System.out.println("------------------------------------------------");
					System.out.printf("Wykryto %d prostokąty:\n", rr.detectedRectangles.size());
					System.out.println("------------------------------------------------");
					for (int i = 0; i < rr.detectedRectangles.size(); i++)
					{
						System.out.printf("Prostokąt nr: %d\n%s\n", i, rr.detectedRectangles.get(i));
					}

					System.out.println("------------------------------------------------");
					System.out.printf("Wykryto %d elipsy:\n", er.detectedEllipses.size());
					System.out.println("------------------------------------------------");
					for (int i = 0; i < er.detectedEllipses.size(); i++)
					{
						System.out.printf("Elipsa nr: %d\n%s\n", i, er.detectedEllipses.get(i));
					}

					System.out.println("------------------------------------------------");
					System.out.println("Wzajemne położenie figur");
					System.out.println("------------------------------------------------");

					sr.printRectRectRelation(rr);
					sr.printElipseElipseRelation(er);
					sr.printRectangleElipseRelation(rr, er);
					sr.printElipseRectangleRelation(er,rr);

					System.out.println("------------------------------------------------");
					System.out.print("Przesunięcie o wektor [1], wyjście [2]: ");

					boolean correctInput = false;
					int option = 0;

					do
					{
						if (scanner.hasNextInt())
						{
							option = scanner.nextInt();

							if (option == 1 || option == 2)
							{
								correctInput = true;
							}
							else
							{
								System.out.print("Błędny numer opcji. Spróbuj ponownie: ");
							}
						}
						else
						{
							System.out.print("Błędny numer opcji. Spróbuj ponownie: ");
							scanner.next();
						}
					}
					while(!correctInput);

					if (option == 1)
					{
						System.out.print("Podaj współrzędne wektora (x, y) oddzielone spacją: ");
						scanner.nextLine();
						String input = scanner.nextLine();
						String[] numbers = input.split(" ");

						Point vector = new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));

						System.out.print("Czy chcesz przesunąć prostokąt(y)? T/N : ");
						String answer = scanner.next();

						if (answer.equals("T") || answer.equals("t"))
						{
							System.out.print("Podaj numery prostokątów do przesunięcia: ");
							scanner.nextLine();
							input = scanner.nextLine();
							numbers = input.split(" ");

							for (String number : numbers)
							{
								Rectangle rect = rr.detectedRectangles.get(Integer.parseInt(number));

								if (rect != null)
								{
									rr.detectedRectangles.get(Integer.parseInt(number)).translateByVector(vector);
								}
							}
						}

						System.out.print("Czy chcesz przesunąć elipsę(y)? T/N : ");
						answer = scanner.next();

						if (answer.equals("T") || answer.equals("t"))
						{
							System.out.print("Podaj numery elips do przesunięcia: ");
							scanner.nextLine();
							input = scanner.nextLine();
							numbers = input.split(" ");

							for (String number : numbers)
							{
								Ellipse ellipse = er.detectedEllipses.get(Integer.parseInt(number));

								if (ellipse != null)
								{
									er.detectedEllipses.get(Integer.parseInt(number)).translateByVector(vector);
								}
							}
						}
					}
					else
					{
						exitFlag = true;
					}
				}
			}

			catch (IOException e)
			{
				System.out.println("Couldn't open file:" + args[0]);
				e.printStackTrace();
			}
		}
	}
}
