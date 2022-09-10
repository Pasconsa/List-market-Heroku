package br.com.springboot.rest_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.rest_treinamento.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%") //like pesquisar por partes 1 parametro
	List<Usuario> buscarPorNome(String name);													//trim tira o espaço da palavra de pesquisa do banco
}																							//uper tira espaço e coloca em maiusculo