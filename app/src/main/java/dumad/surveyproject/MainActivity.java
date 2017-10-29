package dumad.surveyproject;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import dumad.surveyproject.Data.DBController;
import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.Data.Survey.Survey;
import dumad.surveyproject.Data.User.User;

public class MainActivity extends AppCompatActivity {



    @RequiresApi(api = VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBController base = new DBController();
        base.createUser("bill");
        List<Item> list = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            list.add(new Item(i, i+1, i+2, i+3, 5.34, "descrieeption", "a name"+i));
        }
        base.addSurvey("bill", "west union", "survey560", list);
        base.addSurvey("bill", "wat", "survey564", list);
        base.addSurvey("bill", "a location", "survey565", list);
        base.addSurvey("bill", "aloc", "survey566", list);
        base.addSurvey("bill", "loc", "survey569", list);
        base.addSurvey("bill", "er", "survey453", new ArrayList<Item>());

        base.readSurvey("survey453", new Consumer<Survey>() {
            @Override
            public void accept(Survey survey) {
                System.out.println(survey.getMenu());
                System.out.println(survey.getId());
                System.out.println(survey.getLocation());
            }
        });

        base.readSurvey("survey560", new Consumer<Survey>() {
            @Override
            public void accept(Survey survey) {
                System.out.println(survey.getMenu());
            }
        });

        base.readUser("bill", new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.getSurveys());
                System.out.println(user.getUsername());
            }
        });

        base.addMenuItem("survey564", new Item(4, 5, 6, 7, 5.64, "another one", "wer name"));
        base.readSurvey("survey564", new Consumer<Survey>() {
            @Override
            public void accept(Survey survey) {
                System.out.println(survey.getMenu());
                System.out.println("weee");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
