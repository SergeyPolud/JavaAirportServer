package server;

import org.hibernate.SessionFactory;
import users.AdminUser;
import users.ClientUser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.APPEND;

public class CommandProcessor implements Runnable
{
    private final SessionFactory factory;
    private final ObjectOutputStream outputStream;
    private final Socket sck;
    private final ObjectInputStream inputStream;
    private String message;



    CommandProcessor(Socket sck, SessionFactory factory, String ThreadName) throws IOException, NoSuchAlgorithmException
    {

        this.sck = sck;
        this.factory = factory;
        this.outputStream = new ObjectOutputStream(sck.getOutputStream());
        this.inputStream = new ObjectInputStream(sck.getInputStream());
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                message = (String) inputStream.readObject();
                if(Objects.equals(message, "EXIT_THREAD")) break;
                switch(message)
                {
                    case "REGISTER":
                        ClientUser newUser = (ClientUser) inputStream.readObject();
                        byte[] bytesOfMessage = newUser.password.getBytes(StandardCharsets.UTF_8);
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        String pwd = new String(md.digest(bytesOfMessage), StandardCharsets.UTF_8);
                        Files.writeString(Path.of("test.txt"), newUser.username+":"+pwd+"\n", APPEND);
                        break;
                    case "LOGIN":
                        switch ((String) inputStream.readObject())
                        {
                            case "CLIENT" -> {
                                ClientUser user = (ClientUser) inputStream.readObject();
                                BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
                                var line = reader.readLine();
                                boolean found = false;
                                while (line != null)
                                {

                                    String[] creds = line.split(":");
                                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                                    var bytesmd5 = new String(md5.digest(user.password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
                                    try
                                    {
                                        if (bytesmd5.equals(creds[1]) && creds[0].equals(user.username))
                                        {
                                            found = true;
                                            outputStream.writeObject(1);
                                            user.ProcessClientCommands(inputStream, outputStream, factory);
                                            return;
                                        } else line = reader.readLine();
                                    } catch (IndexOutOfBoundsException ex)
                                    {
                                        ex.printStackTrace();
                                    }
                                }
                                if (!found) outputStream.writeObject(0);
                            }
                            case "ADMIN" -> {
                                AdminUser admin = (AdminUser) inputStream.readObject();
                                BufferedReader reader = new BufferedReader(new FileReader("admin.txt"));
                                var line = reader.readLine();
                                boolean found1 = false;
                                while (line!=null)
                                {
                                    if(line.equals(admin.password))
                                    {
                                        found1 = true;
                                        outputStream.writeObject(1);
                                        admin.ProcessAdminCommands(inputStream, outputStream, factory);
                                        return;
                                    } else line = reader.readLine();
                                }
                                outputStream.writeObject(0);
                            }
                        }

                        break;
                }
            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e)
            {
                try
                {
                    sck.close();
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                break;
            }


        }

    }
}

