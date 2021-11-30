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
public class Arrivals implements Serializable
{
    @Serial
    private static final long serialVersionUID = 3L;
    private Integer arrivalId;
    private String baggagePoint;
    private Timestamp actualArrival;
    private String status;
    private String flightId;

    @Id
    @Column(name = "ArrivalID", nullable = false)
    public Integer getArrivalId()
    {
        return arrivalId;
    }

    public void setArrivalId(Integer arrivalId)
    {
        this.arrivalId = arrivalId;
    }

    @Basic
    @Column(name = "BaggagePoint", nullable = false, length = 45)
    public String getBaggagePoint()
    {
        return baggagePoint;
    }

    public void setBaggagePoint(String baggagePoint)
    {
        this.baggagePoint = baggagePoint;
    }

    @Basic
    @Column(name = "ActualArrival", nullable = false)
    public Timestamp getActualArrival()
    {
        return actualArrival;
    }

    public void setActualArrival(Timestamp actualArrival)
    {
        this.actualArrival = actualArrival;
    }

    @Basic
    @Column(name = "Status", nullable = false, length = 60)
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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
        Arrivals arrivals = (Arrivals) o;
        return Objects.equals(arrivalId, arrivals.arrivalId) && Objects.equals(baggagePoint, arrivals.baggagePoint) && Objects.equals(actualArrival, arrivals.actualArrival) && Objects.equals(status, arrivals.status) && Objects.equals(flightId, arrivals.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(arrivalId, baggagePoint, actualArrival, status, flightId);
    }
}
