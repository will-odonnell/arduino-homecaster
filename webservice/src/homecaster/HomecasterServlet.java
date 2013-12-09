package homecaster;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class HomecasterServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    
        Key homescreenKey = KeyFactory.createKey("Homescreen", 1);
        String content = req.getParameter("content");
        String submitter = req.getParameter("submitter");
        Date date = new Date();
        Entity message = new Entity("Message", homescreenKey);
        message.setProperty("date", date);
        message.setProperty("content", content);
        message.setProperty("submitter",submitter);
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(message);
        
        resp.sendRedirect("index.html");
    }

}


