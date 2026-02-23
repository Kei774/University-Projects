package org.example.postaloffice;

import jakarta.persistence.*;

@Entity
@Table(name = "postOffice_table")
public class PostOffice {
    @SequenceGenerator(name = "officeId", sequenceName = "pOffice_sequence",initialValue = 1000,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pOffice_sequence")
    @Id
    private int officeId;
    private String officeName;
    private String district;
    private String province;
    private int telephone;
    private boolean active = true;

    public PostOffice() {
    }

    @Override
    public String toString() {
        return officeName + " (" + district + ")";
    }


    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
