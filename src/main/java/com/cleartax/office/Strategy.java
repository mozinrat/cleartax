package com.cleartax.office;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Created by rohit on 1/23/17.
 */
public class Strategy {

    public static BlockingQueue globalFirstComeFirstServe(int queueSize){
        Comparator<Department.MyTask> comparator = Comparator.comparing(myTask -> myTask.getApplicant().getTokenNo());
        return new PriorityBlockingQueue(queueSize,comparator);
    }

    public static BlockingQueue stageWiseFirstComeFirstServe(int queueSize){
        return new LinkedBlockingQueue(queueSize);
    }

    public static BlockingQueue random(int queueSize){
        Comparator<Department.MyTask> comparator = Comparator.comparing(myTask -> current().nextInt(queueSize));
        return new PriorityBlockingQueue(queueSize,comparator);
    }


}


