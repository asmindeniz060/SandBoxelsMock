import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;


public class MyFrame extends JFrame {

    private JPanel drawPanel;
    private JPanel controlPanel;
    private ButtonGroup select;
    private ButtonGroup proceed;
    public MyFrame() {
        
        this.setTitle("Cell-Based Particle Simulation");
        this.setSize(830, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.drawPanel= new drawPanel(this);
        this.add(drawPanel,BorderLayout.WEST);
        this.add(choiceButtons());
        this.setVisible(true);
        this.setResizable(false);
    }

    public JPanel choiceButtons(){
        controlPanel = new JPanel();
        controlPanel.setBounds(600, 10, 220, 540);
        JToggleButton stepButton = new JToggleButton("Step");
        JToggleButton stopButton = new JToggleButton("Stop");
        proceed = new ButtonGroup();
        proceed.add(stepButton);
        proceed.add(stopButton);
        Timer timer= new Timer(35, new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent event){
            updateIt();
            }
        });
        timer.start();
        
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timer.isRunning()) {
                    updateIt();
                }
                if (timer.isRunning()) {
                    timer.stop();
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
    });
        JToggleButton clearButton = new JToggleButton("Clear");
        JToggleButton stoneButton = new JToggleButton("Place Stone");
        JToggleButton sandButton = new JToggleButton("Place Sand");
        JToggleButton waterButton = new JToggleButton("Place Water");
        JToggleButton smokeButton = new JToggleButton("Place Smoke");
        JToggleButton seedButton= new JToggleButton("Place Seed");
        stepButton.setBounds(10, 10, 85, 30);
        stopButton.setBounds(105, 10, 85, 30);
        clearButton.setBounds(10, 70, 180, 35);
        stoneButton.setBounds(10, 105, 180, 35);
        sandButton.setBounds(10, 140, 180, 35);
        waterButton.setBounds(10, 175, 180, 35);
        smokeButton.setBounds(10, 210, 180, 35);
        seedButton.setBounds(10,245,180,35);
        controlPanel.add(stepButton);
        controlPanel.add(stopButton);
        select = new ButtonGroup();
        select.add(clearButton);
        select.add(stoneButton);
        select.add(sandButton);
        select.add(waterButton);
        select.add(smokeButton);
        select.add(seedButton);
        controlPanel.add(clearButton);
        controlPanel.add(stoneButton);
        controlPanel.add(sandButton);
        controlPanel.add(waterButton);
        controlPanel.add(smokeButton);
        controlPanel.add(seedButton);
        controlPanel.setLayout(null);
        sandButton.setActionCommand("SAND");
        waterButton.setActionCommand("WATER");
        smokeButton.setActionCommand("SMOKE");
        clearButton.setActionCommand("CLEAR");
        stoneButton.setActionCommand("STONE");
        seedButton.setActionCommand("SEED");
        return controlPanel;
    }

    public void updateIt(){
        ((drawPanel)drawPanel).updateIt();
        repaint();
    }

    public String getSelection(){
        ButtonModel selected = select.getSelection();
        if (selected != null) {
            String selectedType = selected.getActionCommand();
            return selectedType;
        }
        return null;
    }

    public boolean proceed(){
        ButtonModel selected= proceed.getSelection();
        if(selected!=null){
            return selected.getActionCommand().equals("STOP");
        }
        return false;
    }
}
