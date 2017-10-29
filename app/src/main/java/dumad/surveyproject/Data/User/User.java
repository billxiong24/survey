package dumad.surveyproject.Data.User;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import dumad.surveyproject.POJO;

/**
 * Created by Ritler on 10/28/17.
 */

public class User implements POJO<DatabaseUser> {
    private String username;
    private Map<String, String> surveys;

    public User(String username, Map<String, String> list){
        this.username = username;
        this.surveys = list;
    }
    public User(String username) {
        this.username = username;
        this.surveys = new HashMap<>();
    }

    public void addSurvey(String name) {
        this.surveys.put(name, name);
    }
    public Map<String, String> getSurveys() {
        return Collections.unmodifiableMap(this.surveys);
    }

    public String getUsername() {
        return this.username;
    }

    public DatabaseUser convert() {
        DatabaseUser d = new DatabaseUser();
        d.username = this.username;
        d.surveys = new HashMap<>();
        for(String s : this.surveys.keySet()) {
            d.surveys.put(s, this.surveys.get(s));
        }
        return d;
    }
}
