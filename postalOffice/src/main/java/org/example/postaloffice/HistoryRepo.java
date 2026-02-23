package org.example.postaloffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<History, Integer> {
    List<History> findByParcel_ParcelId(int parcelId);
}