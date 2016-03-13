package com.example.shrey_000.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ui.ParseLoginBuilder;
import java.util.ArrayList;
import java.util.List;

public class AddProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        final ArrayList<String> projectNames = new ArrayList();
       // Parse.enableLocalDatastore(this);
        if(getIntent().getBooleanExtra("firstLaunch",true)){
            Parse.initialize(this);
        }
//        ParseLoginBuilder builder = new ParseLoginBuilder(AddProjectActivity.this);
//        startActivityForResult(builder.build(), 0);
//        testDbConnect();

//        ParseObject taskInfo = new ParseObject("TaskInfo");
//        taskInfo.put("taskId", 1);
//        taskInfo.put("taskName", "task1");
//        taskInfo.put("projectId", 2);
//        taskInfo.put("projectName", "project2");
//        taskInfo.saveInBackground();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TaskInfo");
        query.whereNotEqualTo("projectId",0);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> taskInfoList, ParseException e) {
                if (e == null) {
                    for (ParseObject object : taskInfoList) {
                        projectNames.add((String) object.get("projectName"));
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

    public void addProjectTextbox(View view){
//        EditText TaskTextbox = new EditText(this);
//        LinearLayout textboxLayout = (LinearLayout) findViewById(R.id.linLayout);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        TaskTextbox.setLayoutParams(params);
////        params.addRule(LinearLayout.BELOW, R.id.title);
//        textboxLayout.addView(TaskTextbox);

        EditText ProjectTextbox = (EditText)findViewById(R.id.projectTextbox);
        String projectName = ProjectTextbox.getText().toString();

        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
        intent.putExtra("projectName",projectName);
        startActivity(intent);
//        ParseObject taskInfo = new ParseObject("TaskInfo");

//        taskInfo.put("projectName", projectName);
//        taskInfo.saveInBackground();


//        refreshPage();
//        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
//        intent.putExtra("projectName",projectName);
//        startActivity(intent);

//        Intent intent = new Intent(getApplicationContext(),AddTasksActivity.class);
//        intent.putExtra("projectName",projectName);
//        startActivity(intent);


        //refreshPage();
    }

    public void refreshPage(){
        Log.d("before","before");
        Intent intent = new Intent(getApplicationContext(),AddProjectActivity.class);
        intent.putExtra("firstLaunch",false);
        startActivity(intent);
        Log.d("after", "after");
        this.finish();
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
