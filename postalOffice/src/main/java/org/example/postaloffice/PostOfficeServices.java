package org.example.postaloffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostOfficeServices {
    @Autowired
    PostOfficeRepo postOfficeRepo;

    public List<PostOffice> getActivePostOffices(){
        return postOfficeRepo.findByActiveTrue();
    }

    public PostOffice addOffice(PostOffice postOffice){
        return postOfficeRepo.save(postOffice);
    }
    public PostOffice getOfficeById(int id){
        return postOfficeRepo.findById(id).get();
    }
    public List<PostOffice> getAllOffice(){
        return postOfficeRepo.findAll();
    }
    public PostOffice updateOffice(PostOffice postOffice){
        return postOfficeRepo.save(postOffice);
    }
    public void deleteOfficeById(int id){
        postOfficeRepo.deleteById(id);
    }
}
