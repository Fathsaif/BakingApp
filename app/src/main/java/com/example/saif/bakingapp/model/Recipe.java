
package com.example.saif.bakingapp.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Step;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Table(name = "Recipes")
public class Recipe extends Model implements Parcelable
{

    @Column(name = "Recipe_id")
    @SerializedName("id")
    @Expose
    private Long id;

    @Column(name = "Name")
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("ingredients")
    @Expose
    public List<Ingredient> ingredientList = new ArrayList<>();

    public List<Ingredient> ingredients (){
        return getMany(Ingredient.class,"Recipe");
    }

    @SerializedName("steps")
    @Expose
    public List<Step> steps = new ArrayList<>();

    public List<Step> steps1(){
        return getMany(Step.class,"Recipe");
    }
    @Column(name = "Servings")
    @SerializedName("servings")
    @Expose
    private Integer servings;

    @Column(name = "Image")
    @SerializedName("image")
    @Expose
    private String image;

    public Recipe(){
        super();
    }
    public final static Parcelable.Creator<Recipe> CREATOR = new Creator<Recipe>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Recipe createFromParcel(Parcel in) {
            Recipe instance = new Recipe();
            instance.id = ((Long) in.readValue((Integer.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.ingredientList, (Ingredient.class.getClassLoader()));
            in.readList(instance.steps, (Step.class.getClassLoader()));
            instance.servings = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.image = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Recipe[] newArray(int size) {
            return (new Recipe[size]);
        }

    }
    ;



    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredientList;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredientList);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return  0;
    }

}
