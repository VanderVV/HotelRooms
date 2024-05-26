package example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static example.MainHotelProgram.mainProgram;

public class MainTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void HotelRoomsConfirmation() throws Exception {

        mainProgram("Sydney", "Hotel Sunshine", "Standard", "2 - Two", "25/05/2024", "27/05/2024", "1 - One", "1 - One");

    }

    @Test
    public void mainTest() {
    }
}