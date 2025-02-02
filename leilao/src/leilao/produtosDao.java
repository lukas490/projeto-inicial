
package leilao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class produtosDao {
    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public boolean conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/uc11","root", "12345678");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
    public int salvar(produtos p){
    int status;
        try {
            st = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
            st.setString(1,p.getNome());
            st.setDouble(2,p.getValor());
            st.setString(3,"A venda");
            
           
            status = st.executeUpdate();
            return status; //retornar 1
            
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
        
        }    }
}
