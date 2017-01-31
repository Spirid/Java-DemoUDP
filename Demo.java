package demoudp;

import java.io.Serializable;
import java.util.Date;

public class Demo implements Serializable
{
        //Demo() {}
        Demo(int _id, String _name, String _msg)
        {
            id = _id;
            name = _name;
            message = _msg;
            date = new Date();
            temp = ++last;
        }
        public void getInfo()
        {
            System.out.println("Object Demo info: " + id + ", " + name + ", " + message + ", " + date.toString() + ", " + temp);
        }
        
        public static int last = 0;
        private int id;         //Sender id
        private String name;    //Sender name
        private String message; 
        private Date date;      
        private transient int temp;
}
