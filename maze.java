import java.util.ArrayList;
import java.util.Arrays;

// LECTURE 28

public class Maze {
    public static void main(String[] args) {
        // System.out.println("Total no. of Paths: " + count(3, 3));
        // path("", 3, 3);
        // System.out.println("ArrayList of Paths: " + pathRetDiagonal("", 3, 3));
        boolean[][] maze = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        // pathRestictons("", maze, 0, 0);
        // allDirections("", maze, 0, 0);
        int[][] path = new int[maze.length][maze[0].length];
        printPath("", maze, 0, 0, path, 1);
    }

    static int count(int r, int c) {
        if (r == 1 || c == 1)
            return 1;

        int left = count(r - 1, c);
        int right = count(r, c - 1);

        return left + right;
    }

    static void path(String p, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(p);
            return;
        }

        if (r > 1)
            path(p + 'D', r - 1, c);

        if (c > 1)
            path(p + 'R', r, c - 1);
    }

    static ArrayList<String> pathRet(String p, int r, int c) {
        if (r == 1 && c == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();

        if (r > 1)
            list.addAll(pathRet(p + 'D', r - 1, c));

        if (c > 1)
            list.addAll(pathRet(p + 'R', r, c - 1));

        return list;
    }

    static ArrayList<String> pathRetDiagonal(String p, int r, int c) {
        if (r == 1 && c == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();

        if (r > 1 && c > 1)
            list.addAll(pathRetDiagonal(p + 'D', r - 1, c - 1));

        if (r > 1)
            list.addAll(pathRetDiagonal(p + 'V', r - 1, c));

        if (c > 1)
            list.addAll(pathRetDiagonal(p + 'H', r, c - 1));

        return list;
    }

    static void pathRestictons(String p, boolean[][] maze, int r, int c) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }

        if (!maze[r][c])
            return;

        maze[r][c] = false;
        if (r < maze.length - 1)
            pathRestictons(p + 'D', maze, r + 1, c);

        if (c < maze[0].length - 1)
            pathRestictons(p + 'R', maze, r, c + 1);

        maze[r][c] = false;

    }

    static void allDirections(String p, boolean[][] maze, int r, int c) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }

        if (!maze[r][c])
            return;

        maze[r][c] = false;

        if (r < maze.length - 1) // Down
            allDirections(p + 'D', maze, r + 1, c);

        if (c < maze[0].length - 1) // Right
            allDirections(p + 'R', maze, r, c + 1);

        if (r > 0) // Up
            allDirections(p + 'R', maze, r - 1, c);

        if (c > 0) // Left
            allDirections(p + 'R', maze, r, c - 1);

        maze[r][c] = true;

    }

    static void printPath(String p, boolean[][] maze, int r, int c, int[][] path, int step) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            path[r][c] = step;
            for (int[] arr : path)
                System.out.println(Arrays.toString(arr));
            System.out.println(p);
            System.out.println();
            return;
        }

        if (!maze[r][c])
            return;

        maze[r][c] = false;
        path[r][c] = step;

        if (r < maze.length - 1) // Down
            printPath(p + 'D', maze, r + 1, c, path, step + 1);

        if (c < maze[0].length - 1) // Right
            printPath(p + 'R', maze, r, c + 1, path, step + 1);

        if (r > 0) // Up
            printPath(p + 'U', maze, r - 1, c, path, step + 1);

        if (c > 0) // Left
            printPath(p + 'L', maze, r, c - 1, path, step + 1);

        maze[r][c] = true;
        path[r][c] = 0;
    }
}
