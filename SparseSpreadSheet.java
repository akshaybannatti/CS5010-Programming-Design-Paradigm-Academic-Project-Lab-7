package spreadsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class for sparse spreadsheet.
 */
public class SparseSpreadSheet implements SpreadSheet {
  private final Map<CellPosition, Double> sheet;
  private int width;
  private int height;

  /**
   * made an empty spreadsheet.
   */
  public SparseSpreadSheet() {
    this.sheet = new HashMap<CellPosition, Double>();
    this.width = 0;
    this.height = 0;
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row and column cannot be in negative value");
    }
    return this.sheet.getOrDefault(new CellPosition(row, col), 0.0);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row and column cannot be in negative");
    }
    this.sheet.put(new CellPosition(row, col), value);
    if ((row + 1) > height) {
      height = row + 1;
    }
    if ((col + 1) > width) {
      width = col + 1;
    }
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row and column cannot have a negative value");
    }
    return !this.sheet.containsKey(new CellPosition(row, col));
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  private static class CellPosition {
    private final int row;
    private final int column;

    private CellPosition(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CellPosition)) {
        return false;
      }
      CellPosition other = (CellPosition) o;
      return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.row, this.column);
    }
  }
}
