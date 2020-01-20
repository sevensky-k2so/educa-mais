package br.com.projeto.educamais.controller.postagem;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.educamais.controller.postagem.form.AtualizarPostagemForm;
import br.com.projeto.educamais.controller.postagem.form.PostagemForm;
import br.com.projeto.educamais.domain.Postagem;
import br.com.projeto.educamais.domain.Usuario;
import br.com.projeto.educamais.service.PostagemService;

@RestController
@RequestMapping("/educamais/turmas")
public class PostagemController {

	@Autowired
	public PostagemService postagemService;
	
	@PostMapping("/{id}/postagem")
	@Transactional
	public ResponseEntity<Postagem> cadastrarPostagem(@RequestBody @Valid PostagemForm form, @PathVariable("id") Long idTurma, Principal principal, UriComponentsBuilder uriBuilder) {
		
		//Recuperando usuário logado
		Usuario usuario = (Usuario) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		
		postagemService.salvar(idTurma, usuario, form.getPostagem());
		URI uri = uriBuilder.build().toUri();
		return created(uri).build();
	}
	
	@PutMapping("/{turmaId}/postagem/{postagemId}")
	@Transactional
	public ResponseEntity<Postagem> atualizarPostagem(@RequestBody @Valid AtualizarPostagemForm form, @PathVariable Long turmaId, @PathVariable Long postagemId, Principal principal, UriComponentsBuilder uriBuilder) {
		
		//Recuperando usuário logado
		Usuario usuario = (Usuario) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		
		postagemService.atualizarPostagem(turmaId, usuario, postagemId, form);
		return ok().build();
	}
	
	@DeleteMapping("/{turmaId}/postagem/{postagemId}")
	@Transactional
	public ResponseEntity<Postagem> deletarPostagem(@PathVariable Long turmaId, @PathVariable Long postagemId, Principal principal, UriComponentsBuilder uriBuilder) {
		
		//Recuperando usuário logado
		Usuario usuario = (Usuario) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
		postagemService.deletarPostagem(turmaId, usuario, postagemId);
		return ok().build();
	}
}
