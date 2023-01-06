package vttp2022.paf.assessment.eshop;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;

@SpringBootApplication
// public class EshopApplication implements CommandLineRunner{
public class EshopApplication {

	// @Autowired
	// private CustomerRepository customerRepository; 

	// @Override
	// public void run(String... args) {
	// 	Optional<Customer> opt = customerRepository.findCustomerByName("jack"); 

	// 	if(opt.isEmpty()) {
	// 		System.out.println("OPT IS EMPTY");
	// 	}
	// 	else {
	// 		Customer customer = opt.get(); 
	// 		System.out.println("CUSTOMER >>>> " + customer.toString());
	// 	}
	// }

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

}
