import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClientPanelMenuTest
{
    Hotel hotel = Hotel.getInstance();
    long userID = hotel.addClient("Jakub", "Jas");

    @Test
    void getAction()
    {
        ClientPanelMenu menu = new ClientPanelMenu(userID);

        try
        {
            assertEquals(ClientPanelMenu.class.getMethod("findVacantRooms"),menu.getAction(1));
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void getHeader()
    {
        ClientPanelMenu menu = new ClientPanelMenu(userID);
        assertEquals("PANEL KLIENTA (Jakub Jas)", menu.getHeader());
    }

    @Test
    void getMenuActions()
    {
        ClientPanelMenu menu = new ClientPanelMenu(userID);
        Map<Integer, Method> menuActions = new HashMap<>();

        try
        {
            menuActions.put(1, ClientPanelMenu.class.getMethod("findVacantRooms"));
            menuActions.put(2, ClientPanelMenu.class.getMethod("showReservations"));
            menuActions.put(3, ClientPanelMenu.class.getMethod("logout"));
            menuActions.put(4, ClientPanelMenu.class.getMethod("exit"));
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
        ClientPanelMenu menu = new ClientPanelMenu(userID);
        Map<Integer, String> menuDescriptions = new HashMap<>();

        menuDescriptions.put(1, "Wyszukaj wolne pokoje");
        menuDescriptions.put(2, "Wyświetl swoje rezerwacje");
        menuDescriptions.put(3, "Wyloguj");
        menuDescriptions.put(4, "Wyjście");

        assertEquals(menuDescriptions,menu.getActionDescriptions());
    }

    @Test
    void dateInput()
    {
        ClientPanelMenu menu = new ClientPanelMenu(userID);
        LocalDate data = LocalDate.of(2016, Month.NOVEMBER, 9);
        assertEquals(data,menu.dateInput("9/11/2016"));
    }

    @Test
    void logout()
    {
        ClientPanelMenu menu = new ClientPanelMenu(userID);
        LogInMenu menu1 = new LogInMenu();
        assertEquals(menu1.getHeader(),menu.logout().getHeader());
    }
}