package aks.Handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import aks.AppFiles.frame2.CharactersPanel;
import aks.AppFiles.frame3.CharacterFrame;
import aks.Threads.CharacterDetailsThread;


public class MouseHandler implements MouseListener{
    CharactersPanel cp;
    public MouseHandler(CharactersPanel cp){
        this.cp = cp;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        //! 0(n) MOST EFFICIENT ALGORITHM AVAILABLE
        if(cp.isFocusable()){
            // int attempts = 0;
            for(int i = 0; i < cp.character.size(); i++){
                // attempts++;
                if(cp.characterAttr[i] != null){
                    if(cp.characterAttr[i].butto.contains(e.getX(), e.getY())){
                        cp.selectedCharacterID = i;
                        if(cp.characterFrame == null){
                            cp.characterFrame = new CharacterFrame(cp);
                        }
                        cp.cdThread = new CharacterDetailsThread(cp, cp.selectedCharacterID);
                        cp.cdThread.execute();
                        
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
}
