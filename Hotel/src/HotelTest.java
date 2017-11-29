import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest
{
    @org.junit.jupiter.api.Test
    void isAdminPassCorrect()
    {
        Hotel hotel = Hotel.getInstance();
        assertFalse(hotel.isAdminPassCorrect("BłędneHasło"));
        assertTrue(hotel.isAdminPassCorrect("QMyWpYKgNGVXUYUjgZPiD6ahNcgbAm"));
    }

    @org.junit.jupiter.api.Test
    void setPath()
    {
        Hotel hotel = Hotel.getInstance();
        hotel.setPath("/testowa/sciezka/");
        assertEquals("/testowa/sciezka/",hotel.getPath());
    }

    @org.junit.jupiter.api.Test
    void addRoom()
    {
        Hotel hotel = Hotel.getInstance();
        long id = hotel.addRoom(4,"Ciekawy", Comfort.standardowy);
        assertEquals(id,hotel.getRooms().get(id).getNr());
        try
        {
            hotel.deleteRoom(id);
        }
        catch (RoomInUse roomInUse)
        {
            roomInUse.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void deleteRoom()
    {
        Hotel hotel = Hotel.getInstance();
        long id = hotel.addRoom(4,"Ciekawy", Comfort.standardowy);
        assertFalse(hotel.getRooms().isEmpty());
        try
        {
            hotel.deleteRoom(id);
        }
        catch (RoomInUse roomInUse)
        {
            roomInUse.printStackTrace();
        }
        assertTrue(hotel.getRooms().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void addClient()
    {
        Hotel hotel = Hotel.getInstance();
        long id = hotel.addClient("Test", "Testowy");
        assertEquals(id,hotel.getClients().get(id).getId());
        hotel.deleteClient(id);
    }

    @org.junit.jupiter.api.Test
    void deleteClient()
    {
        Hotel hotel = Hotel.getInstance();
        long id = hotel.addClient("Test", "Testowy");
        assertFalse(hotel.getClients().isEmpty());
        hotel.deleteClient(id);
        assertTrue(hotel.getClients().isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isDateWithinRange()
    {
        Hotel hotel = Hotel.getInstance();
        LocalDate rangeStart = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate rangeEnd = LocalDate.of(2016, Month.NOVEMBER, 15);
        LocalDate within = LocalDate.of(2016, Month.NOVEMBER, 10);
        assertTrue(hotel.isDateWithinRange(rangeStart, rangeEnd, within));
    }

    @org.junit.jupiter.api.Test
    void addSeasonalFee()
    {
        Hotel hotel = Hotel.getInstance();
        LocalDate rangeStart = LocalDate.of(2016, Month.JANUARY, 15);
        LocalDate rangeEnd = LocalDate.of(2016, Month.JANUARY, 28);
        hotel.addSeasonalFee("Ferie zimowe", rangeStart, rangeEnd, 20.4);
        assertEquals("Ferie zimowe",hotel.getSeasonalFees().get("Ferie zimowe").getEventName());
    }

    @org.junit.jupiter.api.Test
    void deleteSeasonalFee()
    {
        Hotel hotel = Hotel.getInstance();
        LocalDate rangeStart = LocalDate.of(2016, Month.JUNE, 1);
        LocalDate rangeEnd = LocalDate.of(2016, Month.SEPTEMBER, 1);
        hotel.addSeasonalFee("Wakacje", rangeStart, rangeEnd, 20.6);
        assertFalse(hotel.getSeasonalFees().isEmpty());
        hotel.deleteSeasonalFee("Wakacje");
        assertTrue(hotel.getSeasonalFees().isEmpty());
    }

}