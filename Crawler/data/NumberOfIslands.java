1
https://raw.githubusercontent.com/nishantc1527/LeetCode/master/src/NumberOfIslands.java
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int count = 0;

        for(int i = 0; i < grid.length; i ++) {
            for(int j = 0; j < grid[i].length; j ++) {
                if(grid[i][j] == '1') {
                    fill(grid, i, j);
                    count ++;
                }
            }
        }

        return count;
    }

    private void fill(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        fill(grid, i + 1, j);
        fill(grid, i - 1, j);
        fill(grid, i, j + 1);
        fill(grid, i, j - 1);
    }

}
