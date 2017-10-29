package dumad.surveyproject.Data;

import java.util.List;
import java.util.function.Consumer;

import dumad.surveyproject.Data.Database.ItemDB;
import dumad.surveyproject.Data.Database.SurveyDB;
import dumad.surveyproject.Data.Database.UserDB;
import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.Data.Survey.Survey;
import dumad.surveyproject.Data.User.User;

/**
 * Created by Ritler on 10/28/17.
 */

public class DBController {
    private ItemDB itemDB;
    private SurveyDB surveyDB;
    private UserDB userDB;
    
    public DBController() {
        this.itemDB = new ItemDB();
        this.surveyDB = new SurveyDB();
        this.userDB = new UserDB();
    }

    public void createUser(String username){
        this.userDB.createUser(username);
    }
    
    public void readUser(String username, final Consumer<User> consumer) {
       this.userDB.readUser(username, consumer);
    }

    public void addSurvey(String username, String survey, List<Item> questions) {
        this.surveyDB.addSurvey(username, survey, questions);
    }

    public void addMenuItem(String survey, Item item) {
        this.itemDB.addMenuItem(survey, item);
    }

    public void readSurvey(String id, final Consumer<Survey> consumer) {
        this.surveyDB.readSurvey(id, consumer);
    }

}
