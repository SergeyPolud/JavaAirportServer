package users;

import Entities.Arrivals;
import Entities.Booking;
import Entities.Departures;
import Entities.Flights;
import org.hibernate.SessionFactory;

import java.io.*;

public class AdminUser extends User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 999L;
    public void ProcessAdminCommands(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        while(true)
        {
            var message = (String) inputStream.readObject();
            switch (message)
            {
                case "GET_DEPARTURES"    -> GetDepartures(outputStream, factory);
                case "ADD_DEPARTURE"     -> AddDeparture(inputStream, outputStream, factory);
                case "DELETE_DEPARTURE"  -> DeleteDeparture(inputStream, outputStream, factory);
                case "CHANGE_DEP_STATUS" -> UpdateDeparture(inputStream, outputStream, factory);
                case "GET_ARRIVALS"      -> GetArrivals(outputStream, factory);
                case "ADD_ARRIVAL"       -> AddArrival(inputStream, outputStream, factory);
                case "CHANGE_ARR_STATUS" -> UpdateArrival(inputStream, outputStream, factory);
                case "DELETE_ARRIVAL"    -> DeleteArrival(inputStream, outputStream, factory);
                case "GET_ALL_FLIGHTS"   -> GetAllFlights(outputStream, factory);
                case "ADD_FLIGHT "       -> AddFlight(inputStream, outputStream, factory);
                case "CHANGE_FLIGHT"     -> UpdateFlight(inputStream, outputStream, factory);
                case "CHANGE_BOOKING"    -> UpdateBooking(inputStream, outputStream, factory);
                case "DELETE_FLIGHT"     -> DeleteBookingAndFlight(inputStream, factory);
            }
        }
    }

    private void DeleteBookingAndFlight(ObjectInputStream inputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var flight = (Flights) inputStream.readObject();
        var booking = (Booking) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.delete(flight);
            session.delete(booking);
            session.getTransaction().commit();
            session.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void UpdateBooking(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var booking = (Booking) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.update(booking);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void UpdateFlight(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var flight = (Flights) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.update(flight);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void AddFlight(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var flight = (Flights) inputStream.readObject();
        var booking = (Booking) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.save(flight);
            session.save(booking);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void DeleteArrival(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var arr = (Arrivals) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.delete(arr);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void UpdateArrival(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var arr = (Arrivals) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.update(arr);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void AddArrival(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var arr = (Arrivals) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.save(arr);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void UpdateDeparture(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var dep = (Departures) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.update(dep);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void DeleteDeparture(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var dep = (Departures) inputStream.readObject();
        var session = factory.openSession();
        try
        {
          session.beginTransaction();
          session.delete(dep);
          session.getTransaction().commit();
          session.close();
          outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
    }

    private void AddDeparture(ObjectInputStream inputStream, ObjectOutputStream outputStream, SessionFactory factory) throws IOException, ClassNotFoundException
    {
        var dep = (Departures) inputStream.readObject();
        var session = factory.openSession();
        try
        {
            session.beginTransaction();
            session.save(dep);
            session.getTransaction().commit();
            session.close();
            outputStream.writeObject(1);
        } catch(Exception e)
        {
            e.printStackTrace();
            outputStream.writeObject(0);
        }
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
