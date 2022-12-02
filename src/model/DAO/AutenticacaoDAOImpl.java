
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import projeto.erro.AutenticacaoException;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import model.VO.Autenticacao;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 *
 * @author 
 */
public class AutenticacaoDAOImpl implements AutenticacaoDAO{
    
    GerenciadorConexao ger = GerenciadorConexaoImpl.getInstancia();

    /**
     * Metodo checarAutenticação.
     * @param login
     * @param pass
     * @return
     * @throws AutenticacaoException
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public Autenticacao checarAutenticacao(String login, String pass) throws AutenticacaoException, ConexaoException, DaoException {
        Connection con = ger.abrirConexao();
        Autenticacao autenticacao = null;
        String sql = "SELECT * FROM autenticacao WHERE login = ? AND password = ?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,login);
            pstm.setString(2,pass);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                autenticacao = new Autenticacao();
                autenticacao.setLogin(result.getString("login"));
                autenticacao.setPass(result.getString("password"));
                autenticacao.setNrDocumento_func(result.getString("nr_documento"));
                autenticacao.setPrimeiroAcesso(result.getString("primeiro_acesso"));               
            }
            return autenticacao; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para criar as credenciais.
     * @param autenticacao
     * @throws AutenticacaoException
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void criarCredenciais(Autenticacao autenticacao) throws AutenticacaoException, ConexaoException, DaoException {
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO autenticacao (login,password,nr_documento,primeiro_acesso) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, autenticacao.getLogin());
            pstm.setString(2, autenticacao.getPass());
            pstm.setString(3, autenticacao.getNrDocumento_func());
            pstm.setString(4, autenticacao.getPrimeiroAcesso());
            pstm.executeUpdate();
            Msg.msgSucesso("Credenciais do funcionario foram inseridas no banco de dados", "Sucesso ao inserir credenciais");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }finally{
            ger.fecharConexao(con);
        }
    }
    
    /**
     * Metodo para alterar as credenciais.
     * @param nrDocumneto
     * @param newPassword
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void alterarPassword(String nrDocumento, String newPassword) throws ConexaoException, DaoException{
        Connection con = ger.abrirConexao();
        String sql = "UPDATE autenticacao SET password = ?, primeiro_acesso = 'N' WHERE nr_documento = ? ";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, newPassword);
            pstm.setString(2, nrDocumento);
            pstm.executeUpdate(); 
            Msg.msgSucesso("Senha alterada com sucesso", "Sucesso ao alterar");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }
    
}
