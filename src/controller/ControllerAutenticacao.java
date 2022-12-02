package controller;

import projeto.erro.AutenticacaoException;
import projeto.erro.ConexaoException;
import projeto.erro.DaoException;
import model.VO.Autenticacao;
import model.BO.RegraAutenticacao;

/**
 *
 * @author 
 */
public class ControllerAutenticacao {
    
    RegraAutenticacao ra = new RegraAutenticacao();
    
    /**
     * Metodo para inserir as credenciais de um funcionario
     * @param autenticacao
     * @throws AutenticacaoException
     * @throws ConexaoException
     * @throws DaoException 
     */
    public void inserirCredenciais(Autenticacao autenticacao) throws AutenticacaoException, ConexaoException, DaoException{
        ra.checarCredenciais(autenticacao);
        ra.inserirCredenciais(autenticacao);
    }
    
    /**
     * Metodo para checar a autenticação de um funcionario
     * @param login
     * @param pass
     * @return
     * @throws AutenticacaoException 
     */
    public Autenticacao checarAutenticação(String login, String pass) throws AutenticacaoException{
        ra.validarLogin(login, pass);
        
        Autenticacao autenticacao = ra.consultarCredenciais(login, pass);
        return autenticacao;
    }
    
    /**
     * Metodo para alterar as credenciais de um funcionario
     * @param oldPass
     * @param newPass
     * @param cpfCli
     * @throws AutenticacaoException
     * @throws ConexaoException
     * @throws DaoException 
     */
    public void alterarSenha(String oldPass, String newPass, String cpfCli) throws AutenticacaoException, ConexaoException, DaoException{
        ra.checarSenha(newPass);
        ra.checarSenha(oldPass);
        ra.alterarSenha(oldPass, newPass, cpfCli);
    }
    
}
