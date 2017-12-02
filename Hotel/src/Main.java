import java.lang.reflect.Method;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		BaseMenu menu = new MainMenu();
		System.out.print("\033[H\033[2J");
		System.out.flush();

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");

		while(true)
		{
			boolean rError = true;
			int option;
			System.out.print(menu);
            System.out.print("\nPodaj numer opcji: ");

            while(rError)
			{
				if (scanner.hasNextInt())
				{
					option = scanner.nextInt();

					try
					{
						Method method = menu.getAction(option);

						while (method == null)
						{
							System.out.print("Błędny numer opcji [1]. Spróbuj ponownie: ");
							option = scanner.nextInt();
							method = menu.getAction(option);
						}

						menu = (BaseMenu) method.invoke(menu);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.print("Błędny numer opcji [2]. Spróbuj ponownie: ");
					scanner.next();
					continue;
				}

				rError = false;
			}

			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}
