package lk.ijse.gdse71.model;

import java.sql.Timestamp;

public class Complains {
    private String complainId;
    private String userId;
    private String description;
    private String status;
    private String remarks;
    private Timestamp createdDate;

    public Complains(String description, String status, String remarks, Timestamp createdDate, String userId, String complainId) {
        this.description = description;
        this.status = status;
        this.remarks = remarks;
        this.createdDate = createdDate;
        this.userId = userId;
        this.complainId = complainId;
    }

    public Complains() {}

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
