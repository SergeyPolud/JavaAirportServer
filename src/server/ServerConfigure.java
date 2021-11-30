package server;

import Entities.*;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;


public class ServerConfigure
{

    private static final Inet4Address serverAddr = (Inet4Address) Inet4Address.getLoopbackAddress();
    private static final int port = 42069;

    public static void main(String[] args) throws IOException
    {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Flights.class);
        configuration.addAnnotatedClass(Departures.class);
        configuration.addAnnotatedClass(Arrivals.class);
        configuration.addAnnotatedClass(Booking.class);
        configuration.addAnnotatedClass(Boardingpasses.class);
        configuration.addAnnotatedClass(Passengers.class);
        SessionFactory factory;
        var registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        var clientsCount = 0;
        try
        {
            var serverEndpoint = new ServerSocket(port, 50, serverAddr);
            System.out.println("Server started on " + serverAddr + ":" + port + "\n Waiting for incoming connections....");
            while (true)
            {
                var incoming = serverEndpoint.accept();
                System.out.println("New client has been connected. Total clients connected: " + ++clientsCount);
                Thread thread = new Thread(new CommandProcessor(incoming, factory, "ClientThread" + clientsCount));
                thread.start();

                System.out.println();
            }

        } catch (UnknownHostException | NoSuchAlgorithmException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

}
