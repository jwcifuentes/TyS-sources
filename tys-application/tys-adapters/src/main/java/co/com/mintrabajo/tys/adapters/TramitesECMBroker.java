package co.com.mintrabajo.tys.adapters;

import co.com.mintrabajo.tys.adapters.integration.client.ecm.*;
import co.com.mintrabajo.tys.commons.domain.DocumentoTramite;
import co.com.mintrabajo.tys.commons.domain.messages.*;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

@Component
public class TramitesECMBroker extends AbstractBroker {

	private static final Logger LOGGER = LogManager.getLogger(TramitesECMBroker.class);

	@Autowired
	@Qualifier("clienteECMTramites")
	public DocumentosBPMPortType clienteDocumentosBpm;

	public TramitesECMBroker() {
		super();
	}

	public SubirDocumentoActualizarResponse subirDocumentoActualizar(SubirDocumentoActualizarRequest request)
			throws BusinessException, SystemException {

		try {

			LOGGER.info("Iniciando subirDocumentoActualizar");

			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			Holder<EncabezadoSalidaType> encabezadoSalida = new Holder<EncabezadoSalidaType>();
			Holder<String> strCodigoOut = new Holder<String>();
			Holder<String> strMensajeOut = new Holder<String>();

			String filenetId = request.getStrIdFilenetIn() == null ? "" : request.getStrIdFilenetIn();
			String base64 = request.getStrBase64In();
			String baseModify = base64.replace("data:application/pdf;base64,dataAapplication/pdfAbase64A", "").replace("data:application/pdf;base64,", "");
		

			clienteDocumentosBpm.subirDocumentoActualizar(encabezadoEntrada, request.getStrCodigoDependenciaIn(),
					request.getStrNroRadicadoIn(), baseModify, request.getIntTramiteTipologiaIn(),
					request.getStrCorPlantillaIn(), request.getStrNombreDocumentoIn(),request.getIntIdDocumentoIn(),
					filenetId, encabezadoSalida, strCodigoOut, strMensajeOut);

			EncabezadoSalida es = EncabezadoSalida.newInstance().peticionId(encabezadoSalida.value.getPeticionId())
					.criticidad(encabezadoSalida.value.getCriticidad()).rtaCodigo(encabezadoSalida.value.getRtaCodigo())
					.rtaCodigoHost(encabezadoSalida.value.getRtaCodigoHost())
					.rtaDescripcion(encabezadoSalida.value.getRtaDescripcion())
					.rtaDescripcionHost(encabezadoSalida.value.getRtaDescripcionHost()).build();

			return SubirDocumentoActualizarResponse.newInstance().encabezadoSalida(es).strCodigoOut(strCodigoOut.value)
					.strMensajeOut(strMensajeOut.value).build();

		} catch (Exception e) {
			LOGGER.error("Error invocando subirDocumentoActualizar", e);
			throw new SystemException("Error invocando subirDocumentoActualizar");
		}
	}

	public ObtenerDatosDocumentoResponse obtenerDatosDocumento(final ObtenerDatosDocumentoRequest request)
			throws BusinessException, SystemException {

		try {

			LOGGER.info("Iniciando obtenerDatosDocumento");

			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			Holder<EncabezadoSalidaType> encabezadoSalida = new Holder<EncabezadoSalidaType>();
			Holder<ArrayOfActualizarDocumentoDTO> documentosOut = new Holder<ArrayOfActualizarDocumentoDTO>();

			clienteDocumentosBpm.obtenerDatosDocumento(encabezadoEntrada, request.getStrNroRadicado(), encabezadoSalida,
					documentosOut);

			EncabezadoSalida es = EncabezadoSalida.newInstance()
					.peticionId(encabezadoSalida.value.getPeticionId())
					.criticidad(encabezadoSalida.value.getCriticidad())
					.rtaCodigo(encabezadoSalida.value.getRtaCodigo())
					.rtaCodigoHost(encabezadoSalida.value.getRtaCodigoHost())
					.rtaDescripcion(encabezadoSalida.value.getRtaDescripcion())
					.rtaDescripcionHost(encabezadoSalida.value.getRtaDescripcionHost()).build();

			List<DocumentoTramite> documentos = new ArrayList<DocumentoTramite>();
			if (documentosOut != null && documentosOut.value != null) {
				List<ActualizarDocumentoDTO> items = documentosOut.value.getItem();
				for (ActualizarDocumentoDTO item : items) {
					documentos.add(DocumentoTramite.newInstance()
							.idDocumento(item.getIntIdDocumento())
							.base64(item.getStrBase64())
							.idFilenet(item.getStrValFilenet())
							.tramiteTipologia(item.getIntIdTramTipologia())
							.nroRadicado(item.getStrNroRadicado())
							.build());
				}
			}

			return ObtenerDatosDocumentoResponse.newInstance().encabezadoSalida(es).documentosOut(documentos).build();

		} catch (Exception e) {
			LOGGER.error("Error invocando obtenerDatosDocumento", e);
			throw new SystemException("Error invocando obtenerDatosDocumento");
		}
	}

	public ObtenerDocumentoPorIdFilenetResponse obtenerDocumentoPorIdFilenet(
			final ObtenerDocumentoPorIdFilenetRequest request) throws BusinessException, SystemException {

		try {

			LOGGER.info("Iniciando obtenerDocumentoPorIdFilenet");

			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			Holder<EncabezadoSalidaType> encabezadoSalida = new Holder<EncabezadoSalidaType>();
			Holder<String> strBase64Out = new Holder<String>();
			Holder<String> strFileNameOut = new Holder<String>();

			clienteDocumentosBpm.obtenerDocumentoPorIdFilenet(encabezadoEntrada, request.getStrIdFilenet(),
					encabezadoSalida, strBase64Out, strFileNameOut);

			EncabezadoSalida es = EncabezadoSalida.newInstance().peticionId(encabezadoSalida.value.getPeticionId())
					.criticidad(encabezadoSalida.value.getCriticidad()).rtaCodigo(encabezadoSalida.value.getRtaCodigo())
					.rtaCodigoHost(encabezadoSalida.value.getRtaCodigoHost())
					.rtaDescripcion(encabezadoSalida.value.getRtaDescripcion())
					.rtaDescripcionHost(encabezadoSalida.value.getRtaDescripcionHost()).build();

			return ObtenerDocumentoPorIdFilenetResponse.newInstance().encabezadoSalida(es).base64(strBase64Out.value)
					.fileName(strFileNameOut.value).build();

		} catch (Exception e) {
			LOGGER.error("Error invocando obtenerDatosDocumento", e);
			throw new SystemException("Error invocando obtenerDatosDocumento");
		}
	}

}
