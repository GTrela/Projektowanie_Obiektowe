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
		boolean condtition = true;

		while(condtition)
		{
			System.out.print(menu);
            System.out.print("\nPodaj numer opcji: ");
			int option = scanner.nextInt();

			try
			{
				Method method = menu.getAction(option);

				while (method == null)
				{
					System.out.println("Błędny numer opcji. Spróbuj ponownie: ");
					option = scanner.nextInt();
					method = menu.getAction(option);
				}

				menu = (BaseMenu) method.invoke(menu);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}
