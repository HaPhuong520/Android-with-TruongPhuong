package com.example.updateapiusingretrofit;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {

    private String foodName;
    private String imgFood;
    private String material;
    private String recipes;
    private String nutrition;

    public Food(String foodName, String imgFood, String material, String recipes, String nutrition) {
        this.foodName = foodName;
        this.imgFood = imgFood;
        this.material = material;
        this.recipes = recipes;
        this.nutrition = nutrition;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImgFood() {
        return imgFood;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public static Creator<Food> getCREATOR() {
        return CREATOR;
    }

    protected Food(Parcel in) {
        foodName = in.readString();
        imgFood = in.readString();
        material = in.readString();
        recipes = in.readString();
        nutrition = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodName);
        dest.writeString(imgFood);
        dest.writeString(material);
        dest.writeString(recipes);
        dest.writeString(nutrition);
    }
}
