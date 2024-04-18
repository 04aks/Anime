package aks.Backend;

import java.awt.Rectangle;

public class CharacterAttr {
    private String image_url;
    private int char_id;
    private String char_name;
    private String role;
    private int favorites;
    private String char_va;
    private String char_va_image_url;
    private String char_name_kanji;
    public Rectangle butto = new Rectangle(0,0,0,0);

    public CharacterAttr(String char_name, String role){
        this.char_name = char_name;
        this.role = role;
    }
    public CharacterAttr(String image_url, String char_name, String role, int favorites, String char_va, String char_va_image_url){
        this.image_url = image_url;
        this.char_name = char_name;
        this.role = role;
        this.favorites = favorites;
        this.char_va = char_va;
        this.char_va_image_url = char_va_image_url;
    }


    public String getImage_url() {
        return image_url;
    }
    public int getChar_id() {
        return char_id;
    }
    public String getChar_name() {
        return char_name;
    }
    public String getRole() {
        return role;
    }
    public int getFavorites() {
        return favorites;
    }
    public String getChar_va() {
        return char_va;
    }
    public String getChar_va_image_url() {
        return char_va_image_url;
    }
    public String getChar_name_kanji() {
        return char_name_kanji;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }
    public void setChar_name(String char_name) {
        this.char_name = char_name;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }
    public void setChar_va(String char_va) {
        this.char_va = char_va;
    }
    public void setChar_va_image_url(String char_va_image_url) {
        this.char_va_image_url = char_va_image_url;
    }
    public void setChar_name_kanji(String char_name_kanji) {
        this.char_name_kanji = char_name_kanji;
    }
}
