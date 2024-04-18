package aks.AppFiles.frame3;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;

import javax.swing.JPanel;

import aks.AppFiles.frame2.CharactersPanel;
import aks.Handlers.BrowseMouseHandler;
import aks.other.MyUtil;

public class CharacterDetailsPanel extends JPanel{
    BufferedImage frame, frame2, art;
    Font jpFont;
    public Rectangle rect = new Rectangle(0,0,0,0);
    public Rectangle rectC = new Rectangle(0,0,0,0);
    public BufferedImage image = null;
    public BufferedImage actor = null;
    CharactersPanel cp;
    BrowseMouseHandler bmh;
    public CharacterDetailsPanel(CharactersPanel cp){
        this.cp = cp;
        bmh = new BrowseMouseHandler(this,cp);
        setPreferredSize(new Dimension(CharacterFrame.WIDTH, CharacterFrame.HEIGHT));
        setVisible(true);
        addMouseListener(bmh);
        importImages();
        createFont("/aks/res/Japanese.ttf");
    }
    public void importImages(){
        frame = MyUtil.importImage("/aks/res/frame1.png");
        frame2 = MyUtil.importImage("/aks/res/frame2.png");
        art = MyUtil.importImage("/aks/res/art.png");
    }
    public void createFont(String path){
        InputStream is = getClass().getResourceAsStream(path);
        try{
            jpFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(art, 0, CharacterFrame.HEIGHT - art.getHeight(), null);

        
        int charImageWidth = 225, charImageHeight = 350, actorWidth = 90, actorHeight = 140;
        // image = MyUtil.importImageFromWeb(cp.characterAttr[cp.selectedCharacterID].getImage_url());
        // actor = MyUtil.importImageFromWeb(cp.characterAttr[cp.selectedCharacterID].getChar_va_image_url());

        g2.setFont(jpFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
        String text = cp.characterAttr[cp.selectedCharacterID].getChar_name_kanji();
        if(text != null){
            int kanjiX = getXforCenteredText(g2, text, CharacterFrame.WIDTH);
            g2.drawString(text, kanjiX, 55);
        }

        int imageY = 80;
        
        int topBarWidth = charImageWidth + actorWidth + 10;
        int fX = (CharacterFrame.WIDTH - topBarWidth)/2;

        int y = 50 + charImageHeight + 40 + imageY;
        int x = fX + charImageWidth + 20;

        g2.setFont(getFont().deriveFont(15f));
        String id = Integer.toString(cp.characterAttr[cp.selectedCharacterID].getChar_id());
        int idX = getXforCenteredText(g2, id, CharacterFrame.WIDTH);
        g2.drawString(id, idX, 20);
        g2.drawImage(image, fX, imageY, charImageWidth, charImageHeight, null);
        rectC.setBounds(fX,imageY,charImageWidth,charImageHeight);
        g2.drawImage(frame, fX, imageY, null);



        int textX = fX;
        int textY = y;
        int lineHeight = 25;
        g2.setFont(getFont().deriveFont(Font.BOLD, 15));

        g2.drawString("Name: ", textX, textY);
        textY+=lineHeight;
        g2.drawString("Role: ", textX, textY);
        textY+=lineHeight;
        g2.drawString("Favorites: ", textX, textY);
        

        String charName = cp.characterAttr[cp.selectedCharacterID].getChar_name();
        String role = cp.characterAttr[cp.selectedCharacterID].getRole();
        String favorites = Integer.toString(cp.characterAttr[cp.selectedCharacterID].getFavorites()) + " nerd(s)";
        int tailX = fX + charImageWidth;
        //RESET Y VALUE
        textY = y;
        textX = alignTextToRight(g2, charName, tailX);
        g2.drawString(charName, textX, textY);
        textY+=lineHeight;

        textX = alignTextToRight(g2, role, tailX);
        g2.drawString(role, textX, textY);
        textY+=lineHeight;

        textX = alignTextToRight(g2, favorites, tailX);
        g2.drawString(favorites, textX, textY);
        textY+=lineHeight;


        
        // float scale = 0.4f;
        // int imageWidth = (int)(actor.getWidth()*scale);
        // int imageHeight = (int)(actor.getHeight()*scale);
        g2.drawImage(actor, x, imageY, actorWidth, actorHeight, null);
        g2.drawImage(frame2, x, imageY, null);
        rect.setBounds(x,imageY,actorWidth,actorHeight);

        g2.setColor(Color.red);

    }

        
        
        
    
    public int alignTextToRight(Graphics2D g2, String text, int tailX){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public int getXforCenteredText(Graphics2D g2, String text, int panel){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (panel - length)/2;
        return x;
    }
    public void browseChika(String firstname, String lastname){
        String link = "https://www.google.com/search?q="+firstname+"+"+lastname;
        try{
            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                if(desktop.isSupported(Desktop.Action.BROWSE)){
                    desktop.browse(new URI(link));
                }
                else{
                    System.out.println("Browsing is not supported");
                }
            }
            else{
                System.out.println("desktop is not supported");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
