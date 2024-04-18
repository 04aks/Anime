package aks.Backend;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AnimeAttr {
    private int id;
    private ImageIcon cover_image;
    private JLabel button;
    public Rectangle rectangle = new Rectangle(0,0,0,0);

    public AnimeAttr(){
        
    }

    public void setId(int id) {
        this.id = id;
    }
    public ImageIcon getCover_image() {
        return cover_image;
    }
    public JLabel getLabel() {
        return button;
    }
    


    public int getId() {
        return id;
    }
    public void setCover_image(ImageIcon cover_image) {
        this.cover_image = cover_image;
    }
    public void setLabel(JLabel button) {
        this.button = button;
    }
    
}
