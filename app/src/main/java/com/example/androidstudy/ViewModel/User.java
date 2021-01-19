package com.example.androidstudy.ViewModel;

public class User {
    private String name;
    private int height;
    private int weight;
    private boolean woman;

    public User(String name, int height, int weight, boolean woman) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.woman = woman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isWoman() {
        return woman;
    }

    public void setWoman(boolean woman) {
        this.woman = woman;
    }
}
