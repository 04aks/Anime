package aks.AppFiles.frame1;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import aks.AppFiles.FrameManager;
import aks.Backend.AnimeAttr;
import aks.Threads.SearchTitlesThread;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;


public class StartFrame extends JFrame{
    FrameManager fm;
    public JButton button;
    public JLabel label = new JLabel();
    public static int WIDTH = 450, HEIGHT = 720;
    public JTextField id;
    public JPanel mainPanel;
    
    public JPanel titlesPanel;
    public int titlesNum;
    public AnimeAttr[] animeAttrs;
    public LinkedList<AnimeAttr> titles = new LinkedList<AnimeAttr>();
    public JScrollPane pane;
    public JLabel[] titleLabels;

    public StartFrame(FrameManager fm){
        this.fm = fm;
        prepareTitlesPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MiBombaclat");
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/aks/res/icon.ico"));
        System.out.println(imageIcon);
        setIconImage(imageIcon.getImage());
        setSize(WIDTH,HEIGHT);
        setLocation(5,10);
        setLayout(new BorderLayout());
        setResizable(false);
        
        add(titlePanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(startPanel(), BorderLayout.CENTER);
        
        setVisible(true);
    }
    public JPanel startPanel(){
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(100,100));
        id = new JTextField();
        id.setPreferredSize(new Dimension(300,30));
        id.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        

        button = new JButton();
        button.setPreferredSize(new Dimension(25,25));
        button.setIcon(new ImageIcon(getClass().getResource("/aks/res/search.png")));
        button.setFocusable(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if(!id.getText().isEmpty()){
                    // boolean isNum = true;
                    // for(char c : id.getText().toCharArray()){
                    //     if(!Character.isDigit(c)){
                    //         isNum = false;
                    //     }
                    // }
                    if(titleLabels != null){
                        if(titleLabels.length > 0){
                            for(int i = 0; i<titleLabels.length; i++){
                                titlesPanel.remove(titleLabels[i]);
                            }
                            titlesPanel.revalidate();
                            titlesPanel.repaint();
                        }
                    }
                    // if(isNum){
                    SearchTitlesThread sTitlesThread = new SearchTitlesThread(fm, id.getText());
                    sTitlesThread.execute();
                        

                        // ImageIcon image = MyUtil.importIconFromWeb("https://cdn.myanimelist.net/images/anime/13/50521.jpg");
                        // JLabel anime = new JLabel();
                        // anime.setIcon(image);
                        // titlesPanel.add(anime);

                        
                    // }

                    
                }
            }
        });
        

        

        mainPanel.add(id);
        mainPanel.add(button);
        
        return mainPanel;
    }
    public JPanel titlePanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100,100));
        panel.setLayout(new BorderLayout());
        panel.setFocusable(true);

        JLabel label = new JLabel();
        label.setIcon(fm.ui.title);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                // System.out.println(titleLabels.length);
                // if(titleLabels.length > 0){
                //     for(int i = 0; i<titleLabels.length; i++){
                //         titlesPanel.remove(titleLabels[i]);
                //         System.out.println("removed");
                //     }
                //     titlesPanel.revalidate();
                //     titlesPanel.repaint();
                // }
                
            }
        });
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    public JPanel footerPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100,100));
        panel.setLayout(null);
        label.setBounds((WIDTH-100)/2,(100 - 20)/2,100,20);
        JLabel foreground = new JLabel();
        JLabel foreground2 = new JLabel();
        foreground.setIcon(new ImageIcon(getClass().getResource("/aks/res/gal.png")));
        foreground.setBounds(0,10,100,100);

        foreground2.setIcon(new ImageIcon(getClass().getResource("/aks/res/gal2.png")));
        foreground2.setBounds(WIDTH - 100,10,100,100);
        
        panel.add(label);
        panel.add(foreground);
        panel.add(foreground2);
        return panel;
    }
    public void prepareTitlesPanel(){
        titlesPanel = new JPanel();
        titlesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    public void prepareScrollPane(){
        fm.sf.pane = new JScrollPane(fm.sf.titlesPanel,
            JScrollPane.VERTICAL_SCROLLBAR_NEVER,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        fm.sf.pane.setPreferredSize(new Dimension(StartFrame.WIDTH-20, 400));
        fm.sf.pane.getHorizontalScrollBar().setUnitIncrement(15);
    }

}
