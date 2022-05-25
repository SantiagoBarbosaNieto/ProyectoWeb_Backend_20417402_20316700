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

            customer = new UserSys("TestCustomer", "Test", null, "testCustomer@test.com", passwordEncoder.encode("67890"), new ArrayList<>(), new ArrayList<>(), customerRole);

            List<Product> products = new ArrayList<>();
            products.add(new Product("Cerveza Poker", "La cerveza clasica para el parche", 3000d, "assets/img/poker.png"));

            products.add(new Product("Cerveza Aguila", "La consentida de colombia", 3000d, "assets/img/aguila.png"));

            products.add(new Product("Cerveza Corona", "Cerveza mexicana de gran calidad y sabor", 5000d, "assets/img/corona.png"));

            products.add(new Product("Nvidia RTX 3080", "Tarjeta grafica de alta gama, para obtener el meojor desempeño que el dinero puede pagar", 3000000d, "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere/rtx-3080/images/design/geforce-rtx-3080-4-960.jpg"));

            products.add(new Product("Caja de colores prisma color", "Para ti que te  gusta el dibujo y el arte, te traemos la caja mas completa de colores para que tu pasion no se vea limitada por los colores", 130000d, "https://m.media-amazon.com/images/I/811Y0d3mJFL._AC_SY355_.jpg"));

            products.add(new Product("Figura colecionable del Hombre  Araña", "Para que completes la colección o para que decores tu sitio favorito", 55000d, "https://http2.mlstatic.com/D_NQ_NP_823650-MCO32379227504_092019-V.jpg"));

            products.add(new Product("Elantris", "La primera obra publicada del maestro de la fantasia moderna, esta aventura te llevara a decubrir los secretos de la caida en desgracia de la mitica ciudad de elentris", 67000d, "https://juanjelopezponeletras.files.wordpress.com/2019/08/elantris.jpg"));
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
    