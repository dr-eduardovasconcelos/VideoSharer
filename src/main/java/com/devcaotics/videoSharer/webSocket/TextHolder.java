package com.devcaotics.videoSharer.webSocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TextHolder {

    private static Map<String,Text> texts = new HashMap<>();

    public static void addText(Text text){

        if(texts.size() > 99){

            Iterator<String> t = texts.keySet().iterator();

            String key = t.next();

            t = null;

            texts.remove(key);
        }

        texts.put(text.getId(), text);

    }

    public static String getTextString(String key){

        if(texts.get(key) != null)
            return texts.get(key).getText();
        
        return null;
    }

}
