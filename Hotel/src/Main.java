import java.lang.reflect.Method;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
	    Hotel hotel = Hotel.getInstance();
		BaseMenu menu = new MainMenu();
		hotel.Init();
		System.out.print("\033[H\033[2J");
		System.out.flush();

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");

		while(true)
		{
			boolean correctInput = false;
			int option;
			System.out.print(menu);

			do
			{
				System.out.print("\nPodaj numer opcji: ");
				if (scanner.hasNextInt())
				{
					option = scanner.nextInt();
					int rangeStart = 1;
					int rangeEnd = menu.getMenuActions().keySet().size();

					if (option >= rangeStart && option <= rangeEnd)
					{
						try
						{
							Method method = menu.getAction(option);
							menu = (BaseMenu) method.invoke(menu);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

						correctInput = true;
					}
					else
					{
						System.out.print("Błędny numer opcji. Spróbuj ponownie.");
					}
				}
				else
				{
					System.out.print("Błędny numer opcji. Spróbuj ponownie.");
					scanner.next();
				}
			}
			while(!correctInput);

			System.out.print("\033[H\033[2J");
			System.out.flush();
            hotel.saveData();
		}
	}
}
