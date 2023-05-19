package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses")
public class HouseApi {

    private final HouseService houseService;

    @Autowired
    public HouseApi(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/{id}")
    public String getAllHouses(Model model, @PathVariable Long id){
        model.addAttribute("allHouses",houseService.getAllHouses(id));
        return "house/mainPage"+id;
    }

    @GetMapping("/new")
    public String createHouse(Model model){
        model.addAttribute("newHouse",new House());
        return "house/newHouse";

    }

    @PostMapping("/{id}/save")
    public String saveHouse(@PathVariable("id") Long id, @ModelAttribute("newHouse") House house){
        houseService.saveHouse(id, house);
        return "redirect:/houses"+id;

    }

    @GetMapping("/{id}/edit")
    public String editHouse(Model model, @PathVariable("id") Long id){
        model.addAttribute("oldHouse", houseService.getHouseById(id));
        return "house/updateHouse";

    }

    @PostMapping("/{id}/update")
    public String updateHouse(@PathVariable("id") Long id, @ModelAttribute("house") House newHouse){
        houseService.updateHouse(id, newHouse);
        return "redirect:/houses";

    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam("houseId") Long id){
        houseService.deleteHouseById(id);
        return "redirect:houses";
    }
}
