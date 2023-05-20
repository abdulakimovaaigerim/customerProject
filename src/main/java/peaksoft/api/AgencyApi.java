package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.service.AgencyService;


@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {

    private final AgencyService agencyService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("allAgencies", agencyService.getAllAgencies());
        return "agency/mainPage";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "agency/savePage";
    }

    @PostMapping("/savePage")
    public String save(@ModelAttribute("newAgency") Agency agency) {
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}/edit")
    String getUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("newAgency", agencyService.getAgencyById(id));
        return "agency/updateAgency";
    }

    @PostMapping("/{id}/update")
    String updateHospital(@PathVariable("id") Long id, @ModelAttribute("newAgency") Agency agency) {
        agencyService.updateAgency(id, agency);
        return "redirect:/agencies";
    }

    @PostMapping("/{id}/delete")
    String deleteById(@PathVariable("id") Long id) {
        agencyService.deleteAgencyById(id);
        return "redirect:/agencies";
    }
}