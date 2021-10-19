package co.com.mintrabajo.tys.adapters;

import co.com.mintrabajo.tys.commons.domain.messages.RespuestaRadicacionTramite;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.util.DateUtil;
import co.com.mintrabajo.tys.commons.util.JSONUtil;
import co.com.mintrabajo.tys.commons.util.PropertiesUtil;
import co.com.mintrabajo.tys.json.domain.Notificacion;
import co.com.mintrabajo.tys.json.domain.Notificacion.CorreoPersona;
import co.com.mintrabajo.tys.json.domain.Notificacion.CorreoPersona.CorreoPersonaBuilder;
import co.com.mintrabajo.tys.json.domain.Notificacion.ListaDistribucion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;


import java.util.Date;
import java.util.List;

import static co.com.mintrabajo.tys.commons.util.PropertiesUtil.APPLICATION_WEB;

;

@Component
public class NotificacionesBroker {

    private static final Logger LOGGER = LogManager.getLogger(NotificacionesBroker.class);

    private final RestTemplate restTemplate;

    @Value("${providers.notificaciones.service.endpoint}")
    private String endpoint;
    @Value("${providers.notificaciones.service.userIIB}")
    private String userIIB;
    @Value("${providers.notificaciones.service.passwordIIB}")
    private String passwordIIB;

    private JavaMailBroker javaMailBroker;

    @Autowired
    public NotificacionesBroker(JavaMailBroker javaMailBroker) {
        super();
        this.javaMailBroker = javaMailBroker;
        restTemplate = new RestTemplate();
    }

    @Async
    public void notificarActualizacionTramite(final String tramite, final RespuestaRadicacionTramite respuestas,
                                              final List<String> destinatarios) throws SystemException {

        LOGGER.info("Preparando servicio de notificaciones para actualizacion de tramite");

        String template = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_actualizado.contenido");
        String footer = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_fin.contenido");
        String footerEncuetra = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_fin.encuestas");

        template = template.replaceAll("#TRAMITE#", tramite);


        StringBuilder contenido = new StringBuilder();
        contenido.append(template);
        contenido.append("\bDEPENDENCIA\b\b\bNM. RADICADO\b\b\bCOD. SEGURIDAD\n");

        RespuestaRadicacionTramite item = respuestas;
        contenido.append("\b").append(item.getNombreDireccion()).append("\b\b\b").append(item.getNumeroRadicado())
                .append("\b\b\b").append(item.getCodigoSeguridad());
        contenido.append("\n");
        contenido.append(PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_actualizado.contenido.doc").concat("\n\n"));
        for (String documento : respuestas.getDocumentosAdicionalesTramite()) {
            contenido.append("\t").append(documento);
            contenido.append("\n");
        }
        contenido.append("\n");
        contenido.append(footer);
        contenido.append(footerEncuetra);
        notificar(PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_actualizado.asunto"),
                contenido.toString(), destinatarios);
        LOGGER.info("Notificaciones enviadas con exito");
    }

    // -------------------

    @Async
    public void notificarCreacionTramite(final String tramite, final List<RespuestaRadicacionTramite> respuestas,
                                         final List<String> destinatarios, String tiempoGestionTramite) throws SystemException {

        LOGGER.info("Preparando servicio de notificaciones para creacion de tramite");
        String contenido = buildContenido(tramite,respuestas,tiempoGestionTramite);
        notificar(PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_creado.asunto"), contenido,
                destinatarios);
        LOGGER.info("Notificaciones enviadas con exito");
    }
    @Async
    public void notificarCreacionTramitePracticas(final String tramite,
                                                  final List<RespuestaRadicacionTramite> respuestas,
                                                  final List<String> destinatarios,
                                                  String tiempoGestionTramite,
                                                  final String sstrJsonDatosBasicosPdf) {
        LOGGER.info("Preparando servicio de notificaciones para creacion de tramite Practicas Adolecentes");
        String contenido = buildContenido(tramite,respuestas,tiempoGestionTramite);
        javaMailBroker.enviarMensajePracticasAdolecentes(PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_creado.asunto"), contenido,
                destinatarios, sstrJsonDatosBasicosPdf);
        LOGGER.info("Notificaciones enviadas con exito");
    }

    private String buildContenido(final String tramite,
                                  final List<RespuestaRadicacionTramite> respuestas,
                                  String tiempoGestionTramite){
        String template = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_creado.contenido");
        String fecha = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_creado.contenido.fecha");
        String hora = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_creado.contenido.hora");
        String tiempoRespuesta = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_creado.contenido.tiemporespuesta");
        String footer = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_fin.contenido");
        String footerEncuetra = PropertiesUtil.get(APPLICATION_WEB, "notificaciones.tramite_fin.encuestas");


        template = template.replaceAll("#TRAMITE#", tramite);
        fecha = fecha.replaceAll("#FECHA#", DateUtil.getDateToStringNot(new Date()));
        hora = hora.replaceAll("#HORA#", DateUtil.getDateHourToString(new Date()));
        tiempoRespuesta = tiempoRespuesta.replaceAll("#TIEMPO#", tiempoGestionTramite);


        StringBuilder contenido = new StringBuilder();
        contenido.append(template);
        //contenido.append("\bDEPENDENCIA\n\nNM. RADICADO\n\nCOD. SEGURIDAD\n\n");

        for (int i = 0; i < respuestas.size(); i++) {
            RespuestaRadicacionTramite item = respuestas.get(i);
            contenido.append("DEPENDENCIA: ")
                    .append(item.getNombreDireccion()).append("\n")
                    .append("NM. RADICADO: ")
                    .append(item.getNumeroRadicado())
                    .append("\n")
                    .append("COD. SEGURIDAD: ")
                    .append(item.getCodigoSeguridad());
            contenido.append("\n");
        }
        contenido.append("\n");
        contenido.append(fecha);
        contenido.append(hora);
        contenido.append(tiempoRespuesta);
        contenido.append(footer);
        contenido.append(footerEncuetra);
        return contenido.toString();
    }
    // -------------------

    private void notificar(final String asunto, final String contenido, final List<String> destinatarios)
            throws SystemException {

        try {

            LOGGER.info("Invocando servicio de notificaciones para mail {}", asunto);

            CorreoPersonaBuilder correos = CorreoPersona.newInstance();

            for (String destinatario : destinatarios) {
                correos.correo(destinatario);

                ListaDistribucion distribucion = ListaDistribucion.newInstance().destinatario(correos.build())
                        .asunto(asunto).contenido(contenido)
                        .userMail(PropertiesUtil.get(APPLICATION_WEB, "notificaciones.mail.origen")).build();

                Notificacion notificacion = Notificacion.newInstance()
                        .comando(PropertiesUtil.get(APPLICATION_WEB, "notificaciones.mail.comando")).dto(distribucion)
                        .build();

                LOGGER.info("Enviando mensaje json a servicio de notificacion: {}", JSONUtil.marshal(notificacion));
                LOGGER.info("Enpoint :  "+ endpoint);
                LOGGER.info("Enviando mensaje json a servicio de notificacion:  "+ JSONUtil.marshal(notificacion));

                //ResponseEntity<?> response = restTemplate.postForEntity(endpoint, notificacion, String.class);
				
				HttpEntity<?> httpEntity = new HttpEntity<Object>(notificacion, createHeaders(userIIB, passwordIIB));
				
				
				ResponseEntity<?> response = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, String.class);
				
				
                LOGGER.info("Respuesta del servicio de notificaciones {}: {}", response.getStatusCode(),
                        response.getBody());
            }

        } catch (RestClientException e) {
            LOGGER.error("Error invocando servicio de notificaciones: " + e.getMessage(), e);
            throw new SystemException("Error invocando servicio remoto: ", e);
        }

    }
    
    private HttpHeaders createHeaders(String username, String password){
	   return new HttpHeaders() {{
	         String auth = username + ":" + password;
	         byte[] encodedAuth = Base64.encodeBase64( 
	            auth.getBytes(Charset.forName("US-ASCII")) );
	         String authHeader = "Basic " + new String( encodedAuth );
	         set( "Authorization", authHeader );
	      }};
	}
}
