package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.HouseType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(generator = "house_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "house_gen", sequenceName = "house_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
//    @NotNull
//    @NotEmpty(message = "HouseType should not be empty!")
    private HouseType houseType;

    //    @NotEmpty(message = "Address should not be empty!")
    // @NotNull(message = "Address not exceed 40 characters")
    private String address;
    private int price;
    private int room;

    //    @NotEmpty(message = "Country should not be empty!")
    // @NotNull(message = "Do not exceed 30 characters")
    private String country;
    //    @NotEmpty(message = "Description should not be empty!")
    //    @NotNull(message = "Do not exceed 50 characters")
    private String description;
    private Boolean isBooked;

  //  @NotNull
    //    @NotNull(message = "Image should not be empty!")
    private String image;

    public House(HouseType houseType, String address, int price, int room, String country, String description, Boolean isBooked, String image) {
        this.houseType = houseType;
        this.address = address;
        this.price = price;
        this.room = room;
        this.country = country;
        this.description = description;
        this.isBooked = isBooked;
        this.image = image;
    }

    @OneToMany(mappedBy = "house", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Agency agency;

}
