package com.cleartax.office;

import com.cleartax.Applicant;

import java.util.Comparator;
import java.util.concurrent.*;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * Created by rohit on 1/21/17.
 */
public class Department {

    private final String name;
    private final int noOfAgents;
    private final long minProcessingTime;
    private final long maxProcessingTime;
    private Department nextDepartment;
    private ExecutorService service;

    public Department(String name, int noOfAgents, long minProcessingTime, long maxProcessingTime) {
        Comparator<MyTask> comparator = Comparator.comparing(myTask -> myTask.applicant.getTokenNo());
        this.name = name;
        this.noOfAgents = noOfAgents;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
    }

    public String getName() {
        return name;
    }

    public void setStrategy(BlockingQueue strategy) {
        this.service = new ThreadPoolExecutor(noOfAgents,noOfAgents,0l,TimeUnit.MILLISECONDS,strategy);
    }

    public void setNextDepartment(Department nextDepartment) {
        this.nextDepartment = nextDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", noOfAgents=" + noOfAgents +
                '}';
    }

    public void process(Applicant applicant) {
        service.submit(new MyTask(applicant));
        if(applicant.getTokenNo()==PassportOffice.OFFICE_CAPACITY){
            service.shutdown();
        }
    }

    class MyTask implements Runnable {
        private Applicant applicant;

        public Applicant getApplicant() {
            return applicant;
        }

        public MyTask(Applicant applicant) {
            this.applicant = applicant;
        }

        @Override
        public void run() {
            long sleep = current().nextLong(minProcessingTime * 60 * 10, maxProcessingTime * 60 * 10);
            if (sleep > 0) {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
            applicant.setTotalTimeTaken(System.currentTimeMillis() - applicant.getEnteredAt());
            System.out.println(" Application " + applicant.getTokenNo() + " finished at " + name);
            if (nextDepartment != null) {
                nextDepartment.process(applicant);
            }
        }
    }
}

