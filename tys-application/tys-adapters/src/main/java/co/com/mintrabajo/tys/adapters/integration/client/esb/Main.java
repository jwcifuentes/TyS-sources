package co.com.mintrabajo.tys.adapters.integration.client.esb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.mintrabajo.tys.commons.domain.CorrespondenciaTramite;
import co.com.mintrabajo.tys.commons.domain.DatosEmpresa;
import co.com.mintrabajo.tys.commons.domain.DatosSolicitudTramite;
import co.com.mintrabajo.tys.commons.domain.Direccion;
import co.com.mintrabajo.tys.commons.domain.Persona;
import co.com.mintrabajo.tys.commons.util.DateUtil;
import co.com.mintrabajo.tys.commons.util.JSONUtil;
import co.com.mintrabajo.tys.json.domain.ActuaCalidad;
import co.com.mintrabajo.tys.json.domain.Ciudad;
import co.com.mintrabajo.tys.json.domain.Correspondencia;
import co.com.mintrabajo.tys.json.domain.DatosDestinatario;
import co.com.mintrabajo.tys.json.domain.DatosGenerales;
import co.com.mintrabajo.tys.json.domain.DatosTramitesServicios;
import co.com.mintrabajo.tys.json.domain.Departamento;
import co.com.mintrabajo.tys.json.domain.Dependencia;
import co.com.mintrabajo.tys.json.domain.Destinatario;
import co.com.mintrabajo.tys.json.domain.DireccionSucursal;
import co.com.mintrabajo.tys.json.domain.Empresa;
import co.com.mintrabajo.tys.json.domain.MedioRecepcion;
import co.com.mintrabajo.tys.json.domain.Pais;
import co.com.mintrabajo.tys.json.domain.PersonaTramite;
import co.com.mintrabajo.tys.json.domain.RemitenteExterno;
import co.com.mintrabajo.tys.json.domain.SedeAdministrativa;
import co.com.mintrabajo.tys.json.domain.Socio;
import co.com.mintrabajo.tys.json.domain.SolicitudTramite;
import co.com.mintrabajo.tys.json.domain.TipoComunicacion;
import co.com.mintrabajo.tys.json.domain.TipoDestinatario;
import co.com.mintrabajo.tys.json.domain.TipoDocumentoIdentidad;
import co.com.mintrabajo.tys.json.domain.TipoPersona;
import co.com.mintrabajo.tys.json.domain.TipoTelefono;
import co.com.mintrabajo.tys.json.domain.TipologiaDocumental;
import co.com.mintrabajo.tys.json.domain.TratamientoCortesia;
import co.com.mintrabajo.tys.json.domain.Trazabilidad;
import co.com.mintrabajo.tys.json.domain.Usuario;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		List<String> referidos = new ArrayList<String>();
	
		

		List<Destinatario> tablaDestinatario = new ArrayList<Destinatario>();
		
		tablaDestinatario
				.add(Destinatario.newInstance().tipoDestinatario(TipoDestinatario.newInstance().value(2l).build())
						.sedeAdministrativa(SedeAdministrativa.newInstance().value("136l").build())
						.dependencia(Dependencia.newInstance().value("112l").build()).build());

		Correspondencia datosGenerales = Correspondencia.newInstance()
				.tipoComunicacion(TipoComunicacion.newInstance().value(1l).build())
				.medioRecepcion(MedioRecepcion.newInstance().value(13l).build())
				.tipologiaDocumental(TipologiaDocumental.newInstance().value(1l).build()).fechaRadicacion("")
				.numeroRadicado("").tiempoRespuesta(10l).numeroFolios(0l).numeroAnexos(0l).requiereDigitalizacion(false)
				.requiereDistribFisica(false)
				.asunto("APROBACI�N DEL REGLAMENTO DE TRABAJO DE LAS EMPRESAS DE SERVICIOS TEMPORALES.")
				.descripcion("APROBACI�N DEL REGLAMENTO DE TRABAJO DE LAS EMPRESAS DE SERVICIOS TEMPORALES.")
				.referidos(referidos).build();

	

		RemitenteExterno remitenteExterno = RemitenteExterno.newInstance()
				.tipoPersona(TipoPersona.newInstance().value(5l).build())
				.tratamientoCortesia(TratamientoCortesia.newInstance().value(4l).build())
				.enCalidadDe(ActuaCalidad.newInstance().value(3l).build())
				.tipoDocumentoIdentidad(TipoDocumentoIdentidad.newInstance().value(8l).build())
				.tipoTelefono(TipoTelefono.newInstance().value(2l).build()).pais(Pais.newInstance().value(1l).build())
				.departamento(Departamento.newInstance().value(4l).build())
				.ciudad(Ciudad.newInstance().value(575l).build()).nit("86086097121").razonSocial("INDUSTRIAS IIB S.A")
				.nombre("Nifer Rodriguez").indicativo(300l).telefono("3134048765").extension(3090l)
				.correoElectronico("jprodriguez@gmail.com").direccion("Carrera 89 A 123 B 10 Casa 124")
				.codigoPostal("67887").build();
		
		DatosDestinatario datosDestinatario = DatosDestinatario.newInstance().tablaDestinatario(tablaDestinatario)
				.build();

		Usuario datosUsuarios = Usuario.newInstance().idUsuario("usuario1").build();

		DatosGenerales d = DatosGenerales.newInstance().datosGenerales(datosGenerales)
				.remitenteExterno(remitenteExterno)
				.datosDestinatario(datosDestinatario).datosUsuarios(datosUsuarios)
				.build();

		// Empresa e = Empresa.newInstance().fechaExpedicionEscritura(new
		// Date()).valueNotaria(12L).build();
		//System.out.println(JSONUtil.marshal(d));

		SolicitudTramite objDatosTramite = SolicitudTramite.newInstance().strIdTramite(6l)
				.strNumRadicado("13EE2017210000000000468")
				//.strIdJustificacion(10561l)
				//.strIdGradoAsociacion(10568l)
				//.strIdTipoSolicitud(10572l)
				//.strIdRadicaTramite(10596l)
				.strValObjetoSolicitud("Esta es una prueba java")
				.strIdJustaCausa(10639l)
				// 10575 ABIERTO
				.strIdEstadoTramite(10575l)
				.strIndicaReqConceptoSDI(0l)
				.strValObservaciones("Esta es una prueba de java")
				.strValMotivoNiega("Esta es una prueba")
				.strValCircunstancias("PrUEBA")
				.datFechaCrea(DateUtil.getDateToString( new Date())).build();

		
		
		List<PersonaTramite> lstPersonas = new ArrayList<PersonaTramite>();
		lstPersonas.add(PersonaTramite.newInstance().strIdTipoPersona("RLEMP").strNombreCompleto("JENNY PAOLA RODRIGUEZ")
				.strIdTipoIdentificacion(1430l).strNumeroIdentificacion("109099878").strValTelefono("6827865")
				.strValExtension("45455").strValIndicativo("44").strValMail("jprodriguez@gmail.com")
				.strValCelular("3134048976").build());

		List<Socio> lstSocio = new ArrayList<Socio>();
		lstSocio.add(Socio.newInstance().strIdTipoPersona("SOCIO").strIdTipoIdentificacion(1430l)
				.strNumeroIdentificacion("10879675").strNombreCompleto("CELEMIN ROMERO").build());
		lstSocio.add(Socio.newInstance().strIdTipoPersona("SOCIO").strIdTipoIdentificacion(1430l)
				.strNumeroIdentificacion("1096778800").strNombreCompleto("NIFER ROA").build());

		DireccionSucursal objDireccionSucursal = DireccionSucursal.newInstance().strCodigoPostal("1001010")
				.strValDireccion("AUTOPISTA a A BIS NORTE autopista A 12 NORTE TORRE stark").strIdPais(1l)
				.strIdDepartamento(4l).strIdMunicipio(575l)

				.build();

		Empresa objEmpresa = Empresa.newInstance().strIdSolicitante(10565l).lstSocio(lstSocio)
				.strNumeroEscritura("7789900099").datFechaExpedicionEscritura("2017-06-14 00:00:00Z")
	//			.strIdDepartamentoExpedicion(14l).strIdMunicipioExpedicion(624l).strIdNotaria(434l)
				.objDireccionSucursal(objDireccionSucursal).strNumeroPoliza("9088700000").strValPoliza("7000000")
				.strNombreAseguradora("SURA").datFechaInicioPoliza("2017-10-05 00:00:00Z")
				.datFechaFinPoliza("2018-05-19 00:00:00Z").build();
		
		

		Trazabilidad objTrazabilidad = Trazabilidad.newInstance().strUsuarioModifica("2017")
				.strInstruccionRealizada("INSERT").strDireccionIP("127.0.0.1").build();

		DatosTramitesServicios t = DatosTramitesServicios.newInstance().objDatosTramite(objDatosTramite)
				.lstPersonas(lstPersonas).objEmpresa(objEmpresa).objTrazabilidad(objTrazabilidad).build();

		//System.out.println(JSONUtil.marshal(t));
		
		
		
		CorrespondenciaTramite correspondencia =CorrespondenciaTramite.newInstance()
				.idTipoComunicacion(1l)
				.idMedioRecepcion(13L)
				.idTipologiaDocumental(1l)
				.fechaRadicacion("")
				.numeroRadicado("")
				.tiempoRespuesta(10l)
				.asunto("AUTORIZACIÓN PARA TERMINACIÓN DE CONTRATOS DE TRABAJADORAS EN ESTADO DE EMBARAZO O LACTANCIA")
				.descripcion("AUTORIZACIÓN PARA TERMINACIÓN DE CONTRATOS DE TRABAJADORAS EN ESTADO DE EMBARAZO O LACTANCIA")
				.build();
	
		
		
		co.com.mintrabajo.tys.commons.domain.Empresa empresaWeb =co.com.mintrabajo.tys.commons.domain.Empresa.newInstance()
				.autorizacionNotificacionElectronica(true)
				.idNotificacionElectronica(true)
				.build();
		
		
		 List<Persona> listaPersonas = new ArrayList<Persona>();
		 
		 Persona persona =Persona.newInstance()
				 .idTipoPersona("EMP")
				 .nombreCompleto("Sandra patricia Romero")
				 .correoElectronico("jprodriguez@soaint.com")
				 .direccionPersona(Direccion.newInstance()
						 .idPais(1l)
						 .idDepartamento(19l)
						 .idMunicipio(750l)
						 .valDireccion("Calle 100 # 12 -60 Norte")
						 .codigoPostal("12121")
						 .build())
				 .build();
		 
		 listaPersonas.add(persona);
		 
		
		DatosEmpresa datosEmpresa =DatosEmpresa.newInstance()
				.empresa(empresaWeb)
				.listaPersonas(listaPersonas)
				.build();
		
		
		co.com.mintrabajo.tys.commons.domain.SolicitudTramite solicitudTramite = co.com.mintrabajo.tys.commons.domain.SolicitudTramite.newInstance()
				.idTramite(6l)
				.idJustificacionSolicitud(null)
				.idGradoAsociacion(null)
				.idTipoSolicitud(null)
				.valCircunstancias("prueba de embarazo")
				.idJustaCausa(10639l)
				.build();
		
		
		DatosSolicitudTramite tramite =
				DatosSolicitudTramite.newInstance()
				.correspondencia(correspondencia)
				.empresa(datosEmpresa)
				.solicitudTramite(solicitudTramite)
				.build();
		
		
		System.out.println(JSONUtil.marshal(tramite));
		
		
		
		

	}

}