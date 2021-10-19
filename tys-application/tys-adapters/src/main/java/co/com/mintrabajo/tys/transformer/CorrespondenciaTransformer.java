package co.com.mintrabajo.tys.transformer;

import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkNullString;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.DatosSolicitudTramite;
import co.com.mintrabajo.tys.infrastructure.Transformer;
import co.com.mintrabajo.tys.json.domain.ActuaCalidad;
import co.com.mintrabajo.tys.json.domain.Ciudad;
import co.com.mintrabajo.tys.json.domain.Correspondencia;
import co.com.mintrabajo.tys.json.domain.DatosDestinatario;
import co.com.mintrabajo.tys.json.domain.DatosGenerales;
import co.com.mintrabajo.tys.json.domain.Departamento;
import co.com.mintrabajo.tys.json.domain.Dependencia;
import co.com.mintrabajo.tys.json.domain.Destinatario;
import co.com.mintrabajo.tys.json.domain.MedioRecepcion;
import co.com.mintrabajo.tys.json.domain.Pais;
import co.com.mintrabajo.tys.json.domain.RemitenteExterno;
import co.com.mintrabajo.tys.json.domain.SedeAdministrativa;
import co.com.mintrabajo.tys.json.domain.TipoComunicacion;
import co.com.mintrabajo.tys.json.domain.TipoDestinatario;
import co.com.mintrabajo.tys.json.domain.TipoDocumentoIdentidad;
import co.com.mintrabajo.tys.json.domain.TipoPersona;
import co.com.mintrabajo.tys.json.domain.TipoTelefono;
import co.com.mintrabajo.tys.json.domain.TipologiaDocumental;
import co.com.mintrabajo.tys.json.domain.TratamientoCortesia;
import co.com.mintrabajo.tys.json.domain.Usuario;

@Component
public class CorrespondenciaTransformer implements Transformer<DatosSolicitudTramite, DatosGenerales> {

	@Value("${system.services.params.usuario_correspondencia}")
	protected String usuario;

	@Override
	public DatosGenerales transform(final DatosSolicitudTramite data) {

		
		
		Correspondencia datosGenerales = Correspondencia.newInstance()
				.tipoComunicacion(
						TipoComunicacion.newInstance().value(data.getCorrespondencia().getIdTipoComunicacion()).build())
				.medioRecepcion(
						MedioRecepcion.newInstance().value(data.getCorrespondencia().getIdMedioRecepcion()).build())
				.tipologiaDocumental(TipologiaDocumental.newInstance()
						.value(data.getCorrespondencia().getIdTipologiaDocumental()).build())
				.fechaRadicacion("").numeroRadicado("").tiempoRespuesta(data.getCorrespondencia().getTiempoRespuesta())
				.numeroFolios(0l).numeroAnexos(0l).requiereDigitalizacion(false).requiereDistribFisica(false)
				.asunto(data.getCorrespondencia().getAsunto()).descripcion(data.getCorrespondencia().getDescripcion())
				.sedeNacional(Long.parseLong(data.getDependencias().getStrIdSubFondoOut()))
				.build();

		if (data.getCorrespondencia().getReferidos().size() !=0) {
			datosGenerales.setReferidos(data.getCorrespondencia().getReferidos());
		}

		RemitenteExterno remitenteExterno = RemitenteExterno.newInstance()
				.tipoPersona(TipoPersona.newInstance()
						.value(data.getCorrespondencia().getRemitente().getIdtipoPersona()).build())
				.tratamientoCortesia(TratamientoCortesia.newInstance()
						.value(data.getCorrespondencia().getRemitente().getIdtratamientoCortesia()).build())
				.enCalidadDe(ActuaCalidad.newInstance()
						.value(data.getCorrespondencia().getRemitente().getIdenCalidadDe()).build())
				.tipoDocumentoIdentidad(TipoDocumentoIdentidad.newInstance()
						.value(data.getCorrespondencia().getRemitente().getIdtipoDocumentoIdentidad()).build())
				.tipoTelefono(TipoTelefono.newInstance()
						.value(data.getCorrespondencia().getRemitente().getIdtipoTelefono()).build())
				.pais(Pais.newInstance().value(data.getCorrespondencia().getRemitente().getDireccion().getIdPais())
						.build())
				.departamento(Departamento.newInstance()
						.value(data.getCorrespondencia().getRemitente().getDireccion().getIdDepartamento()).build())
				.ciudad(Ciudad.newInstance()
						.value(data.getCorrespondencia().getRemitente().getDireccion().getIdMunicipio()).build())
				.nit(checkNullString(data.getCorrespondencia().getRemitente().getNit()))
				.razonSocial(checkNullString(data.getCorrespondencia().getRemitente().getRazonSocial()))
				.numeroDocIdentidad(checkNullString(data.getCorrespondencia().getRemitente().getNumeroDocumento()))
				.nombre(data.getCorrespondencia().getRemitente().getNombre())
				.indicativo(data.getCorrespondencia().getRemitente().getIndicativo())
				.telefono(data.getCorrespondencia().getRemitente().getTelefono())
				.extension(data.getCorrespondencia().getRemitente().getExtension())
				.correoElectronico(data.getCorrespondencia().getRemitente().getCorreoElectronico())
				.direccion(data.getCorrespondencia().getRemitente().getDireccion().getValDireccion())
				.codigoPostal(data.getCorrespondencia().getRemitente().getDireccion().getCodigoPostal())
				.strNombre1(data.getCorrespondencia().getRemitente().getPrimerNombre())
				.strNombre2(data.getCorrespondencia().getRemitente().getSegundoNombre())
				.strApellido1(data.getCorrespondencia().getRemitente().getPrimerApellido())
				.strApellido2(data.getCorrespondencia().getRemitente().getSegundoApellido()).build();

		List<Destinatario> tablaDestinatario = new ArrayList<Destinatario>();
		tablaDestinatario.add(Destinatario.newInstance()
				.tipoDestinatario(TipoDestinatario.newInstance().value(2l).build())
				.sedeAdministrativa(
						SedeAdministrativa.newInstance().value(data.getDependencias().getStrIdSubFondoOut()).build())
				.dependencia(Dependencia.newInstance().value(data.getDependencias().getStrIdSeccionOut()).build())
				.build());

		DatosDestinatario datosDestinatario = DatosDestinatario.newInstance().tablaDestinatario(tablaDestinatario)
				.build();

		Usuario datosUsuarios = Usuario.newInstance().idUsuario(usuario).build();

		DatosGenerales radicacion = DatosGenerales.newInstance().datosGenerales(datosGenerales)
				.remitenteExterno(remitenteExterno).datosDestinatario(datosDestinatario).datosUsuarios(datosUsuarios)
				.build();

		return radicacion;
	}

}
