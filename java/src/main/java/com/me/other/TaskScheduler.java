package com.me.other;

import java.util.*;

class Task implements Comparable<Task> {
    int id;
    int priority;
    int readyTime;
    int executionTime;
    int remainingTime;

    Task(int id, int priority, int readyTime, int executionTime) {
        this.id = id;
        this.priority = priority;
        this.readyTime = readyTime;
        this.executionTime = executionTime;
        this.remainingTime = executionTime;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        } else if (this.readyTime != other.readyTime) {
            return Integer.compare(this.readyTime, other.readyTime);
        } else {
            return Integer.compare(this.id, other.id);
        }
    }
}

public class TaskScheduler {
    public static int[] getCompletionTimes(int[][] tasks) {
        int n = tasks.length;
        Task[] taskList = new Task[n];
        for (int i = 0; i < n; i++) {
            taskList[i] = new Task(i, tasks[i][0], tasks[i][1], tasks[i][2]);
        }
        Arrays.sort(taskList, Comparator.comparingInt(task -> task.readyTime));

        PriorityQueue<Task> pq = new PriorityQueue<>();
        int[] completionTimes = new int[n];
        int currentTime = 0;
        int index = 0;

        while (!pq.isEmpty() || index < n) {
            while (index < n && taskList[index].readyTime <= currentTime) {
                pq.offer(taskList[index]);
                index++;
            }
            if (pq.isEmpty()) {
                currentTime = taskList[index].readyTime;
                continue;
            }

            Task currentTask = pq.poll();
            int timeToNextTask = (index < n) ? taskList[index].readyTime - currentTime : currentTask.remainingTime;
            int timeToExecute = Math.min(timeToNextTask, currentTask.remainingTime);

            currentTask.remainingTime -= timeToExecute;


            currentTime += timeToExecute;

            if (currentTask.remainingTime == 0) {
                completionTimes[currentTask.id] = currentTime;
            } else {
                pq.offer(currentTask);
            }
        }
        return completionTimes;
    }

    public static void main(String[] args) {
        int[][] tasks = {
                {0, 10, 5},
                {3, 0, 10},
                {0, 5, 10},
                {0, 5, 5},
        };
        int[] result = getCompletionTimes(tasks);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
