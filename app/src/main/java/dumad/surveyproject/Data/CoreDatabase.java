package dumad.surveyproject.Data;

import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.Data.Survey.DatabaseSurvey;
import dumad.surveyproject.Data.Survey.Survey;
import dumad.surveyproject.Data.User.DatabaseUser;
import dumad.surveyproject.Data.User.User;
import dumad.surveyproject.POJO;

/**
 * Created by Ritler on 10/28/17.
 */

public class CoreDatabase {
    private DatabaseReference Database = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference Users = Database.child("Users");
    private DatabaseReference Surveys = Database.child("Surveys");

    public void createUser(String username){
        User user = new User(username, new HashMap<String, String>());
        Users.child(username).setValue(user.convert());
    }



    public void readUser(String username, final Consumer<User> consumer) {
        ValueEventListener postListener = new ValueEventListener() {
            @RequiresApi(api = VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseUser d = dataSnapshot.getValue(DatabaseUser.class);
                User user = d.deserialize();

                consumer.accept(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        Users.child(username).addListenerForSingleValueEvent(postListener);
    }

    public void addSurvey(String username, String survey, Map<String, Item> questions) {
        POJO s = new Survey(survey, questions);
        Surveys.child(survey).setValue(s.convert());
        Users.child(username).child("surveys").child(survey).setValue(survey);
    }

    public void addMenuItem(String survey, String itemName,  Item item) {
        Surveys.child(survey).child("questions").child(itemName).setValue(item);
    }

    public void readSurvey(String id, final Consumer<Survey> consumer) {
        ValueEventListener postListener = new ValueEventListener() {

            @RequiresApi(api = 24)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseSurvey survey = dataSnapshot.getValue(DatabaseSurvey.class);
                Survey rev = survey.deserialize();
                consumer.accept(rev);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };

        Surveys.child(id).addListenerForSingleValueEvent(postListener);
    }

}
