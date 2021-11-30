package Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Aircrafts
{
    private Integer aircraftId;
    private String type;
    private String regNumber;
    private String flightId;

    @Id
    @Column(name = "AircraftID", nullable = false)
    public Integer getAircraftId()
    {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId)
    {
        this.aircraftId = aircraftId;
    }

    @Basic
    @Column(name = "Type", nullable = false, length = 45)
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Basic
    @Column(name = "RegNumber", nullable = false, length = 45)
    public String getRegNumber()
    {
        return regNumber;
    }

    public void setRegNumber(String regNumber)
    {
        this.regNumber = regNumber;
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
        Aircrafts aircrafts = (Aircrafts) o;
        return Objects.equals(aircraftId, aircrafts.aircraftId) && Objects.equals(type, aircrafts.type) && Objects.equals(regNumber, aircrafts.regNumber) && Objects.equals(flightId, aircrafts.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(aircraftId, type, regNumber, flightId);
    }
}
