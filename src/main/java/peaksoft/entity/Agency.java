package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(generator = "agency_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;

  //  @NotNull
//    @NotEmpty(message = "Name should not be empty!")
    private String name;
   // @NotNull(message = "Do not exceed 30 characters")
    private String country;
    //    @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
    private String phoneNumber;
    //  @Column(unique = true)
//    @NotEmpty(message = "Email should not be empty!")
//    @Email(message = "Please provide a valid email address!")
    private String email;
    //    @NotNull(message = "Image should not be empty!")
    private String image;

    public Agency(String name, String country, String phoneNumber, String email, String image) {
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
    }

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "agency", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private List<House> houses = new ArrayList<>();

    @OneToMany(mappedBy = "agency", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Booking> bookings = new ArrayList<>();

}
