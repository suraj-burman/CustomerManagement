package com.example.jspdemo.controller;

import com.example.jspdemo.model.Customer;
import com.example.jspdemo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    @Autowired
    CompanyService customerService;

    @GetMapping({"/", "/viewCustomerList"})
    public String viewCustomerList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("customerList", customerService.getAllCustomer());
        model.addAttribute("message", message);

        return "ViewCustomerList";
    }

    @GetMapping("/addCustomer")
    public String addCustomer(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("message", message);

        return "AddCustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        if (customerService.saveOrUpdateCustomer(customer)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewCustomerList";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addCustomer";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));

        return "EditCustomer";
    }

    @PostMapping("/editSaveCustomer")
    public String editSaveCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        if (customerService.saveOrUpdateCustomer(customer)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/viewCustomerList";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editCustomer/" + customer.getId();
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (customerService.deleteCustomer(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Delete Failure");
        }

        return "redirect:/viewCustomerList";
    }

}
