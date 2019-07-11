package br.com.projeto.laacademia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projeto.laacademia.application.service.AcessoService;
import br.com.projeto.laacademia.application.util.ExcecaoValidacao;
import br.com.projeto.laacademia.domain.acesso.TipoAcesso;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	@EJB
	private AcessoService acessoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String matricula;
	private Integer rg;
	
	public String registrarAcesso() {
		
		TipoAcesso tipoAcesso;
		
		try {
			
			tipoAcesso = acessoService.registrarAcesso(matricula, rg);
		} catch (ExcecaoValidacao e) {
			
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		String msg;
		if(tipoAcesso == TipoAcesso.Entrada) {
			
			msg = "Entrada registrada!";
		} else if(tipoAcesso == TipoAcesso.Saida) {
			
			msg = "Saída registrada!";
		} else {
			
			msg = "Dados de registro de acesso inconsistentes!";
		}
		
		facesContext.addMessage(null, new FacesMessage(msg));
		return null;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Integer getRg() {
		return rg;
	}
	public void setRg(Integer rg) {
		this.rg = rg;
	}
	
	
}
