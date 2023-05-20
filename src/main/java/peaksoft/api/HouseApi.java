package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses/{agencyId}")
public class HouseApi {

    private final HouseService houseService;

    @Autowired
    public HouseApi(HouseService houseService) {
        this.houseService = houseService;
    }


    @GetMapping
    public String getAllHouses(@PathVariable Long agencyId, Model model){
        model.addAttribute("houses",houseService.getAllHouses(agencyId));
        model.addAttribute("agencyId",agencyId);
        return "/house/mainPage";
    }

    @GetMapping("/new")
    public String create(@PathVariable Long agencyId, Model model){
        model.addAttribute("agencyId",agencyId);
        model.addAttribute("newHouse",new House());
        return "house/newHouse";
    }

    @PostMapping("/save")
    public String save(@PathVariable Long agencyId, @ModelAttribute("newHouse") House house) {
        houseService.saveHouse(agencyId,house);
        return "redirect:/houses/" + agencyId;
    }

//    @GetMapping("/{houseId}/edit")
//    public String edit(@PathVariable Long houseId, Model model, @PathVariable Long agencyId){
//        model.addAttribute("house",houseService.getHouseById(houseId));
//        return "house/updateHouse"+agencyId;
//    }
//
//    @PostMapping("{id}/update")
//    public String updateHouse(@PathVariable Long id, House newHouse, @PathVariable Long agencyId){
//        houseService.updateHouse(id, newHouse);
//        return "redirect:houses/"+agencyId;
//    }

    @DeleteMapping("/{houseId}/delete")
    public String deleteHouse(@PathVariable("houseId") Long id, @PathVariable Long agencyId) {
        houseService.deleteHouseById(id);
        return "redirect:/houses"+agencyId;
    }
}