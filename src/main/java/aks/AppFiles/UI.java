package aks.AppFiles;

import java.awt.Font;
import java.io.InputStream;
import javax.swing.ImageIcon;

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
    
}
