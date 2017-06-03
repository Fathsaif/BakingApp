
package com.example.saif.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Table(name = "Ingredients")
public class Ingredient extends Model implements Parcelable
{

    @Column(name = "Quantity")
    @SerializedName("quantity")
    @Expose
    private double quantity;

    @Column(name = "Measure")
    @SerializedName("measure")
    @Expose
    private String measure;

    @Column(name = "Ingredient")
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    @Column(name = "Recipe")
    @Expose
    public Recipe recipe;

    public final static Parcelable.Creator<Ingredient> CREATOR = new Creator<Ingredient>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Ingredient createFromParcel(Parcel in) {
            Ingredient instance = new Ingredient();
            instance.quantity = ((double) in.readValue((double.class.getClassLoader())));
            instance.measure = ((String) in.readValue((String.class.getClassLoader())));
            instance.ingredient = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Ingredient[] newArray(int size) {
            return (new Ingredient[size]);
        }

    }
    ;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(quantity);
        dest.writeValue(measure);
        dest.writeValue(ingredient);
    }

    public int describeContents() {
        return  0;
    }

}
