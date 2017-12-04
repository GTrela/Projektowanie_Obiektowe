import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest
{

    @Test
    void getNr()
    {
        Room room = new Room(1,4,"Opisik",Comfort.apartament);
        assertEquals(1,room.getNr());
    }

    @Test
    void getnOfBeds()
    {
        Room room = new Room(1,4,"Opisik",Comfort.apartament);
        assertEquals(4,room.getnOfBeds());
    }

    @Test
    void getDescription()
    {
        Room room = new Room(1,4,"Opisik",Comfort.apartament);
        assertEquals("Opisik",room.getDescription());
    }

    @Test
    void getComfort()
    {
        Room room = new Room(1,4,"Opisik",Comfort.apartament);
        assertEquals(Comfort.apartament,room.getComfort());
    }

    @Test
    void compareTo()
    {
        Room room1 = new Room(1,4,"Opisik 1",Comfort.apartament);
        Room room2 = new Room(1,3,"Opisik 2",Comfort.apartament);

        assertEquals(1,room1.compareTo(room2));
    }

    @Test
    void toStringTest()
    {
        Room room = new Room(1,4,"Opisik",Comfort.apartament);
        String testString = String.format("%-8d%-8d%-15s%-40s",1,4,Comfort.apartament,"Opisik");

        assertEquals(testString,room.toString());
    }
}