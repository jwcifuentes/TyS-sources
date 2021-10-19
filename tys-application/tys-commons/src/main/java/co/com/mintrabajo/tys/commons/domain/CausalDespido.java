package co.com.mintrabajo.tys.commons.domain;

import co.com.mintrabajo.tys.commons.domain.JustaCausa.JustaCausaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class CausalDespido {

	private Long idCausalDespido;
	private Long codCausalDespido;
	private String descripcionCausalDespido;

	public CausalDespido() {
		super();
	}

}
