import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogInMenuTest
{
    @Test
    void getAction()
    {
        LogInMenu menu = new LogInMenu();

        try
        {
            assertEquals(LogInMenu.class.getMethod("logInAsAdmin"),menu.getAction(1));
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void getHeader()
    {
        LogInMenu menu = new LogInMenu();
        assertEquals("LOGOWANIE", menu.getHeader());
    }

    @Test
    void getMenuActions()
    {
        LogInMenu menu = new LogInMenu();
        Map<Integer, Method> menuActions = new HashMap<>();

        try
        {
            menuActions.put(1, LogInMenu.class.getMethod("logInAsAdmin"));
            menuActions.put(2, LogInMenu.class.getMethod("logInAsClient"));
            menuActions.put(3, LogInMenu.class.getMethod("goBack"));
            menuActions.put(4, LogInMenu.class.getMethod("exit"));
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
        LogInMenu menu = new LogInMenu();
        Map<Integer, String> menuDescriptions = new HashMap<>();

        menuDescriptions.put(1, "Dostęp dla recepcjonisty");
        menuDescriptions.put(2, "Dostęp dla klienta");
        menuDescriptions.put(3, "Powrót");
        menuDescriptions.put(4, "Wyjście");

        assertEquals(menuDescriptions,menu.getActionDescriptions());
    }

    @Test
    void dateInput()
    {
        LogInMenu menu = new LogInMenu();
        LocalDate data = LocalDate.of(2016, Month.NOVEMBER, 9);
        assertEquals(data,menu.dateInput("9/11/2016"));
    }

    @Test
    void goBack()
    {
        LogInMenu menu = new LogInMenu();
        MainMenu menu1 = new MainMenu();
        assertEquals(menu1.getHeader(),menu.goBack().getHeader());
    }
}