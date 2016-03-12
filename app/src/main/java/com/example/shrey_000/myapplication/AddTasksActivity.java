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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        final ArrayList<String> taskNames = new ArrayList();
//        Parse.enableLocalDatastore(this);
        if(getIntent().getBooleanExtra("firstLaunch",true)){
            Parse.initialize(this);
        }


        ParseObject taskInfo = new ParseObject("TaskInfo");
        taskInfo.put("taskId", 1);
        taskInfo.put("taskName", "task1");
        taskInfo.put("projectId", 1);
        taskInfo.put("projectName", "project1");
        taskInfo.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TaskInfo");
        query.whereNotEqualTo("projectId",0);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> taskInfoList, ParseException e) {
                if (e == null) {
                    for (ParseObject object : taskInfoList) {
                        taskNames.add((String) object.get("taskName"));
                    }
                    Log.d("score", "Retrieved " + taskInfoList.size() + " scores");
                    String[] taskNames_list = new String[taskNames.size()];
                    taskNames_list = taskNames.toArray(taskNames_list);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, taskNames_list){
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
        String taskName = TaskTextbox.getText().toString();
        ParseObject taskInfo = new ParseObject("TaskInfo");
        taskInfo.put("taskId",2);
        taskInfo.put("taskName",taskName);
        taskInfo.put("projectId",1);
        taskInfo.put("projectName","project1");
        taskInfo.saveInBackground();

        refreshPage();
    }

    public void refreshPage(){
        Log.d("before","before");
        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
        intent.putExtra("firstLaunch",false);
        startActivity(intent);
        Log.d("after", "after");
        this.finish();
    }

    public void goToNext(View view){
        Intent intent = new Intent(getApplicationContext(),AddDependencyActivity.class);
        startActivity(intent);
    }

    public void testDbConnect() {
        DbConnect dbConnect = new DbConnect(this);

    }


}
