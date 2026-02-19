import java.awt.Color;

public class Sand extends Particle{
    

    public Sand(){
        super(3);
        this.color=Color.decode("#DAA520");
    }

    @Override
    public void update(Particle [][]grid,int y,int x){

        if (updated) return;
        if (y+1 == grid.length) {
            updated = true;
            return;
        }

        if (canMove(x, y+1, grid)) {
            grid[y][x] = grid[y + 1][x];               
            grid[y+1][x] = this;
            updated = true;
            return;
        }

        int direction=Math.random()<0.5 ? 1:-1;
        if(canMove(x+direction, y+1, grid)){
            grid[y][x]=grid[y+1][x+direction];
            grid[y+1][x+direction]=this;
            updated = true;
            return;
        }
        
        if(canMove(x-direction, y+1, grid)){
            grid[y][x]=grid[y+1][x-direction];
            grid[y+1][x-direction]=this;
            updated = true;
        }    
    }  
}
    

