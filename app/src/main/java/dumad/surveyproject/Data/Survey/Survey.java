package dumad.surveyproject.Data.Survey;

import java.util.Collections;
import java.util.HashMap;
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

    public Survey(String id, Map<String, Item> menu) {
        this.id = id;
        this.menu = menu;
    }

    public Map<String, Item> getMenu() {
        return Collections.unmodifiableMap(menu);
    }
    public void addItem(String name, Item item) {
        menu.put(name, item);
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
