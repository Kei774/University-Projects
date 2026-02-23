package org.example.postaloffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class HistoryServises {
    @Autowired
    HistoryRepo historyRepo;
    @Autowired
    ParcelRepo parcelRepo;

    public void updateParcel(Parcel parcel) {
        parcelRepo.save(parcel);

        History history = new History();
        history.setParcel(parcel);
        history.setStatus(parcel.getStatus());
        history.setLocation(parcel.getAssignedPostOffice());
        history.setUpdatedAt(LocalDateTime.now());

        historyRepo.save(history);
    }


    public History addHistory(History history) {
        return historyRepo.save(history);
    }

    public List<History> getAllHistory () {
        return historyRepo.findAll();
    }

    public History getHistoryById (int id) {
        return historyRepo.findById(id).get();
    }

    public List<History> getHistoryByParcelId(int parcelId) {
        return historyRepo.findByParcel_ParcelId(parcelId);
    }


    public void deleteParcel(int id) {
        historyRepo.deleteById(id);
    }
}