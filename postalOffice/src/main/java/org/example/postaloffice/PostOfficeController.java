package org.example.postaloffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/office")
public class PostOfficeController {
    @Autowired
    PostOfficeServices postOfficeServices;
    @Autowired
    ParcelServices parcelServices;


    @RequestMapping("/home")
    public String showhome(Model model){
        List<Parcel> parcel = parcelServices.findRecentParcels();
        model.addAttribute("recentParcels", parcel);
        return "index";
    }




    @RequestMapping("/save")
    public String addOffice(@ModelAttribute("office") PostOffice postOffice){
        postOfficeServices.addOffice(postOffice);
        return "redirect:/office/list";
    }
    @RequestMapping("/addNew")
    public String view(Model model) {
        PostOffice postOffice = new PostOffice();
        model.addAttribute("office", postOffice);
        return "office_save";
    }

    @RequestMapping("/list")
    public String home(Model model){
        List<PostOffice> postOffices = postOfficeServices.getAllOffice();
        model.addAttribute("office",postOffices );
        return "office_tables";
    }

    @RequestMapping("/edit/{officeId}")
    public String edit(Model model,@PathVariable("officeId") int id){
        PostOffice postOffice = postOfficeServices.getOfficeById(id);
        model.addAttribute("office",postOffice);
        return "office_edit";
    }
    @RequestMapping("/delete/{officeId}")
    public String delete(@PathVariable ("officeId") int id, RedirectAttributes redirectAttributes){
        try{
            postOfficeServices.deleteOfficeById(id);
            redirectAttributes.addFlashAttribute("message","Office deleted successfully.");
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Cannot Delete a Office with Parcels in it.");
        }
        return "redirect:/office/list";
    }
}


