package co.com.mintrabajo.tys.adapters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.adapters.integration.client.esb.EncabezadoEntradaType;
import co.com.mintrabajo.tys.adapters.integration.client.esb.JSONGenericoPortType;
import co.com.mintrabajo.tys.adapters.integration.client.esb.MensajeEntradaType;
import co.com.mintrabajo.tys.adapters.integration.client.esb.MensajeSalidaType;
import co.com.mintrabajo.tys.commons.domain.messages.RadicacionesRequest;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.util.DateUtil;
import co.com.mintrabajo.tys.commons.util.JSONUtil;

@Component
public class RadicacionesYTramitesBroker extends AbstractBroker {

	public static final String OPERACION_CORRESPONDENCIA_RADICACION = "SP_SGD_CORR_RADICACION";
	public static final String OPERACION_TRAMITE_RADICACION = "SP_TYS_INS_TRAMITE";

	private static final Logger LOGGER = LogManager.getLogger(RadicacionesYTramitesBroker.class);

	@Autowired
	@Qualifier("clienteTramites")
	private JSONGenericoPortType clienteTramites;

	public RadicacionesYTramitesBroker() {
	}

	public <T> T ejecutarOperacion(final RadicacionesRequest request, final Class<T> type)
			throws BusinessException, SystemException {

		LOGGER.info("Iniciando ejecutarOperacion para servicio JSONGenerico: {}", request.getStoreProcedure());

		try {
			EncabezadoEntradaType encabezadoEntrada = new EncabezadoEntradaType();
			encabezadoEntrada.setPeticionId(peticion);
			encabezadoEntrada.setCanal(canal);
			encabezadoEntrada.setPeticionFecha(DateUtil.getCurrentDateAsXmlDate());
			encabezadoEntrada.setUsuario(usuario);

			MensajeEntradaType met = new MensajeEntradaType();
			met.setEncabezadoEntrada(encabezadoEntrada);
			met.setStoreProcedure(request.getStoreProcedure());
			met.setJson(JSONUtil.marshal(request.getPayload()));

			LOGGER.info("Documento json generado {}", met.getJson());
			System.out.println("Documento json generado: "+ met.getJson());
			MensajeSalidaType mst = clienteTramites.ejecutar(met);
			LOGGER.info("Documento json obtenido {}", mst.getJson());
			System.out.println("Documento json(JSONGenerico) obtenido :"+ mst.getJson());
			
			if (mst.getJson() == null)
				throw new SystemException("No hay documento JSON de respuesta para el servicio");

			return (T) JSONUtil.unmarshal(mst.getJson(), type);

		} catch (Exception e) {
			LOGGER.error("Iniciando ejecutarOperacion para servicio JSONGenerico", e);
			throw new SystemException("Iniciando ejecutarOperacion para servicio RadicacionesYTramites");
		}
	}


}
