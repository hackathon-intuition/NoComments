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

public class AddTasksActivity extends AppCompatActivity {

    Intent i;
    String projectName;
    ArrayList<String> taskNames;
    ArrayList<String> daysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        taskNames = new ArrayList();
        i = getIntent();
        projectName = i.getStringExtra("projectName");
//        Parse.enableLocalDatastore(this);
//        if(getIntent().getBooleanExtra("firstLaunch",true)){
//            Parse.initialize(this);
//        }


//        ParseObject taskInfo = new ParseObject("TaskInfo");
//        taskInfo.put("taskId", 1);
//        taskInfo.put("taskName", "task1");
//        taskInfo.put("projectId", 1);
//        taskInfo.put("projectName", "project1");
//        taskInfo.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TaskInfo");
        query.whereEqualTo("projectName", projectName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> taskInfoList, ParseException e) {
                if (e == null) {
                    for (ParseObject object : taskInfoList) {
                        taskNames.add((String) object.get("taskName"));
                    }
                    Log.d("score", "Retrieved " + taskInfoList.size() + " scores");
                    String[] taskNames_list = new String[taskNames.size()];
                    taskNames_list = taskNames.toArray(taskNames_list);
                    ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,taskNames_list){
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
                    ListView listView = (ListView) findViewById(R.id.taskList_lv);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(),AddDependencyActivity.class);
                            i.putExtra("projectName", projectName);
                            i.putExtra("taskName",taskNames.get(position));
                            startActivity(i);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTaskTextbox(View view){
//        EditText TaskTextbox = new EditText(this);
//        LinearLayout textboxLayout = (LinearLayout) findViewById(R.id.linLayout);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        TaskTextbox.setLayoutParams(params);
////        params.addRule(LinearLayout.BELOW, R.id.title);
//        textboxLayout.addView(TaskTextbox);

        EditText TaskTextbox = (EditText)findViewById(R.id.taskTextbox);
        EditText DaysTextbox = (EditText)findViewById(R.id.daysTextbox);
        String taskName = TaskTextbox.getText().toString();
        String days = DaysTextbox.getText().toString();


        ParseObject taskInfo = new ParseObject("TaskInfo");
        taskInfo.put("taskName", taskName);
        taskInfo.put("projectName",projectName);
        taskInfo.saveInBackground();

        ParseObject taskDuration = new ParseObject("TaskDuration");
        taskDuration.put("taskName",taskName);
        taskDuration.put("projectName",projectName);
        taskDuration.put("estimatedDays",days);
        taskDuration.saveInBackground();

        refreshPage();
    }

    public void refreshPage(){
//        Log.d("before", "before");
//        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
//        intent.putExtra("firstLaunch", false);
//        this.finish();
//        startActivity(intent);
//        Log.d("after", "after");
//        this.finish();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TaskInfo");
        query.whereEqualTo("projectName", projectName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> taskInfoList, ParseException e) {
                if (e == null) {
                    for (ParseObject object : taskInfoList) {
                        taskNames.add((String) object.get("taskName"));
                    }
                    Log.d("score", "Retrieved " + taskInfoList.size() + " scores");
                    String[] taskNames_list = new String[taskNames.size()];
                    taskNames_list = taskNames.toArray(taskNames_list);
                    ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,taskNames_list){
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
                    ListView listView = (ListView) findViewById(R.id.taskList_lv);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(),AddDependencyActivity.class);
                            i.putExtra("projectName", projectName);
                            i.putExtra("taskName",taskNames.get(position));
                            startActivity(i);
                        }
                    });
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void goToNext(View view){
        Intent intent = new Intent(getApplicationContext(),AddDependencyActivity.class);
        startActivity(intent);
    }

    public void testDbConnect() {
        DbConnect dbConnect = new DbConnect(this);

    }


}
