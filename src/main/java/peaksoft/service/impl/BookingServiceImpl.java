package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.repository.BookingRepository;
import peaksoft.service.BookingService;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.getBookingById(bookingId);
    }

    @Override
    public Customer getCustomerByBookingId(Long bookingId) {
        return bookingRepository.getCustomerByBookingId(bookingId);
    }

    @Override
    public House getHouseByBookingId(Long bookingId) {
        return bookingRepository.getHouseByBookingId(bookingId);
    }
}
