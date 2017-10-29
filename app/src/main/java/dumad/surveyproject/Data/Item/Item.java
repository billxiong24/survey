package dumad.surveyproject.Data.Item;

/**
 * Created by Ritler on 10/29/17.
 * This item itself is pOJO, doesnt need to implement interface
 */

public class Item {
    double fats, carbs, proteins, calories, price;

    public Item() {

    }

    public Item(double f, double carbs, double prot, double cals, double price) {
        this.fats = f;
        this.carbs = carbs;
        this.proteins = prot;
        this.calories = cals;
        this.price = price;
    }

    public double getCalories() {
        return calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    public double getProteins() {
        return proteins;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return calories + " " + fats + " " + carbs + " " + proteins;
    }

}
