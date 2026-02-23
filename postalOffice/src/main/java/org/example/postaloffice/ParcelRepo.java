package org.example.postaloffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepo extends JpaRepository<Parcel, Integer> {
    List<Parcel> findTop10ByOrderByParcelIdDesc();
    List<Parcel> findByStatus(String status);

}