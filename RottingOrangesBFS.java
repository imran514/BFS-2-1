import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode: https://leetcode.com/problems/rotting-oranges/
 *
 * Approach:
 * - Multi-source BFS: enqueue all initially rotten oranges and perform level-order traversal.
 * - Each BFS level represents one minute; convert adjacent fresh oranges (1) to rotten and enqueue them.
 * - Track remaining fresh oranges; when zero, return elapsed minutes. If any fresh oranges remain after BFS, return -1.
 *
 * Time Complexity: O(R * C) where R and C are the grid dimensions — each cell is processed at most once.
 * Space Complexity: O(R * C) in the worst case for the queue.
 */
public class RottingOrangesBFS {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        int freshOrangeCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOrangeCount++;
                }
            }
        }
        if (freshOrangeCount == 0) return 0;

        int minutes = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] dir : dirs) {
                    int cr = current[0] + dir[0];
                    int cc = current[1] + dir[1];
                    if (cr >= 0 && cc >= 0 && cr < grid.length && cc < grid[0].length && grid[cr][cc] == 1) {
                        grid[cr][cc] = -1;
                        freshOrangeCount--;
                        queue.add(new int[]{cr, cc});
                        if(freshOrangeCount == 0) return minutes;
                    }
                }
            }

        }
        return  -1;
    }
}
