package com.example.customres;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class CusController {
    private CustomerRepository customerRepository;

    @Autowired
    public CusController(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    @RequestMapping("/")
    public String home() {
        return "home";

    }
    @RequestMapping(value = "/customerPage", method = RequestMethod.GET)
    public String customers(Model model) {
        List<Customer> customersList = customerRepository.findAll();
        if (customersList != null) {
            model.addAttribute("customers", customersList);
        }
        return "customerPage";
    }

    @PostMapping(value = "/customerPage")
    public String addToCustomers(String firstName, Customer customer) {
        customer.setEmailAddress(firstName);
        customerRepository.save(customer);
        return "redirect:/customerPage";
    }
}/*


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CusController {
    private CustomerRepository customerRepository;
    @Autowired
    public CusController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping(value="/")
    public String readersBooks(
            String firstName,
            Model model) {
        List<Customer> customerList =customerRepository.findByFirstName(firstName);
        if (customerList != null) {
            model.addAttribute("customers",customerList);}
        return "customerList";
    }



    @PostMapping(value = "/")
    public String addToCustomerList(
            String firstName, Customer customer) {
        customer.setFirstName(firstName);
        customerRepository.save(customer);
        return "redirect:/";}
}







/*    @GetMapping(value = "/")
    public String ind(){
        return "redirect:/customerList";
    }

}*/


