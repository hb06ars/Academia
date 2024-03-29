package brandaoti.sistema.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Treino;
import brandaoti.sistema.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
	@Query(" select u from Usuario u where upper( u.email ) like upper( :email ) and upper( u.senha ) like upper( :senha ) ")
	Usuario fazerLogin(@Param("email") String login, @Param("senha") String senha);
	
	@Query(" select u from Usuario u where 1=1 and nome = '(:aluno.nome)' ")
	List<Usuario> filtro(@Param("aluno") Usuario aluno);
	
	@Query(" select u from Usuario u where 1=1 and u.perfil.aluno = TRUE and u.ativo = TRUE")
	List<Usuario> buscarAlunos();
	
	@Query(" select u from Usuario u where 1=1 and u.perfil.funcionario = TRUE and u.ativo = TRUE")
	List<Usuario> buscarFuncionarios();
	
	@Query(" select u from Usuario u where 1=1 and u.perfil.professor = TRUE and u.ativo = TRUE")
	List<Usuario> buscarProfessores();
	
	@Query(" select u from Usuario u where upper( u.matricula ) like upper( :matricula ) ")
	Usuario buscarMatricula(@Param("matricula") String matricula);
	
	@Query(" select u from Usuario u where upper( u.matricula ) like upper( :matricula ) or upper( u.cpf ) like upper( :cpf ) and u.perfil.aluno = TRUE and u.ativo = TRUE")
	List<Usuario> buscarAlunosRepetidos(@Param("matricula") String matricula, @Param("cpf") String cpf);
	
	@Query(" select u from Usuario u where upper( u.matricula ) like upper( :matricula ) or upper( u.cpf ) like upper( :cpf ) and u.perfil.funcionario = TRUE and u.ativo = TRUE")
	List<Usuario> buscarFuncionariosRepetidos(@Param("matricula") String matricula, @Param("cpf") String cpf);
	
	@Query(" select p from Usuario p where ativo = TRUE")
	List<Usuario> buscarTudo();
	
	@Query(" select p from Usuario p where extract(month from data_nascimento) = extract(month from sysdate) and extract(day from data_nascimento) = extract(day from sysdate) and ativo = TRUE ")
	List<Usuario> buscarAniversariantes();
	
	@Query(" select p from Usuario p where extract(month from dataDeCadastro) = extract(month from sysdate) and ativo = TRUE and YEAR(dataDeCadastro)=(YEAR(NOW())) ")
	List<Usuario> novosDoMes();
	
	@Query(" select u from Usuario u where 1=1 and u.perfil.aluno = TRUE and u.ativo = TRUE and u.perfil.admin = FALSE and u.perfil.funcionario = FALSE ")
	List<Usuario> buscarAlunosNaoFuncionarios();
	
	@Query(" select u from Usuario u where 1=1 and u.hashDigital like :hashDigital ")
	Usuario buscarDigital(@Param("hashDigital") String hashDigital);
	
	
}
