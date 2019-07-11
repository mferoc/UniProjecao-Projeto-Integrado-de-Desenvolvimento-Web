package br.com.projeto.laacademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projeto.laacademia.application.service.AlunoService;
import br.com.projeto.laacademia.application.util.StringUtils;
import br.com.projeto.laacademia.domain.aluno.Aluno;

@SuppressWarnings("serial")
@Named
@RequestScoped
public class AlunoBean implements Serializable {

	@EJB
	private AlunoService alunoService;
	
	@Inject
	private FacesContext facesContext;
	
	private Aluno aluno = new Aluno();
	
	private String matricula;
	
	private String titulo = "Novo aluno";

	public void carregar() {
		
		if(!StringUtils.estaVazia(matricula)) {
			
			aluno = alunoService.findByMatricula(matricula);
			titulo = "Alterar aluno";
		}
	}
	
	public String salvar() {
		
		alunoService.createOrUpdate(aluno);
		facesContext.addMessage(null, new FacesMessage("Dados cadastrados com sucesso!"));
		return null;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getTitulo() {
		return titulo;
	}

}
