package co.com.mintrabajo.tys.adapters;

import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractBroker {

	@Value("${system.services.params.canal}")
	protected String canal;
	@Value("${system.services.params.peticion}")
	protected String peticion;
	@Value("${system.services.params.usuario}")
	protected String usuario;

	public AbstractBroker() {
		super();
	}

}
