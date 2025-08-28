package mikey.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    void deadline_parsesHappyPath() {
        Parser.ParseResult r = parser.parse("deadline submit report /by 2/12/2019 1800");
        assertFalse(r.isError);
        assertEquals(Parser.Command.DEADLINE, r.command);
        assertNotNull(r.arguments.byRaw);
    }

    @Test
    void deadline_badDateGivesNiceError() {
        Parser.ParseResult r = parser.parse("deadline x /by not-a-date");
        assertTrue(r.isError);
        assertTrue(r.errorMessage.contains("please use format"));
    }

    @Test
    void list_commandParses() {
        Parser.ParseResult r = parser.parse("list");
        assertFalse(r.isError);
        assertEquals(Parser.Command.LIST, r.command);
    }
}
