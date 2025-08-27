package mikey.ui;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream original = System.out;

    @BeforeEach void setup() { System.setOut(new PrintStream(out)); }
    @AfterEach  void tearDown() { System.setOut(original); }

    @Test
    void greet_printsHello() {
        Ui ui = new Ui();
        ui.greet();
        String printed = out.toString();
        assertTrue(printed.contains("Hello, I'm Mikey!"));
    }
}
