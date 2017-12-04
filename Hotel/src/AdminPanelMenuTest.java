import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdminPanelMenuTest
{
    @Test
    void getAction()
    {
        AdminPanelMenu menu = new AdminPanelMenu();

        try
        {
            assertEquals(AdminPanelMenu.class.getMethod("findVacantRooms"),menu.getAction(1));
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void getHeader()
    {
        AdminPanelMenu menu = new AdminPanelMenu();
        assertEquals("PANEL RECEPCJONISTY", menu.getHeader());
    }

    @Test
    void getMenuActions()
    {
        AdminPanelMenu menu = new AdminPanelMenu();
        Map<Integer, Method> menuActions = new HashMap<>();

        try
        {
            menuActions.put(1, AdminPanelMenu.class.getMethod("findVacantRooms"));
            menuActions.put(2, AdminPanelMenu.class.getMethod("showReservation"));
            menuActions.put(3, AdminPanelMenu.class.getMethod("showReservations"));
            menuActions.put(4, AdminPanelMenu.class.getMethod("addReservation"));
            menuActions.put(5, AdminPanelMenu.class.getMethod("deleteReservation"));
            menuActions.put(6, AdminPanelMenu.class.getMethod("showSeasonalFees"));
            menuActions.put(7, AdminPanelMenu.class.getMethod("addSeasonalFee"));
            menuActions.put(8, AdminPanelMenu.class.getMethod("deleteSeasonalFee"));
            menuActions.put(9, AdminPanelMenu.class.getMethod("showRooms"));
            menuActions.put(10, AdminPanelMenu.class.getMethod("addRoom"));
            menuActions.put(11, AdminPanelMenu.class.getMethod("deleteRoom"));
            menuActions.put(12, AdminPanelMenu.class.getMethod("showUsers"));
            menuActions.put(13, AdminPanelMenu.class.getMethod("addUser"));
            menuActions.put(14, AdminPanelMenu.class.getMethod("deleteUser"));
            menuActions.put(15, AdminPanelMenu.class.getMethod("logout"));
            menuActions.put(16, AdminPanelMenu.class.getMethod("exit"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        assertEquals(menuActions,menu.getMenuActions());
    }

    @Test
    void getActionDescriptions()
    {
        AdminPanelMenu menu = new AdminPanelMenu();
        Map<Integer, String> menuDescriptions = new HashMap<>();

        menuDescriptions.put(1, "Wyszukaj wolne pokoje");
        menuDescriptions.put(2, "Wyświetl pojedynczą rezerwację");
        menuDescriptions.put(3, "Wyświetl wszystkie rezerwacje");
        menuDescriptions.put(4, "Dodaj rezerwację");
        menuDescriptions.put(5, "Usuń rezerwację");
        menuDescriptions.put(6, "Wyświetl okresy specjalne");
        menuDescriptions.put(7, "Dodaj okres specjalny");
        menuDescriptions.put(8, "Usuń okres specjalny");
        menuDescriptions.put(9, "Wyświetl pokoje");
        menuDescriptions.put(10, "Dodaj pokój");
        menuDescriptions.put(11, "Usuń pokój");
        menuDescriptions.put(12, "Wyświetl użytkowników");
        menuDescriptions.put(13, "Dodaj użytkownika");
        menuDescriptions.put(14, "Usuń użytkownika");
        menuDescriptions.put(15, "Wyloguj");
        menuDescriptions.put(16, "Wyjście");

        assertEquals(menuDescriptions,menu.getActionDescriptions());
    }

    @Test
    void dateInput()
    {
        AdminPanelMenu menu = new AdminPanelMenu();
        LocalDate data = LocalDate.of(2016, Month.NOVEMBER, 9);
        assertEquals(data,menu.dateInput("9/11/2016"));
    }

    @Test
    void logout()
    {
        AdminPanelMenu menu = new AdminPanelMenu();
        LogInMenu menu1 = new LogInMenu();
        assertEquals(menu1.getHeader(),menu.logout().getHeader());
    }
}