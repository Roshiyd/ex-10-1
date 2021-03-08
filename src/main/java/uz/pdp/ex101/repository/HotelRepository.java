package uz.pdp.ex101.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ex101.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

}
