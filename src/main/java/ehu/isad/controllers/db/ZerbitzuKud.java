package ehu.isad.controllers.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<String> lortuZerbitzuak() {

        String query = "select id, izena from services";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                int kodea = rs.getInt("id");
                String izena = rs.getString("izena");

                System.out.println(kodea + ":" + izena);
                emaitza.add(izena);


            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void ezabatuHautatutakoa(String aukera){

        String query = "delete from zerbitzuak.services WHERE izena ='" + aukera + "';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

    }

    public void gehituOrriKop(String aukera, String isbn){

        String query = "UPDATE liburua SET orriKop="+aukera+" WHERE isbn="+isbn;
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

    }

    public void gehituArgitaletxea(String aukera, String isbn){

        String query = "UPDATE liburua SET argitaletxea=\""+aukera+"\" WHERE isbn="+isbn;
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

    }

    public void gehituLiburua(String irudi, String isbn){

        String query = "UPDATE liburua SET irudia=\""+irudi+"\" WHERE isbn="+isbn;
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

    }

    public boolean badagoDBan(String zutabe, String isbn) throws SQLException {

        String query = "SELECT " + zutabe + " FROM liburua WHERE isbn= "+ isbn+ " AND "+zutabe+" is not NULL";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        return rs.next();

    }

    public String lortu(String zutabe, String isbn) throws SQLException {

        String query = "SELECT " + zutabe + " FROM liburua WHERE isbn= "+ isbn;
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        rs.next();
        return rs.getString(zutabe);

    }

}
