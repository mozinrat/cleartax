package com.cleartax.office;

import com.cleartax.Applicant;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by rohit on 1/21/17.
 */
public class PassportOffice {

    public static final int OFFICE_CAPACITY = 10;

    /**
     * Departments are organised to work in certain orders,
     * we could have more departments also
     * like biometric can be splitted in two deps, scanning fingerprints, retina scan
     * or even the order could change
     * <p>
     * Applying chain of responsibility here, but for our use case each department is doing just waiting so
     * not applying interface pattern
     *
     * @param strategy
     */

    public static Department getChainOfDepartments(BlockingQueue strategy) {
        Department documentProcessing = new Department("documentProcessing", 15, 5, 15);
        documentProcessing.setStrategy(strategy);

        Department policeVerification = new Department("policeVerification", 10, 3, 8);
        policeVerification.setStrategy(strategy);
        documentProcessing.setNextDepartment(policeVerification);

        Department biometricScans = new Department("biometricScans", 12, 5, 7);
        biometricScans.setStrategy(strategy);
        policeVerification.setNextDepartment(biometricScans);

        biometricScans.setNextDepartment(null);
        return documentProcessing;
    }


}
