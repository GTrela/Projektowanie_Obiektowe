import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest
{
    @Test
    void getId()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        assertEquals(1,client.getId());
    }

    @Test
    void getName()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        assertEquals("Jan",client.getName());
    }

    @Test
    void getSurname()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        assertEquals("Kowalski",client.getSurname());
    }

    @Test
    void incVisitCount()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        assertEquals(0,client.getVisitCount());
        client.incVisitCount();
        assertEquals(1, client.getVisitCount());
    }

    @Test
    void decVisitCount()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        assertEquals(0,client.getVisitCount());
        client.incVisitCount();
        assertEquals(1, client.getVisitCount());
        client.decVisitCount();
        assertEquals(0, client.getVisitCount());
    }

    @Test
    void getVisitCount()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        assertEquals(0, client.getVisitCount());
    }

    @Test
    void toStringTest()
    {
        Client client = new Client(1, "Jan", "Kowalski");
        String testString = String.format("%-5s%-15s%-15s%-10s",1,"Jan","Kowalski",0);
        assertEquals(testString,client.toString());
    }
}