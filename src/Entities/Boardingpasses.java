package Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Boardingpasses implements Serializable
{
    @Serial
    private static final long serialVersionUID = 7L;
    private Integer boardingPassId;
    private String seatNumber;
    private String gateCode;
    private Integer passengerId;

    @Id
    @Column(name = "BoardingPassID", nullable = false)
    public Integer getBoardingPassId()
    {
        return boardingPassId;
    }

    public void setBoardingPassId(Integer boardingPassId)
    {
        this.boardingPassId = boardingPassId;
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
    @Column(name = "PassengerID", nullable = false)
    public Integer getPassengerId()
    {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId)
    {
        this.passengerId = passengerId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boardingpasses that = (Boardingpasses) o;
        return Objects.equals(boardingPassId, that.boardingPassId) && Objects.equals(seatNumber, that.seatNumber) && Objects.equals(gateCode, that.gateCode) && Objects.equals(passengerId, that.passengerId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(boardingPassId, seatNumber, gateCode, passengerId);
    }
}
