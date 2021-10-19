package co.com.mintrabajo.tys.adapters;

import javax.xml.ws.Holder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.adapters.integration.client.bpm.EncabezadoEntradaType;
import co.com.mintrabajo.tys.adapters.integration.client.bpm.EncabezadoSalidaType;
import co.com.mintrabajo.tys.adapters.integration.client.bpm.IniciarProcesoTySResponseType;
import co.com.mintrabajo.tys.adapters.integration.client.bpm.IniciarProcesoTySType;
import co.com.mintrabajo.tys.adapters.integration.client.bpm.TramitesBPMPortType;
import co.com.mintrabajo.tys.commons.domain.messages.ActualizarProcesoRequest;
import co.com.mintrabajo.tys.commons.domain.messages.ActualizarProcesoResponse;
import co.com.mintrabajo.tys.commons.domain.messages.EncabezadoSalida;
import co.com.mintrabajo.tys.commons.domain.messages.IniciarProcesoRequest;
import co.com.mintrabajo.tys.commons.domain.messages.IniciarProcesoResponse;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosSeccionalRequest;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosSeccionalResponse;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.util.DateUtil;
import co.com.mintrabajo.tys.commons.util.JSONUtil;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Component
public class TramitesBPMBroker extends AbstractBroker {

	private static final Logger LOGGER = LogManager.getLogger(TramitesBPMBroker.class);

	@Autowired
	@Qualifier("clienteBPMTramites")
	public TramitesBPMPortType clienteTramitesBpm;
	@Value("${service.pdfPracticasAdolecente}")
	private String urlPdfPracticasAdolecente;
	public TramitesBPMBroker() {
		super();
	}

	public ObtenerDatosSeccionalResponse obtenerDatosSeccional(final ObtenerDatosSeccionalRequest request)
			throws BusinessException, SystemException {

		try {

			LOGGER.info("Iniciando obtenerDatosSeccional");

			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			Holder<EncabezadoSalidaType> encabezadoSalida = new Holder<EncabezadoSalidaType>();
			Holder<String> strIdSeccionOut = new Holder<String>();
			Holder<String> strCodigoSeccionOut = new Holder<String>();
			Holder<String> strIdSubFondoOut = new Holder<String>();

			LOGGER.info("Codigo tramite " + request.getStrCodigoTramiteIn());
			LOGGER.info("Asociacion " + request.getStrGradoAsociacionIn());
			LOGGER.info("Subfondo " + request.getStrIdSubFondoIn());

			clienteTramitesBpm.obtenerDatosSeccional(encabezadoEntrada, request.getStrCodigoTramiteIn(),
					request.getStrGradoAsociacionIn(), request.getStrIdSubFondoIn(), encabezadoSalida, strIdSeccionOut,
					strCodigoSeccionOut, strIdSubFondoOut);

			EncabezadoSalida es = EncabezadoSalida.newInstance().peticionId(encabezadoSalida.value.getPeticionId())
					.criticidad(encabezadoSalida.value.getCriticidad()).rtaCodigo(encabezadoSalida.value.getRtaCodigo())
					.rtaCodigoHost(encabezadoSalida.value.getRtaCodigoHost())
					.rtaDescripcion(encabezadoSalida.value.getRtaDescripcion())
					.rtaDescripcionHost(encabezadoSalida.value.getRtaDescripcionHost()).build();
			System.out.println("Codigo de respuesta del servicio obtenerDatosSeccional : "+es.getRtaCodigo()+" "+es.getRtaDescripcion());

			return ObtenerDatosSeccionalResponse.newInstance().encabezadoSalida(es)
					.strIdSeccionOut(strIdSeccionOut.value).strCodigoSeccionOut(strCodigoSeccionOut.value)
					.strIdSubFondoOut(strIdSubFondoOut.value).build();

		} catch (Exception e) {
			LOGGER.error("Error invocando obtenerDatosSeccional", e);
			throw new SystemException("Error invocando obtenerDatosSeccional");
		}
	}

	public IniciarProcesoResponse iniciarInstanciaBPM(final IniciarProcesoRequest request)
			throws BusinessException, SystemException {

		LOGGER.info("Iniciando iniciarInstanciaBPM para servicio TramitesBPM: {}", request.getPayload());

		try {

			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			IniciarProcesoTySType proceso = new IniciarProcesoTySType();
			proceso.setEncabezadoEntrada(encabezadoEntrada);
			proceso.setStrJson(JSONUtil.marshal(request.getPayload()));

			LOGGER.info("Documento json generado iniciar instancia BPM {}", proceso.getStrJson());
			IniciarProcesoTySResponseType iptysr = clienteTramitesBpm.iniciarProcesoTyS(proceso);
			LOGGER.info("Documento json obtenido instancia BPM {}", iptysr.getStrCodRespuesta());

			EncabezadoSalida es = EncabezadoSalida.newInstance()
					.peticionId(iptysr.getEncabezadoSalida().getPeticionId())
					.criticidad(iptysr.getEncabezadoSalida().getCriticidad())
					.rtaCodigo(iptysr.getEncabezadoSalida().getRtaCodigo())
					.rtaCodigoHost(iptysr.getEncabezadoSalida().getRtaCodigoHost())
					.rtaDescripcion(iptysr.getEncabezadoSalida().getRtaDescripcion())
					.rtaDescripcionHost(iptysr.getEncabezadoSalida().getRtaDescripcionHost()).build();

			return IniciarProcesoResponse.newInstance().encabezadoSalida(es)
					.codigoRespuesta(iptysr.getStrCodRespuesta()).build();

		} catch (Exception e) {
			LOGGER.error("Error invocando iniciarInstanciaBPM ", e);
			throw new SystemException("Error invocando iniciarInstanciaBPM");
		}
	}

	public ActualizarProcesoResponse actuaizarProcesoBPM(ActualizarProcesoRequest request)
			throws BusinessException, SystemException {

		LOGGER.info("Iniciando actuaizarProcesoBPM para servicio TramitesBPM: {}", request.getStrId());

		try {

			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			Holder<EncabezadoSalidaType> encabezadoSalida = new Holder<EncabezadoSalidaType>();
			Holder<String> strId = new Holder<String>();
			strId.value = request.getStrId();

			LOGGER.info("Iniciar actualizacion de tramite - BPM {}", request.getStrId());
			clienteTramitesBpm.actualizarDocumentoWeb(encabezadoEntrada, strId, encabezadoSalida);
			LOGGER.info("Respuesta de actualizacion de tramite BPM {}", strId.value);

			EncabezadoSalida es = EncabezadoSalida.newInstance().peticionId(encabezadoSalida.value.getPeticionId())
					.criticidad(encabezadoSalida.value.getCriticidad()).rtaCodigo(encabezadoSalida.value.getRtaCodigo())
					.rtaCodigoHost(encabezadoSalida.value.getRtaCodigoHost())
					.rtaDescripcion(encabezadoSalida.value.getRtaDescripcion())
					.rtaDescripcionHost(encabezadoSalida.value.getRtaDescripcionHost()).build();

			return ActualizarProcesoResponse.newInstance().encabezadoSalida(es).codigoRespuesta(strId.value).build();

		} catch (Exception e) {
			LOGGER.error("Error invocando actuaizarProcesoBPM ", e);
			throw new SystemException("Error invocando actuaizarProcesoBPM");
		}

	}
	public Response fetchPdfPracticasAdolecente(String jsonVal) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();
		okhttp3.MediaType plainTextType = okhttp3.MediaType.parse(MediaType.TEXT_PLAIN_VALUE);
		okhttp3.RequestBody body = okhttp3.RequestBody.create(jsonVal, plainTextType);
		Request request = new Request.Builder()
				.url(urlPdfPracticasAdolecente)
				.post(body)
				.addHeader("Content-Type", "text/plain")
				.build();
		return client.newCall(request).execute();
	}
}
