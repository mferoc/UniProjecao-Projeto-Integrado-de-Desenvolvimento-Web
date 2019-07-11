package br.com.projeto.laacademia.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.projeto.laacademia.application.util.ExcecaoValidacao;
import br.com.projeto.laacademia.application.util.StringUtils;
import br.com.projeto.laacademia.domain.acesso.Acesso;
import br.com.projeto.laacademia.domain.acesso.AcessoRepository;
import br.com.projeto.laacademia.domain.acesso.TipoAcesso;
import br.com.projeto.laacademia.domain.aluno.Aluno;
import br.com.projeto.laacademia.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public TipoAcesso registrarAcesso(String matricula, Integer rg) {
		
		if(StringUtils.estaVazia(matricula) && rg == null) {
			
			throw new ExcecaoValidacao("É preciso fornecer a matrícula ou o RG do aluno para realizar um acesso!");
		}
		
		Aluno aluno;
		if(StringUtils.estaVazia(matricula)) {
			
			aluno = alunoRepository.findByRG(rg);
		} else {
			
			aluno = alunoRepository.findByMatricula(matricula);
		}
		
		if(aluno == null) {
			
			throw new ExcecaoValidacao("O aluno não foi encontrado!");
		}
		
		//Ao registrar um novo acesso me baseio no último acesso registrado no sistema
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		//Atualizar a Entrada ou Saida
		if(ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			
			//Nunca entrou ou último acesso já está preenchido
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.gravar(ultimoAcesso);
		} else {
			
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		
		return tipoAcesso;
	}
}
