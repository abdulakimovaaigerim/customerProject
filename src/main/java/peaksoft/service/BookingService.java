package peaksoft.service;

import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;

public interface BookingService {

    Booking getBookingById(Long bookingId);

    Customer getCustomerByBookingId(Long bookingId);

    House getHouseByBookingId(Long bookingId);
}
