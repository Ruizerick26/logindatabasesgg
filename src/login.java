import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class login {
    private JPanel root;
    private JTextField USSERTXT;
    private JPasswordField PASSTXT;
    private JButton INICIARSESIÓNButton;
    private JButton REGISTRARSEButton;
    private JButton ELIMINARButton;
    private String Usuario;
    private String contra;
    static final String db_URL="jdbc:mysql://localhost/POO2";
    static final String usser="root";
    static final String pass="pc2rcee";
    static final String query="SELECT * from Usuarios";
    private static boolean data = false;
    public login(){
        INICIARSESIÓNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario = USSERTXT.getText();
                contra = new String(PASSTXT.getPassword());
                coneccion(Usuario, contra);

                if(data == false) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(root);
                    frame.setVisible(false);

                    JFrame usserFrame = new JFrame("SECCION");
                    sesion usserventana = new sesion();
                    usserventana.usertxt.setText(Usuario);
                    usserFrame.setContentPane(usserventana.root);
                    usserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    usserFrame.pack();
                    usserFrame.setVisible(true);
                }
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario = USSERTXT.getText();
                delete(Usuario);
            }
        });
        REGISTRARSEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario = USSERTXT.getText();
                contra = new String(PASSTXT.getPassword());
                insert(Usuario,contra);
            }
        });
    }
    public static void coneccion(String usu, String cont){

        try(
                Connection conn =DriverManager.getConnection(db_URL,usser,pass);
                Statement stat =conn.createStatement();
                ResultSet rs =stat.executeQuery(query);
                ){
            while(rs.next()){
                System.out.println(rs.getString("Usser"));
                System.out.println(rs.getString("passw"));
                if(usu.equals(rs.getString("Usser")) && cont.equals(rs.getString("passw"))){
                    System.out.println("INGRESO");
                    data = false;
                    break;
                }
                else{
                    data = true;
                }
            }
            if(data == true){
                System.out.println("Usuario o contraseña incorrecto");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(String usu){
        String data = "Delete from Usuarios Where Usser = "+ "'" + usu + "'";
        System.out.println(data);
        try(
                Connection conn =DriverManager.getConnection(db_URL,usser,pass);
                Statement stat =conn.createStatement();
        ) {
            stat.executeUpdate(data);
            System.out.println("USUARIO ELIMINADO");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insert (String usu, String contra){
        String data = "Insert Into Usuarios Values('"+usu+"','"+contra +"')";
        System.out.println(data);
        try(
                Connection conn =DriverManager.getConnection(db_URL,usser,pass);
                Statement stat =conn.createStatement();
        ) {
            stat.executeUpdate(data);
            System.out.println("USUARIO REGISTRADP");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
