package aks.other;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyUtil {
    
    public MyUtil(){

    }
    public static BufferedImage importImageFromWeb(String link){
        BufferedImage image = null;
        if(link != null){
            URI uri = URI.create(link);
            try{
                URL url = uri.toURL();
                image = ImageIO.read(url);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return image;
    }
    public static BufferedImage importImage(String path){
        BufferedImage image = null;
        InputStream is = MyUtil.class.getResourceAsStream(path);
        try{
            image = ImageIO.read(is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public static ImageIcon importIconFromWeb(String link){

        URI uri = URI.create(link);
        try{

            URL url = uri.toURL();
            ImageIcon ii = new ImageIcon(url);
            return ii;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
