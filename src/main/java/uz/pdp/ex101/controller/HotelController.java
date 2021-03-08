package uz.pdp.ex101.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ex101.entity.Hotel;
import uz.pdp.ex101.entity.Room;
import uz.pdp.ex101.repository.HotelRepository;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;


    @GetMapping
    public Page<Hotel> getHotels(@RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        return hotelPage;
    }

    @GetMapping(value = "/{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            return optionalHotel.get();
        }
        return null;
    }


    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        try {
            hotelRepository.save(hotel);
            return "Hotel added!!! ";
        }
        catch (Exception ex){
            return "Error! Check your method";
        }

    }

    @DeleteMapping(value = "/{id}")
    public String deleteHotel(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return "Such hotel doesn't exist";
        }
        Hotel current =new Hotel();
        current.setName(hotel.getName());
        return "Hotel edited!!!";
    }
}
