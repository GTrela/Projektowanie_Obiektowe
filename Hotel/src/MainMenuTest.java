import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest
{

    @Test
    void getAction()
    {
        MainMenu menu = new MainMenu();

        try
        {
            assertEquals(MainMenu.class.getMethod("logIn"),menu.getAction(1));
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void getHeader()
    {
        MainMenu menu = new MainMenu();
        assertEquals("MENU GŁÓWNE", menu.getHeader());
    }

    @Test
    void getMenuActions()
    {
        MainMenu menu = new MainMenu();
        Map<Integer, Method> menuActions = new HashMap<>();

        try
        {
            menuActions.put(1, MainMenu.class.getMethod("logIn"));
            menuActions.put(2, MainMenu.class.getMethod("createNewUser"));
            menuActions.put(3, MainMenu.class.getMethod("exit"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        assertEquals(menuActions,menu.getMenuActions());
    }

    @Test
    void getActionDescriptions()
    {
        MainMenu menu = new MainMenu();
        Map<Integer, String> menuDescriptions = new HashMap<>();

        menuDescriptions.put(1, "Logowanie");
        menuDescriptions.put(2, "Rejestracja");
        menuDescriptions.put(3, "Wyjście");

        assertEquals(menuDescriptions,menu.getActionDescriptions());
    }

    @Test
    void dateInput()
    {
        MainMenu menu = new MainMenu();
        LocalDate data = LocalDate.of(2016, Month.NOVEMBER, 9);
        assertEquals(data,menu.dateInput("9/11/2016"));
    }

    @Test
    void logIn()
    {
        MainMenu menu = new MainMenu();
        LogInMenu menu1 = new LogInMenu();
        assertEquals(menu1.getHeader(),menu.logIn().getHeader());
    }
}