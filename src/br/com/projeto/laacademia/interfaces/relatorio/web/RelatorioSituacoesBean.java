package br.com.projeto.laacademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projeto.laacademia.application.service.AlunoService;
import br.com.projeto.laacademia.application.util.ExcecaoValidacao;
import br.com.projeto.laacademia.domain.aluno.Aluno;
import br.com.projeto.laacademia.domain.aluno.Aluno.Situacao;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class RelatorioSituacoesBean implements Serializable {

	@EJB
	private AlunoService alunoService;

	@Inject
	private FacesContext facesContext;

	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	private Situacao situacao;

	private List<Aluno> alunos;

	public void checagem() {

		String clear = requestParamsMap.get("clear");
		if (clear != null && Boolean.valueOf(clear)) {

			situacao = null;
			alunos = null;
		}
	}

	public String gerarRelatorio() {

		try {
			alunos = alunoService.listSituacoesAlunos(situacao);
		} catch (ExcecaoValidacao e) {

			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		return null;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
