import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sesion {
    public JPanel root;
    public JTextField usertxt;
    private JTextField nuevaesertxt;
    private JButton actualizarButton;
    private JPasswordField newcontra;

    public String usuarioj;
    public String contraj;
    static final String db_URL="jdbc:mysql://localhost/POO2";
    static final String usser="root";
    static final String pass="pc2rcee";

    public sesion(){
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioj = usertxt.getText();
                String nuevousuario = nuevaesertxt.getText();
                String nuevacontra = newcontra.getText();
                String query="SELECT * from Usuarios where Usser = '" + usuarioj + "'";
                String query2 = "UPDATE Usuarios SET Usser = '" + nuevousuario +"' where Usser='" + usuarioj + "'";
                String query3 = "UPDATE Usuarios SET passw = '" + nuevacontra +"' where Usser ='" + usuarioj + "'";
                try(
                        Connection conn = DriverManager.getConnection(db_URL,usser,pass);
                        Statement stat =conn.createStatement();
                ){
                    System.out.println(query);
                    if(!nuevaesertxt.getText().equals("") && newcontra.getText().equals("")){
                        stat.executeUpdate(query2);
                        System.out.println(query2);
                    }
                    if(nuevaesertxt.getText().equals("") && !newcontra.getText().equals("")){
                        stat.executeUpdate(query3);
                        System.out.println(query3);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}
