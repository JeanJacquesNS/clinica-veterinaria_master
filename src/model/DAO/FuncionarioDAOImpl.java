/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import model.VO.Funcionario;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 *
 * @author 
 */
public class FuncionarioDAOImpl implements FuncionarioDAO{
     private final GerenciadorConexao ger;
    public FuncionarioDAOImpl(){
        ger = GerenciadorConexaoImpl.getInstancia();
    }
    
    /**
     * Metodo para inserir os dados do funcionrio no banco de dados
     * @param funcionario
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public void inserir(Funcionario funcionario) throws DaoException, ConexaoException {
        
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO funcionario (nome,sexo,cargo,tipo_documento,nr_documento,telefone,telefone_2,email,bairro,avenida,rua) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getSexo());
            pstm.setString(3, funcionario.getCargo());
            pstm.setString(4, funcionario.getTipoDocumento());
            pstm.setString(5, funcionario.getNrDocumento());
            pstm.setString(6, funcionario.getTelefone());
            pstm.setString(7, funcionario.getTelefone2());
            pstm.setString(8, funcionario.getEmail());
            pstm.setString(9, funcionario.getBairro());
            pstm.setString(10, funcionario.getAvenida());
            pstm.setString(11, funcionario.getRua());
            pstm.executeUpdate();
            Msg.msgSucesso("Funcionario inserido com sucesso", "Sucesso ao inserir Cliente");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para alterar um funcionario
     * @param funcionario
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public void alterar(Funcionario funcionario) throws DaoException, ConexaoException {
        
        Connection con = ger.abrirConexao();
        String sql = "UPDATE funcionario SET nome=?,sexo=?,cargo=?,tipo_documento=?,nr_documento=?,telefone=?,telefone_2=?,email=?,bairro=?,avenida=?,rua=?  WHERE idFuncionario = ?";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getSexo());
            pstm.setString(3, funcionario.getCargo());
            pstm.setString(4, funcionario.getTipoDocumento());
            pstm.setString(5, funcionario.getNrDocumento());
            pstm.setString(6, funcionario.getTelefone());
            pstm.setString(7, funcionario.getTelefone2());
            pstm.setString(8, funcionario.getEmail());
            pstm.setString(9, funcionario.getBairro());
            pstm.setString(10, funcionario.getAvenida());
            pstm.setString(11, funcionario.getRua());
            pstm.setInt(12,funcionario.getIdFuncionario());
            pstm.executeUpdate(); 
            Msg.msgSucesso("Funcionario alterado com sucesso", "Sucesso ao alterar");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para deletar um funcionario do banco de dados
     * @param idFunc
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public void deletar(int idFunc) throws DaoException, ConexaoException {
        
        Connection con = ger.abrirConexao();
        String sql = "DELETE FROM funcionario WHERE idFuncionario = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idFunc);
            pstm.executeUpdate();
            Msg.msgSucesso("Funcionario deletado do BD com sucesso!", "Sucesso ao deletar Funcionario");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para consultar um funcionario no banco de dados
     * @param nrDocumento
     * @return Funcionario funcionario
     * @throws DaoException
     * @throws ConexaoException 
     */
    @Override
    public Funcionario consultar(String nrDocumento) throws DaoException, ConexaoException {
        
        Connection con = ger.abrirConexao();
        Funcionario func = null;
        String sql = "SELECT * FROM funcionario WHERE nr_documento=?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,nrDocumento);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                func = new Funcionario();
                func.setIdFuncionario(result.getInt("idFuncionario"));
                func.setNome(result.getString("nome"));
                func.setSexo(result.getString("sexo"));
                func.setCargo(result.getString("cargo"));
                func.setTipoDocumento(result.getString("tipo_documento"));
                func.setNrDocumento(result.getString("nr_documento"));
                func.setTelefone(result.getString("telefone"));
                func.setTelefone2(result.getString("telefone_2"));
                func.setEmail(result.getString("email"));
                func.setBairro(result.getString("bairro"));
                func.setAvenida(result.getString("avenida"));
                func.setRua(result.getString("rua"));
            }
            return func; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
    }

    /**
     * Metodo que lista todos os funcionarios e retorna em um Array.
     * @return lista de funcionarios.
     * @throws ConexaoException
     * @throws DaoException Veterinario
     */
    @Override
    public ArrayList<Funcionario> consultarFuncionarios() throws ConexaoException, DaoException {
        Connection con = ger.abrirConexao();
        Funcionario func;
        ArrayList <Funcionario> ListaFunc = new ArrayList();
        String sql = "SELECT * FROM funcionario";

       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while(result.next()){
                func = new Funcionario();
                func.setIdFuncionario(result.getInt("idFuncionario"));
                func.setNome(result.getString("nome"));
                func.setSexo(result.getString("sexo"));
                 func.setCargo(result.getString("cargo"));
                func.setTipoDocumento(result.getString("tipo_documento"));
                func.setNrDocumento(result.getString("nr_documento"));
                func.setTelefone(result.getString("telefone"));
                func.setTelefone2(result.getString("telefone_2"));
                func.setEmail(result.getString("email"));
                func.setBairro(result.getString("bairro"));
                func.setAvenida(result.getString("avenida"));
                func.setRua(result.getString("rua"));
                ListaFunc.add(func);
            }
           return ListaFunc; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    } 
     public ArrayList<Funcionario> consultarFuncionarios(String cargo) throws ConexaoException, DaoException {
        Connection con = ger.abrirConexao();
        Funcionario func;
        ArrayList <Funcionario> ListaFunc = new ArrayList();
        String sql = "SELECT * FROM funcionario WHERE cargo=?";

       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,cargo);
            ResultSet result = pstm.executeQuery();
            while(result.next()){
                func = new Funcionario();
                func.setIdFuncionario(result.getInt("idFuncionario"));
                func.setNome(result.getString("nome"));
                func.setSexo(result.getString("sexo"));
                func.setCargo(result.getString("cargo"));
                func.setTipoDocumento(result.getString("tipo_documento"));
                func.setNrDocumento(result.getString("nr_documento"));
                func.setTelefone(result.getString("telefone"));
                func.setTelefone2(result.getString("telefone_2"));
                func.setEmail(result.getString("email"));
                func.setBairro(result.getString("bairro"));
                func.setAvenida(result.getString("avenida"));
                func.setRua(result.getString("rua"));
                ListaFunc.add(func);
            }
           return ListaFunc; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    } 
}
