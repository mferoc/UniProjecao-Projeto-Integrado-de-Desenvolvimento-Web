package br.com.projeto.laacademia.domain.aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.projeto.laacademia.application.util.StringUtils;
import br.com.projeto.laacademia.domain.acesso.Acesso;
import br.com.projeto.laacademia.domain.aluno.Aluno.Situacao;

@Stateless
public class AlunoRepository {

	@PersistenceContext
	private EntityManager em;

	public void store(Aluno aluno) {

		em.persist(aluno);
	}

	public void update(Aluno aluno) {

		em.merge(aluno);
	}

	public Aluno findByMatricula(String matricula) {

		return em.find(Aluno.class, matricula);
	}

	public Aluno findByRG(Integer rg) {

		try {
			return em.createQuery("SELECT a FROM Aluno a WHERE a.rg = :rg", Aluno.class).setParameter("rg", rg)
					.getSingleResult();
		} catch (NoResultException e) {

			return null;
		}
	}

	public void delete(String matricula) {

		Aluno aluno = findByMatricula(matricula);

		if (aluno != null) {

			em.remove(aluno);
		}
	}

	public String getMaxMatriculaAno() {

		return em.createQuery("SELECT MAX(a.matricula) FROM Aluno a WHERE a.matricula LIKE:ano", String.class)
				.setParameter("ano", Year.now() + "%").getSingleResult();
	}

	public List<Aluno> listAlunos(String matricula, String nome, Integer rg) {

		StringBuilder jpql = new StringBuilder("SELECT a FROM Aluno a WHERE ");

		if (!StringUtils.estaVazia(matricula)) {

			jpql.append("a.matricula = :matricula AND ");
		}

		if (!StringUtils.estaVazia(nome)) {

			jpql.append("a.nome LIKE :nome AND ");
		}

		if (rg != null) {

			jpql.append("a.rg = :rg AND ");
		}

		jpql.append("1 = 1"); // concatenar com condição verdadeira
		TypedQuery<Aluno> q = em.createQuery(jpql.toString(), Aluno.class);

		if (!StringUtils.estaVazia(matricula)) {

			q.setParameter("matricula", matricula);
		}

		if (!StringUtils.estaVazia(nome)) {

			q.setParameter("nome", "%" + nome + "%");
		}

		if (rg != null) {

			q.setParameter("rg", rg);
		}

		return q.getResultList();

	}

	public List<Aluno> listSituacoesAlunos(Situacao situacao) {

		return em.createQuery("SELECT a FROM Aluno a WHERE a.situacao = :situacao ORDER BY a.nome", Aluno.class)
				.setParameter("situacao", situacao).getResultList();
	}

	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) {

		StringBuilder jpql = new StringBuilder("SELECT a FROM Acesso a WHERE ");

		if (!StringUtils.estaVazia(matricula)) {

			jpql.append("a.aluno.matricula = :matricula AND ");
		}

		if (dataInicial != null) {

			jpql.append("a.entrada >= :entradaInicio AND ");
		}

		if (dataFinal != null) {

			jpql.append("a.saida <= :saidaFim AND ");
		}

		jpql.append("1 = 1 ORDER BY a.entrada");
		
		TypedQuery<Acesso> q = em.createQuery(jpql.toString(), Acesso.class);
		
		if (!StringUtils.estaVazia(matricula)) {

			q.setParameter("matricula", matricula);
		}

		if (dataInicial != null) {

			LocalDateTime ldt = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
			q.setParameter("entradaInicio", ldt);
			
		}

		if (dataFinal != null) {
						
			LocalDateTime ldt = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));
			q.setParameter("saidaFim", ldt);
		}
		
		return q.getResultList();
	}

}
