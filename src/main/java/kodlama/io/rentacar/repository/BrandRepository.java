package kodlama.io.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlama.io.rentacar.entities.Brand;

//CRUD Operations
public interface BrandRepository extends JpaRepository<Brand,Integer> {
//    custom queries
}
