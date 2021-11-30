package Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FlightpilotsPK implements Serializable
{
    private Integer pilotId;
    private String flightId;

    @Column(name = "PilotID", nullable = false)
    @Id
    public Integer getPilotId()
    {
        return pilotId;
    }

    public void setPilotId(Integer pilotId)
    {
        this.pilotId = pilotId;
    }

    @Column(name = "FlightID", nullable = false, length = 45)
    @Id
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
        FlightpilotsPK that = (FlightpilotsPK) o;
        return Objects.equals(pilotId, that.pilotId) && Objects.equals(flightId, that.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(pilotId, flightId);
    }
}
