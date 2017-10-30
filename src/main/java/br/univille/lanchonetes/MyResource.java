package br.univille.lanchonetes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.univille.lanchonetes.jdbc.DBConnection;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	DBConnection dbconn = new DBConnection();
    	Connection connection = dbconn.openConnection();
    	try {
    		Statement stmt = connection.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUTO");
    		
    		while (rs.next()) {
				System.out.println(1);
				
			}
    		connection.close();
    		dbconn.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
        return "Got it!";
    }
}
