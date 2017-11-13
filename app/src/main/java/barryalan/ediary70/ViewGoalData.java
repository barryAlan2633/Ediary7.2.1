package barryalan.ediary70;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alexa on 11/12/2017.
 */

public class ViewGoalData extends AppCompatActivity {

    final databaseHelper db = new databaseHelper(this);
    private ListView lv;;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal_data);

        lv = (ListView) findViewById(R.id.lv_Ggoals);

        populateGoalListView();


    }

    public void populateGoalListView(){
        //Log.d(String.valueOf(this),"is this working?"  );
        Cursor res = db.getUserCount();
        ArrayList<String> listData = new ArrayList<>();
        if (res.getCount() == 0) {
            //show message
            showmessage("Error", "No data found");
            return;

        }
        while (res.moveToNext())
        {
            //printing out name column of short term goals
            listData.add(res.getString(9));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv.setAdapter(adapter);

    }

    public void showmessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
