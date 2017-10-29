package dumad.surveyproject.Data.Survey;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.POJO;

/**
 * Created by Ritler on 10/28/17.
 * Survey model.
 */

public class Survey implements POJO<DatabaseSurvey> {
    private Map<String, String> orders;
    private Map<String, Item> menu;
    private String id, location;

    public Survey(String id, String location) {
        this.id = id;
        this.location = location;
        this.menu = new HashMap<>();
        this.orders = new HashMap<>();
    }

    public Survey(String id, String location, List<Item> menu) {
        this.id = id;
        this.location = location;
        this.menu = new HashMap<>();
        for(Item i : menu) {
            this.menu.put(i.getName(), i);
        }

        this.orders = new HashMap<>();
    }

    public Survey(String id, String location, List<Item> menu, Map<String, String> orders) {
        this.id = id;
        this.location = location;
        this.menu = new HashMap<>();
        for(Item i : menu) {
            this.menu.put(i.getName(), i);
        }

        this.orders = orders;
    }

    public Map<String, String> getOrders() {
        return Collections.unmodifiableMap(this.orders);
    }

    public Map<String, Item> getMenu() {
        return Collections.unmodifiableMap(menu);
    }
    public void addItem(Item item) {
        menu.put(item.getName(), item);
    }

    public void addOrder(String name, String item) {
        this.orders.put(name, item);
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public DatabaseSurvey convert() {
        DatabaseSurvey survey = new DatabaseSurvey();
        survey.id = this.id;
        survey.location = this.location;
        survey.questions = new HashMap<>();
        survey.orders = new HashMap<>();

        for(String s : this.orders.keySet()) {
            survey.orders.put(s, this.orders.get(s));
        }
        for(String s : this.menu.keySet()) {
            survey.questions.put(s, this.menu.get(s));
        }

        return survey;
    }
}
