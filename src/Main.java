import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 5, 3}, {4, 2, 1, 1, 2}, {1, 1, 1, 4, 3}, {2, 1, 1, 3, 1}, {4, 2, 1, 1, 2}};
        int[][] matrix1 = {{1, 2, 3}, {4, 2, 1}, {1, 1, 1}};
        Matrix res = new Matrix(3, 3, matrix1);

        System.out.println(res.buildMatrix());
    }
}

class Matrix {
    private int row;
    private int column;
    private int[][] matrix;

    Matrix(int row, int column, int[][] matrix) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
    }

    public int buildMatrix() {
        //два на два
        if (this.column != this.row || this.matrix.length != this.row || this.matrix[0].length != this.column) {
            return 0;
        }

        if (this.row == 2 && this.column == 2) {
            return twoDimensional(this.matrix);
        }

        int result = 0;
        //три на три - метод треугольника
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                result += this.matrix[i][j] * (int) Math.pow(-1, i + 1 + j + 1) * twoDimensional(findMinor(i, j));
            }
        }

        return result;
    }

    private void blockedLabels() {
        int[] labels = new int[this.matrix.length - 2];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = i;
        }
    }

    private int twoDimensional(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    private int[][] findMinor(int row, int column) {
        int currentRow = this.row - 1;
        int currentColumn = this.row - 1;

        int[][] minor = new int[currentRow][currentColumn];

        for (int i = 0, t = 0; i < this.matrix.length; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0, k = 0; j < this.matrix[i].length; j++) {
                if (j == column) {
                    continue;
                }
                minor[t][k] = this.matrix[i][j];
                System.out.print(minor[t][k] + " ");
                k++;
            }
            t++;
            System.out.println();
        }

        return minor;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }
}