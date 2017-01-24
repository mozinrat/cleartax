package com.cleartax;


/**
 * Created by rohit on 1/21/17.
 */
public final class Applicant{

    private final int tokenNo;
    private final long enteredAt;
    private long totalTimeTaken;

    public Applicant(int tokenNo) {
        this.tokenNo = tokenNo;
        this.enteredAt = System.currentTimeMillis();
    }

    public int getTokenNo() {
        return tokenNo;
    }

    public long getEnteredAt() {
        return enteredAt;
    }

    public long getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(long totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        return tokenNo == applicant.tokenNo;
    }

    @Override
    public int hashCode() {
        return tokenNo;
    }

}
