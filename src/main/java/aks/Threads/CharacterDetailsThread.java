package aks.Threads;

import javax.swing.SwingWorker;

import aks.AppFiles.frame2.CharactersPanel;
import aks.other.MyUtil;

public class CharacterDetailsThread extends SwingWorker<Void,Void>{
    CharactersPanel cp;
    int index;
    public CharacterDetailsThread(CharactersPanel cp, int index){
        this.cp = cp;
        this.index = index;
    }
    @Override
    protected Void doInBackground() throws Exception {
        int charId = cp.cd.charactersOtherInfo(index);
        cp.cd.getJapaneseName(charId, index);
        cp.characterFrame.cdp.image = MyUtil.importImageFromWeb(cp.characterAttr[cp.selectedCharacterID].getImage_url());
        cp.characterFrame.cdp.actor = MyUtil.importImageFromWeb(cp.characterAttr[cp.selectedCharacterID].getChar_va_image_url());
        cp.characterFrame.cdp.repaint();
        return null;
    }
    
}
