package brandaoti.sistema.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Presenca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //Esse número é o ID automático gerado.
	
	@Column
	private LocalDateTime dataPresenca = LocalDate.now().atStartOfDay();
	
	@Column
	private LocalDateTime presenca = LocalDateTime.now();
	
	@OneToOne
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getPresenca() {
		return presenca;
	}

	public void setPresenca(LocalDateTime presenca) {
		this.presenca = presenca;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataPresenca() {
		return dataPresenca;
	}

	public void setDataPresenca(LocalDateTime dataPresenca) {
		this.dataPresenca = dataPresenca;
	}

	
	
	
	
}
