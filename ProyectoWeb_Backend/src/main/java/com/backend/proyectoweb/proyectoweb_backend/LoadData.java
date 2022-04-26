package com.backend.proyectoweb.proyectoweb_backend;

import java.util.ArrayList;
import java.util.List;

import com.backend.proyectoweb.proyectoweb_backend.model.Producto;
import com.backend.proyectoweb.proyectoweb_backend.model.OrdenCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.CarritoCompra;
import com.backend.proyectoweb.proyectoweb_backend.model.Roles;
import com.backend.proyectoweb.proyectoweb_backend.model.UsuarioSys;
import com.backend.proyectoweb.proyectoweb_backend.repository.ProductoRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.OrdenCompraRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.RolesRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.CarritoCompraRepository;
import com.backend.proyectoweb.proyectoweb_backend.repository.UsusarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadData {
    
    @Bean
    CommandLineRunner initAllDB(UsusarioRepository ususarioRepository, RolesRepository rolesRepository, ProductoRepository productoRepository,  PasswordEncoder passwordEncoder, OrdenCompraRepository purchaseRepository, CarritoCompraRepository cartRepository){
        return args ->{

            rolesRepository.deleteAll();
            productoRepository.deleteAll();
            purchaseRepository.deleteAll();
            cartRepository.deleteAll();
            ususarioRepository.deleteAll();


            Roles adminRole = new Roles();
			adminRole.setName("ADMIN");
			rolesRepository.save(adminRole);

			Roles customerRole = new Roles();
			customerRole.setName("CUSTOMER");
			rolesRepository.save(customerRole);


            UsuarioSys customer = new UsuarioSys("TestAdmin", "Test", null, "testAdmin@test.com", passwordEncoder.encode("12345"), null, null, adminRole);
            ususarioRepository.save(customer);

            customer = new UsuarioSys("TestCustomer", "Test", null, "testCustomer@test.com", passwordEncoder.encode("67890"), new ArrayList<>(), new ArrayList<>(), customerRole);

            List<Producto> productos = new ArrayList<>();
            productos.add(new Producto("Cerveza Poker", "La cerveza clasica para el parche", 3000d, "assets/img/poker.png"));

            productos.add(new Producto("Cerveza Aguila", "La consentida de colombia", 3000d, "assets/img/aguila.png"));

            productos.add(new Producto("Cerveza Corona", "Cerveza mexicana de gran calidad y sabor", 5000d, "assets/img/corona.png"));

            productos.add(new Producto("Nvidia RTX 3080", "Tarjeta grafica de alta gama, para obtener el meojor desempeño que el dinero puede pagar", 3000000d, "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere/rtx-3080/images/design/geforce-rtx-3080-4-960.jpg"));

            productos.add(new Producto("Caja de colores prisma color", "Para ti que te  gusta el dibujo y el arte, te traemos la caja mas completa de colores para que tu pasion no se vea limitada por los colores", 130000d, "https://m.media-amazon.com/images/I/811Y0d3mJFL._AC_SY355_.jpg"));

            productos.add(new Producto("Figura colecionable del Hombre  Araña", "Para que completes la colección o para que decores tu sitio favorito", 55000d, "https://http2.mlstatic.com/D_NQ_NP_823650-MCO32379227504_092019-V.jpg"));

            productos.add(new Producto("Elantris", "La primera obra publicada del maestro de la fantasia moderna, esta aventura te llevara a decubrir los secretos de la caida en desgracia de la mitica ciudad de elentris", 67000d, "https://juanjelopezponeletras.files.wordpress.com/2019/08/elantris.jpg"));
            productoRepository.saveAll(productos);

            List<Producto> compras = new ArrayList<>();
            List<Producto> compras2 = new ArrayList<>();

            for (Producto producto : productoRepository.findAll()) {

                compras.add(producto);
                compras2.add(producto);

            }

            CarritoCompra  ne = new CarritoCompra(customer, compras);

            CarritoCompra  ne2 = new CarritoCompra(customer, compras2);

            CarritoCompra ne3 = new CarritoCompra(customer, compras2);

            customer.getCarts().add(ne);
            customer.getCarts().add(ne2);
            customer.getCarts().add(ne3);
            
            


            OrdenCompra pur = new OrdenCompra(customer, 233456d, ne, null);
            OrdenCompra pur2 = new OrdenCompra(customer, 233456d, ne2, null);

            customer.getOrders().add(pur);
            customer.getOrders().add(pur2);

            ususarioRepository.save(customer);
            cartRepository.saveAll(customer.getCarts());
            purchaseRepository.saveAll(customer.getOrders());

            
        };

    }

}
    