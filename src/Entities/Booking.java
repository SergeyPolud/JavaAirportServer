package Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Booking implements Serializable
{
    @Serial
    private static final long serialVersionUID = 6L;
    private Integer bookingId;
    private BigDecimal price;
    private String seatNumber;
    private String flightId;

    @Id
    @Column(name = "BookingID", nullable = false)
    public Integer getBookingId()
    {
        return bookingId;
    }

    public void setBookingId(Integer bookingId)
    {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "Price", nullable = false, precision = 2)
    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @Basic
    @Column(name = "SeatNumber", nullable = false, length = 45)
    public String getSeatNumber()
    {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber)
    {
        this.seatNumber = seatNumber;
    }

    @Basic
    @Column(name = "FlightID", nullable = false, length = 45)
    public String getFlightId()
    {
        return flightId;
    }

    public void setFlightId(String flightId)
    {
        this.flightId = flightId;
    }



    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) && Objects.equals(price, booking.price) && Objects.equals(seatNumber, booking.seatNumber) && Objects.equals(flightId, booking.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(bookingId, price, seatNumber, flightId);
    }
}
