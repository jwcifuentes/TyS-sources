package co.com.mintrabajo.tys.json.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Notificacion {

	private String comando;
	private ListaDistribucion dto;


	@Data
	@AllArgsConstructor
	@Builder(builderMethodName = "newInstance")
	public static class ListaDistribucion {

		@Singular
		private List<CorreoPersona> destinatarios;
		private String asunto;
		private String contenido;
		private String userMail;
		
	}

	@Data
	@AllArgsConstructor
	@Builder(builderMethodName = "newInstance")
	public static class CorreoPersona{
		
		private String correo;	
		
	}
	
}




