package Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Flights implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    private String flightId;
    private Timestamp scheduledDeparture;
    private Timestamp scheduledArrival;
    private String departureAirportIata;
    private String arrivalAirportIata;

    @Id
    @Column(name = "FlightID", nullable = false, length = 45)
    public String getFlightId()
    {
        return flightId;
    }

    public void setFlightId(String flightId)
    {
        this.flightId = flightId;
    }

    @Basic
    @Column(name = "ScheduledDeparture", nullable = false)
    public Timestamp getScheduledDeparture()
    {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(Timestamp scheduledDeparture)
    {
        this.scheduledDeparture = scheduledDeparture;
    }

    @Basic
    @Column(name = "ScheduledArrival", nullable = false)
    public Timestamp getScheduledArrival()
    {
        return scheduledArrival;
    }

    public void setScheduledArrival(Timestamp scheduledArrival)
    {
        this.scheduledArrival = scheduledArrival;
    }

    @Basic
    @Column(name = "DepartureAirportIATA", nullable = false, length = 45)
    public String getDepartureAirportIata()
    {
        return departureAirportIata;
    }

    public void setDepartureAirportIata(String departureAirportIata)
    {
        this.departureAirportIata = departureAirportIata;
    }

    @Basic
    @Column(name = "ArrivalAirportIATA", nullable = false, length = 45)
    public String getArrivalAirportIata()
    {
        return arrivalAirportIata;
    }

    public void setArrivalAirportIata(String arrivalAirportIata)
    {
        this.arrivalAirportIata = arrivalAirportIata;
    }



    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flights = (Flights) o;
        return Objects.equals(flightId, flights.flightId) && Objects.equals(scheduledDeparture, flights.scheduledDeparture) && Objects.equals(scheduledArrival, flights.scheduledArrival) && Objects.equals(departureAirportIata, flights.departureAirportIata) && Objects.equals(arrivalAirportIata, flights.arrivalAirportIata);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(flightId, scheduledDeparture, scheduledArrival, departureAirportIata, arrivalAirportIata);
    }
}
