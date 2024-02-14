import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

public class GuessHandlerTest {

    @Test
    public void countTest1() {
        GuessHandler handler = new GuessHandler("1234");
        Assert.expectThrows(WrongInputException.class,
                () -> handler.countCowsAndOxes("ajdslkasmd"));
    }

    @Test
    public void countTest2() {
        GuessHandler handler = new GuessHandler("1234");
        Assert.expectThrows(WrongInputException.class,
                () -> handler.countCowsAndOxes("12345"));
    }

    @Test
    public void countTest3() {
        GuessHandler handler = new GuessHandler("1234");
        Assert.expectThrows(WrongInputException.class,
                () -> handler.countCowsAndOxes("1233"));
    }

    @Test
    public void countTest4() {
        GuessHandler handler = new GuessHandler("1234");
        try {
            handler.countCowsAndOxes("1234");
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(handler.allOxes());
    }
}
