package dumad.surveyproject.Data.User;

import java.util.Map;

import dumad.surveyproject.Deserializable;

/**
 * Created by Ritler on 10/28/17.
 */

public class DatabaseUser implements Deserializable<User>{
    String username;
    Map<String, Object> surveys;

    public DatabaseUser(){

    }

    public String getUsername() {
        return username;
    }

    public Map<String, Object> getSurveys() {
        return surveys;
    }

    @Override
    public User deserialize() {
        User u = new User(this.username);
        if(surveys == null)
            return u;
        for(String s : surveys.keySet()) {
            u.addSurvey(s);
        }
        //System.out.println("hi");
        //System.out.println(u.getSurveys());
        return u;
    }

}
