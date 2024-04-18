package aks.Threads;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import aks.AppFiles.FrameManager;
import aks.AppFiles.frame1.StartFrame;

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
            fm.sf.titlesNum = fm.animeD.getTitlesNum(info);
        }else{
            fm.sf.titlesNum = fm.animeD.getTitlesNum(info);
        }
        return null;
    }
    @Override
    protected void done() {




        if(fm.sf.pane == null){

            fm.sf.pane = new JScrollPane(
                fm.sf.titlesPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            );
            fm.sf.pane.setPreferredSize(new Dimension(StartFrame.WIDTH-20, 400));
            fm.sf.pane.getHorizontalScrollBar().setUnitIncrement(15);
        }else{

            System.out.println("idk to be honest");

        }





        for(int i = 0; i<fm.sf.titles.size(); i++){
            int currentIndex = i;
            // JLabel titleButton = ;
            fm.sf.titles.get(i).getLabel().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent arg0) {
                    SearchThread sThread = new SearchThread(fm, fm.cp, fm.sf.titles.get(currentIndex).getId());
                    sThread.execute();
                }
            });
            fm.sf.titlesPanel.add(fm.sf.titles.get(i).getLabel());
        }

        fm.sf.mainPanel.add(fm.sf.pane);


        fm.sf.titlesPanel.revalidate();
        fm.sf.titlesPanel.repaint();

        
        fm.sf.mainPanel.revalidate();
        fm.sf.mainPanel.repaint();


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
