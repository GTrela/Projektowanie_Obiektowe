import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class NoVacantRoomsTest
{
    @Test
    void noVacantRooms()
    {
        LocalDate startDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate endDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        String testString = "W okresie od 2016-11-09 do 2016-11-15, nie znaleziono żadnych wolnych pokoi!";

        try
        {
            throw new NoVacantRooms(startDate,endDate);
        }
        catch (NoVacantRooms e)
        {
            assertEquals(testString,e.getMessage());
        }
    }
}