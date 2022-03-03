package models;

import java.util.Date;

public class IssueModel {
    private int ISID;
    private int BID;
    private int UID;
    private Date IssueDate;
    private Date ReturnDate;

    //constructor
    public IssueModel(int ISID, int BID, int UID, Date issueDate, Date returnDate) {
        this.ISID = ISID;
        this.BID = BID;
        this.UID = UID;
        IssueDate = issueDate;
        ReturnDate = returnDate;
    }

    //overloaded constructor
    public IssueModel(int BID, int UID, Date issueDate, Date returnDate) {
        this.BID = BID;
        this.UID = UID;
        IssueDate = issueDate;
        ReturnDate = returnDate;
    }

    public IssueModel(){

    }


    //getters and setters
    public int getISID() {
        return ISID;
    }

    public void setISID(int ISID) {
        this.ISID = ISID;
    }

    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date issueDate) {
        IssueDate = issueDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }
}
