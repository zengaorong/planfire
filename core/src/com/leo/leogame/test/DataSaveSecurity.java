package com.leo.leogame.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.ObjectMap;

public class DataSaveSecurity {
    private Save save;
    private FileHandle file = Gdx.files.local("bin/scores.json");

    public DataSaveSecurity() {
        save = getSave();
    }

    private Save getSave() {
        Save save = new Save();

        if (file.exists()) {
            Json json = new Json();
            // 读取文件，并且解密
            //save = json.fromJson(Save.class, Base64Coder.decodeString(file.readString()));
            save = json.fromJson(Save.class, file.readString());
        }
        return save;
    }

    public void saveToJson() {
        Json json = new Json();
        json.setOutputType(OutputType.json);
        //file.writeString(Base64Coder.encodeString(json.prettyPrint(save)), false);
        file.writeString(json.prettyPrint(save), false);
    }

    public void saveDataValue(String key, Object object){
        save.testdata.put(key, object);
        saveToJson(); // 立即保存数据

    }

    public Integer loadDataValue(String key, Class<Integer> type){
        // 如果包含key的数据则返回数据，否则返回null
        if(save.testdata.containsKey(key))
            return (Integer) save.testdata.get(key);
        else
            return null;
    }
    /** 根据需要T替换要读取的类型
     * public <T> T loadDataValue(String key, Class type){
     if(save.data.containsKey(key))return (T) save.data.get(key);
     else return null;   //this if() avoids exception, but check for null on load.

     }
     */
    private static class Save {
        public ObjectMap<String, Object> testdata = new ObjectMap<String, Object>();
    }
}