package dumad.surveyproject.Data.Survey;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.POJO;

/**
 * Created by Ritler on 10/28/17.
 */

public class Survey implements POJO<DatabaseSurvey> {
    private Map<String, Item> menu;
    private String id;

    public Survey(String id) {
        this.id = id;
        this.menu = new HashMap<>();
    }

    public Survey(String id, List<Item> menu) {
        this.id = id;
        this.menu = new HashMap<>();
        for(Item i : menu) {
            this.menu.put(i.getName(), i);
        }
    }

    public Map<String, Item> getMenu() {
        return Collections.unmodifiableMap(menu);
    }
    public void addItem(Item item) {
        menu.put(item.getName(), item);
    }

    public String getId() {
        return id;
    }

    @Override
    public DatabaseSurvey convert() {
        DatabaseSurvey survey = new DatabaseSurvey();
        survey.id = this.id;
        survey.questions = new HashMap<>();
        for(String s : this.menu.keySet()) {
            survey.questions.put(s, this.menu.get(s));
        }

        return survey;
    }
}
