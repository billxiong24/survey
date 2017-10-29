package dumad.surveyproject.Data.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ritler on 10/29/17.
 */

public abstract class DB {
    private DatabaseReference Database, Users, Surveys;

    public DB() {
        this.Database = FirebaseDatabase.getInstance().getReference();
        this.Surveys = this.Database.child("Surveys");
        this.Users = this.Database.child("Users");
    }

    protected DatabaseReference getSurveys() {
        return Surveys;
    }

    protected DatabaseReference getUsers() {
        return Users;
    }
}
