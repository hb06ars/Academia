package brandaoti.sistema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //Esse número é o ID automático gerado.
	
	@Column
	private String codigo;
	
	@OneToOne
	private Usuario aluno;
	
	@Column
	private Boolean ativo = true;
	
	@Column
	private String observacoes;

	@Column
	private Date inicio = new Date();
	
	@Column
	private Date fim;
	
	@OneToOne
	private Usuario avaliador;

	@Column
	private Date inicioAvaliacao = new Date();
	@Column
	private Date ultimaVisita = new Date();
	
	@Column 
	private Double idade;
	@Column 
	private Double peso;
	@Column 
	private Double altura;
	@Column 
	private Double triceps;
	@Column 
	private Double abdomenDobra;
	@Column 
	private Double bracoDir;
	@Column 
	private Double bracoEsq;
	@Column 
	private Double antiBracoDir;
	@Column 
	private Double antiBracoEsq;
	@Column 
	private Double abdomenCirc;
	@Column 
	private Double quadril;
	@Column 
	private Double cintura;
	@Column 
	private Double coxaDir;
	@Column 
	private Double coxaEsq;
	@Column 
	private Double pernaDir;
	@Column 
	private Double pernaEsq;
	@Column 
	private Double radio;
	@Column 
	private Double femur;
	@Column 
	private Double gorduraCorporal;
	@Column 
	private Double classificacao;
	@Column 
	private Double massaMagra;
	@Column 
	private Double massaGorda;
	@Column 
	private Double massaMuscular;
	@Column 
	private Double massaOssea;
	@Column 
	private Double massaResidual;
	@Column 
	private Double icq;
	@Column 
	private Double imc;
	@Column 
	private Double classificacaoIMC;
	@Column 
	private Double classificacaoICQ;
	
	
	
	// Valores e limites de seu percentual
	
	@Column 
	private Double limite_peso = 0.0;
	@Column 
	private Double limite_altura = 0.0;
	@Column 
	private Double limite_triceps = 0.0;
	@Column 
	private Double limite_abdomenDobra = 0.0;
	@Column 
	private Double limite_bracoDir = 0.0;
	@Column 
	private Double limite_bracoEsq = 0.0;
	@Column 
	private Double limite_antiBracoDir = 0.0;
	@Column 
	private Double limite_antiBracoEsq = 0.0;
	@Column 
	private Double limite_abdomenCirc = 0.0;
	@Column 
	private Double limite_quadril = 0.0;
	@Column 
	private Double limite_cintura = 0.0;
	@Column 
	private Double limite_coxaDir = 0.0;
	@Column 
	private Double limite_coxaEsq = 0.0;
	@Column 
	private Double limite_pernaDir = 0.0;
	@Column 
	private Double limite_pernaEsq = 0.0;
	@Column 
	private Double limite_radio = 0.0;
	@Column 
	private Double limite_femur = 0.0;
	@Column 
	private Double limite_gorduraCorporal = 0.0;
	@Column 
	private Double limite_classificacao = 0.0;
	@Column 
	private Double limite_massaMagra = 0.0;
	@Column 
	private Double limite_massaGorda = 0.0;
	@Column 
	private Double limite_massaMuscular = 0.0;
	@Column 
	private Double limite_massaOssea = 0.0;
	@Column 
	private Double limite_massaResidual = 0.0;
	@Column 
	private Double limite_icq = 0.0;
	@Column 
	private Double limite_imc = 0.0;
	@Column 
	private Double limite_classificacaoIMC = 0.0;
	@Column 
	private Double limite_classificacaoICQ = 0.0;
	
	// Valores e limites do percentual
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Usuario getAluno() {
		return aluno;
	}
	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Usuario getAvaliador() {
		return avaliador;
	}
	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}
	public Date getInicioAvaliacao() {
		return inicioAvaliacao;
	}
	public void setInicioAvaliacao(Date inicioAvaliacao) {
		this.inicioAvaliacao = inicioAvaliacao;
	}
	public Date getUltimaVisita() {
		return ultimaVisita;
	}
	public void setUltimaVisita(Date ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}
	public Double getIdade() {
		return idade;
	}
	public void setIdade(Double idade) {
		this.idade = idade;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getTriceps() {
		return triceps;
	}
	public void setTriceps(Double triceps) {
		this.triceps = triceps;
	}
	public Double getAbdomenDobra() {
		return abdomenDobra;
	}
	public void setAbdomenDobra(Double abdomenDobra) {
		this.abdomenDobra = abdomenDobra;
	}
	public Double getBracoDir() {
		return bracoDir;
	}
	public void setBracoDir(Double bracoDir) {
		this.bracoDir = bracoDir;
	}
	public Double getBracoEsq() {
		return bracoEsq;
	}
	public void setBracoEsq(Double bracoEsq) {
		this.bracoEsq = bracoEsq;
	}
	public Double getAntiBracoDir() {
		return antiBracoDir;
	}
	public void setAntiBracoDir(Double antiBracoDir) {
		this.antiBracoDir = antiBracoDir;
	}
	public Double getAntiBracoEsq() {
		return antiBracoEsq;
	}
	public void setAntiBracoEsq(Double antiBracoEsq) {
		this.antiBracoEsq = antiBracoEsq;
	}
	public Double getAbdomenCirc() {
		return abdomenCirc;
	}
	public void setAbdomenCirc(Double abdomenCirc) {
		this.abdomenCirc = abdomenCirc;
	}
	public Double getQuadril() {
		return quadril;
	}
	public void setQuadril(Double quadril) {
		this.quadril = quadril;
	}
	public Double getCintura() {
		return cintura;
	}
	public void setCintura(Double cintura) {
		this.cintura = cintura;
	}
	public Double getCoxaDir() {
		return coxaDir;
	}
	public void setCoxaDir(Double coxaDir) {
		this.coxaDir = coxaDir;
	}
	public Double getCoxaEsq() {
		return coxaEsq;
	}
	public void setCoxaEsq(Double coxaEsq) {
		this.coxaEsq = coxaEsq;
	}
	public Double getPernaDir() {
		return pernaDir;
	}
	public void setPernaDir(Double pernaDir) {
		this.pernaDir = pernaDir;
	}
	public Double getPernaEsq() {
		return pernaEsq;
	}
	public void setPernaEsq(Double pernaEsq) {
		this.pernaEsq = pernaEsq;
	}
	public Double getRadio() {
		return radio;
	}
	public void setRadio(Double radio) {
		this.radio = radio;
	}
	public Double getFemur() {
		return femur;
	}
	public void setFemur(Double femur) {
		this.femur = femur;
	}
	public Double getGorduraCorporal() {
		return gorduraCorporal;
	}
	public void setGorduraCorporal(Double gorduraCorporal) {
		this.gorduraCorporal = gorduraCorporal;
	}
	public Double getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Double classificacao) {
		this.classificacao = classificacao;
	}
	public Double getMassaMagra() {
		return massaMagra;
	}
	public void setMassaMagra(Double massaMagra) {
		this.massaMagra = massaMagra;
	}
	public Double getMassaGorda() {
		return massaGorda;
	}
	public void setMassaGorda(Double massaGorda) {
		this.massaGorda = massaGorda;
	}
	public Double getMassaMuscular() {
		return massaMuscular;
	}
	public void setMassaMuscular(Double massaMuscular) {
		this.massaMuscular = massaMuscular;
	}
	public Double getMassaOssea() {
		return massaOssea;
	}
	public void setMassaOssea(Double massaOssea) {
		this.massaOssea = massaOssea;
	}
	public Double getMassaResidual() {
		return massaResidual;
	}
	public void setMassaResidual(Double massaResidual) {
		this.massaResidual = massaResidual;
	}
	public Double getIcq() {
		return icq;
	}
	public void setIcq(Double icq) {
		this.icq = icq;
	}
	public Double getImc() {
		return imc;
	}
	public void setImc(Double imc) {
		this.imc = imc;
	}
	public Double getClassificacaoIMC() {
		return classificacaoIMC;
	}
	public void setClassificacaoIMC(Double classificacaoIMC) {
		this.classificacaoIMC = classificacaoIMC;
	}
	public Double getClassificacaoICQ() {
		return classificacaoICQ;
	}
	public void setClassificacaoICQ(Double classificacaoICQ) {
		this.classificacaoICQ = classificacaoICQ;
	}
	public Double getLimite_peso() {
		return limite_peso;
	}
	public void setLimite_peso(Double limite_peso) {
		this.limite_peso = limite_peso;
	}
	public Double getLimite_altura() {
		return limite_altura;
	}
	public void setLimite_altura(Double limite_altura) {
		this.limite_altura = limite_altura;
	}
	public Double getLimite_triceps() {
		return limite_triceps;
	}
	public void setLimite_triceps(Double limite_triceps) {
		this.limite_triceps = limite_triceps;
	}
	public Double getLimite_abdomenDobra() {
		return limite_abdomenDobra;
	}
	public void setLimite_abdomenDobra(Double limite_abdomenDobra) {
		this.limite_abdomenDobra = limite_abdomenDobra;
	}
	public Double getLimite_bracoDir() {
		return limite_bracoDir;
	}
	public void setLimite_bracoDir(Double limite_bracoDir) {
		this.limite_bracoDir = limite_bracoDir;
	}
	public Double getLimite_bracoEsq() {
		return limite_bracoEsq;
	}
	public void setLimite_bracoEsq(Double limite_bracoEsq) {
		this.limite_bracoEsq = limite_bracoEsq;
	}
	public Double getLimite_antiBracoDir() {
		return limite_antiBracoDir;
	}
	public void setLimite_antiBracoDir(Double limite_antiBracoDir) {
		this.limite_antiBracoDir = limite_antiBracoDir;
	}
	public Double getLimite_antiBracoEsq() {
		return limite_antiBracoEsq;
	}
	public void setLimite_antiBracoEsq(Double limite_antiBracoEsq) {
		this.limite_antiBracoEsq = limite_antiBracoEsq;
	}
	public Double getLimite_abdomenCirc() {
		return limite_abdomenCirc;
	}
	public void setLimite_abdomenCirc(Double limite_abdomenCirc) {
		this.limite_abdomenCirc = limite_abdomenCirc;
	}
	public Double getLimite_quadril() {
		return limite_quadril;
	}
	public void setLimite_quadril(Double limite_quadril) {
		this.limite_quadril = limite_quadril;
	}
	public Double getLimite_cintura() {
		return limite_cintura;
	}
	public void setLimite_cintura(Double limite_cintura) {
		this.limite_cintura = limite_cintura;
	}
	public Double getLimite_coxaDir() {
		return limite_coxaDir;
	}
	public void setLimite_coxaDir(Double limite_coxaDir) {
		this.limite_coxaDir = limite_coxaDir;
	}
	public Double getLimite_coxaEsq() {
		return limite_coxaEsq;
	}
	public void setLimite_coxaEsq(Double limite_coxaEsq) {
		this.limite_coxaEsq = limite_coxaEsq;
	}
	public Double getLimite_pernaDir() {
		return limite_pernaDir;
	}
	public void setLimite_pernaDir(Double limite_pernaDir) {
		this.limite_pernaDir = limite_pernaDir;
	}
	public Double getLimite_pernaEsq() {
		return limite_pernaEsq;
	}
	public void setLimite_pernaEsq(Double limite_pernaEsq) {
		this.limite_pernaEsq = limite_pernaEsq;
	}
	public Double getLimite_radio() {
		return limite_radio;
	}
	public void setLimite_radio(Double limite_radio) {
		this.limite_radio = limite_radio;
	}
	public Double getLimite_femur() {
		return limite_femur;
	}
	public void setLimite_femur(Double limite_femur) {
		this.limite_femur = limite_femur;
	}
	public Double getLimite_gorduraCorporal() {
		return limite_gorduraCorporal;
	}
	public void setLimite_gorduraCorporal(Double limite_gorduraCorporal) {
		this.limite_gorduraCorporal = limite_gorduraCorporal;
	}
	public Double getLimite_classificacao() {
		return limite_classificacao;
	}
	public void setLimite_classificacao(Double limite_classificacao) {
		this.limite_classificacao = limite_classificacao;
	}
	public Double getLimite_massaMagra() {
		return limite_massaMagra;
	}
	public void setLimite_massaMagra(Double limite_massaMagra) {
		this.limite_massaMagra = limite_massaMagra;
	}
	public Double getLimite_massaGorda() {
		return limite_massaGorda;
	}
	public void setLimite_massaGorda(Double limite_massaGorda) {
		this.limite_massaGorda = limite_massaGorda;
	}
	public Double getLimite_massaMuscular() {
		return limite_massaMuscular;
	}
	public void setLimite_massaMuscular(Double limite_massaMuscular) {
		this.limite_massaMuscular = limite_massaMuscular;
	}
	public Double getLimite_massaOssea() {
		return limite_massaOssea;
	}
	public void setLimite_massaOssea(Double limite_massaOssea) {
		this.limite_massaOssea = limite_massaOssea;
	}
	public Double getLimite_massaResidual() {
		return limite_massaResidual;
	}
	public void setLimite_massaResidual(Double limite_massaResidual) {
		this.limite_massaResidual = limite_massaResidual;
	}
	public Double getLimite_icq() {
		return limite_icq;
	}
	public void setLimite_icq(Double limite_icq) {
		this.limite_icq = limite_icq;
	}
	public Double getLimite_imc() {
		return limite_imc;
	}
	public void setLimite_imc(Double limite_imc) {
		this.limite_imc = limite_imc;
	}
	public Double getLimite_classificacaoIMC() {
		return limite_classificacaoIMC;
	}
	public void setLimite_classificacaoIMC(Double limite_classificacaoIMC) {
		this.limite_classificacaoIMC = limite_classificacaoIMC;
	}
	public Double getLimite_classificacaoICQ() {
		return limite_classificacaoICQ;
	}
	public void setLimite_classificacaoICQ(Double limite_classificacaoICQ) {
		this.limite_classificacaoICQ = limite_classificacaoICQ;
	}
	
	
	
	
	
	
}
