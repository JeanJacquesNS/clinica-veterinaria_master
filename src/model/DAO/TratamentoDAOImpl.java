package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import model.VO.Tratamento;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 * 
 * @author 
 */

public class TratamentoDAOImpl implements TratamentoDAO{
    private final GerenciadorConexao ger;
    public TratamentoDAOImpl(){
        ger = GerenciadorConexaoImpl.getInstancia();
    }
    
    /**
     * Metodo para consultar um tratamento do banco de dados.
     * @param codigoTratamento
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public Tratamento consultar(int codigoTratamento) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        Tratamento tratamento = null;
        String sql = "SELECT idTratamento,data_inicial,data_final,descricao,nome_animal,diagnostico FROM tratamento WHERE idTratamento=?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1,codigoTratamento);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                tratamento = new Tratamento();
                tratamento.setCodigoTratamento(result.getInt("idTratamento"));
                tratamento.setDataInicialTratamento(result.getString("data_inicial"));
                tratamento.setDataFinalTratamento(result.getString("data_final"));
                tratamento.setDescricaoTratamento(result.getString("descricao")); 
                tratamento.setNomeAnimal(result.getString("nome_animal"));
                tratamento.setDiagnosticoTratamento("diagnostico");
            }
            return tratamento; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
    }

    /**
     * Metodo para inserir um tratamento no banco de dados.
     * @param tratamento
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void inserir(Tratamento tratamento) throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO tratamento (data_inicial,data_final,descricao,diagnostico,nome_animal) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tratamento.getDataInicialTratamento());
            pstm.setString(2, tratamento.getDataFinalTratamento());
            pstm.setString(3, tratamento.getDescricaoTratamento());
            pstm.setString(4, tratamento.getDiagnosticoTratamento());
            pstm.setString(5, tratamento.getNomeAnimal());
            pstm.executeUpdate();
            Msg.msgSucesso("Tratamento agendado com sucesso", "Sucesso ao agendar Tratamento");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para deletar um tratamento do banco de dados.
     * @param codTratamento
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void deletar(int codTratamento) throws ConexaoException, DaoException {
        GerenciadorConexao ger = GerenciadorConexaoImpl.getInstancia();
        Connection con = ger.abrirConexao();
        String sql = "DELETE FROM tratamento WHERE idTratamento = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, codTratamento);
            pstm.executeUpdate();
            Msg.msgSucesso("Tratamento deletado do BD com sucesso!", "Sucesso ao deletar Tratamento");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para alterar um tratamento do banco de dados.
     * @param tratamento
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void atualizar(Tratamento tratamento) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao(); 
        String sql = "UPDATE tratamento SET data_inicial = ?,data_final = ?,descricao = ?, diagnostico = ?, nome_animal = ?  WHERE idTratamento = ?";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tratamento.getDataInicialTratamento());
            pstm.setString(2,  tratamento.getDataFinalTratamento());
            pstm.setString(3, tratamento.getDescricaoTratamento());
            pstm.setString(4, tratamento.getDiagnosticoTratamento());
            pstm.setString(5, tratamento.getNomeAnimal());
            pstm.setInt(6, tratamento.getCodigoTratamento());
            pstm.executeUpdate(); 
            Msg.msgSucesso("Tratamento alterado com sucesso", "Sucesso ao alterar tratamento");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para listar todos os tratamentos do banco de dados.
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public ArrayList<Tratamento> listar() throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        String sql ="SELECT * FROM tratamento";
        ArrayList <Tratamento> Lista = new ArrayList();
        Tratamento tratamento;
       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while(result.next()){
                tratamento = new Tratamento();
                tratamento.setCodigoTratamento(result.getInt("idTratamento"));
                tratamento.setDataInicialTratamento(result.getString("data_inicial"));
                tratamento.setDataFinalTratamento(result.getString("data_final"));
                tratamento.setDescricaoTratamento(result.getString("descricao"));  
                tratamento.setDiagnosticoTratamento(result.getString("diagnostico"));
                tratamento.setNomeAnimal(result.getString("nome_animal"));
                Lista.add(tratamento);
            }
           return Lista; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }        
    }  
}    