package com.leo.leogame.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class ReadJsonData extends ApplicationAdapter {


    public static class ArrayData{
        public String name;
        public int size;

        public Array<RectangleData> rectangleDataArray = new Array<RectangleData>();

        @Override
        public String toString() {
            String string = new String();
            string += "Name: " + name + "\n";
            string += "size: " + size + "\n";
            string += "RectangleData: ";

            for (RectangleData rectangleData : rectangleDataArray) {
                string += rectangleData.toString() + " ";
            }

            return string;
        }
    }

    public static class RectangleData{
        float position_x;
        float position_y;
        float width;
        float height;
    }

    @Override
    public void create() {
        Json json = new Json();
        json.setElementType(ArrayData.class, "rectangleDataArray", RectangleData.class);  // 指定Character中的item数据类型
        ArrayData out = json.fromJson(ArrayData.class, Gdx.files.internal("position.json"));
        //System.out.println(json.prettyPrint(json.toJson(out)));
        Array<RectangleData> rectangleDataArray = out.rectangleDataArray;
        for(RectangleData data:rectangleDataArray){
            System.out.println(data.height);
        }

        //Json json = new Json();
        //json.setElementType(JsonTest.Character.class, "items", JsonTest.Item.class);  // 指定Character中的item数据类型
        //JsonTest.Character character = json.fromJson(JsonTest.Character.class, Gdx.files.internal("data.json"));    // 从Json文件中创建一个Charactor对象
        //System.out.println(character);
    }
}
