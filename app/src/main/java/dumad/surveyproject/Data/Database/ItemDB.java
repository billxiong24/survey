package dumad.surveyproject.Data.Database;

import dumad.surveyproject.Data.Item.Item;

/**
 * Created by Ritler on 10/29/17.
 */

public class ItemDB extends DB {

    public ItemDB() {
        super();
    }
    public void addMenuItem(String survey, Item item) {
        super.getSurveys().child(survey).child("questions").child(item.getName()).setValue(item);
    }
}
