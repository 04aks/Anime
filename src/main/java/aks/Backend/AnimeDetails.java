package aks.Backend;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import aks.AppFiles.FrameManager;
import aks.Threads.SearchThread;
import aks.other.MyUtil;

public class AnimeDetails {
    int characters;
    FrameManager fm;
    public AnimeDetails(FrameManager fm){
        this.fm = fm;
    }



    public String fetchDataWithName(String name){
        String result = fm.fbp.fetchData("https://api.jikan.moe/v4/anime?q="+name);
        return result;
    }
    public int getTitlesNum(String info){
        try{
            JsonNode mainNode = fm.fbp.fetchNodes(info);
            if (mainNode.has("data")) {
                JsonNode dataNode = mainNode.get("data");
                int index = 0;
                fm.sf.animeAttrs = new AnimeAttr[dataNode.size()];
                fm.sf.titleLabels = new JLabel[dataNode.size()];
                // System.out.println("appro size: "+dataNode.size());
                // System.out.println(fm.sf.animeAttrs.length);
                for(JsonNode subNode : dataNode){
                    int currentIndex = index;
                    fm.sf.animeAttrs[index] = new AnimeAttr();
                    fm.sf.animeAttrs[index].setId(subNode.get("mal_id").asInt());
                    fm.sf.animeAttrs[index].setCover_image(MyUtil.importIconFromWeb(subNode.get("images").get("jpg").get("image_url").asText()));
                    fm.sf.animeAttrs[index].setLabel(fm.sf.titleLabels[index] = new JLabel(fm.sf.animeAttrs[index].getCover_image()));
                    fm.sf.animeAttrs[index].getLabel().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent arg0) {
                            SearchThread sThread = new SearchThread(fm, fm.cp, fm.sf.titles.get(currentIndex).getId());
                            sThread.execute();
                            System.out.println(fm.sf.titles.get(currentIndex).getId());
                            
                        }
                    });
                    fm.sf.titles.offerLast(fm.sf.animeAttrs[index]);
                    // System.out.print(fm.sf.animeAttrs[index].getId()+"//");
                    fm.ui.addTitlesToPane();// /SHOULDNT EVEN BE CALLED FROM HERE
                    index++;
                }
                return dataNode.size();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }




    public String info(int id){
        String result = fm.fbp.fetchData("https://api.jikan.moe/v4/anime/"+id+"/full");
        return result;
    }

    public void animeNode(String info){
        ObjectMapper om = new ObjectMapper();
        try{
            JsonNode jsonNode = om.readTree(info);
            if(jsonNode.has("data")){
                JsonNode dataNode = jsonNode.get("data");
                if(dataNode.has("trailer")){    
                    JsonNode trialerNode = dataNode.get("trailer").get("url");
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
