import java.util.List;
/*
Amazon plans to open a Treasure Truck Pop Ups in the park areas of Technicia.
Technicia is represented as a grid of M*N blocks. Each block represents eitehr a park area
denoted by 1, or commercial area denoted by 0. Adjacent block with value 1 are considered a contigous
park. Diagonal blocks with Value 1 are not considered part of the same contiguity.
Amazon plans to have a treasure truck pop up.
 */
public class AmazonTreasureTruck {
    int numberAmazonTreasureTrucks(int rows, int column, List<List<Integer>> grid){
        if(grid == null || grid.size() == 0) return 0;
        int numOfAmazonTreasureTrucks = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < column; j++){
                if(grid.get(i).get(j) == 1){
                    numOfAmazonTreasureTrucks++;
                    dfs(i, j, grid, rows, column);
                }
            }
        }

        return numOfAmazonTreasureTrucks;
    }

    public void dfs(int r, int c, List<List<Integer>> grid, int nr, int nc){
        if(r >= nr || c >= nc || r < 0 || c < 0 || grid.get(r).get(c) == 0) return;
        grid.get(r).set(c, 0);
        dfs(r+1, c, grid, nr, nc);
        dfs(r-1, c, grid, nr, nc);
        dfs(r, c+1, grid, nr, nc);
        dfs(r, c-1, grid, nr, nc);
    }
}
