package spreadsheet;

/**
 * BetterSpreadSheet for assignment.
 */
public interface BetterSpreadSheet extends SpreadSheet {

  /**
   * All the cells are in the rectangular part area.
   */
  void setRegion(int startRow, int startCol, int endRow, int endCol, double value);
}
