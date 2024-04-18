package aks.AppFiles.frame2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import aks.AppFiles.FrameManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;


public class DetailsFrame extends JFrame{
    FrameManager fm;
    static int WIDTH = 450, HEIGHT = 720;
    public DetailsFrame(FrameManager fm){
        this.fm = fm;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setResizable(false);
        add(setPanel());

        setLocation(5 + WIDTH + 5, 10);;
        setVisible(true);
    }
    public JPanel setPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Table", tablePanel());
        tabbedPane.addTab("Details", detailsPannel());

        

        

        panel.add(tabbedPane, BorderLayout.CENTER);
        return panel;
    }
    private JPanel detailsPannel() {
        JPanel panel = new JPanel();
        return panel;
    }
    public Component tablePanel(){
        return fm.cp;
    }
    
}
