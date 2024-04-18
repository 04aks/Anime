package aks.Threads;

import javax.swing.SwingWorker;

import aks.AppFiles.FrameManager;
import aks.AppFiles.frame2.CharactersPanel;
import aks.AppFiles.frame2.DetailsFrame;

public class SearchThread extends SwingWorker<Void, Void>{
    FrameManager fm;
    CharactersPanel cp;
    int animeID;
    public SearchThread(FrameManager fm, CharactersPanel cp, int animeID){
        this.fm = fm;
        this.cp = cp;
        this.animeID = animeID;
    }
    // fm.cp.animeInfo = fm.animeD.info(Integer.parseInt(fm.sf.id.getText()));
    // fm.animeD.animeNode(fm.cp.animeInfo);
    @Override
    protected Void doInBackground() throws Exception {
        try{
            fm.sf.label.setText("");
            if(fm.df == null){
                fm.df = new DetailsFrame(fm);
            }else{
                fm.df.setVisible(true);
            }

            fm.cp.chracterInfo = cp.cd.chractersInfo(animeID);
            if(fm.cp.chracterInfo != null){
                cp.character.clear();
                fm.cp.setNum(cp.cd.chractersNode(fm.cp.chracterInfo) + 1);
                cp.cd.fillCharactersObj();
            }else{
                fm.sf.label.setText("No Anime With This ID");
                fm.df.dispose();
            }

        }catch(Exception e){
            fm.sf.label.setText("Choose another ID");
        }
        return null;
    }
    @Override
    protected void done() {
        cp.scroll = 0;
        cp.repaint();
    }
    
}
