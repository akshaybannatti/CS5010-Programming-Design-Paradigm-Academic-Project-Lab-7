package spreadsheet;

/**
 * BetterSpreadSheet extends SparseSpreadSheet for bulk assignment.
 */
public class BetterSpreadSheetImpl extends SparseSpreadSheet implements BetterSpreadSheet {

  @Override
  public void setRegion(int startRow, int startCol, int endRow, int endCol, double value)
      throws IllegalArgumentException {
    if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0) {
      throw new IllegalArgumentException("values cannot be negative");
    }
    if (endRow < startRow || endCol < startCol) {
      throw new IllegalArgumentException("last value should be greater and equal to start value");
    }
    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        set(row, col, value);
      }
    }
  }
}