
package leilao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
    
    public List<produtos> consultar() {
    List<produtos> lista = new ArrayList<>();
    try {
        
        st = conn.prepareStatement("SELECT * FROM produtos");
        
        
        rs = st.executeQuery();
        
        while (rs.next()) {
            produtos p = new produtos();
            p.setId(rs.getInt("id")); 
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getDouble("valor")); 
            p.setStatus(rs.getString("status"));
            lista.add(p);
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao consultar: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    return lista;
}
    
    public int venderProduto(produtos p){
        int status;

            try {
            st = conn.prepareStatement("UPDATE produtos SET status = ? where id = ?");
            st.setString(1,p.getStatus());
            st.setInt(2,p.getId());
            status = st.executeUpdate();
            return status; 

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
            
          }
        }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
        
        }    }
}
