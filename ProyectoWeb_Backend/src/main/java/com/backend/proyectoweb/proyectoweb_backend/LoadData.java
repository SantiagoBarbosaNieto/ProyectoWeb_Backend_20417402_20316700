package com.backend.proyectoweb.proyectoweb_backend;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.model.Product;
import com.backend.proyectoweb.proyectoweb_backend.model.PurchaseOrder;
import com.backend.proyectoweb.proyectoweb_backend.model.Role;
import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.ProductRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.PurchaseOrderRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.RoleRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadData {
    
    @Bean
    CommandLineRunner initAllDB(UsusarioRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder, PurchaseOrderRepository purchaseRepository){
        return args ->{

            roleRepository.deleteAll();
            productRepository.deleteAll();
            purchaseRepository.deleteAll();
            userRepository.deleteAll();


            Role adminRole = new Role();
			adminRole.setName("ADMIN");
			roleRepository.save(adminRole);

			Role customerRole = new Role();
			customerRole.setName("CUSTOMER");
			roleRepository.save(customerRole);


            UserSys customer = new UserSys("TestAdmin", "Test", null, "testAdmin@test.com", passwordEncoder.encode("12345"), null, adminRole);
            userRepository.save(customer);

            customer = new UserSys("TestCustomer", "Test", null, "testCustomer@test.com", passwordEncoder.encode("12345"), new ArrayList<>(), customerRole);

            List<Product> products = new ArrayList<>();
            products.add(new Product("Elden Ring", "Souls-like game", "FromSoftware", 200000d, "assets/elden-ring.jpg"));
            products.add(new Product("Fortnite", "TPS gamer", "Epic Games", 10000d, "assets/fortnite.jfif"));
            productRepository.saveAll(products);


            PurchaseOrder pur = new PurchaseOrder(customer, 233456d, products.get(0), null);
            PurchaseOrder pur2 = new PurchaseOrder(customer, 233456d, products.get(1), null);

            customer.getOrders().add(pur);
            customer.getOrders().add(pur2);

            userRepository.save(customer);
            purchaseRepository.saveAll(customer.getOrders());

            
        };

    }

}
    