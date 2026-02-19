import java.awt.Color;
public class Smoke extends Particle {

    private  int colorNum=55;
    private final static int MAXIMUM_AGE =100;
    private int nowAge;

    //constructor
    public Smoke(){
        super(0);
        this.color=new Color(colorNum,colorNum,colorNum);
        nowAge=1;
    }

    @Override
    public void update(Particle [][]grid,int y,int x){ 
    
        if (updated) return;
        colorNum+=2;
        nowAge++;
        
        if(this.nowAge==100||colorNum>255){
            grid[y][x]=null;
            return;
        }

        color=new Color(colorNum,colorNum,colorNum);

        if (y-1 == 0) {
            updated = true;
            return;
        }
        
        
        if(Math.random()<riseProbability()){
            if (canMove(x, y-1, grid)) {
            grid[y][x] = grid[y - 1][x];               
            grid[y-1][x] = this;
            updated = true;
            return;
            }
            int direction=Math.random()<0.5 ? 1:-1;
            if(canMove(x+direction, y-1, grid)){
                grid[y][x]=grid[y-1][x+direction];
                grid[y-1][x+direction]=this;
                updated = true;
            }
            
            if(canMove(x-direction, y-1, grid)){
                grid[y][x]=grid[y-1][x-direction];
                grid[y-1][x-direction]=this;
                updated = true;
            }    
        }

        //if cant move upwards then it spreads
        else if(Math.random()<spreadProbability()){
            int direction=Math.random()<0.5 ? 1:-1;//for it to go randomly right or left
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
    
    public double riseProbability(){//using the given formula
        return 1-0.8*nowAge/MAXIMUM_AGE;
    }

    public double spreadProbability(){//using the given formula
        return 0.2+0.5*nowAge/MAXIMUM_AGE;
    }
    
}
