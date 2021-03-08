package uz.pdp.ex101.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ex101.payload.RoomDto;
import uz.pdp.ex101.entity.Hotel;
import uz.pdp.ex101.entity.Room;
import uz.pdp.ex101.repository.HotelRepository;
import uz.pdp.ex101.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public Page<Room> getRoomList(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }

    @GetMapping("/{hotelId}")
    public Page<Room> getRoomByHotelId(@RequestParam int page, @PathVariable Integer hotelId) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> byHotelId = roomRepository.findByHotel_Id(hotelId);
        return byHotelId;
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            return optionalRoom.get();
        }
        return null;
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Room room=new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "Such hotel doesn't exist";
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room added!!!";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        roomRepository.deleteById(id);
        return "Room deleted";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return "Such room doesn't exist!";
        }
        Room room = optionalRoom.get();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "Such hotel doesn't exist";
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room edited!!!";


    }

}
