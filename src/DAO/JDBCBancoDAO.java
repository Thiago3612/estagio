package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JDBCBancoDAO {
    
    private Conexao conexao;
    private Statement stmt;
    private static Correntista correntista;
    
    public JDBCBancoDAO() throws SQLException, ClassNotFoundException {
        conexao = new Conexao();
        try {
            stmt = (Statement) conexao.getConn().createStatement();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    //Login
    public Correntista login(String conta, String senha) throws SQLException{
        this.correntista = new Correntista();
        
        try {
            ResultSet rs = 
                    stmt.executeQuery("SELECT conta, senha FROM correntista"
                                    + " WHERE conta = "  + conta 
                                    + " AND senha = "    + senha +";");
            while(rs.next()){
                this.correntista.setConta(rs.getString("conta"));
                this.correntista.setSenha(rs.getString("senha"));
            }
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
         //   conexao.fecharConexao();
        }
        return this.correntista;
    }
    
    //verSaldo
    public float verSaldo() throws SQLException{
        float vSaldo = 0;
        String conta = correntista.getConta();
        try {
            ResultSet rs = stmt.executeQuery("SELECT saldo FROM correntista"
                                           + " Where conta = " + conta + ";");
            while (rs.next()) {
                vSaldo = rs.getFloat("saldo");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
           // conexao.fecharConexao();
        }
        return vSaldo;
    }
    
    //verExtrato
    public List<Transacao> verExtrato() throws SQLException{
        List<Transacao> vExtrato = new ArrayList<Transacao>();
        String conta = correntista.getConta();
        try {
            ResultSet rs = stmt.executeQuery("SELECT data, tipo, valor FROM transacao"
                                           + " Where conta = " + conta + ";");
            while (rs.next()) {
                Transacao valor = new Transacao();
                valor.setDataHora(rs.getDate("data"));
                valor.setTipo(rs.getString("tipo"));
                valor.setValor(rs.getFloat("valor"));
                vExtrato.add(valor);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
        return vExtrato;
    }
    
    //perfil
    public String Usuario() throws SQLException{
        String conta = correntista.getConta();
        String perfil ="";
        String user = "";
        
        try {
            ResultSet rs = 
                    stmt.executeQuery("SELECT perfil, conta FROM correntista"
                                    + " WHERE conta = "  + conta +";");
            while(rs.next()){
                perfil = rs.getString("perfil");
                user = rs.getString("conta");
            }
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
        return perfil;
    }
    
    //conta
    public String Usuario2() throws SQLException{
        String conta = correntista.getConta();
        String nConta ="";
        
        try {
            ResultSet rs = 
                    stmt.executeQuery("SELECT conta FROM correntista"
                                    + " WHERE conta = "  + conta +";");
            while(rs.next()){
                nConta = rs.getString("conta");
            }
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
        return nConta;
    }
    
    //Saque
    public float Saque(float vSaque) throws SQLException{
        
        String conta = correntista.getConta();
        
        try {
            return stmt.executeUpdate("UPDATE correntista SET saldo = '"
                                      +vSaque+"' WHERE conta ="
                                      +conta+ ";");
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
        
        
    }
    
    public int salvaTransacao(Transacao transacao) throws SQLException{

        try{
        return stmt.executeUpdate("INSERT transacao INTO conta , data, tipo, valor) VALUES ("
                                + transacao.getConta() +", '"
                                + transacao.getDataHora() +"', '"
                                + transacao.getTipo() +"', '"
                                + transacao.getValor()+"')" );
        
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
    }
    //depósito
    public float Deposito(float vDeposito) throws SQLException{
        
        String conta = correntista.getConta();
        
        try {
            return stmt.executeUpdate("UPDATE correntista SET saldo = '"
                                      +vDeposito+"' WHERE conta ="
                                      +conta+ ";");
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
    }

    //transfer
    
    //solGerente
    public float solGerente(float taxa) throws SQLException{
        
        String conta = correntista.getConta();
        
        try {
            return stmt.executeUpdate("UPDATE correntista SET saldo = '"
                            +taxa+"' WHERE conta ="
                            +conta);
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
            //conexao.fecharConexao();
        }
    }
}