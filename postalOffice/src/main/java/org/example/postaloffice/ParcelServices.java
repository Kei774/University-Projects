package org.example.postaloffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ParcelServices {
    @Autowired
    ParcelRepo parcelRepo;


    public Parcel addParcel(Parcel parcel) {
        return parcelRepo.save(parcel);
    }

    public List<Parcel> getAllParcels() {
        return parcelRepo.findAll();
    }

    public Parcel getParcelById(int id) {
        return parcelRepo.findById(id).get();
    }

    public void deleteParcel(int id) {
        parcelRepo.deleteById(id);
    }

    public List<Parcel> findRecentParcels() {
        return parcelRepo.findTop10ByOrderByParcelIdDesc();
    }

    public List<Parcel> getParcelsRegistered() {
        return parcelRepo.findByStatus("Registered");
    }
    public List<Parcel> getParcelsInTransit() {
        return parcelRepo.findByStatus("In Transit");
    }
    public List<Parcel> getParcelsDelivered() {
        return parcelRepo.findByStatus("Delivered");
    }
    public List<Parcel> getParcelsReturned() {
        return parcelRepo.findByStatus("Returned");
    }
    public List<Parcel> getParcelsOnhold() {
        return parcelRepo.findByStatus("On Hold");
    }

}