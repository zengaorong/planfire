package com.leo.leogame.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class JsonTest extends ApplicationAdapter {

    @Override
    public void create() {

        Json json = new Json();
        json.setElementType(Character.class, "items", Item.class);  // 指定Character中的item数据类型
        Character character = json.fromJson(Character.class, Gdx.files.internal("data.json"));    // 从Json文件中创建一个Charactor对象
        System.out.println(character);
//        System.out.println("dexterity: " + character.dexterity);  // 访问单个属性,需要建立对应的class

        System.out.println();

        System.out.println("=====================");
        System.out.println("Serializing character");
        System.out.println("=====================");

        System.out.println(json.prettyPrint(json.toJson(character)));

    }

    public static class Item {
        private String name;
        private int number;

        @Override
        public String toString() {
            return name + "(" + number + ")";
        }
    }

    public static class Character {
        private String name = "";
        private int experience = 0;
        private  int strength = 1;
        private  int dexterity = 1;
        private  int intelligence = 1;

        public Array<Item> items = new Array<Item>();

        @Override
        public String toString() {
            String string = new String();
            string += "Name: " + name + "\n";
            string += "Experience: " + experience + "\n";
            string += "Strength: " + strength + "\n";
            string += "Dexterity: " + dexterity + "\n";
            string += "Intelligence: " + intelligence + "\n";
            string += "Items: ";

            for (Item item : items) {
                string += item.toString() + " ";
            }

            return string;
        }
    }
}