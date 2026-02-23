package org.example.postaloffice;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "parcel_table")
public class Parcel {
    @SequenceGenerator(name = "parcelId", sequenceName = "parcel_sequence",initialValue = 1000,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "parcel_sequence")
    @Id

    private int parcelId;

    private String senderName;
    private String receiverName;
    private double weight;
    private String category;
    private String status;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
    private List<History> historyList;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private PostOffice assignedPostOffice;

    public Parcel() {
    }
    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public int getParcelId() {
        return parcelId;
    }

    public void setParcelId(int parcelId) {
        this.parcelId = parcelId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PostOffice getAssignedPostOffice() {
        return assignedPostOffice;
    }

    public void setAssignedPostOffice(PostOffice assignedPostOffice) {
        this.assignedPostOffice = assignedPostOffice;
    }
}
