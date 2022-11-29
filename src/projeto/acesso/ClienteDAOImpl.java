package projeto.acesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import projeto.negocio.classesBasicas.Cliente;
import projeto.util.GerenciadorConexao;
import projeto.util.GerenciadorConexaoImpl;
import projeto.util.Msg;

/**
 *
 * @author Daiene
 */
public class ClienteDAOImpl implements ClienteDAO{
    private final GerenciadorConexao ger;
    public ClienteDAOImpl(){
        ger = GerenciadorConexaoImpl.getInstancia();
    }
    
    /**
     * Metodo para consultar um cliente
     * @param cpf
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public Cliente consultar(String nrDocumento) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE nr_documento=?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,nrDocumento);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                cliente = new Cliente();
                cliente.setCodigoCliente(result.getInt("idCliente"));
                cliente.setNome(result.getString("nome"));  
                cliente.setSexo(result.getString("sexo"));
                cliente.setTipoDocumento(result.getString("tipo_documento"));
                cliente.setNrDocumento(result.getString("nr_documento"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setTelefone2(result.getString("telefone_2"));
                cliente.setBairro(result.getString("bairro"));
                cliente.setAvenida(result.getString("avenida"));
                cliente.setRua(result.getString("rua"));
            }
            return cliente; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
    }
    
    /**
     * Metodo para consultar por ID
     * @param cpf
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public int consultarId(String nrDocumento) throws ConexaoException, DaoException{
        Connection con = ger.abrirConexao();
        int codigoCliente = 0;
        String sql = "SELECT idCliente FROM cliente WHERE nr_documento = ?";
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,nrDocumento);
            ResultSet result = pstm.executeQuery();
            if(result.first()){
                codigoCliente = result.getInt("idCliente");             
            }
            return codigoCliente; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }   
    }

    /**
     * Metodo para inserir um cliente no BD
     * @param cliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void inserir(Cliente cliente) throws ConexaoException, DaoException {
        
        Connection con = ger.abrirConexao();
        String sql = "INSERT INTO cliente  (nome,sexo,tipo_documento,nr_documento,telefone,telefone_2,bairro,avenida,rua) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2,cliente.getSexo());
            pstm.setString(3,cliente.getTipoDocumento());
            pstm.setString(4, cliente.getNrDocumento());
            pstm.setString(5, cliente.getTelefone());
            pstm.setString(6,cliente.getTelefone2());
            pstm.setString(7, cliente.getBairro());
            pstm.setString(8, cliente.getAvenida());
            pstm.setString(9, cliente.getRua());
            pstm.executeUpdate();
            Msg.msgSucesso("Cliente inserido com sucesso", "Sucesso ao inserir Cliente");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para deletar do BD
     * @param codCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void deletar(int codCliente) throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, codCliente);
            pstm.executeUpdate();
            Msg.msgSucesso("Cliente deletado do BD com sucesso!", "Sucesso ao deletar Cliente");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para atualizar no BD
     * @param cliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public void atualizar(Cliente cliente) throws ConexaoException, DaoException {
      
        Connection con = ger.abrirConexao();
        String sql = "UPDATE cliente SET nome = ?,sexo = ?, tipo_documento = ?, nr_documento = ?, telefone = ?, telefone_2 = ?, bairro = ?,avenida = ?,rua = ? WHERE idCliente = ?";
        try { 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setString(2,cliente.getSexo());
            pstm.setString(3,cliente.getTipoDocumento());
            pstm.setString(4, cliente.getNrDocumento());
            pstm.setString(5, cliente.getTelefone());
            pstm.setString(6,cliente.getTelefone2());
            pstm.setString(7, cliente.getBairro());
            pstm.setString(8, cliente.getAvenida());
            pstm.setString(9, cliente.getRua());
            pstm.setInt(10,cliente.getCodigoCliente());
            pstm.executeUpdate(); 
            Msg.msgSucesso("Cliente alterado com sucesso", "Sucesso ao alterar");
        } catch (SQLException ex) {
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }
    }

    /**
     * Metodo para listar todos os Clientes do BD
     * @return
     * @throws ConexaoException
     * @throws DaoException 
     */
    @Override
    public ArrayList<Cliente> listar() throws ConexaoException, DaoException {
       
        Connection con = ger.abrirConexao();
        Cliente cliente;
        ArrayList <Cliente> Lista = new ArrayList();
        String sql = "SELECT * FROM CLIENTE";

       try{
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while(result.next()){
               cliente = new Cliente();
                cliente.setCodigoCliente(result.getInt("idCliente"));
                cliente.setNome(result.getString("nome"));  
                cliente.setSexo(result.getString("sexo"));
                cliente.setTipoDocumento(result.getString("tipo_documento"));
                cliente.setNrDocumento(result.getString("nr_documento"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setTelefone2(result.getString("telefone_2"));
                cliente.setBairro(result.getString("bairro"));
                cliente.setAvenida(result.getString("avenida"));
                cliente.setRua(result.getString("rua"));
                Lista.add(cliente);
            }
           return Lista; 
        }catch(SQLException e){
            throw new DaoException();
        }finally{
            ger.fecharConexao(con);
        }        
    }  
}
