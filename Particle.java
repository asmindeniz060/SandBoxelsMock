import java.awt.Color;
/**
   This component displays a rectangle that can be moved. 
*/
public class Particle
{  

   protected int priority;
   protected boolean updated=false;
   protected Color color;


   public Particle(int priority )
   {  
      // The rectangle that the paintComponent method draw  
      this.priority=priority;    
   }

   public Color getColor(){
      return this.color;
   }


   public void update(Particle [][] grid,int y, int x){}

  
   public void reset(){
      this.updated=false;
   }

   public boolean updateState(){
      return this.updated;
   }

   public int getPriority(){
      return this.priority;
   }

   public boolean canMove(int x, int y, Particle [][]grid){
      return  y>-1&&y < grid.length&& x>-1&&x<grid[0].length &&(grid[y][x]==null||grid[y][x].getPriority()<this.getPriority());
   }
} 
