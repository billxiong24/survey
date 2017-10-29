package dumad.surveyproject.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import dumad.surveyproject.Data.Database.ItemDB;
import dumad.surveyproject.Data.Database.SurveyDB;
import dumad.surveyproject.Data.Database.UserDB;
import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.Data.Survey.Survey;
import dumad.surveyproject.Data.User.User;

/**
 * Created by Ritler on 10/28/17.
 * Central controller for data access, use instance of this class to query information
 * regarding users, surveys, items, etc.
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

    /**
     * Create new user with specified username. This will initialize a user with an
     * empty survey list.
     * @param username the username of user to create
     */
    public void createUser(String username){
        this.userDB.createUser(username);
    }

    /**
     * Reads user with specified username from FireBase. The User object passed into the
     * accept method of the Consumer instance is the result, containing username and surveys created.
     * @param username username of the user
     * @param consumer an implementation of the functional interface defining a method to execute when
     *                 user object retrieved from database
     *
     * Example invocation:
     *                 readSurvey(id, new Consumer<Survey>() {
     *                     public void accept(Survey survey) {
     *                      //here, survey is the resultant object that is retrieved from database.
     *                     }
     *                 });
     */
    public void readUser(String username, final Consumer<User> consumer) {
       this.userDB.readUser(username, consumer);
    }


    /**
     * Add a new survey to a specific user, i.e. the user created this survey.
     * @param username the username of user
     * @param location the location of the survey
     * @param survey the name of the survey (must be unique, so far no error handling so be careful)
     * @param questions list of items on survey
     */
    public void addSurvey(String username, String location, String survey, List<Item> questions) {
        this.surveyDB.addSurvey(username, location, survey, questions);
    }

    /**
     * Add item to specific survey.
     * @param survey name of survey (must be unique)
     * @param item the item to insert to specific survey
     */
    public void addMenuItem(String survey, Item item) {
        this.itemDB.addMenuItem(survey, item);
    }

    /**
     * Read a survey with specific name. This will get the survey's name, and the
     * items associated with the survey, and encapsulate it in a Survey object.
     * @param id the survey id to read
     * @param consumer the implementation of the functional interface defining a method to execute
     *                 when survey object retrieved from database.
     *
     * Example invocation:
     *                 readSurvey(id, new Consumer<Survey>() {
     *                     public void accept(Survey survey) {
     *                      //here, survey is the resultant object that is retrieved from database.
     *                     }
     *                 });
     */
    public void readSurvey(String id, final Consumer<Survey> consumer) {
        this.surveyDB.readSurvey(id, consumer);
    }


    public void order(String survey, String username, String item) {
        this.surveyDB.order(survey, username, item);
    }

    public void readOrder(String survey, final Consumer<Map<String, String>> consumer) {
        this.surveyDB.readOrder(survey, consumer);
    }

}
