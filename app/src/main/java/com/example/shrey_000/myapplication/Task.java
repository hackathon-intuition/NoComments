package com.example.shrey_000.myapplication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by saidurga001 on 3/12/2016.
 */


// A wrapper class to hold the tasks during the calculation


public class Task {
    // the actual cost of the task
    public int cost;
    // the cost of the task along the critical path
    public int criticalCost;
    // a name for the task for printing
    public String name;
    // the earliest start
    public int earlyStart;
    // the earliest finish
    public int earlyFinish;
    // the latest start
    public int latestStart;
    // the latest finish
    public int latestFinish;
    // the tasks on which this task is dependant
    public HashSet<Task> dependencies = new HashSet<Task>();




    //Contructor for Task
    public Task(String name, int cost, Task... dependencies) {
        this.name = name;
        this.cost = cost;
        for (Task t : dependencies) {
            this.dependencies.add(t);
        }
        this.earlyFinish = -1;
    }







    public void setLatest() {
        latestStart = CriticalPath.maxCost - criticalCost;
        latestFinish = latestStart + cost;
    }








    public String[] toStringArray() {
        String criticalCond = earlyStart == latestStart ? "Yes" : "No";
        String[] toString = { name, earlyStart + "", earlyFinish + "", latestStart + "", latestFinish + "",
                latestStart - earlyStart + "", criticalCond };
        return toString;
    }








    public boolean isDependent(Task t) {
        // is it a direct dependency?
        if (dependencies.contains(t)) {
            return true;
        }
        // is t an indirect dependency
        for (Task dep : dependencies) {
            if (dep.isDependent(t)) {
                return true;
            }
        }
        return false;
    }



    public static Task[] criticalPath(Set<Task> tasks) {
        // tasks whose critical cost has been calculated
        HashSet<Task> completed = new HashSet<Task>();
        // tasks whose critical cost needs to be calculated
        HashSet<Task> remaining = new HashSet<Task>(tasks);

        // Backflow algorithm
        // while there are tasks whose critical cost isn't calculated.
        while (!remaining.isEmpty()) {
            boolean progress = false;

            // find a new task to calculate
            for (Iterator<Task> it = remaining.iterator(); it.hasNext();) {
                Task task = it.next();
                if (completed.containsAll(task.dependencies)) {
                    // all dependencies calculated, critical cost is max
                    // dependency
                    // critical cost, plus our cost
                    int critical = 0;
                    for (Task t : task.dependencies) {
                        if (t.criticalCost > critical) {
                            critical = t.criticalCost;
                        }
                    }
                    task.criticalCost = critical + task.cost;
                    // set task as calculated an remove
                    completed.add(task);
                    it.remove();
                    // note we are making progress
                    progress = true;
                }
            }
            // If we haven't made any progress then a cycle must exist in
            // the graph and we wont be able to calculate the critical path
            if (!progress)
                throw new RuntimeException("Cyclic dependency, algorithm stopped!");
        }

        // get the cost
        maxCost(tasks);
        HashSet<Task> initialNodes = initials(tasks);
        calculateEarly(initialNodes);

        // get the tasks
        Task[] ret = completed.toArray(new Task[0]);
        // create a priority list
        Arrays.sort(ret, new Comparator<Task>() {

            public int compare(Task o1, Task o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        //System.out.println(ret.toString().toString());
        return ret;
    }











    //////////////////////////////////////////////
    public static void calculateEarly(HashSet<Task> initials) {
        for (Task initial : initials) {
            initial.earlyStart = 0;
            initial.earlyFinish = initial.cost;
            setEarly(initial);
        }
    }











    public static void setEarly(Task initial) {
        int completionTime = initial.earlyFinish;
        for (Task t : initial.dependencies) {
            if (completionTime >= t.earlyStart) {
                t.earlyStart = completionTime;
                t.earlyFinish = completionTime + t.cost;
            }
            setEarly(t);
        }
    }

    public static int getLatest(Task curr)
    {
        return curr.latestStart;
    }










    public static HashSet<Task> initials(Set<Task> tasks) {
        HashSet<Task> remaining = new HashSet<Task>(tasks);
        for (Task t : tasks) {
            for (Task td : t.dependencies) {
                remaining.remove(td);
            }
        }

        System.out.print("Initial nodes: ");
        for (Task t : remaining)
            System.out.print(t.name + " ");
        System.out.print("\n\n");
        return remaining;
    }











    public static void maxCost(Set<Task> tasks) {
        int max = -1;
        for (Task t : tasks) {
            if (t.criticalCost > max)
                max = t.criticalCost;
        }
        CriticalPath.maxCost = max;
        System.out.println("Critical path length (cost): " + CriticalPath.maxCost);
        for (Task t : tasks) {
            t.setLatest();
        }
    }








    public static void print(Task[] tasks) {
//    	 ArrayList <Task> result=new ArrayList<Task>();
        System.out.format(CriticalPath.format, "Task", "ES", "EF", "LS", "LF", "Slack", "Critical?");

        ArrayList<String> taskNames=new ArrayList <String>();
        ArrayList<Integer> ES=new ArrayList <Integer>();
        ArrayList<Integer> LS=new ArrayList <Integer>();
        ArrayList<Integer> EF=new ArrayList <Integer>();
        ArrayList<Integer> LF=new ArrayList <Integer>();
        ArrayList<ArrayList<String>> result=new ArrayList<ArrayList <String>>();

        ArrayList<Task> tss=new ArrayList<Task>();
//    	ArrayList<>
        //,ES,EF,LS,LF;
        for (Task t : tasks)
        {
            System.out.format(CriticalPath.format, (Object[]) t.toStringArray());
            tss.add(t);


//        	taskNames.add((t.name).toString());
//        	ES.add((t.earlyStart));
//        	EF.add((t.earlyFinish));
//        	LS.add((t.latestStart));
//        	LF.add((t.latestFinish));
//        	System.out.println((Object[]) t.toStringArray());
        }

        for(int i=0;i<tss.size();i++){
            System.out.println("Name: "+tss.get(i).name);
        }
//    	System.out.println(tss);
    }

}

//System.out.println(taskNames+"\n"+ES+"\n"+EF+"\n"+LS+"\n"+LF);



//    public static void printResult(Task[] tasks){
//    	ArrayList <String> res = new ArrayList<String>(); 
//    	for(Task t:tasks)
//    	{
//    		res.toArray((Object[])t.toStringArray());
//    	}
//    	System.out.println(res);
//    }
//}
