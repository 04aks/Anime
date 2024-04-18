package aks.Handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import aks.AppFiles.frame2.CharactersPanel;
import aks.AppFiles.frame3.CharacterDetailsPanel;

public class BrowseMouseHandler implements MouseListener{
    CharactersPanel cp;
    CharacterDetailsPanel cdp;
    public BrowseMouseHandler(CharacterDetailsPanel cdp, CharactersPanel cp){
        this.cp = cp;
        this.cdp = cdp;
    }
    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(cdp.isFocusable()){
            String fullname;
            int commaIndex;
            String firstname;
            String lastname;
            if(cdp.rect.contains(e.getX(),e.getY())){

                fullname = cp.characterAttr[cp.selectedCharacterID].getChar_va();
                commaIndex = fullname.indexOf(",");
                firstname = fullname.substring(0, commaIndex);
                lastname = fullname.substring(commaIndex+2,fullname.length());
                cdp.browseChika(firstname,lastname);
            }
            if(cdp.rectC.contains(e.getX(),e.getY())){

                fullname = cp.characterAttr[cp.selectedCharacterID].getChar_name();
                if(fullname.contains(",")){
                    commaIndex = fullname.indexOf(",");
                    firstname = fullname.substring(0, commaIndex).replace(" ", "+");
                    lastname = fullname.substring(commaIndex+2, fullname.length());
                    cdp.browseChika(firstname, lastname);
                }else{
                    cdp.browseChika(fullname, ".");}
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
    
}
