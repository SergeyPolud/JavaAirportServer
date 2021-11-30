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
public class Passengers implements Serializable
{
    @Serial
    private static final long serialVersionUID = 5L;
    private Integer passengerId;
    private String name;
    private String surname;
    private Timestamp birthDate;
    private String nationality;
    private Integer passportNumber;

    @Id
    @Column(name = "PassengerID", nullable = false)
    public Integer getPassengerId()
    {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId)
    {
        this.passengerId = passengerId;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 45)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "Surname", nullable = false, length = 45)
    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    @Basic
    @Column(name = "BirthDate", nullable = false)
    public Timestamp getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate)
    {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "Nationality", nullable = false, length = 45)
    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "PassportNumber", nullable = false)
    public Integer getPassportNumber()
    {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber)
    {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passengers that = (Passengers) o;
        return Objects.equals(passengerId, that.passengerId) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthDate, that.birthDate) && Objects.equals(nationality, that.nationality) && Objects.equals(passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(passengerId, name, surname, birthDate, nationality, passportNumber);
    }
}
