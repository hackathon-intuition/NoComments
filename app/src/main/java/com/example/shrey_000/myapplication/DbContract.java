package com.example.shrey_000.myapplication;

import android.provider.BaseColumns;

public class DbContract {

    public static abstract class TaskInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "TaskInfo";
        public static final String COLUMN_NAME_TASK_ID = "taskId";
        public static final String COLUMN_NAME_TASK_NAME = "taskName";
        public static final String COLUMN_NAME_PROJECT_ID = "projectId";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectName";
    }

    public static abstract class TaskDependencyEntry implements BaseColumns {
        public static final String TABLE_NAME = "TaskDependency";
        public static final String COLUMN_NAME_TASK_ID = "taskId";
        public static final String COLUMN_NAME_PROJECT_ID = "projectId";
        public static final String COLUMN_NAME_ESTIMATED_DURATION = "estimatedDuration";
        public static final String COLUMN_NAME_DEPENDENT_TASK = "dependency"; //task that this particular task is dependent ON
    }

    public static abstract class TaskHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "TaskHistory";
        public static final String COLUMN_NAME_TASK_ID = "taskId";
        public static final String COLUMN_NAME_PAST_DURATION = "pastDuration";
        public static final String COLUMN_NAME_PROJECT_ID = "projectId";
    }
}
