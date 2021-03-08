package uz.pdp.ex101.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;

    private Integer floor;

    private String size;

    private Integer hotelId;
}
