import javax.swing.*;
import java.sql.*;

public class sesion {
    public JPanel root;
    public JTextField usertxt;
    private JTextField nuevaesertxt;
    private JTextField newcontratxt;
    private JButton actualizarButton;

    public String usuarioj;
    public String contraj;
    static final String db_URL="jdbc:mysql://localhost/POO2";
    static final String usser="root";
    static final String pass="root_bas3";

    public sesion(){
        String nuevousuario = nuevaesertxt.getText();
        String nuevacontra = newcontratxt.getText();
        usuarioj = newcontratxt.getText();
        String query="SELECT * from Usuarios where Usser = '" + usuarioj + "'";
        String query2 = "UPDATE USUARIOS SET Usser = '" + nuevousuario +"' where ='" + usuarioj + "'";
        String query3 = "UPDATE USUARIOS SET passw = '" + nuevacontra +"' where ='" + usuarioj + "'";
        try(
                Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                Statement stat =conn.createStatement();
        ){
            if(nuevaesertxt.getText()!="" && newcontratxt.getText()==""){
                stat.executeUpdate(query2);
            }
            if(nuevaesertxt.getText()=="" && newcontratxt.getText()!=""){
                stat.executeUpdate(query3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
