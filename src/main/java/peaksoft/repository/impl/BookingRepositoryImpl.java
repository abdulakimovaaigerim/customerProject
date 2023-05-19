package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingRepository implements peaksoft.repository.BookingRepository {

    private final EntityManager entityManager;
    @Override
    public Booking getBookingById(Long bookingId) {
        return entityManager.find(Booking.class, bookingId);
    }

    @Override
    public Customer getCustomerByBookingId(Long bookingId) {
        return entityManager.createQuery("select c from Customer c join c.bookings b where b.id=:bookingId", Customer.class)
                .setParameter("bookingId",bookingId)
                .getSingleResult();
    }

    @Override
    public House getHouseByBookingId(Long bookingId) {
        return entityManager.createQuery("select h from House h join h.bookings b where b.id=: bookinId", House.class)
                .setParameter("bookingId",bookingId)
                .getSingleResult();
    }

}
