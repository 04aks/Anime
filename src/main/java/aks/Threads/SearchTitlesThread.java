package aks.Threads;

import javax.swing.SwingWorker;
import aks.AppFiles.FrameManager;

public class SearchTitlesThread extends SwingWorker<Void,Void>{
    FrameManager fm;
    String animeName;
    public SearchTitlesThread(FrameManager fm, String animeName){
        this.fm = fm;
        this.animeName = animeName;
    }
    @Override
    protected Void doInBackground() throws Exception {
        String name = animeName.replaceAll(" ", "%20");//"Moriarty%20The";
        fm.sf.label.setText("Searching ...");
        fm.sf.button.setEnabled(false);
        String info = fm.animeD.fetchDataWithName(name);
        if(fm.sf.pane != null){
            fm.sf.titles.clear();
            // clearObjects();
        }
        fm.sf.titlesNum = fm.animeD.getTitlesNum(info);
        
        return null;
    }
    @Override
    protected void done() {
        fm.sf.label.setText("");
        fm.sf.button.setEnabled(true);
    }
    





    public void clearObjects(){
        for(int i = 0; i<fm.sf.titles.size(); i++){
            fm.sf.titlesPanel.remove(fm.sf.titleLabels[i]);
            // fm.sf.animeAttrs[i] = null;
            
        }
        fm.sf.titlesPanel.revalidate();
        fm.sf.titlesPanel.repaint();
    }
    
}
