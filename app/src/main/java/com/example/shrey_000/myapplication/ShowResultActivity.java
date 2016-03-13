package com.example.shrey_000.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ShowResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);


        final ArrayList<String> resultNames = new ArrayList();
        // Parse.enableLocalDatastore(this);
        if (getIntent().getBooleanExtra("firstLaunch", true)) {
            Parse.initialize(this);
        }
    }


    ParseQuery<ParseObject> query = ParseQuery.getQuery("TaskDuration");
    //query.whereNotEqualTo("projectName",0);
    query.whereEqualTo("projectName",projectName);
    query.findInBackground(new FindCallback<ParseObject>() {
        public void done(List<ParseObject> taskDurationList, ParseException e) {
            if (e == null) {
                for (ParseObject object : taskDurationList) {
                    taskTimes.add((String) object.get("projectName"));
                }
                Log.d("score", "Retrieved " + taskInfoList.size() + " scores");
                String[] projectNames_list = new String[projectNames.size()];
                projectNames_list = projectNames.toArray(projectNames_list);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, projectNames_list){
                    @Override
                    public View getView(int position, View convertView,
                                        ViewGroup parent) {
                        View view =super.getView(position, convertView, parent);
                        TextView textView=(TextView) view.findViewById(android.R.id.text1);
            /*YOUR CHOICE OF COLOR*/
                        textView.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                ListView listView = (ListView) findViewById(R.id.projectList_lv);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
                        intent.putExtra("projectName",projectNames.get(position));
                        startActivity(intent);
                    }
                });

            } else {
                Log.d("score", "Error: " + e.getMessage());
            }
        }
    });


//        ParseLoginBuilder builder = new ParseLoginBuilder(AddTasksActivity.this);
//        startActivityForResult(builder.build(), 0);
//        testDbConnect();
}

//    public void goToNext(View view){
//        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
//        intent.putExtra("projectName",)
//        startActivity(intent);
//    }

    public void testDbConnect() {
        DbConnect dbConnect = new DbConnect(this);

    }


}

