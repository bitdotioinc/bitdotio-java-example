import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class BitioExample {
   public static void main(String args[]) {
      Connection c = null;
      String bitApiKey = "<your_bitdotio_key>"; // "Password" from connect menu
      String bitDB = "dliden.2020_Census_Reapportionment";
      String bitUser = "<your_bitdotio_username>";
      String bitHost = "db.bit.io";
      String bitPort = "5432"; // We keep this as a string here as we are concact'ing it into the connection string
      Properties props = new Properties();
      props.setProperty("sslmode","require");
      props.setProperty("user",bitUser);
      props.setProperty("password",bitApiKey);
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://" + bitHost + ":" + bitPort + "/" + bitDB, props);
         Statement stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM \"dliden/2020_Census_Reapportionment\".\"Historical Apportionment\" limit 10;" );
         while (rs.next()) {
             ResultSetMetaData rsmd = rs.getMetaData();
             // The ResultSet .getXXX() methods expect the column index to start at 1. 
             // No idea why.
             for (int i = 1; i <= rsmd.getColumnCount(); i++) {
               System.out.print(rsmd.getColumnName(i) + "="+ rs.getString(i) + " ");
             }
             System.out.println();
         }

      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
   }
}
