import java.awt.Color;
public class Water extends Particle{

    public Water(){
        super(1);
        this.color=Color.decode("#53b3d0");
    }

    @Override
    public void update(Particle [][]grid,int y,int x){

        if (updated) return;
        if (y+1 == grid.length) {
            updated = true;
            return;
        }

        if (canMove(x, y+1, grid)) {
            grid[y][x] = grid[y+1][x];               
            grid[y+1][x] = this;
            updated = true;
            return;
        }

        int direction=Math.random()<0.5 ? 1:-1;//making the movement random
        if(canMove(x+direction, y, grid)){
            grid[y][x]=grid[y][x+direction];
            grid[y][x+direction]=this;
            updated = true;
            return;
        }
        
        if(canMove(x-direction, y, grid)){
            grid[y][x]=grid[y][x-direction];
            grid[y][x-direction]=this;
            updated = true;
        }    
    }  
    
}
