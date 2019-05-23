package JDBC;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DriverClass {
    public static void main(String args[]){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }
            catch(ClassNotFoundException ex) {
               System.out.println("Error: unable to load driver class!");
               System.exit(1);
            }
        String URL = DBURL;
        String username = "";
        String password = "";
        try{
            Connection con = DriverManager.getConnection(URL);
            System.out.print("Connection Secured!!!!");
            fileChooser fc = new fileChooser();
            String filename = fc.choose();
            System.out.println(filename);
            String queryCode = getSQLQuery(filename);

            int QUERY_NUMBER = 4;//Change This To Select Which Query It Displays From The SQL File
                                //Leave At 0 If There's Only One

            PreparedStatement ps = null; 

            List<String> allMatches = new ArrayList<String>();
            allMatches = Arrays.asList(queryCode.split(";"));

            //System.out.println(allMatches.size());

            System.out.println("Query Loaded:\n"+allMatches.get(QUERY_NUMBER)); //Prints Query
            ps = con.prepareStatement ( allMatches.get(QUERY_NUMBER));
            ResultSet rs =ps.executeQuery();


            JDBCGui jdbcGui = new JDBCGui();
            jdbcGui.insertValue(rs);

        }
        catch(Exception E){
            System.out.println(E);
        }
    }
    public static String getSQLQuery(String Filename) throws IOException{
        BufferedReader bfr = new BufferedReader(new FileReader(Filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bfr.readLine()) != null)
        {
            sb.append(line + "\n");
        }

        return sb.toString();
    }
}