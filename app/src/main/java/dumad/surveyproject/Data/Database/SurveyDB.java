package dumad.surveyproject.Data.Database;

import android.support.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.Data.Survey.DatabaseSurvey;
import dumad.surveyproject.Data.Survey.Survey;
import dumad.surveyproject.POJO;

/**
 * Created by Ritler on 10/29/17.
 */

public class SurveyDB extends DB {

    public SurveyDB() {
        super();
    }

    public void addSurvey(String username, String location, String survey, List<Item> questions) {
        POJO s = new Survey(survey, location, questions);
        super.getSurveys().child(survey).setValue(s.convert());
        super.getUsers().child(username).child("surveys").child(survey).setValue(survey);
    }

    public void readSurvey(String id, final Consumer<Survey> consumer) {
        ValueEventListener postListener = new ValueEventListener() {

            @RequiresApi(api = 24)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseSurvey survey = dataSnapshot.getValue(DatabaseSurvey.class);
                if (survey == null) {
                    consumer.accept(null);
                    return;
                }
                Survey rev = survey.deserialize();
                consumer.accept(rev);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };

        super.getSurveys().child(id).addListenerForSingleValueEvent(postListener);
    }

    public void order(String survey, String username, String item) {
        super.getSurveys().child(survey).child("orders").child(username +"--" + generateString()).setValue(item);
    }

    public void readOrder(String survey, final Consumer<Map<String, String>> consumer) {
        ValueEventListener postListener = new ValueEventListener() {

            @RequiresApi(api = 24)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> orders = (Map<String, String>) dataSnapshot.getValue();
                if (orders == null) {
                    consumer.accept(null);
                    return;
                }

                consumer.accept(orders);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };

        super.getSurveys().child(survey).child("orders").addValueEventListener(postListener);
    }

    private static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
