package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Customer;
import peaksoft.service.AgencyService;
import peaksoft.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;
    private final AgencyService agencyService;

    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/mainPage";
    }


    @GetMapping("/{customerId}/edit")
    public String get(@PathVariable("customerId") Long id,
                      Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/edit";
    }

    @PostMapping("{customerId}/update")
    public String update(@ModelAttribute("customer") Customer customer,
                         @PathVariable("customerId") Long id) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }


    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }


    @GetMapping("/new")
    public String createCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "customer/newCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("newCustomer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("custom", customerService.getCustomerById(id));
        return "customer/customerById";
    }

    @GetMapping("/{customerId}/assignPage")
    public String assignPage(@PathVariable Long customerId,
                             Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("agencyId", agencyService.getAllAgencies());
        return "customer/assignPage";
    }

    @PostMapping("/{customerId}/assign")
    public String assign(@PathVariable Long customerId,
                         @RequestParam List<Long> agencyIdes) {
        customerService.assignCustomerToAgency(customerId, agencyIdes);
        return "redirect:/customers";
    }

}