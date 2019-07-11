package br.com.projeto.laacademia.application.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.projeto.laacademia.application.util.ExcecaoValidacao;
import br.com.projeto.laacademia.application.util.StringUtils;
import br.com.projeto.laacademia.application.util.Validacao;
import br.com.projeto.laacademia.domain.acesso.Acesso;
import br.com.projeto.laacademia.domain.aluno.Aluno;
import br.com.projeto.laacademia.domain.aluno.Aluno.Situacao;
import br.com.projeto.laacademia.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository;

	public void createOrUpdate(Aluno aluno) {

		if (StringUtils.estaVazia(aluno.getMatricula())) {

			create(aluno);
		} else {

			update(aluno);
		}

	}

	private void create(Aluno aluno) {

		Validacao.confirmarNaoVazio(aluno);

		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}

	private void update(Aluno aluno) {

		Validacao.confirmarNaoVazio(aluno);
		Validacao.confirmarNaoVazio(aluno.getMatricula());
		alunoRepository.update(aluno);
	}

	public void delete(String matricula) {

		alunoRepository.delete(matricula);
	}

	public Aluno findByMatricula(String matricula) {

		return alunoRepository.findByMatricula(matricula);
	}

	public List<Aluno> listAlunos(String matricula, String nome, Integer rg) {

		if (StringUtils.estaVazia(matricula) && StringUtils.estaVazia(nome) && rg == null) {

			throw new ExcecaoValidacao("Ao menos um critério de pesquisa deve ser fornecido!");
		}

		return alunoRepository.listAlunos(matricula, nome, rg);
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao) {
		
		Validacao.confirmarNaoVazio(situacao);
		return alunoRepository.listSituacoesAlunos(situacao);
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) {
		
		if(StringUtils.estaVazia(matricula) && dataInicial == null && dataFinal == null) {
			
			throw new ExcecaoValidacao("Ao menos um critério de pesquisa deve ser fornecido!");
		}
		
		return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
