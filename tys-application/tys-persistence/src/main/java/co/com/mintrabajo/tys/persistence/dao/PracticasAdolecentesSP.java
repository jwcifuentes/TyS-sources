package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.DatosSolicitudTramite;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
@Component
public class PracticasAdolecentesSP {

    private static final Logger LOGGER = LogManager.getLogger(PracticasAdolecentesSP.class);

    @Autowired
    private JdbcTemplate template;

    public void guardarDatosPracticasAdolecentes(Long idRegTramite, DatosSolicitudTramite solicitud) throws SystemException {
        if(solicitud.getDatosEscenarioPractica() == null || solicitud.getDatosVinculacion() == null || solicitud.getDatosFormacion() == null) return;
        LOGGER.info("Realizando llamada a procedimiento para persistir datos practica adolecentes");
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("idRegTramite",idRegTramite);
        jsonMap.put("datosFormacion",solicitud.getDatosFormacion());
        jsonMap.put("datosEscenarioPractica",solicitud.getDatosEscenarioPractica());
        jsonMap.put("datosVinculacion",solicitud.getDatosVinculacion());
        try {
            ObjectMapper mapper = new ObjectMapper();
            String strJson = new ObjectMapper().writeValueAsString(jsonMap);
            System.out.println("---------------------------------json");
            System.out.println(strJson);
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(template)
                    .withProcedureName("SP_TYS_INS_DATOS_PRACTICAS");
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("V_JSON_ENTRADA",strJson);
            Map<String,Object> out = simpleJdbcCall.execute(in);
            String id = (String) out.get("V_RESPUESTA");
        } catch (Exception e) {
        	System.out.println("Exception SP_TYS_INS_DATOS_PRACTICAS: " + e.toString()+" Mensaje: "+e.getMessage());
            throw new SystemException("Excepcion convirtiendo los datos practicas a json: " + e.getMessage(), e);
        }
    }
}
