package br.com.projeto.laacademia.domain.acesso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.projeto.laacademia.domain.aluno.Aluno;

@Stateless
public class AcessoRepository {

	@PersistenceContext
	private EntityManager em;
	
	//Ao registrar um novo acesso me baseio no último acesso registrado no sistema
	//Aqui eu pego esses dados
	public Acesso findUltimoAcesso(Aluno aluno) {
		
		try {
			return em.createQuery("SELECT a FROM Acesso a WHERE a.aluno.matricula = :matricula ORDER BY a.id DESC", Acesso.class)
					.setParameter("matricula", aluno.getMatricula())
					.setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e){
			
			return null;
		}
	}
	
	public void gravar(Acesso acesso) {
		
		em.persist(acesso);
	}
	
}
