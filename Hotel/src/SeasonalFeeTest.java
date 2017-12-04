import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class SeasonalFeeTest
{

    @Test
    void getEventName()
    {
        LocalDate startDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate endDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        SeasonalFee seasonalFee = new SeasonalFee("Testowy", startDate, endDate, 3.5);
        assertEquals("Testowy", seasonalFee.getEventName());
    }

    @Test
    void getStartDate()
    {
        LocalDate startDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate endDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        SeasonalFee seasonalFee = new SeasonalFee("Testowy", startDate, endDate, 3.5);
        assertEquals(startDate, seasonalFee.getStartDate());
    }

    @Test
    void getEndDate()
    {
        LocalDate startDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate endDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        SeasonalFee seasonalFee = new SeasonalFee("Testowy", startDate, endDate, 3.5);
        assertEquals(endDate, seasonalFee.getEndDate());
    }

    @Test
    void getFee()
    {
        LocalDate startDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate endDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        SeasonalFee seasonalFee = new SeasonalFee("Testowy", startDate, endDate, 3.5);
        assertEquals(3.5, seasonalFee.getFee());
    }

    @Test
    void toStringTest()
    {
        LocalDate startDate = LocalDate.of(2016, Month.NOVEMBER, 9);
        LocalDate endDate = LocalDate.of(2016, Month.NOVEMBER, 15);
        SeasonalFee seasonalFee = new SeasonalFee("Testowy", startDate, endDate, 3.5);
        String testString = String.format("%-20s%-15s%-15s%-12s",
                "Testowy", "9/11/2016", "15/11/2016", "3.5 PLN");
        assertEquals(testString,seasonalFee.toString());
    }
}