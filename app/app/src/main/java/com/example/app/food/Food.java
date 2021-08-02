package com.example.app.food;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String image;
    protected String FoodName;
    private String Material;
    private String Recipes;
    private String Nutrition;

    public Food(String image, String foodName, String material, String recipes, String nutrition) {
        this.image = image;
        FoodName = foodName;
        Material = material;
        Recipes = recipes;
        Nutrition = nutrition;
    }

    protected Food(Parcel in) {
        image = in.readString();
        FoodName = in.readString();
        Material = in.readString();
        Recipes = in.readString();
        Nutrition = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getRecipes() {
        return Recipes;
    }

    public void setRecipes(String recipes) {
        Recipes = recipes;
    }

    public String getNutrition() {
        return Nutrition;
    }

    public void setNutrition(String nutrition) {
        Nutrition = nutrition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(FoodName);
        dest.writeString(Material);
        dest.writeString(Recipes);
        dest.writeString(Nutrition);
    }
}