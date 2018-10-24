package main.java.personal.toy.jim.cnj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/customer")
public class CustomerController {


    @GetMapping(value = "/name")
    public String getName() {
        return "Jim";
    }

    @GetMapping(value = "/exception")
    public String throwException() throws CustomerNotFoundException {
        throw new CustomerNotFoundException("this didn't work");
    }
}
