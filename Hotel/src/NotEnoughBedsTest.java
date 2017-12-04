import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEnoughBedsTest
{
    @Test
    void notEnoughBeds()
    {
        String testString = "W dostępnych pokojach, nie ma wystarczającej ilości wolnych łózek!";

        try
        {
            throw new NotEnoughBeds();
        }
        catch (NotEnoughBeds e)
        {
            assertEquals(testString,e.getMessage());
        }
    }
}