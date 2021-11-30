package users;

import Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ClientUser extends User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 420L;
    public int userNumber;
    public ClientUser(String username, String password)
    {
        super.username = username;
        super.password = password;
        userNumber = new Random().nextInt(99999);
    }

    public void ProcessClientCommands(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        while(true)
        {
            var message = (String) inputStream.readObject();
            switch (message)
            {
                case "GET_DEPARTURES" -> GetDepartures(outputStream, factory);
                case "GET_ARRIVALS" -> GetArrivals(outputStream, factory);
                case "INSERT_PASSENGER" -> InsertNewPassenger(inputStream, outputStream, factory);
                case "UPDATE_PAS" -> UpdatePassenger(inputStream, outputStream, factory);
                case "GET_ALL_FLIGHTS" -> GetAllFlights(outputStream, factory);
                case "BUY_SEAT" -> BuySeat(inputStream, factory);
                case "REGISTER_PASSENGER" -> RegisterPassenger(inputStream, outputStream, factory);
            }
        }

    }

    private void RegisterPassenger(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var passport = (Integer) inputStream.readObject();
        var passenger = (Passengers) inputStream.readObject();
        var booking = (Booking) inputStream.readObject();
        if(Objects.equals(passenger.getPassportNumber(), passport))
        {
            var boardingPass = CreateBoardingPass(passenger, booking);
            var session = factory.openSession();
            session.beginTransaction();
            session.save(boardingPass);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(boardingPass);
        }
    }

    private Boardingpasses CreateBoardingPass(Passengers passenger, Booking booking)
    {
        StringBuilder gatecode = new StringBuilder();
        Random rd = new Random();
        var num = rd.nextInt(15);
        var c = (char)(rd.nextInt(6) + 'a');
        gatecode.append(num).append(" ").append(c);
        var boardingPass = new Boardingpasses();
        boardingPass.setSeatNumber(booking.getSeatNumber());
        boardingPass.setGateCode(gatecode.toString().toUpperCase());
        boardingPass.setPassengerId(passenger.getPassengerId());
        return boardingPass;
    }

    private void BuySeat(ObjectInputStream inputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var bought = (Booking) inputStream.readObject();
        var session = factory.openSession();
        session.beginTransaction();
        session.update(bought);
        session.getTransaction().commit();
        session.close();
    }

    private void GetAllFlights(ObjectOutputStream outputStream, SessionFactory factory) throws IOException
    {
        var session = factory.openSession();
        session.beginTransaction();
        var query = session.createQuery("from Flights", Flights.class);
        var query2 = session.createQuery("from Booking ", Booking.class);
        var bookings = query2.getResultList();
        var flights = query.getResultList();
        session.getTransaction().commit();
        session.close();
        outputStream.writeObject(flights);
        outputStream.writeObject(bookings);
    }

    private void UpdatePassenger(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var newp = (Passengers) inputStream.readObject();
        var session = factory.openSession();
        session.beginTransaction();
        session.update(newp);
        var p = session.get(Passengers.class, newp.getPassengerId());
        session.getTransaction().commit();
        session.close();
        outputStream.writeObject(p);
    }

    private void InsertNewPassenger(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var pas = (Passengers) inputStream.readObject();
        var session = factory.openSession();
        session.beginTransaction();
        session.save(pas);
        session.getTransaction().commit();
        session.close();
        outputStream.writeObject(pas);
    }

    private void GetArrivals(ObjectOutputStream outputStream, SessionFactory factory) throws IOException
    {
        var session = factory.openSession();
        session.beginTransaction();
        var query = session.createQuery("from Arrivals ", Arrivals.class);
        var arrivs = query.getResultList();
        session.getTransaction().commit();
        session.close();
        outputStream.writeObject(arrivs);
    }

    private void GetDepartures(ObjectOutputStream outputStream, SessionFactory factory) throws IOException
    {
        var session = factory.openSession();
        session.beginTransaction();
        var query2 = session.createQuery("from Departures", Departures.class);
        var deps = query2.getResultList();
        session.getTransaction().commit();
        session.close();
        outputStream.writeObject(deps);
    }

}
