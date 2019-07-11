package br.com.projeto.laacademia.domain.acesso;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.projeto.laacademia.domain.aluno.Aluno;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENTRADAS_SAIDAS")
public class Acesso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ALUNO_ID", nullable = false)
	private Aluno aluno = new Aluno();

	@Column(name = "ENTRADA", nullable = false)
	private LocalDateTime entrada;

	@Column(name = "SAIDA", nullable = true)
	private LocalDateTime saida;

	
	public boolean isEntradaSaidaPreenchidas() {
		
		if(entrada != null && saida != null) {
			
			return true;
		}
		
		return false;
	}
	
	public TipoAcesso registrarAcesso() {
		
		//Lógica do acesso está aqui - Data e hora do sistema no registro de E/S
		LocalDateTime now = LocalDateTime.now();
		TipoAcesso tipoAcesso;
		
		if(entrada == null) {
			
			entrada = now;
			tipoAcesso = TipoAcesso.Entrada;
		} else if(saida == null) {
			
			saida = now;
			tipoAcesso = TipoAcesso.Saida;
		} else {
			
			tipoAcesso = null;
		}
		
		return tipoAcesso;
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	@Override
	public String toString() {
		return "Acesso [id=" + id + ", aluno=" + aluno + ", entrada=" + entrada + ", saida=" + saida + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
