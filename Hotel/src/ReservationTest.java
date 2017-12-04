import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest
{

    @Test
    void getId()
    {
        LocalDate checkInDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate checkOutDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        List<Long> roomsList = new ArrayList<>();
        roomsList.add(1L);
        roomsList.add(2L);
        roomsList.add(3L);
        Reservation reservation = new Reservation(1,checkInDate,checkOutDate,1,3530.5,roomsList);
        assertEquals(1,reservation.getId());
    }

    @Test
    void getCheckInDate()
    {
        LocalDate checkInDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate checkOutDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        List<Long> roomsList = new ArrayList<>();
        roomsList.add(1L);
        roomsList.add(2L);
        roomsList.add(3L);
        Reservation reservation = new Reservation(1,checkInDate,checkOutDate,1,3530.5,roomsList);
        assertEquals(checkInDate,reservation.getCheckInDate());
    }

    @Test
    void getCheckOutDate()
    {
        LocalDate checkInDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate checkOutDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        List<Long> roomsList = new ArrayList<>();
        roomsList.add(1L);
        roomsList.add(2L);
        roomsList.add(3L);
        Reservation reservation = new Reservation(1,checkInDate,checkOutDate,1,3530.5,roomsList);
        assertEquals(checkOutDate,reservation.getCheckOutDate());
    }

    @Test
    void getClientId()
    {
        LocalDate checkInDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate checkOutDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        List<Long> roomsList = new ArrayList<>();
        roomsList.add(1L);
        roomsList.add(2L);
        roomsList.add(3L);
        Reservation reservation = new Reservation(1,checkInDate,checkOutDate,1,3530.5,roomsList);
        assertEquals(1,reservation.getClientId());
    }

    @Test
    void getRoomsList()
    {
        LocalDate checkInDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate checkOutDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        List<Long> roomsList = new ArrayList<>();
        roomsList.add(1L);
        roomsList.add(2L);
        roomsList.add(3L);
        Reservation reservation = new Reservation(1,checkInDate,checkOutDate,1,3530.5,roomsList);
        assertEquals(roomsList,reservation.getRoomsList());
    }

    @Test
    void getTotalPrice()
    {
        LocalDate checkInDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate checkOutDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        List<Long> roomsList = new ArrayList<>();
        roomsList.add(1L);
        roomsList.add(2L);
        roomsList.add(3L);
        Reservation reservation = new Reservation(1,checkInDate,checkOutDate,1,3530.5,roomsList);
        assertEquals(3530.5,reservation.getTotalPrice());
    }
}