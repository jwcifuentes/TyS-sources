package co.com.mintrabajo.tys.commons.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosEmpresa  {

	private Empresa empresa;
	private List<Persona> listaPersonas;
	private List<SocioEmpresa> listaSocios;
	private Direccion direccionSucursal;
	private List <Direccion> listaDireccionesSucursales;
	private DatosEmpleador datosEmpleador;
	public DatosEmpresa(){
		
	}

}
