package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(generator = "customer_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    //    @NotNull
//    @NotEmpty(message = "Name should not be empty!")
    private String name;

    //    @NotNull
//    @NotEmpty(message = "surName should not be empty!")
    private String surname;

    //    @NotEmpty(message = "int should not be empty!")

    //  @Column(unique = true)
//    @NotEmpty(message = "Email should not be empty!")
//    @Email(message = "Please provide a valid email address!")
    private String email;

//    @Enumerated
//    @NotNull
//    @NotEmpty(message = "Gender should not be empty!")
    private Gender gender;
//    @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
    private String phoneNumber;

//    @NotNull(message = "Date should not be empty!")
//    @DateTimeFormat(pattern = "MM/DD/YYYY")
//    @Future(message = "Date must be future time!")
    private LocalDate dateOfBirth;
    private String image;



    public Customer(String name, String surname,  String email, Gender gender, String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToMany(mappedBy = "customers", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private List<Agency> agencies = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private List<Booking> bookings = new ArrayList<>();

}
