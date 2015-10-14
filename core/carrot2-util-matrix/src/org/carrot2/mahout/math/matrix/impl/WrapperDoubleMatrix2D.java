/*
Copyright � 1999 CERN - European Organization for Nuclear Research.
Permission to use, copy, modify, distribute and sell this software and its documentation for any purpose 
is hereby granted without fee, provided that the above copyright notice appear in all copies and 
that both that copyright notice and this permission notice appear in supporting documentation. 
CERN makes no representations about the suitability of this software for any purpose. 
It is provided "as is" without expressed or implied warranty.
*/
package org.carrot2.mahout.math.matrix.impl;

import org.carrot2.mahout.math.matrix.DoubleMatrix1D;
import org.carrot2.mahout.math.matrix.DoubleMatrix2D;

/* removed */
class WrapperDoubleMatrix2D extends DoubleMatrix2D {
  /*
   * The elements of the matrix.
   */
  private final DoubleMatrix2D content;

  /* removed */
  WrapperDoubleMatrix2D(DoubleMatrix2D newContent) {
    if (newContent != null) {
      setUp(newContent.rows(), newContent.columns());
    }
    this.content = newContent;
  }

  /* removed */
  @Override
  protected DoubleMatrix2D getContent() {
    return content;
  }

  /* removed */
  @Override
  public double getQuick(int row, int column) {
    return content.getQuick(row, column);
  }

  /* removed */
  @Override
  public DoubleMatrix2D like(int rows, int columns) {
    return content.like(rows, columns);
  }

  /* removed */
  @Override
  public DoubleMatrix1D like1D(int size) {
    return content.like1D(size);
  }

  /* removed */
  @Override
  protected DoubleMatrix1D like1D(int size, int offset, int stride) {
    throw new UnsupportedOperationException(); // should never get called
  }

  /* removed */
  @Override
  public void setQuick(int row, int column, double value) {
    content.setQuick(row, column, value);
  }

  /* removed */
  @Override
  public DoubleMatrix1D viewColumn(int column) {
    return viewDice().viewRow(column);
  }

  /* removed */
  @Override
  public DoubleMatrix2D viewColumnFlip() {
    if (columns == 0) {
      return this;
    }
    return new WrapperDoubleMatrix2D(WrapperDoubleMatrix2D.this) {
      @Override
      public double getQuick(int row, int column) {
        return content.get(row, columns - 1 - column);
      }

      @Override
      public void setQuick(int row, int column, double value) {
        content.set(row, columns - 1 - column, value);
      }
    };
  }

  /* removed */
  @Override
  public DoubleMatrix2D viewDice() {
    DoubleMatrix2D view = new WrapperDoubleMatrix2D(this) {
      @Override
      public double getQuick(int row, int column) {
        return content.get(column, row);
      }

      @Override
      public void setQuick(int row, int column, double value) {
        content.set(column, row, value);
      }
    };
    view.rows = columns;
    view.columns = rows;
    return view;
  }

  /* removed */
  @Override
  public DoubleMatrix2D viewPart(final int row, final int column, int height, int width) {
    checkBox(row, column, height, width);
    DoubleMatrix2D view = new WrapperDoubleMatrix2D(this) {
      @Override
      public double getQuick(int i, int j) {
        return content.get(row + i, column + j);
      }

      @Override
      public void setQuick(int i, int j, double value) {
        content.set(row + i, column + j, value);
      }
    };
    view.rows = height;
    view.columns = width;

    return view;
  }

  /* removed */
  @Override
  public DoubleMatrix1D viewRow(int row) {
    checkRow(row);
    return new DelegateDoubleMatrix1D(this, row);
  }

  /* removed */
  @Override
  public DoubleMatrix2D viewRowFlip() {
    if (rows == 0) {
      return this;
    }
    return new WrapperDoubleMatrix2D(WrapperDoubleMatrix2D.this) {
      @Override
      public double getQuick(int row, int column) {
        return content.get(rows - 1 - row, column);
      }

      @Override
      public void setQuick(int row, int column, double value) {
        content.set(rows - 1 - row, column, value);
      }
    };
  }

  /* removed */
  @Override
  public DoubleMatrix2D viewSelection(int[] rowIndexes, int[] columnIndexes) {
    // check for "all"
    if (rowIndexes == null) {
      rowIndexes = new int[rows];
      for (int i = rows; --i >= 0;) {
        rowIndexes[i] = i;
      }
    }
    if (columnIndexes == null) {
      columnIndexes = new int[columns];
      for (int i = columns; --i >= 0;) {
        columnIndexes[i] = i;
      }
    }

    checkRowIndexes(rowIndexes);
    checkColumnIndexes(columnIndexes);
    final int[] rix = rowIndexes;
    final int[] cix = columnIndexes;

    DoubleMatrix2D view = new WrapperDoubleMatrix2D(this) {
      @Override
      public double getQuick(int i, int j) {
        return content.get(rix[i], cix[j]);
      }

      @Override
      public void setQuick(int i, int j, double value) {
        content.set(rix[i], cix[j], value);
      }
    };
    view.rows = rowIndexes.length;
    view.columns = columnIndexes.length;

    return view;
  }

  /* removed */
  @Override
  protected DoubleMatrix2D viewSelectionLike(int[] rowOffsets, int[] columnOffsets) {
    throw new UnsupportedOperationException(); // should never be called
  }

}
