package br.com.springboot.rest_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.rest_treinamento.model.Usuario;
import br.com.springboot.rest_treinamento.repository.UsuarioRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired  // IC ,CDI ,CD - iNJEÇÃO DE DEPENDENCIAS
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
    
     */
   /* @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }*/
    
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {

		Usuario usuario = new Usuario();
		usuario.setNome(nome);

		usuarioRepository.save(usuario);/* grava no banco de dados */

		return "Ola mundo " + nome;
	}
    
    @GetMapping(value = "listatodos") /* Nosso primeiro método de API */
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();/* executa a consulta no banco de dados */

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/* Retorna a lista em JSON */
	
    }
		
		@PostMapping(value = "salvar") /* mapeia a url */
		@ResponseBody /* Descricao da resposta */
		public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */

			Usuario user = usuarioRepository.save(usuario);

			return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);	
	}
		
		
		@DeleteMapping(value = "delete") /* mapeia a url */
		@ResponseBody /* Descricao da resposta */
		public ResponseEntity<String> delete(@RequestParam Long iduser) { /* Recebe os dados para delete */

			usuarioRepository.deleteById(iduser);

			return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);

		}
		
		
		@GetMapping(value = "buscaruserid") /* mapeia a url */
		@ResponseBody /* Descricao da resposta */
		public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) { /* Recebe os dados para consultar */

			Usuario usuario = usuarioRepository.findById(iduser).get(); //Pesquisar no banco de dados

			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); //Fazer retorno na tela

		}
		
		@PutMapping(value = "atualizar") /* mapeia a url */
		@ResponseBody /* Descricao da resposta */
		public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */  //Quando coloca ? pode dar retorno generico
			
			if (usuario.getId() == null) {
				return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK); //para ter obrigatorio um ID
			}

			Usuario user = usuarioRepository.saveAndFlush(usuario); //saveandFlush salva e roda direto do banco de dados

			return new ResponseEntity<Usuario>(user, HttpStatus.OK);

		}
		
		@GetMapping(value = "buscarPorNome") /* mapeia a url , procurar palavra dentro do nome*/ 
		@ResponseBody /* Descricao da resposta */
		public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) { /* Recebe os dados para consultar */

			List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());  //trim tira o espaço , letra maiuscula é aceitavel toupercase

			return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

		}
}
