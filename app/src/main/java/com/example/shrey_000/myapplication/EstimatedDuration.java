package com.example.shrey_000.myapplication;

/**
 * Created by saidurga001 on 3/12/2016.
 */
public class EstimatedDuration
{
    private static int estimatedDuration;
    private static int noOfTaskInst;
    private static double avgPastTimes,sum,pastTimes[];

    public static int EstimateTime(DbConnect db) //estimate time of a particular task
    {

         /*

         -- A func in DbConnect to first get NO of prev instances
         -- A func in DbConnect to get ARRAY of pastTimes of the same task from TaskHistory

         */


//        noOfTaskInst=Integer.parseInt(db.getNoOfTaskInstances());   //find how many similar tasks are there in the previous projects.
//        pastTimes=Double.parseDouble();
//
//        for(int i=0;i<noOfTaskInstances;i++)
//            sum +=pastTimes[i];


        estimatedDuration=(int)Math.ceil(sum/noOfTaskInst);


        return estimatedDuration;
    }


}

