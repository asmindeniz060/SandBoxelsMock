import java.awt.Color;
public class Cactus  extends Particle{
    //*A cactus particle will not grow into a target cell if two or more cactus particles are already
//present in the surrounding positions. The surrounding positions considered are the cells
//directly above, above-left, above-right, left, and right of the target cell.*/

    private int growthLevel;
    private int updateSteps;

    //not displacebale and does not move
    public Cactus(int growthLevel){
        super(4);
        this.color=Color.decode("#00dd42") ;
        this.growthLevel=growthLevel;
        this.updateSteps=0;
    }
    
    public void update(Particle [][]grid,int y,int x){
        updateSteps++;
        if (updateSteps<10) return;
        if(growthLevel==8)return;

        if(willItGrow(grid, y, x)){
            if(y>-1&&y < grid.length&& x>-1&&x<grid[0].length &&grid[y-1][x]==null){       
            growthLevel++;      
            grid[y-1][x] = new Cactus(growthLevel) ;
            updated = true;
            this.updateSteps=0;
            return; 
            }
            if(Math.random()<0.1&&y>-1&&y < grid.length&& x>-1&&x<grid[0].length &&grid[y-1][x]==null){
                growthLevel++;      
                grid[y][x-1] = new Cactus(growthLevel) ;
                updated = true;
                this.updateSteps=0;
                return;
            }
            if(Math.random()<0.1&&y>-1&&y < grid.length&& x>-1&&x<grid[0].length &&grid[y-1][x]==null){
                growthLevel++;      
                grid[y][x+1] = new Cactus(growthLevel) ;
                updated = true;
                this.updateSteps=0;
                return;
            }

            if (y-1 == grid.length) {
                updated = true;
                return;
            }
        }
    }  
    public boolean willItGrow(Particle [][]grid,int y,int x){
        boolean canGrow=true;
        if(!(y>-1&&y < grid.length&& x>-1)){return false;}
        for(int i=x-1;i<x+2;i++){
            if(grid[y-1][x]instanceof Cactus){
                return false;
            }
        }
        if(grid[y][x-1] instanceof Cactus){return false;}
        if(grid[y][x+1] instanceof Cactus){return false;}
        return canGrow;
    }
}
