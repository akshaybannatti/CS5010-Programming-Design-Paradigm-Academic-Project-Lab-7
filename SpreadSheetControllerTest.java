import static org.junit.Assert.assertEquals;
import static  org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import spreadsheet.BetterSpreadSheet;
import spreadsheet.SpreadSheetController;

/**
 * Tests for the SpreadSheetController.
 */
public class SpreadSheetControllerTest {

  private StringBuilder log;
  private Appendable out;

  /**
   * Setup test.
   */
  @Before
  public void setup() {
    log = new StringBuilder();
    out = new StringBuilder();
  }

  /**
   * Mock spreadsheet testing.
   */
  private static class MockSpreadSheet implements BetterSpreadSheet {
    private final StringBuilder log;

    public MockSpreadSheet(StringBuilder log) {
      this.log = log;
    }

    @Override
    public double get(int row, int col) {
      log.append(String.format("get(%d,%d)\n", row, col));
      return 42.0;
    }

    @Override
    public void set(int row, int col, double value) {
      log.append(String.format("set(%d,%d,%f)\n", row, col, value));
    }

    @Override
    public boolean isEmpty(int row, int col) {
      log.append(String.format("isEmpty(%d,%d)\n", row, col));
      return false;
    }

    @Override
    public int getWidth() {
      return 0;
    }

    @Override
    public int getHeight() {
      return 0;
    }

    @Override
    public void setRegion(int startRow, int startCol, int endRow, int endCol, double value) {
      log.append("bulkAssign ")
          .append(startRow).append(" ")
          .append(startCol).append(" ")
          .append(endRow).append(" ")
          .append(endCol).append(" ")
          .append(value).append("\n");
    }
  }

  @Test
  public void testWelcomeMessage() {
    StringReader input = new StringReader("q");
    SpreadSheetController controller = new SpreadSheetController(new MockSpreadSheet(log),
        input, out);
    controller.control();
    String[] lines = out.toString().split("\n");
    assertEquals("Welcome to the spreadsheet program!", lines[0].trim());
  }

  @Test
  public void testFarewellMessage() {
    Readable input = new StringReader("quit\n");
    SpreadSheetController controller =
        new SpreadSheetController(new MockSpreadSheet(log), input, out);
    controller.control();

    String output = out.toString().trim();
    String lastLine = output.substring(output.lastIndexOf("Thank you")).trim();

    assertEquals("Thank you for using this program!", lastLine);
  }

  @Test
  public void testAssignValueCommand() {
    Readable input = new StringReader("assign-value A 1 5.5 q\n");
    BetterSpreadSheet mock = new MockSpreadSheet(log);
    SpreadSheetController controller = new SpreadSheetController(mock, input, out);
    controller.control();

    String expectedLog = "set(0,0,5.500000)\n";
    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testPrintValueCommand() {
    Readable input = new StringReader("print-value A 1 q\n");
    BetterSpreadSheet mock = new MockSpreadSheet(log);
    SpreadSheetController controller = new SpreadSheetController(mock, input, out);
    controller.control();

    String expectedLog = "get(0,0)\n";
    assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testBulkAssignCommand() {
    Readable input = new StringReader("bulk-assign A 1 B 4 100 q\n");
    StringBuilder log = new StringBuilder();
    BetterSpreadSheet mock = new MockBetterSpreadSheet(log);
    Appendable out = new StringBuilder();

    SpreadSheetController controller = new SpreadSheetController(mock, input, out);
    controller.control();

    String expected = "setRegion(0,0,1,3,100.000000)\n";
    assertEquals(expected, log.toString());
  }

}
