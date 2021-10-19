package co.com.mintrabajo.tys.commons.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Direccion  {

	
	// IDE_DIRECCION
	private Long idDireccion;
	// NUM_DIRECCION
	private String numeroDireccion;
	// NUM_VIA_GENERADORA
	private String numeroViaGeneradora;
	// NUM_PLACA
	private String numeroPlaca;
	// IDE_TIPO_VIA
	private Long idTipoVia;
	// IDE_PREFIJO_CUADRANT
	private Long idPrejifoCuadrante;
	// COD_POSTAL
	private String codigoPostal;
	// VAL_DIRECCION
	private String valDireccion;
	// IDE_USUARIO_CAMBIO
	private Long idUsuarioCambio;
	// FEC_CAMBIO
	private Date fechaCambio;
	// IDE_PAIS
	private Long idPais;
	private String nombrePais;
	// IDE_DEPARTAMENTO
	private Long idDepartamento;
	private String nombreDepartamento;
	// IDE_MUNICIPIO
	private Long idMunicipio;
	private String nombreMunicipio;
	// VAL_PROV_ESTADO
	private String valorProvidecniaEstado;
	// VAL_CIUDAD
	private String valCiudad;
	// VAL_CORREO_ELECT
	private String valCorreoElectronico;
	// IDE_ESTADO_REG
	private Long idEstadoRegistro;
	// IDE_USUARIO_CREO
	private Long idUsuarioCreacion;
	// FEC_CREO
	private Date fechaCreacion;
	
	public Direccion(){
		
	}

}
