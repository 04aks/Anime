package aks.AppFiles.frame2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import javax.swing.JPanel;

import aks.AppFiles.FrameManager;
import aks.AppFiles.frame3.CharacterFrame;
import aks.Backend.CharacterAttr;
import aks.Backend.CharactersDetails;
import aks.Handlers.MouseHandler;
import aks.Handlers.WheelHandler;
import aks.Threads.CharacterDetailsThread;

public class CharactersPanel extends JPanel{
    public int charactersNum = 0;
    public CharacterAttr[] characterAttr; 
    public String animeInfo, chracterInfo;
    public LinkedList<CharacterAttr> character = new LinkedList<CharacterAttr>();
    private MouseHandler mh = new MouseHandler(this);
    private WheelHandler wh = new WheelHandler(this);
    public CharacterFrame characterFrame;
    public int scroll;
    public int selectedCharacterID;
    public CharacterDetailsThread cdThread;
    public CharactersDetails cd;
    FrameManager fm;
    public CharactersPanel(FrameManager fm){
        this.fm = fm;
        cd = new CharactersDetails(fm);
        setLayout(new BorderLayout());
        addMouseListener(mh);
        addMouseWheelListener(wh);
        setVisible(true);
    }
    public void setNum(int num){
        this.charactersNum = num;
        characterAttr = new CharacterAttr[charactersNum]; 
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12));
        g2.setColor(Color.GRAY);
        int y = 0 + scroll;
        
        for(int i = 0; i<character.size(); i++){

            g2.drawRoundRect(0, y, getWidth()-1, 50, 20, 20);

            g2.drawString(i+1+"    "+character.get(i).getChar_name() + " / " +
                        character.get(i).getRole(), 10, y+30);

            if(characterAttr[i] != null){
                characterAttr[i].butto.setBounds(0,y,getWidth()-1,50);
            }  
            y+= 55;
        }
    }
}
