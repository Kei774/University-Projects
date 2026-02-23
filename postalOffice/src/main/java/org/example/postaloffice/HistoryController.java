package org.example.postaloffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    HistoryServises historyServises;
    ParcelServices parcelServices;

    @Autowired
    public HistoryController(ParcelServices parcelServices, HistoryServises historyService) {
        this.parcelServices = parcelServices;
        this.historyServises = historyService;
    }



    @PostMapping("/save")
    public String saveParcel(@ModelAttribute("parcel") Parcel parcel) {
        Parcel existingParcel = parcelServices.getParcelById(parcel.getParcelId());

        // Check if location or status changed
        if (existingParcel != null && (
                !existingParcel.getAssignedPostOffice().equals(parcel.getAssignedPostOffice()) ||
                        !existingParcel.getStatus().equals(parcel.getStatus())
        )) {

            History history = new History();
            history.setParcel(parcel);
            history.setLocation(parcel.getAssignedPostOffice());
            history.setStatus(parcel.getStatus());
            history.setUpdatedAt(LocalDateTime.now());


            historyServises.addHistory(history);
            parcelServices.addParcel(parcel);
            return "redirect:/parcel/list";
        }

        parcelServices.addParcel(parcel);
        return "redirect:/parcel/list";
    }

    // Show all histories
    @GetMapping("/list")
    public String showAllHistory(Model model) {
        List<History> historyList = historyServises.getAllHistory();
        model.addAttribute("historyList", historyList);
        return "history_list"; // Thymeleaf template for displaying all history
    }

    // Show history for a specific parcel
    @GetMapping("/parcel/{id}")
    public String showHistoryByParcel(@PathVariable("id") int id, Model model) {
        Parcel parcel = parcelServices.getParcelById(id);
        List<History> historyList = historyServises.getHistoryByParcelId(id);

        model.addAttribute("parcel", parcel);
        model.addAttribute("historyList", historyList);

        return "history_by_parcel"; // Another Thymeleaf page if you want
    }

    @RequestMapping("/remarks/{id}")
    public String edit(Model model,@PathVariable("id") int id){
        History history = historyServises.getHistoryById(id);
        model.addAttribute("history",history);
        return "edit_remarks";
    }
}
