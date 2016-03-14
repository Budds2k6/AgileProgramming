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
            //InetAddress ipAddr = InetAddress.getByName("http://silva.computing.dundee.ac.uk/2015-agileteam2/Login");

/*
            InetAddress ipAddr = InetAddress.getByAddress("http://www.google.co.uk");


            InetAddress ipA = InetAddress.getBy


            // if there is an address, the connection is available
            if (!ipAddr.equals("")) {
                success = true;
            }*/

//            InetSocketAddress isa = new InetSocketAddress("http://www.google.co.uk", 3306);
//            Socket sock = new Socket();
//            sock.connect(isa,50);
//            sock.close();
//            success = true;



            //boolean seven = InetAddress.getByName("http://www.google.com").isReachable(50);

            URL myUrl = new URL("http://silva.computing.dundee.ac.uk/2015-agileteam2");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(25);
            connection.connect();

            System.out.println("Placeholder");

            success = true;

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
