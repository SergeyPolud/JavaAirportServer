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
public class Departures implements Serializable
{
    @Serial
    private static final long serialVersionUID = 2L;
    private Integer departureId;
    private String gateCode;
    private Timestamp actualDeparture;
    private String status;
    private String flightId;
    private Integer runwayId;

    @Id
    @Column(name = "DepartureID", nullable = false)
    public Integer getDepartureId()
    {
        return departureId;
    }

    public void setDepartureId(Integer departureId)
    {
        this.departureId = departureId;
    }

    @Basic
    @Column(name = "GateCode", nullable = false, length = 45)
    public String getGateCode()
    {
        return gateCode;
    }

    public void setGateCode(String gateCode)
    {
        this.gateCode = gateCode;
    }

    @Basic
    @Column(name = "ActualDeparture", nullable = false)
    public Timestamp getActualDeparture()
    {
        return actualDeparture;
    }

    public void setActualDeparture(Timestamp actualDeparture)
    {
        this.actualDeparture = actualDeparture;
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

    @Basic
    @Column(name = "RunwayID", nullable = false)
    public Integer getRunwayId()
    {
        return runwayId;
    }

    public void setRunwayId(Integer runwayId)
    {
        this.runwayId = runwayId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departures that = (Departures) o;
        return Objects.equals(departureId, that.departureId) && Objects.equals(gateCode, that.gateCode) && Objects.equals(actualDeparture, that.actualDeparture) && Objects.equals(status, that.status) && Objects.equals(flightId, that.flightId) && Objects.equals(runwayId, that.runwayId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(departureId, gateCode, actualDeparture, status, flightId, runwayId);
    }
}
