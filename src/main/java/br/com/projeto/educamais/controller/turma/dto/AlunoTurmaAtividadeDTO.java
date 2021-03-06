package br.com.projeto.educamais.controller.turma.dto;

import java.util.List;

import br.com.projeto.educamais.controller.atividade.dto.AlunoAtividadeDTO;
import br.com.projeto.educamais.domain.Atividade;
import br.com.projeto.educamais.domain.Turma;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlunoTurmaAtividadeDTO extends TurmaAtividadeDTO {

	private List<AlunoAtividadeDTO> atividades;
	
	public AlunoTurmaAtividadeDTO(Turma turma, List<Atividade> atividades) {
		super(turma);
		List<AlunoAtividadeDTO> atividadesDTO = new AlunoAtividadeDTO().converter(atividades);
		this.setAtividades(atividadesDTO);
	}
}
