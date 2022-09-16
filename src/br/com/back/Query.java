/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.back;

/**
 *
 * @author thiag
 */
import br.com.front.Tela;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Query {
    
    String nome = "";
    String end = "";
    String num = "";
    String url = "jdbc:postgresql://localhost:4002/aluguel_de_carro";
    String usuario = "postgres";
    String senha = "password";
    
    ResultSet resultSet = null;
    Connection con = null;
    
    Tela tela = new Tela();
    
    public Query() {
          
    } 
    
    public void create() throws Exception{
        String querySQL = "CREATE TABLE crud ( id serial primary key, nome text , telefone text , endereco text );";
        con = DriverManager.getConnection(url, usuario, senha);
        Statement stm = con.createStatement();
        stm.executeUpdate(querySQL); 
    }
    
    public void Inserir(String nome, String end, String num) throws Exception{        
        String querySQL = "INSERT INTO crud (id,nome,telefone,endereco) values (default, '"+nome+"', '"+num+"', '"+end+"')";
        con = DriverManager.getConnection(url, usuario, senha);
        Statement stm = con.createStatement();
        stm.executeUpdate(querySQL);  
        JOptionPane.showMessageDialog(null, "Dados inseridos com Sucesso!");
    }
    
    public String Consultar() throws Exception{
        String querySQL = "SELECT * FROM crud";
        String saida = "*** CLIENTES CADASTRADOS ***\n-------------------------------------------------\n";
        
        con = DriverManager.getConnection(url, usuario, senha);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(querySQL);
        while(rs.next()){
            saida += rs.getInt("id") + " - " + rs.getString("nome") + " - " + rs.getString("telefone") +  " - " + rs.getString("endereco") + "\n"; 
        }
        return saida;
    }
    
    public void Alterar(){
        
    }
    
    public void Excluir(String nome)throws Exception{
        String querySQL = "DELETE FROM crud WHERE nome ='"+nome+"';";
        con = DriverManager.getConnection(url, usuario, senha);
        Statement stm = con.createStatement();
        stm.executeUpdate(querySQL);  
        JOptionPane.showMessageDialog(null, "o Cliente "+nome+" foi excluído com sucesso!");
    }
  
}
