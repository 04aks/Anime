package aks.AppFiles.frame3;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import aks.AppFiles.frame2.CharactersPanel;

public class CharacterFrame extends JFrame{
    public static int WIDTH = 450, HEIGHT = 720;
    CharactersPanel cp;
    public CharacterDetailsPanel cdp;
    public CharacterFrame(CharactersPanel cp){
        this.cp = cp;
        cdp = new CharacterDetailsPanel(cp);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(5 + WIDTH*2 + 10, 10);
        setResizable(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        add(cdp);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent arg0) {
                cp.characterFrame = null;
            }
        });
    }
}
