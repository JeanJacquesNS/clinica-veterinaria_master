package projeto.acesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import projeto.negocio.classesBasicas.Consulta;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 *
 * @author Andre
 */
public class ConsultaDAOImpl implements ConsultaDAO{
     private final GerenciadorConexao ger;
    public ConsultaDAOImpl(){
        ger = GerenciadorConexaoImpl.getInstancia();
    }

   /**
    * Metodo para listar o agendamento de uma consulta.
    * @param idConsulta
    * @return consulta
    * @throws ConexaoException
    * @throws DaoException 
    */
    @Override
    public Consulta pesquisar(int idConsulta) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        Consulta consulta = null;
        String sql = "SELECT * FROM CONSULTA WHERE idConsulta=?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1,idConsulta);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                consulta = new Consulta();
                consulta.setIdConsulta(result.getInt("idConsulta"));
                consulta.setDataConsulta(result.getString("data"));
                consulta.setDescricaoConsulta(result.getString("descricao"));
                consulta.setNomeAnimal(result.getString("nome_animal"));
                consulta.setNomeVeterinario(result.getString("nome_veterinario"));
            }
            return consulta; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
    }

    /**
     * Metodo para inserir uma consulta no banco de dados
     * @param consulta
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void inserir(Consulta consulta) throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO consulta (desricao,data,nome_animal,nome_veterinario) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, consulta.getDescricaoConsulta());
            pstm.setString(2, consulta.getDataConsulta());
            pstm.setString(3, consulta.getNomeAnimal());
            pstm.setString(4, consulta.getNomeVeterinario());
            pstm.executeUpdate();
            Msg.msgSucesso("Consulta marcada com sucesso", "Sucesso ao marcar Consulta");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para deletar uma consulta do banco de dados
     * @param codTratamento
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void deletar(int codTratamento) throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "DELETE FROM CONSULTA WHERE idConsulta = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, codTratamento);
            pstm.executeUpdate();
            Msg.msgSucesso("Consulta deletada do BD com sucesso!", "Sucesso ao deletar Consulta");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para alterar uma consulta do banco de dados
     * @param consulta
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void atualizar(Consulta consulta) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao(); 
        String sql = "UPDATE consulta SET data = ?,descricao = ?, nome_animal = ?, nome_veterinario = ? WHERE idConsulta = ?";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, consulta.getDataConsulta());
            pstm.setString(2, consulta.getDescricaoConsulta());
            pstm.setString(3, consulta.getNomeAnimal());
            pstm.setString(4, consulta.getNomeVeterinario());
            pstm.setInt(5, consulta.getIdConsulta());
            pstm.executeUpdate(); 
            Msg.msgSucesso("Consulta alterada com sucesso", "Sucesso ao alterar consulta");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para listar todas as consultas do banco de dados
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public ArrayList<Consulta> listar() throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql ="SELECT * FROM CONSULTA";
        ArrayList <Consulta> Lista = new ArrayList();
        Consulta consulta;
       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while(result.next()){
                consulta = new Consulta();
                consulta.setIdConsulta(result.getInt("idConsulta"));
                consulta.setDataConsulta(result.getString("data"));
                consulta.setNomeAnimal(result.getString("nome_animal"));
                consulta.setNomeVeterinario(result.getString("nome_veterinario"));
                consulta.setDescricaoConsulta(result.getString("descricao"));
                Lista.add(consulta);
            }
           return Lista; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }        
    }  
    
}
