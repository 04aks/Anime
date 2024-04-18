package aks.AppFiles;

import aks.AppFiles.frame1.StartFrame;
import aks.AppFiles.frame2.CharactersPanel;
import aks.AppFiles.frame2.DetailsFrame;
import aks.Backend.AnimeDetails;
import aks.Backend.FetchingBluePrint;
import aks.other.MyUtil;

public class FrameManager {
    public UI ui = new UI(this);
    public AnimeDetails animeD = new AnimeDetails(this);
    public FetchingBluePrint fbp = new FetchingBluePrint();
    public CharactersPanel cp = new CharactersPanel(this);
    public MyUtil util = new MyUtil();
    public DetailsFrame df;
    public StartFrame sf;
    public FrameManager(){
        sf = new StartFrame(this);
    }
}
