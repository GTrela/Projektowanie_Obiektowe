import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomInUseTest
{
    @Test
    void roomInUse()
    {
        String testString = "Pokoj o id = 1 jest uzywany!";

        try
        {
            throw new RoomInUse(1);
        }
        catch (RoomInUse e)
        {
            assertEquals(testString,e.getMessage());
        }
    }
}