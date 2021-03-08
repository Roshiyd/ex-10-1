package uz.pdp.ex101.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ex101.entity.Hotel;
import uz.pdp.ex101.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findByHotel_Id(Integer hotel_id);
}
