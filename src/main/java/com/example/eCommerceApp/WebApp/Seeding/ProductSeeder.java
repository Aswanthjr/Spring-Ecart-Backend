package com.example.eCommerceApp.WebApp.Seeding;

import com.example.eCommerceApp.WebApp.DTO.ProductsDTO;
import com.example.eCommerceApp.WebApp.Entities.Products;
import com.example.eCommerceApp.WebApp.Repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepo productRepo;

    @Override
    public void run(String... args) throws Exception {
        if(productRepo.count() == 0){
            List<Products> products = List.of(
                    // ================= Smartphones =================
                    new Products(null, "Apple iPhone 14", 120000.00, "A15 Bionic chip", "Smartphones", 4.8, "Aswanthdev", 10, 320, List.of("/products/Apple13.jpg")),
                    new Products(null, "Samsung Galaxy S23", 110000.00, "Flagship Android phone", "Smartphones", 4.7, "Aswanthdev", 15, 280, List.of("/products/samsungs23.jpg")),
                    new Products(null, "OnePlus 11", 65000.00, "Fast performance phone", "Smartphones", 4.5, "Aswanthdev", 20, 210, List.of("/products/oneplus11.jpg")),
                    new Products(null, "Xiaomi 13 Pro", 70000.00, "Value flagship device", "Smartphones", 4.4, "Aswanthdev", 25, 190, List.of("/products/Xiaomi13Pro.jpg")),
                    new Products(null, "Google Pixel 7", 85000.00, "Best camera smartphone", "Smartphones", 4.6, "Aswanthdev", 12, 240, List.of("/products/GooglePixel7.jpg")),

                    // ================= Laptops =================
                    new Products(null, "MacBook Air M2", 115000.00, "Apple silicon efficiency", "Laptops", 4.8, "Aswanthdev", 8, 150, List.of("/products/MacBookAirM2.webp")),
                    new Products(null, "Dell XPuS 13", 105000.00, "Premium Windows ultrabook", "Laptops", 4.6, "Aswanthdev", 10, 130, List.of("/products/DellXPS13.jpeg")),
                    new Products(null, "HP Pavilion 15", 60000.00, "Balanced everyday laptop", "Laptops", 4.3, "Aswanthdev", 18, 95, List.of("/products/HPPavilion15.jpeg")),
                    new Products(null, "Lenovo IdeaPad Slim 5", 58000.00, "Lightweight productivity laptop", "Laptops", 4.2, "Aswanthdev", 22, 80, List.of("/products/LenovoIdeaPadSlim5.jpeg")),
                    new Products(null, "ASUS ROG Zephyrus G14", 135000.00, "Gaming powerhouse laptop", "Laptops", 4.7, "Aswanthdev", 6, 175, List.of("/products/ASUSROGZephyrusG14.png")),

                    // ================= Tablets =================
                    new Products(null, "iPad Air 5", 68000.00, "Powerful Apple tablet", "Tablets", 4.8, "Aswanthdev", 14, 210, List.of("/products/iPadAir5.jpg")),
                    new Products(null, "Samsung Galaxy Tab S8", 72000.00, "Android productivity tablet", "Tablets", 4.6, "Aswanthdev", 12, 160, List.of("/products/SamsungGalaxyTabS8.jpeg")),
                    new Products(null, "Lenovo Tab P12", 40000.00, "Entertainment + study tablet", "Tablets", 4.3, "Aswanthdev", 20, 110, List.of("/products/LenovoTabP12.jpg")),
                    new Products(null, "Realme Pad X", 30000.00, "Budget Android tablet", "Tablets", 4.1, "Aswanthdev", 30, 90, List.of("/products/RealmePadX.jpg")),
                    new Products(null, "Xiaomi Pad 6", 35000.00, "Value-for-money tablet", "Tablets", 4.4, "Aswanthdev", 25, 120, List.of("/products/XiaomiPad6.jpeg")),

                    // ================= Accessories =================
                    new Products(null, "AirPods Pro 2", 22000.00, "Noise cancelling earbuds", "Accessories", 4.7, "Aswanthdev", 40, 500, List.of("/products/AirPodsPro2.jpeg")),
                    new Products(null, "Samsung Galaxy Buds 2", 12000.00, "Compact wireless earbuds", "Accessories", 4.4, "Aswanthdev", 50, 300, List.of("/products/SamsungGalaxyBuds2.jpeg")),
                    new Products(null, "Apple Watch Series 9", 45000.00, "Smart fitness watch", "Accessories", 4.8, "Aswanthdev", 18, 260, List.of("/products/AppleWatchSeries9.webp")),
                    new Products(null, "Boat Airdopes 141", 1500.00, "Budget earbuds", "Accessories", 4.2, "Aswanthdev", 100, 900, List.of("/products/BoatAirdopes141.webp")),
                    new Products(null, "Logitech MX Master 3S", 8500.00, "Premium productivity mouse", "Accessories", 4.7, "Aswanthdev", 35, 400, List.of("/products/LogitechMXMaster3S.webp"))
            );

            productRepo.saveAll(products);
            System.out.println("Seeded Demo Products");
        }
        else {
            System.out.println("Products Already Exits! Skip Seeding");
        }
    }
}
