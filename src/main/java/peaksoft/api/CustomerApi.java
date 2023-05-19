package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.entity.Customer;
import peaksoft.service.CustomerService;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;


    @GetMapping
    public String gatAll(Model model) {
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        return "customer/getAll";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "customer/savePage";
    }

    @PostMapping("/savePage")
    public String save(@ModelAttribute("newCustomer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/agencies";
    }
}
