package br.com.projeto.educamais.controller.turma.dto;

import br.com.projeto.educamais.controller.usuario.dto.UsuarioDTO;
import br.com.projeto.educamais.domain.Turma;
import lombok.Data;

@Data
public abstract class TurmaAtividadeDTO {

	private Long id;
	private String nome;
	private String codigo;
	private UsuarioDTO professor;
	
	public TurmaAtividadeDTO(Turma turma) {
		
		this.setId(turma.getId());
		this.setNome(turma.getNome());
		this.setCodigo(turma.getCodigo());
		this.setProfessor(new UsuarioDTO(turma.getProfessor()));
	}
}
