package com.example.shrey_000.myapplication;

import java.util.ArrayList;

/**
 * Created by SHREY_000 on 3/12/2016.
 */
public class DependencyList {
    private int taskId;
    private ArrayList <DependencyList> dependentTasks;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public ArrayList getDependentTasks(){
        return dependentTasks;
    }

    public void setDependentTasks(ArrayList dependentTasks) {
        this.dependentTasks = dependentTasks;
    }


}
