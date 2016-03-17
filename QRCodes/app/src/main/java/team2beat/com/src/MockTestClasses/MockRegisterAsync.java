package team2beat.com.src.MockTestClasses;

import team2beat.com.qrcodes.PresentRecord;

/**
 * Created by Matt on 17/03/2016.
 */
public class MockRegisterAsync {

        private String username;
        private String booking_id;
        public String [] toReturn;
        public boolean completed;

    public MockRegisterAsync (String bid, String un){

        this.booking_id = bid;
        this.username = un;

        //new PostClass().execute(booking_id,username);

        executeRegister();

    }


    public void executeRegister(){

        String[] returned = new String[2];

        if(username.equals("080003474"))
        {
            returned[0] = "080003474";
            returned[1] = "true";
        }else if(username.equals("0493828x5"))
        {
            returned[0] = "0493828x5";
            returned[1] = "false";

        }else if(username.equals("130005440"))
        {
            returned[0] = "080003474";
            returned[1] = "false";

        }
        toReturn = returned;
        completed = true;
    }


}
