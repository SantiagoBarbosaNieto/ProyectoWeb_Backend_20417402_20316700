package com.backend.proyectoweb.proyectoweb_backend;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.model.Product;
import com.backend.proyectoweb.proyectoweb_backend.model.PurchaseOrder;
import com.backend.proyectoweb.proyectoweb_backend.model.ShoppingCart;
import com.backend.proyectoweb.proyectoweb_backend.model.Role;
import com.backend.proyectoweb.proyectoweb_backend.model.UserSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.ProductRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.OrdenCompraRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.RoleRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.ShoppingCartRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadData {
    
    @Bean
    CommandLineRunner initAllDB(UsusarioRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder, OrdenCompraRepository purchaseRepository, ShoppingCartRepository cartRepository){
        return args ->{

            roleRepository.deleteAll();
            productRepository.deleteAll();
            purchaseRepository.deleteAll();
            cartRepository.deleteAll();
            userRepository.deleteAll();


            Role adminRole = new Role();
			adminRole.setName("ADMIN");
			roleRepository.save(adminRole);

			Role customerRole = new Role();
			customerRole.setName("CUSTOMER");
			roleRepository.save(customerRole);


            UserSys customer = new UserSys("TestAdmin", "Test", null, "testAdmin@test.com", passwordEncoder.encode("12345"), null, null, adminRole);
            userRepository.save(customer);

            customer = new UserSys("TestCustomer", "Test", null, "testCustomer@test.com", passwordEncoder.encode("12345"), new ArrayList<>(), new ArrayList<>(), customerRole);

            List<Product> products = new ArrayList<>();
            products.add(new Product("Elden Ring", "Souls-like game", "FromSoftware", 200000d, "assets/elden-ring.jpg"));
            products.add(new Product("Fortnite", "TPS gamer", "Epic Games", 10000d, "assets/fortnite.jfif"));
            productRepository.saveAll(products);

            List<Product> compras = new ArrayList<>();
            List<Product> compras2 = new ArrayList<>();

            for (Product product : productRepository.findAll()) {

                compras.add(product);
                compras2.add(product);

            }

            ShoppingCart ne = new ShoppingCart(customer, compras);

            ShoppingCart ne2 = new ShoppingCart(customer, compras2);

            ShoppingCart ne3 = new ShoppingCart(customer, compras2);

            customer.getCarts().add(ne);
            customer.getCarts().add(ne2);
            customer.getCarts().add(ne3);
            
            


            PurchaseOrder pur = new PurchaseOrder(customer, 233456d, ne, null);
            PurchaseOrder pur2 = new PurchaseOrder(customer, 233456d, ne2, null);

            customer.getOrders().add(pur);
            customer.getOrders().add(pur2);

            userRepository.save(customer);
            cartRepository.saveAll(customer.getCarts());
            purchaseRepository.saveAll(customer.getOrders());

            
        };

    }

}
    