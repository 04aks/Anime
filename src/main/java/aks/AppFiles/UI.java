package aks.AppFiles;

import java.awt.Dimension;
import java.awt.Font;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import aks.AppFiles.frame1.StartFrame;

public class UI {
    public Font titleFont;
    
    public ImageIcon title;
    FrameManager fm;
    public UI(FrameManager fm){
        this.fm = fm;
        importImages();
        createFont("/aks/res/OldLondon.ttf", titleFont);
        
    }
    public void importImages(){
        title = new ImageIcon(getClass().getResource("/aks/res/title.png"));        
    }
    public void createFont(String path, Font font){
        InputStream is = getClass().getResourceAsStream(path);
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addTitlesToPane(){
        // CREATE THE SCROLL PANE IF NOT ALREADY CREATED
        if(fm.sf.pane == null){

            fm.sf.pane = new JScrollPane(
                fm.sf.titlesPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            );
            fm.sf.pane.setPreferredSize(new Dimension(StartFrame.WIDTH-20, 400));
            fm.sf.pane.getHorizontalScrollBar().setUnitIncrement(15);
            fm.sf.mainPanel.add(fm.sf.pane);
        }

        //ADD TITELS' LABELS
        for(int i = 0; i<fm.sf.titles.size(); i++){            
            
            fm.sf.titlesPanel.add(fm.sf.titles.get(i).getLabel());
            fm.sf.titlesPanel.revalidate();
            fm.sf.titlesPanel.repaint();

            
            fm.sf.mainPanel.revalidate();
            fm.sf.mainPanel.repaint();
            // System.out.println("bloodclat");
            
        }


    }
    
}
