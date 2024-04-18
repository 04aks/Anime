package aks.Handlers;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import aks.AppFiles.frame2.CharactersPanel;

public class WheelHandler implements MouseWheelListener{
    CharactersPanel cp;
    int scrollAmount = 20;
    public WheelHandler(CharactersPanel cp){
        this.cp = cp;
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {    
        if(e.getWheelRotation() > 0){
            cp.scroll -= scrollAmount;
        }
        else if(e.getWheelRotation() < 0){
            if(cp.scroll + scrollAmount >= 0){
                cp.scroll = 0;
            }else{
                cp.scroll += scrollAmount;
            }
        }
        cp.repaint();
    }
    
}
