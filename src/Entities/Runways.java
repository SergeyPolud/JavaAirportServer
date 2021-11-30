package Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Runways
{
    private Integer runwayId;
    private String runwayCategory;

    @Id
    @Column(name = "RunwayID", nullable = false)
    public Integer getRunwayId()
    {
        return runwayId;
    }

    public void setRunwayId(Integer runwayId)
    {
        this.runwayId = runwayId;
    }

    @Basic
    @Column(name = "RunwayCategory", nullable = false, length = 45)
    public String getRunwayCategory()
    {
        return runwayCategory;
    }

    public void setRunwayCategory(String runwayCategory)
    {
        this.runwayCategory = runwayCategory;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Runways runways = (Runways) o;
        return Objects.equals(runwayId, runways.runwayId) && Objects.equals(runwayCategory, runways.runwayCategory);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(runwayId, runwayCategory);
    }
}
