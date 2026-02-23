package org.example.postaloffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/parcel")
public class ParcelController {
    @Autowired
    ParcelServices parcelServices;
    @Autowired
    PostOfficeServices postOfficeServices;


    @RequestMapping("/save")
    public String addParcel(@ModelAttribute("parcel") Parcel parcel){
        parcelServices.addParcel(parcel);
        parcel.setCreatedDate(LocalDateTime.now());
        return "redirect:/parcel/list";
    }

    @RequestMapping("/home")
    public String showHome(Model model) {
        List<Parcel> parcel = parcelServices.findRecentParcels();
        model.addAttribute("recentParcels", parcel);
        return "index";
    }

    @RequestMapping("/addNew")
    public String view(Model model, @ModelAttribute("parcel")Parcel parcel) {
        PostOffice office = new PostOffice();
        model.addAttribute("parcel", parcel);
        model.addAttribute("office", office);
        model.addAttribute("office", postOfficeServices.getActivePostOffices());
        return "parcel_save";
    }

    @RequestMapping("/list")
    public String home(Model model){
        List<Parcel> parcel = parcelServices.getAllParcels();
        model.addAttribute("parcel",parcel);
        return "parcel_tables";
    }

    @RequestMapping("/edit/{parcelId}")
    public String edit(Model model,@PathVariable("parcelId") int id){
        Parcel parcel = parcelServices.getParcelById(id);
        PostOffice office = new PostOffice();
        model.addAttribute("parcel",parcel);
        model.addAttribute("office", office);
        model.addAttribute("office", postOfficeServices.getActivePostOffices());
        return "parcel_edit";
    }

    @RequestMapping("/delete/{parcelId}")
    public String delete(@PathVariable ("parcelId") int id){
        parcelServices.deleteParcel(id);
        return "redirect:/parcel/list";
    }
    @RequestMapping("/recent")
    public String recent(Model model,@RequestParam String type){
        if (type.equals("active-offices")) {
            model.addAttribute("parcels",postOfficeServices.getActivePostOffices());
            model.addAttribute("title","Active-Offices are listed Below.");
            return "active_offices";
        }else{
            if(type.equals("registered")){
                model.addAttribute("parcels",parcelServices.getParcelsRegistered());
                model.addAttribute("title","Registered Parcels are listed Below.");
            } else if (type.equals("in-transit")) {
                model.addAttribute("parcels",parcelServices.getParcelsInTransit());
                model.addAttribute("title","In-Transit Parcels are listed Below.");
            }else if (type.equals("delivered")) {
                model.addAttribute("parcels",parcelServices.getParcelsDelivered());
                model.addAttribute("title","Delivered Parcels are listed Below.");
            } else if (type.equals("returned")) {
                model.addAttribute("parcels",parcelServices.getParcelsReturned());
                model.addAttribute("title","Returned Parcels are listed Below.");
            }else if (type.equals("on-hold")) {
                model.addAttribute("parcels",parcelServices.getParcelsOnhold());
                model.addAttribute("title","On-Hold Parcels are listed Below.");
            }
            return "parcels-per-status";
        }


    }
}


