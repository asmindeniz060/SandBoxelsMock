
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;


public class drawPanel extends JPanel{

    private Particle [][] grid = new Particle[113][120];
    private MyFrame myframe ;
    final int cellSize=5;
    
    public drawPanel(MyFrame myframe){
        this.setBounds(0, 0, 600, 600);
        this.setBackground(Color.WHITE);
        this.myframe=myframe;
        MousePressListener listener = new MousePressListener();
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    
    class MousePressListener implements MouseListener, MouseMotionListener
         {  
            //radius can be changed
            double radius=30;
            
            @Override
            public void mouseClicked(MouseEvent event)
            {  
                int x = event.getX();
                int y = event.getY();

                for (int i = x - (int)radius; i <= x + radius; i++) {
                    for (int j = y - (int)radius; j <= y + radius; j++) {
                        int dx = i - x;
                        int dy = j - y;

                        if (dx * dx + dy * dy <= radius * radius) {
                            applyBrush(i, j);
                        }
                    }
                }
               
            }

            @Override
            public void mousePressed(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();

                for (int i = x - (int)radius; i <= x + radius; i++) {
                    for (int j = y - (int)radius; j <= y + radius; j++) {
                        int dx = i - x;
                        int dy = j - y;

                        if (dx * dx + dy * dy <= radius * radius) {
                            applyBrush(i, j);
                     }
                 }
                }
            }

            @Override
            public void mouseDragged(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();

                for (int i = x - (int)radius; i <= x + radius; i++) {
                    for (int j = y - (int)radius; j <= y + radius; j++) {
                        int dx = i - x;
                        int dy = j - y;

                        if (dx * dx + dy * dy <= radius * radius) {
                            applyBrush(i, j);
                     }
                 }
                }
              }
            @Override
            public void mouseReleased(MouseEvent event) {}
            @Override
            public void mouseEntered(MouseEvent event) {}
            @Override
            public void mouseExited(MouseEvent event) {}
            @Override
            public void mouseMoved(MouseEvent e) {}     
        }


    public void updateIt(){

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                Particle p = grid[y][x];
                if (p != null) {
                    p.reset();
                }
            }
        }

        ArrayList <int[]> particles= new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                Particle p = grid[y][x];
                if (p != null) {
                    particles.add(new int[]{y,x});
                }
            }
        }

        for (int pass = 0; pass < 8; pass++) {

            // shuffle visit order
            Collections.shuffle(particles);

            // visit in random order
            for (int[] pos : particles) {
                int y = pos[0];
                int x = pos[1];

                Particle p = grid[y][x];
                if(p!=null){
                p.update(grid, y, x);
                }
            }
        }

        for (int y = grid.length - 1; y >= 0; y--) {
            for (int x = 0; x < grid[0].length; x++) {
                Particle p = grid[y][x];
                    if (p != null&&!p.updateState()) {
                        p.update(grid,y,x);
                    }
            }
        }
        repaint();
    }
         

    private void applyBrush(int mouseX, int mouseY) {
        int x = mouseX / 5;
        int y = mouseY / 5;

        if (x < 0 || y < 0 || y >= grid.length || x >= grid[0].length) {return;}

        String sel = myframe.getSelection();
        if (sel == null) return;

        if (grid[y][x] == null) {
            switch (sel) {
                case "SAND" -> grid[y][x] = new Sand();
                case "WATER" -> grid[y][x] = new Water();
                case "SMOKE" -> grid[y][x] = new Smoke();
                case "STONE" -> grid[y][x] = new Stone();
                case "SEED" -> grid[y][x] = new Seed();
            }
        }
        else{
            if(sel.equals("CLEAR")){
                grid[y][x]=null;
            }
        }
            repaint();
    }

        @Override
        protected void paintComponent(Graphics g)
        {  
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    Particle p = grid[y][x];
                    if (p != null) {
                        g2.setColor(p.getColor());
                        g2.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }
        
  
