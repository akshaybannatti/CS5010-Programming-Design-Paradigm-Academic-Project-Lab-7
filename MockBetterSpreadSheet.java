import spreadsheet.BetterSpreadSheet;

/**
 * Spreadsheet.
 */
public class MockBetterSpreadSheet implements BetterSpreadSheet {

  private final StringBuilder log;

  /**
   * StringBuilder value.
   *
   * @param log value.
   */
  public MockBetterSpreadSheet(StringBuilder log) {
    this.log = log;
  }

  @Override
  public double get(int row, int col) {
    log.append(String.format("get(%d,%d)\n", row, col));
    return 0;
  }

  @Override
  public void set(int row, int col, double value) {
    log.append(String.format("set(%d,%d,%f)\n", row, col, value));
  }

  @Override
  public boolean isEmpty(int row, int col) {
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
    log.append(String.format("setRegion(%d,%d,%d,%d,%f)\n",
        startRow, startCol, endRow, endCol, value));
  }
}
