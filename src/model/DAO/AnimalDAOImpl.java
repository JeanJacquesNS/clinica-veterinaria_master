package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import model.VO.Animal;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 * 
 * @author 
 */

public class AnimalDAOImpl implements AnimalDAO {
    private final GerenciadorConexao ger;
    public AnimalDAOImpl(){
        ger = GerenciadorConexaoImpl.getInstancia();
    }

    /**
     * Metodo para consultar animal no banco de dados
     * @param id
     * @return Animal
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public Animal consultar(int id) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        Animal animal = null;
        String sql = "SELECT * FROM ANIMAL WHERE idAnimal = ?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1,id);
            ResultSet result = pstm.executeQuery();
            if(result.next()){
                animal = new Animal();
                animal.setIdAnimal(result.getInt("idAnimal"));
                animal.setNome(result.getString("nome"));
                animal.setEspecie(result.getString("especie"));
                animal.setDataNascimento(result.getString("data_nascimento"));
                animal.setNrDocumento(result.getString("nrDocumento"));
                animal.setSexo(result.getString("sexo"));
                animal.setIdCliente(result.getInt("idCliente"));
            }
            return animal; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
    }
    
    /**
     * Metodo para inserir animal no banco de dados
     * @param animal
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void inserir(Animal animal) throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO animal (nome,nrDocumento,especie,sexo,data_nascimento,idCliente) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, animal.getNome());
            pstm.setString(2, animal.getNrDocumento());
            pstm.setString(3, animal.getEspecie());
            pstm.setString(4, animal.getSexo());
            pstm.setString(5, animal.getDataNascimento());
            pstm.setInt(6, animal.getIdCliente());
            pstm.executeUpdate();  
            Msg.msgSucesso("Animal inserido com sucesso!", "Inserido com sucesso!");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para deletar animal no banco de dados.
     * @param idAnimal
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void deletar(int idAnimal) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        String sql = "DELETE FROM ANIMAL WHERE idAnimal = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idAnimal);
            pstm.executeUpdate();
            Msg.msgSucesso("Animal deletado com sucesso!", "Deletado do banco de dados!");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para alterar animal no banco de dados.
     * @param animal
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void atualizar(Animal animal) throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "UPDATE animal SET nome = ?, nrDocumento = ?, especie = ?, sexo = ?, data_nascimento = ? WHERE idAnimal = ?";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, animal.getNome());
            pstm.setString(2, animal.getNrDocumento());
            pstm.setString(3, animal.getEspecie());
            pstm.setString(4, animal.getSexo());
            pstm.setString(5, animal.getDataNascimento());
            pstm.setInt(6, animal.getIdAnimal());
            pstm.executeUpdate();   
            Msg.msgSucesso("Animal alterado com sucesso", "Sucesso ao alterar");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

   /**
    * Metodo para listar todos os animais.
    * @return ArrayList
    * @throws ConexaoException
    * @throws DaoException 
    */
    @Override
    public ArrayList<Animal> listarA() throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        String sql ="SELECT * FROM ANIMAL";
        ArrayList <Animal> ListaA = new ArrayList();
        Animal animal;
       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()){
                animal = new Animal();
                animal.setIdAnimal(result.getInt("idAnimal"));
                animal.setEspecie(result.getString("especie"));
                animal.setNome(result.getString("nome"));
                animal.setSexo(result.getString("sexo"));
                animal.setDataNascimento(result.getString("data_nascimento"));
                animal.setNrDocumento(result.getString("nrDocumento"));
                animal.setIdCliente(result.getInt("idCliente"));
                 
                ListaA.add(animal);
            }
           return ListaA; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }        
    }  
}

