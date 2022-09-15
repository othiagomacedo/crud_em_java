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
    String connectionUrl = "";
    
    ResultSet resultSet = null;
    
    Tela tela = new Tela();
    
    public Query() {
        
    }
    
    public Query(String nome, String end, String num){
        this.nome = nome;
        this.end = end;
        this.num = num;
    }
    
    public void connectarBD(){
        //conectando ao banco!
        setConnectionUrl(
                  "jdbc:postgresql://localhost:4002/aluguel_de_carro;"
                + "database=aluguel_de_carro;"
                + "user=postgres;"
                + "password=positivo;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=10;");

        try ( Connection connection = DriverManager.getConnection(getConnectionUrl());) {
            System.out.println("\nCONEXÃO AO BANCO REALIZADA COM SUCESSO!\n");
        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void consulta(){
        connectarBD();
        String selectSql = "SELECT * FROM testes";
        
        try (Connection connection = DriverManager.getConnection(getConnectionUrl());
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            String resultado = "";
            int contQuery = 0;
            while (resultSet.next()) {
                resultado += resultSet.getString(contQuery) + "\n  ";
                //resultSet.getString(2) + " " + resultSet.getString(3));
                contQuery += 1;
            }
            
            tela.setSaida(resultado);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //GETTERS
    public String getNome() {
        return nome;
    }

    public String getEnd() {
        return end;
    }

    public String getNum() {
        return num;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
    
    
    
}
