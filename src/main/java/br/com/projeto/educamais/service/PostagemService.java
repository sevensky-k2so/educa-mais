package br.com.projeto.educamais.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.educamais.domain.Postagem;
import br.com.projeto.educamais.domain.Turma;
import br.com.projeto.educamais.domain.Usuario;
import br.com.projeto.educamais.exception.UsuarioNaoTemPermissaoParaEssaAtividadeException;
import br.com.projeto.educamais.repository.PostagemRepository;

@Service
public class PostagemService extends GenericService {

	@Autowired
	public TurmaService turmaService;
	
	@Autowired
	public PostagemRepository repository;
	
	@Transactional
	public void salvar(Long turmaId, Usuario usuario, Postagem postagem) {
		
		Turma turma = turmaService.obterTurmaPorId(turmaId);
		
		if(turma.professorIsNotEqualTo(usuario)) {
			throw new UsuarioNaoTemPermissaoParaEssaAtividadeException("Apenas o professor tem permissão para cadastrar postagens.");
		}
		
		preencherCamposAuditoria(postagem, turma.getProfessor());
		postagem = repository.saveAndFlush(postagem);
		
		turma.add(postagem);
		turmaService.atualizarDados(turma);
	}
	
}