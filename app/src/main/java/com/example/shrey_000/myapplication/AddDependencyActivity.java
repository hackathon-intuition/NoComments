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
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddDependencyActivity extends AppCompatActivity {

    String taskName;
    String projectName;
    Intent i;
    ArrayList<String> taskNames;
    ArrayAdapter<String> adapter;
    ArrayList<String> dependencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependency);

        taskNames = new ArrayList<String>();
        i = getIntent();
        taskName = i.getStringExtra("taskName");
        projectName = i.getStringExtra("projectName");

        dependencies = new ArrayList<String>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TaskInfo");
        query.whereEqualTo("projectName", projectName);
        query.whereNotEqualTo("taskName", taskName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> taskInfoList, ParseException e) {
                if (e == null) {
                    for (ParseObject object : taskInfoList) {
                        taskNames.add((String) object.get("taskName"));
                    }
                    Log.d("score", "Retrieved " + taskInfoList.size() + " scores");
                    String[] taskNames_list = new String[taskNames.size()];
                    taskNames_list = taskNames.toArray(taskNames_list);
                    adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,taskNames_list){
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
                            TextView textView=(TextView) view.findViewById(android.R.id.text1);
                            if(textView.getCurrentTextColor()==Color.BLACK){
                                textView.setTextColor(Color.GREEN);
                                dependencies.add(textView.getText().toString());
                            }
                            else{
                                textView.setTextColor(Color.BLACK);
                                dependencies.remove(textView.getText().toString());
                            }

                        }
                    });
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        Log.d("Name","Name in depency");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_dependency, menu);
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

    public void goToTaskPage(View view){
        saveDependencies();
        Intent i = new Intent(this,AddTasksActivity.class);
        i.putExtra("projectName",projectName);
        startActivity(i);
    }

    private void saveDependencies() {


        for(String dependency:dependencies){

            ParseObject taskDependency = new ParseObject("TaskDependency");
            taskDependency.put("projectName",projectName);
            taskDependency.put("taskName",taskName);
            taskDependency.put("dependency",dependency);
            taskDependency.saveInBackground();
        }


    }
}
