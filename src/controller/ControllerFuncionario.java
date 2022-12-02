
package controller;

import java.util.ArrayList;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import projeto.erro.RegraExceptionFuncionario;
import model.VO.Funcionario;
import model.BO.RegraFuncionario;

/**
 *
 * @author 
 */
public class ControllerFuncionario {
    
    RegraFuncionario regraFunc = new RegraFuncionario();

    /**
     * Metodo que checa as conformidades e solicita a inclusão no banco.
     * @param funcionario
     * @throws RegraExceptionFuncionario
     * @throws DaoException
     * @throws ConexaoException 
     */
    public void cadastrarFuncionario(Funcionario funcionario) throws RegraExceptionFuncionario, DaoException, ConexaoException{
        regraFunc.validaFuncionario(funcionario);
        regraFunc.verificaDuplicidade(funcionario);
        regraFunc.incluirFuncionario(funcionario);
    }
    
    /**
     * Metodo para deletar funcionario
     * @param idFuncionario
     * @throws RegraExceptionFuncionario
     * @throws DaoException
     * @throws ConexaoException 
     */
    public void deletarFuncionario(int idFuncionario) throws RegraExceptionFuncionario, DaoException, ConexaoException{
        regraFunc.deletarFuncionario(idFuncionario);
    }
    
    /**
     * Metodo para alterar funcionario
     * @param funcionario
     * @throws RegraExceptionFuncionario
     * @throws DaoException
     * @throws ConexaoException 
     */
    public void alterarFuncionario(Funcionario funcionario) throws RegraExceptionFuncionario,DaoException, ConexaoException{
        regraFunc.validaFuncionario(funcionario);
        regraFunc.alterarFuncionario(funcionario);
    }
    
    /**
     * Metodo para consultar um funcionario pelo CPF
     * @param nrDocumento
     * @return retorna um funcionario
     * @throws RegraExceptionFuncionario
     * @throws ConexaoException
     * @throws DaoException 
     */
    public Funcionario consultarFuncionario(String nrDocumento) throws RegraExceptionFuncionario, ConexaoException, DaoException{
        Funcionario funcionario  = regraFunc.consultarFuncionario(nrDocumento);
        return funcionario;
    }
    
    /**
     * Metodo para consultar todos os funcionarios do banco de dados
     * @return lista de funcionarios
     * @throws RegraExceptionFuncionario
     * @throws ConexaoException
     * @throws DaoException 
     */
    public ArrayList<Funcionario> listarFuncionarios() throws RegraExceptionFuncionario, ConexaoException, DaoException{
        return regraFunc.listarFuncionarios();
    }
     public ArrayList<Funcionario> consultarFuncionarios(String cargo) throws RegraExceptionFuncionario, ConexaoException, DaoException{
        return regraFunc.consultarFuncionarios(cargo);
    }
    
}
