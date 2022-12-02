
package model.VO;

/**
 *
 * @author 
 */
public class Autenticacao {
    
    private String login;
    private String pass;
    private String primeiroAcesso;
    private String nrDocumento_func;

    public Autenticacao() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPrimeiroAcesso() {
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(String primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }

    public String getNrDocumento_func() {
        return nrDocumento_func;
    }

    public void setNrDocumento_func(String nrDocumento_func) {
        this.nrDocumento_func = nrDocumento_func;
    }
    
    
}
