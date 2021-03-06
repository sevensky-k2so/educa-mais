package br.com.projeto.educamais.controller.atividade.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.educamais.controller.usuario.dto.UsuarioDTO;
import br.com.projeto.educamais.domain.Atividade;
import br.com.projeto.educamais.domain.Pergunta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorAtividadeDTO {

	private Long id;
	
	private String titulo;
	
	private String codigo;
	
	private UsuarioDTO aluno;
	
	private double nota;
	
	private int tentativas;
	
	private boolean habilitada;
	
	private String dataEntrega;
	
	private List<Pergunta> perguntas;
	
	public ProfessorAtividadeDTO(Atividade atividade) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		this.id = atividade.getId();
		this.titulo = atividade.getTitulo();
		this.codigo = atividade.getCodigo();
		this.aluno = new UsuarioDTO(atividade.getAluno());
		this.nota = atividade.getNota();
		this.tentativas = atividade.getTentativas();
		this.habilitada = atividade.estaHabilitada();
		this.dataEntrega = atividade.getDataEntrega().format(formatter);
		this.perguntas = atividade.getPerguntas();
	}

	public List<ProfessorAtividadeDTO> converter(List<Atividade> atividades) {
		return atividades.stream()
				.map(atividade -> new ProfessorAtividadeDTO(atividade))
				.collect(Collectors.toList());
	}

}
