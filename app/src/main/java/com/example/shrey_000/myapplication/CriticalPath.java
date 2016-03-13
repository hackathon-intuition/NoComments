package com.example.shrey_000.myapplication;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by saidurga001 on 3/12/2016.
 */
public class CriticalPath {
    public static int maxCost;
    public static String format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n";

    public static void main(String[] args) {
        // The example dependency graph
        HashSet<Task> allTasks = new HashSet<Task>();

//        for(int i=0;i<NOOFTASKS; i++)
//       
//        {
//            allTasks.add(new Task("GetStringFromDB",integer,taskObject/Dependancies));
//        }

        Task end = new Task("End", 0);
        Task F = new Task("F", 2, end);
        Task A = new Task("A", 3, end);
        Task X = new Task("X", 4, F, A);
        Task Q = new Task("Q", 2, A, X);
        Task start = new Task("Start", 0, Q);
        allTasks.add(end);
        allTasks.add(F);
        allTasks.add(A);
        allTasks.add(X);
        allTasks.add(Q);
        allTasks.add(start);


        Task[] result = Task.criticalPath(allTasks);
        //ArrayList <Task> res=new ArrayList<Task>();
//        for(Task t: allTasks)
//        {
//        	System.out.println(Task.getLatest(t));
//        }


//        
//        for(int i=0;i<result.length;i++)
//    	{
//    		result[i].toStringArray().toString();
//    	}
//        for(int i=0;i<result.length;i++)
//        	System.out.println(result[i]);
//        


        Task.print(result);


        // System.out.println(result[1].toStringArray());
        //System.out.println(result[0].toString());
        //System.out.println("Critical Path: " + Arrays.toString(result));
    }




}