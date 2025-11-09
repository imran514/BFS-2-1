/**
 * LeetCode: <a href="https://leetcode.com/problems/rotting-oranges/">Rotting Oranges</a>
 * Approach: Multi-source DFS with timestamping: start DFS from every initially rotten orange (value 2). Pass a
 * "time" (starting from 2) into DFS and write the earliest time a cell becomes rotten. Use pruning to avoid
 * overwriting cells that were reached earlier. After all DFS runs, scan the grid for any remaining fresh
 * oranges (1). The answer is the maximum timestamp - 2.
 * Time Complexity: O(R * C) where R and C are grid dimensions — each cell is effectively processed a constant
 * number of times thanks to pruning.
 * Space Complexity: O(R * C) for recursion stack in the worst case (deepest possible chain).
 * @see <a href="https://leetcode.com/problems/rotting-oranges/">LeetCode - Rotting Oranges</a>
 */
public class RottingOrangesDFS {

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    dfs(grid, i, j, 2);
                }
            }
        }

        int result = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) return -1;
                else {
                    result = Math.max(result, grid[i][j]);
                }
            }
        }
        return result - 2;
    }

    private void dfs(int[][] grid, int cr, int cc, int time) {
        //base
        if (cr < 0 || cc < 0 || cr == grid.length || cc == grid[0].length) return;
        if (grid[cr][cc] != 1 && grid[cr][cc] < time) return;
        //logic
        grid[cr][cc] = time;
        for (int[] dir : dirs) {
            int r = cr + dir[0];
            int c = cc + dir[1];
            dfs(grid, r, c, time + 1);
        }
    }
}
