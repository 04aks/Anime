package aks.Backend;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aks.AppFiles.FrameManager;

public class CharactersDetails {
    FrameManager fm;
    public CharactersDetails(FrameManager fm){
        this.fm = fm;
    }
    


    public String chractersInfo(int id){
        String result = fm.fbp.fetchData("https://api.jikan.moe/v4/anime/"+id+"/characters");
        return result;
    }
    public int chractersNode(String info){
        ObjectMapper om = new ObjectMapper();
        try{
            JsonNode jsonNode= om.readTree(info);
            if(jsonNode.has("data")){
                JsonNode dataNode = jsonNode.get("data");
                if(dataNode.isArray()){
                    int characters = 0;
                    for(JsonNode node : dataNode){
                        if(node.has("character")){
                            characters++;
                        }
                    }
                    return characters;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public void fillCharactersObj(){
        try{
            JsonNode node = fm.fbp.fetchNodes(fm.cp.chracterInfo);
            if(node.has("data")){
                JsonNode dataNode = node.get("data");
                if(dataNode.isArray()){
                    int character = 0;
                    for(JsonNode subNode : dataNode){
                        if(subNode.has("character")){
                            
                            fm.cp.characterAttr[character] = new CharacterAttr
                                (subNode.get("character").get("name").asText(),
                                subNode.get("role").asText());
                            //! PLANNED TO USE LINKED LIST FROM THE BEGINNING
                            //! DIDNT KNOW IT WILL MAKE THIS PART SO MUCH FASTER
                            //! LESS THAN 5 SECS FOR DATA THAT USED TO TAKE 30 SECS MINI
                            //! NO CLUE WHY THIS HAPPENS BUT AINT COMPLAINING
                            //? JUST FIGURED OUT WHY \o/
                            fm.cp.character.addLast(fm.cp.characterAttr[character]);
                            fm.cp.repaint();
                            character++;
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int charactersOtherInfo(int index){
        try{
            JsonNode node = fm.fbp.fetchNodes(fm.cp.chracterInfo);
            if(node.has("data")){
                JsonNode dataNode = node.get("data");
                if(dataNode.isArray()){

                    int charID = dataNode.get(index).get("character").get("mal_id").asInt();
                    fm.cp.characterAttr[index].setImage_url(dataNode.get(index).get("character").get("images").get("jpg").get("image_url").asText()); 
                    fm.cp.characterAttr[index].setFavorites(dataNode.get(index).get("favorites").asInt());
                    fm.cp.characterAttr[index].setChar_id(charID);
                    if(dataNode.get(index).has("voice_actors")){
                        JsonNode voiceActorsNode = dataNode.get(index).get("voice_actors");
                        for(JsonNode subNode : voiceActorsNode){
                            if(subNode.get("language").asText().equalsIgnoreCase("japanese")){
                                
                                fm.cp.characterAttr[index].setChar_va(subNode.get("person").get("name").asText());
                                fm.cp.characterAttr[index].setChar_va_image_url(subNode.get("person").get("images").get("jpg").get("image_url").asText());
                                break;
                                
                            }
                        }
                    }
                    
                    fm.cp.characterFrame.cdp.repaint();
                    return charID;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public void getJapaneseName(int id, int index){
        String info = fm.fbp.fetchData("https://api.jikan.moe/v4/characters/"+id);
        try{
            JsonNode dataNode = fm.fbp.fetchNodes(info);
            if(dataNode.has("data")){
                JsonNode data = dataNode.get("data");
                if(data.has("name_kanji")){ 
                    fm.cp.characterAttr[index].setChar_name_kanji(data.get("name_kanji").asText());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
