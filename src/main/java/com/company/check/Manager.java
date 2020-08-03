package com.company.check;

import java.io.IOException;

/**
 * Управление записями
 */
public class Manager {
    private Storage storage;

    public Manager() {
        storage = new Storage();
        storage.readList();
    }

    public int add(String name, String url){
        try {
            if(storage.getUrlByName(name)!=null){
                return 1;
            }
            Storage.writeFile(name, Site.get(url));
            storage.addRecord(name, url);
            storage.writeList();
        } catch (IOException e) {
            return 2;
        }
        return 0;
    }

    public int check(String name){
        try {
            String url = storage.getUrlByName(name);
            if (storage==null){
                return 3;
            }
            String oldText = Storage.readFile(name);
            String newText = Site.get(url);
            newText = Storage.distortText(newText);
            if(oldText.equals(newText)){
                return 0;
            }else {
                Storage.writeFile(name, newText);
                storage.updateTime(name);
                storage.writeList();
                return 1;
            }
        } catch (IOException e) {
            return 2;
        }
    }

    public int delete(String name){
        storage.dropRecord(name);
        storage.writeList();
        return 0;
    }

    public int check(int number){
        String name = storage.getNameByNumber(number);
        if(name==null){
            return 2;
        }
        return check(name);
    }

    public String getListText(){
        return storage.getListText();
    }
}
