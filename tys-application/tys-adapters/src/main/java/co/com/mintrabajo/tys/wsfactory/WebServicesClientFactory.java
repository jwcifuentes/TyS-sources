package co.com.mintrabajo.tys.wsfactory;

import co.com.mintrabajo.tys.adapters.integration.client.bpm.TramitesBPMPortType;
import co.com.mintrabajo.tys.adapters.integration.client.bpm.TramitesBPMService;
import co.com.mintrabajo.tys.adapters.integration.client.ecm.DocumentosBPMPortType;
import co.com.mintrabajo.tys.adapters.integration.client.ecm.DocumentosBPMService;
import co.com.mintrabajo.tys.adapters.integration.client.esb.JSONGenericoPortType;
import co.com.mintrabajo.tys.adapters.integration.client.esb.JSONGenericoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.URL;

public class WebServicesClientFactory {

	@Value("${providers.esb.tramites.service.wsdl}")
	private String wsdlLocation;
	@Value("${providers.esb.tramites.service.endpoint}")
	private String endpoint;

	@Value("${providers.esb.tramites.service.bpm.wsdl}")
	private String wsdlLocationBPN;
	@Value("${providers.esb.tramites.service.bpm.endpoint}")
	private String endpointBPM;

	@Value("${providers.esb.tramites.service.ecm.wsdl}")
	private String wsdlLocationECM;
	@Value("${providers.esb.tramites.service.ecm.endpoint}")
	private String endpointECM;

	private static final Logger LOGGER = LogManager.getLogger(WebServicesClientFactory.class);

	public JSONGenericoPortType buildTramitesService() {
		try {
			QName qname = new QName("http://soaint.com/Servicios/Utilitario/wsdl/JSONGenerico", "JSONGenericoService");
			JSONGenericoService s = new JSONGenericoService(new URL(wsdlLocation), qname);
			JSONGenericoPortType pt = s.getJSONGenericoSOAP();
			((BindingProvider) pt).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
			return pt;
		} catch (Exception e) {
			LOGGER.info("Excepcion generando cliente gestion de tramites : ", e.getMessage());
			throw new RuntimeException("Excepcion cliente gestion de tramites :", e);
		}

	}

	public TramitesBPMPortType buildTramitesBPN() {
		try {
			System.out.println("URL wsdlL TramitesBPMPortType : "+wsdlLocationBPN + " Y endPoint: "+endpointBPM);
			QName qname = new QName("http://soaint.com/Servicios/Negocio/wsdl/TramitesBPM", "TramitesBPMService");
			TramitesBPMService s = new TramitesBPMService(new URL(wsdlLocationBPN), qname);
			TramitesBPMPortType tPortType = s.getTramitesBPMSOAP();
			((BindingProvider) tPortType).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,endpointBPM);
			return tPortType;
		} catch (Exception e) {
			System.out.println("Error EndPoint TramitesBPMPortType: " + e.toString()+"Mensaje : "+e.getMessage());
			LOGGER.info("Excepcion generando cliente bpm tramites : ", e.getMessage());
			throw new RuntimeException("Excepcion cliente bpm tramites :", e);
		}
	}

	public DocumentosBPMPortType buildDocumentosBPM() {
		try {
			QName qname = new QName("http://soaint.com/Servicios/Negocio/wsdl/DocumentosBPM", "DocumentosBPMService");
			DocumentosBPMService s = new DocumentosBPMService(new URL(wsdlLocationECM), qname);
			DocumentosBPMPortType dPortType = s.getDocumentosBPMSOAP();
			((BindingProvider) dPortType).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,endpointECM);
			return dPortType;
		} catch (Exception e) {
			LOGGER.info("Excepcion generando cliente ecm documentos : ", e.getMessage());
			throw new RuntimeException("Excepcion cliente ecm documentos :", e);
		}
	}
}
