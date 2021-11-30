package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(FlightpilotsPK.class)
public class Flightpilots
{
    private Integer pilotId;
    private String flightId;

    @Id
    @Column(name = "PilotID", nullable = false)
    public Integer getPilotId()
    {
        return pilotId;
    }

    public void setPilotId(Integer pilotId)
    {
        this.pilotId = pilotId;
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flightpilots that = (Flightpilots) o;
        return Objects.equals(pilotId, that.pilotId) && Objects.equals(flightId, that.flightId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(pilotId, flightId);
    }
}
