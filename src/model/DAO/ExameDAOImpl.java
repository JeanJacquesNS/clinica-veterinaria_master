package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import model.VO.Exame;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 *
 * @author 
 */
public class ExameDAOImpl implements ExameDAO {
    private final GerenciadorConexao ger;
    public ExameDAOImpl(){
        ger = GerenciadorConexaoImpl.getInstancia();
    }
    
    /**
     * Metodo para inserir no banco de dados
     * @param exame
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public void inserir(Exame exame) throws DaoException, ConexaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO exame (nome_animal,descricao,nome_veterinario,data) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, exame.getNomeAnimal());
            pstm.setString(2, exame.getExameDescricao());
            pstm.setString(3, exame.getVeterinarioExame());
            pstm.setString(4, exame.getDataExame());
            pstm.execute();
            Msg.msgSucesso("Exame agendado com sucesso", "Sucesso ao agendar exame");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        } finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para alterar no banco de dados
     * @param exame
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public void alterar(Exame exame) throws DaoException, ConexaoException {
         
        Connection con = ger.abrirConexao();
        String sql = "UPDATE exame SET nome_animal=?,descricao=?,nome_veterinario=?,data=? WHERE idExame = ?";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, exame.getNomeAnimal());
            pstm.setString(2, exame.getExameDescricao());
            pstm.setString(3, exame.getVeterinarioExame());
            pstm.setString(4, exame.getDataExame());
            pstm.setInt(5, exame.getIdExame());
            pstm.executeUpdate(); 
            Msg.msgSucesso("Exame alterado com sucesso", "Sucesso ao alterar Exame");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para deletar do banco de dados
     * @param idExame
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public void deletar(int idExame) throws DaoException, ConexaoException {
        
        Connection con = ger.abrirConexao();
        String sql = "DELETE FROM exame WHERE idExame = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idExame);
            pstm.executeUpdate();
            Msg.msgSucesso("Exame deletado do BD com sucesso!", "Sucesso ao deletar Exame");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para consultar no banco de dados
     * @param idExame
     * @return
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public Exame consultar(int idExame) throws DaoException, ConexaoException {
        
        Connection con = ger.abrirConexao();
        Exame exame = null;
        String sql = "SELECT * FROM exame WHERE idExame = ?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1,idExame);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                exame = new Exame();
                exame.setIdExame(result.getInt("idExame"));
                exame.setExameDescricao(result.getString("descricao"));
                exame.setVeterinarioExame(result.getString("nome_veterinario"));
                exame.setDataExame(result.getString("data"));
                exame.setNomeAnimal(result.getString("nome_animal"));
            }
            return exame; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
        
    }

    /**
     * Metodo para consultar uma lista de todos os exames no banco de dados
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public ArrayList<Exame> listarExames() throws ConexaoException, DaoException {
        Connection con = ger.abrirConexao();
        Exame exame;
        ArrayList <Exame> Lista = new ArrayList();
        String sql = "SELECT * FROM exame";

       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while(result.next()){
                exame = new Exame();
                exame.setIdExame(result.getInt("idExame"));
                exame.setDataExame(result.getString("data"));
                exame.setExameDescricao(result.getString("descricao"));
                exame.setNomeAnimal(result.getString("nome_animal"));  
                exame.setVeterinarioExame(result.getString("nome_veterinario"));
                Lista.add(exame);
            }
           return Lista; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }
    
}
