package homecaster;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;


@SuppressWarnings("serial")
public class MessageServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key homescreenKey = KeyFactory.createKey("Homescreen", 1);
        String postedMsg = "empty";
        
        Query query = new Query("Message", homescreenKey).addSort("date", Query.SortDirection.DESCENDING);
        List<Entity> messages = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
       
        if (messages.isEmpty()) {
            resp.setContentType("text/plain");
            resp.getWriter().println("No Messages...");
        } else {
            for (Entity message : messages) {
        		postedMsg = message.getProperty("content").toString();
            	//resp.setContentType("text/plain");
            	//resp.getWriter().println("Begin");
            	//resp.getWriter().println(msg);
            	//resp.getWriter().println("End");
            	
        		//resp.setContentType("text/plain");
        		//resp.getWriter().println("Hello, world. I am writing to Arduino.");

            }
        }
        resp.setContentType("text/plain");
        resp.getWriter().println(">>>>");
        resp.getWriter().println(postedMsg);
        resp.getWriter().println("<<<<");

    }

}


