package dumad.surveyproject.Data.Database;

import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

import dumad.surveyproject.Data.User.DatabaseUser;
import dumad.surveyproject.Data.User.User;

/**
 * Created by Ritler on 10/29/17.
 */

public class UserDB extends DB {

    public UserDB() {
        super();
    }

    public void createUser(String username){
        User user = new User(username, new HashMap<String, String>());
        super.getUsers().child(username).setValue(user.convert());
    }



    public void readUser(String username, final Consumer<User> consumer) {
        ValueEventListener postListener = new ValueEventListener() {
            @RequiresApi(api = VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseUser d = dataSnapshot.getValue(DatabaseUser.class);
                if(d == null) {
                    consumer.accept(null);
                    return;
                }

                User user = d.deserialize();

                consumer.accept(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        super.getUsers().child(username).addListenerForSingleValueEvent(postListener);
    }
}
