package it.prova.triage.dto;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PazienteDTO {


	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	
	private LocalDate dataRegistrazione;
	
	private StatoPaziente statoPaziente;
	
	private String codiceDottore;
	
	public Paziente buildPazienteModel() {
		Paziente result = Paziente.builder().id(this.id).nome(this.nome).cognome(this.cognome).codiceFiscale(codiceFiscale).statoPaziente(this.statoPaziente).registrazione(this.dataRegistrazione).build();
		return result;
	}
	
	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = PazienteDTO.builder().id(pazienteModel.getId()).nome(pazienteModel.getNome()).cognome(pazienteModel.getCognome()).codiceFiscale(pazienteModel.getCodiceFiscale()).statoPaziente(pazienteModel.getStatoPaziente()).dataRegistrazione(pazienteModel.getRegistrazione()).build();
		return result;
	}
	
	public static List<PazienteDTO> createPazienteDTOListFromModelList(
			List<Paziente> modelListInput) {
		return modelListInput.stream().map(inputEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(inputEntity);
		}).collect(Collectors.toList());
	}
	
}
