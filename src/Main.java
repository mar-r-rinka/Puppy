import java.util.Arrays;

public class Main {
    public static String direction;
    static int n = 10;
    static String[][] field = new String[n][n];
    static String[][] memory = new String[n][n];

    public static void main(String[] args) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(memory[i], "?");
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(field[i], "-");
        }
        field[0][0] = "Щ";
        for (int i = 0; i < 2 * n; i++) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            while (!field[x][y].equals("-")) {
                x = getRandomNumber();
                y = getRandomNumber();
            }
            field[x][y] = "*";
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 3 && j == 8) {
                    System.out.print("Ч ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
        findPath(field, 8, 3, memory);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * n);
    }

    public static void findPath(String[][] field, int x0, int y0, String[][] memory) {
        String[][] path = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = "нет";
            }
        }
        int x = x0;
        int y = y0;
        while (!(x == 0 && y == 0)) {
            direction = whereFrom(field, x, y, memory);
            if (direction.equals("N")) {
                System.out.println("Нет такого пути :(");
                break;
            } else if (direction.equals("U")) {
                path[y][x] = "да";
                y -= 1;
            } else if (direction.equals("L")) {
                path[y][x] = "да";
                x -= 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == y0 && j == x0) {
                    field[i][j] = "Ч";
                } else if ((path[i][j]).equals("да")) {
                    field[i][j] = "X";
                }
            }
        }
    }

    public static String whereFrom(String[][] field, int x0, int y0, String[][] memory) {
        if (!memory[y0][x0].equals("?")) {
            return memory[y0][x0];
        }

        if (x0 > 0) {
            int left_x = x0 - 1;
            int left_y = y0;
            if (left_x == 0 && left_y == 0) {
                memory[y0][x0] = "L";
                return "L";
            }
            if (!(field[left_y][left_x]).equals("*")) {
                if (!(whereFrom(field, left_x, left_y, memory)).equals("N")) {
                    memory[y0][x0] = "L";
                    return "L";
                }
            }
        }

        if (y0 > 0) {
            int up_x = x0;
            int up_y = y0 - 1;
            if (up_x == 0 && up_y == 0) {
                memory[y0][x0] = "U";
                return "U";
            }
            if (!(field[up_y][up_x]).equals("*")) {
                if (!(whereFrom(field, up_x, up_y, memory)).equals("N")) {
                    memory[y0][x0] = "U";
                    return "U";
                }

            }
        }
        memory[y0][x0] = "N";
        return "N";
    }
}