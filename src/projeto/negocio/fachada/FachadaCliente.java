package projeto.negocio.fachada;
import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import projeto.erro.RegraExceptionCliente;
import projeto.negocio.classesBasicas.Cliente;
import projeto.negocio.regras.RegraCliente;

/**
 *
 * @author Daiene
 */
public class FachadaCliente  {
    RegraCliente rn = new RegraCliente();
    
    /**
     * Metodo para validar cliente antes de inserir
     * @param cliente
     * @throws RegraExceptionCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    public void inserirCliente (Cliente cliente) throws RegraExceptionCliente, ConexaoException, DaoException {        
        rn.validaNome(cliente.getNome());
        rn.validaDocumento(cliente.getNrDocumento());
        rn.validarTelefone(cliente.getTelefone());
        rn.verificaDuplicidade(cliente);
        rn.inserirCliente(cliente);       
    }
    
    /**
     * Metodo para validar cliente antes de deletar
     * @param cliente
     * @throws RegraExceptionCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    public void deletarCliente (int codCliente) throws RegraExceptionCliente, ConexaoException, DaoException{
        rn.deletarCliente(codCliente);
    }
    
    /**
     * Metodo para validar cliente antes de alterar
     * @param cliente
     * @throws RegraExceptionCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    public void alterarCliente (Cliente cliente) throws RegraExceptionCliente, ConexaoException, DaoException{
        rn.validaNome(cliente.getNome());
        rn.validaDocumento(cliente.getNrDocumento());
        rn.atualizarCliente(cliente);  
    }
    
    /**
     * Consultar cliente
     * @param nrDocumento
     * @return cliente
     * @throws RegraExceptionCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    public Cliente consultarCliente (String nrDocumento) throws RegraExceptionCliente, ConexaoException, DaoException{
        rn.validaDocumento(nrDocumento);
        Cliente cliente = rn.consultarCliente(nrDocumento);
        return cliente;
    }
    
    /**
     * Consultar codigo do cliente
     * @param cpf
     * @return codigo do cliente
     * @throws RegraExceptionCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    public int consultarCodigoCliente (String nrDocumento) throws RegraExceptionCliente, ConexaoException, DaoException{
        rn.validaDocumento(nrDocumento);
        int codigoCliente = rn.consultarCodigoCliente(nrDocumento);
        return codigoCliente;
    }
    
    /**
     * Metodo para consultar todos os clientes.
     * @return
     * @throws RegraExceptionCliente
     * @throws ConexaoException
     * @throws DaoException 
     */
    public ArrayList<Cliente> listarCliente()throws RegraExceptionCliente, ConexaoException, DaoException{
        return rn.listar();      
    }
}

