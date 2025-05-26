package com.example.randomword.fairies;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DataStorage {

    private Random random;
    private static final String Eternal = "AppStorage";
    private static final String EterItems = "stored_items";
    private SharedPreferences sharPrefer;

    public DataStorage(Context context){
        this.sharPrefer = context.getSharedPreferences(Eternal, Context.MODE_PRIVATE);
        random = new Random();
    }

    public void setStoredItems(String items) {
        if (items != null && !items.trim().isEmpty()){
            Set<String> currentitem =  new HashSet<>(getStoreditemsSet());
            currentitem.add(items);
            sharPrefer.edit().putStringSet(EterItems,new HashSet<>(currentitem) ).apply();
        }
    }

    public String getRandomItem(){
        Set<String> item = getStoreditemsSet();
        if(item.isEmpty()){
            return "No Items stored yet";
        }
        int index = random.nextInt(item.size());
        return new ArrayList<>(item).get(index);
    }

    public String searchItem(String search){
        Set<String> item = getStoreditemsSet();
        for (String items: item){
            if (items.equalsIgnoreCase(search)){
                return items;
            }
        }
        return "Item not Found";
    }

    public String getallItems(){
        Set<String> item = getStoreditemsSet();
        if (item.isEmpty()){
            return "No Items yet added";
        }
        StringBuilder strBuilder = new StringBuilder();
        for(String items : item){
            strBuilder.append(items).append("\n");
        }
        return String.join("\t" + "," , item);
    }

    public void ClearStorage(){
        sharPrefer.edit().remove(EterItems).apply();
    }

    private Set<String> getStoreditemsSet(){
        return new HashSet<>(sharPrefer.getStringSet(EterItems, new HashSet<>()));
    }
}
