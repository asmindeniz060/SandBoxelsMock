import java.awt.Color;
public class Seed extends Particle {
    //The Seed particle is affected by gravity. If the seed lands on a cell that is not sand, it will disappear. 
     public Seed(){
        super(2);
        this.color=Color.decode("#d8cc5d") ;
    }

      public void update(Particle [][]grid,int y,int x){

        if (updated) return;
        if (y+1 == grid.length) {
            updated = true;
            return;
        }

        if ( y>-1&&y < grid.length&& x>-1&&x<grid[0].length &&grid[y+1][x]==null) {
            grid[y][x] = grid[y + 1][x];               
            grid[y+1][x] = this;
            updated = true;
            return;
        }

        else if ( y>-1&&y < grid.length&& x>-1&&x<grid[0].length &&grid[y+1][x] instanceof Sand) {
            grid[y][x] = new Cactus(1);             
            updated = true;
            return;
        }
        else{
            grid[y][x]=null;
        }
    }  
}
