package com.cleartax;

import com.cleartax.office.PassportOffice;
import com.cleartax.office.Strategy;

import java.util.stream.IntStream;

import static com.cleartax.office.PassportOffice.OFFICE_CAPACITY;

/**
 * Created by rohit on 1/23/17.
 */
public class Simulation {
    public static void main(String[] args) {
        final double average1 = IntStream.range(0, 10).mapToDouble(i -> gfcfs()).average().getAsDouble();
        final double average2 = IntStream.range(0, 10).mapToDouble(i -> swfcfs()).average().getAsDouble();
        final double average3 = IntStream.range(0, 10).mapToDouble(i -> random()).average().getAsDouble();
        System.out.println("Average time per applicant using globalFirstComeFirstServe = " + average1);
        System.out.println("Average time per applicant using stageWiseFirstComeFirstServe = " + average2);
        System.out.println("Average time per applicant using random = " + average3);


    }

    public static double gfcfs() {
        final double average = IntStream.range(1, OFFICE_CAPACITY + 1).mapToObj(Applicant::new).mapToLong(applicant -> {
            PassportOffice.getChainOfDepartments(
                    Strategy.globalFirstComeFirstServe(OFFICE_CAPACITY))
                    .process(applicant);
            return applicant.getTotalTimeTaken();
        }).average().getAsDouble();
        return average;
    }

    public static double swfcfs() {
        final double average = IntStream.range(1, OFFICE_CAPACITY + 1).mapToObj(Applicant::new).mapToLong(applicant -> {
            PassportOffice.getChainOfDepartments(
                    Strategy.stageWiseFirstComeFirstServe(OFFICE_CAPACITY))
                    .process(applicant);
            return applicant.getTotalTimeTaken();
        }).average().getAsDouble();
        return average;
    }

    public static double random() {
        final double average = IntStream.range(1, OFFICE_CAPACITY + 1).mapToObj(Applicant::new).mapToLong(applicant -> {
            PassportOffice.getChainOfDepartments(
                    Strategy.random(OFFICE_CAPACITY))
                    .process(applicant);
            return applicant.getTotalTimeTaken();
        }).average().getAsDouble();
        return average;
    }
}
