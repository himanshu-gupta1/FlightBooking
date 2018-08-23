package e.wolfsoft1.tickets.ModelClasses;

/**
 * Created by wolfsoft1 on 24/2/18.
 */

public class TicketsModel {
    String time1,time2,platNo,timeStatus,amount;

    public TicketsModel(String time1, String time2, String platNo, String timeStatus, String amount) {
        this.time1 = time1;
        this.time2 = time2;
        this.platNo = platNo;
        this.timeStatus = timeStatus;
        this.amount = amount;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }

    public String getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(String timeStatus) {
        this.timeStatus = timeStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
