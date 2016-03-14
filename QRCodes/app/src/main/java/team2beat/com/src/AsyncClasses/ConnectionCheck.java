package team2beat.com.src.AsyncClasses;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by asuth on his laptop.
 */
public class ConnectionCheck implements Runnable
{
    boolean success = false;

    @Override
    public void run()
    {
        try{
            // try and get an address for the web API file path
            InetAddress ipAddr = InetAddress.getByName("http://silva.computing.dundee.ac.uk/2015-agileteam2/");

            // if there is an address, the connection is available
            if (!ipAddr.equals("")) {
                success = true;
            }

            // catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
    }

    // function to make the boolean value accessible from outside this class
    public boolean checkConnection()
    {
        return success;
    }

}
