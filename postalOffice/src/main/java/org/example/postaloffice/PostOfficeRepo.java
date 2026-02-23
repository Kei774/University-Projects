package org.example.postaloffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostOfficeRepo extends JpaRepository<PostOffice,Integer> {
    List<PostOffice> findByActiveTrue();

}
