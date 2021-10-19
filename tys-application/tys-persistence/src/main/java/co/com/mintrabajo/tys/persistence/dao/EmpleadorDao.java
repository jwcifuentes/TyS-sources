package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.DatosEmpleador;
import co.com.mintrabajo.tys.persistence.Queries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class EmpleadorDao {
    private static final Logger LOGGER = LogManager.getLogger(EmpleadorDao.class);
    @Autowired
    private JdbcTemplate template;
    public void crearEmpleador(Long idRegTramite, DatosEmpleador datosEmpleador){
        if(datosEmpleador==null) return;
        LOGGER.info("Creando datos empleador", datosEmpleador);
        try{
            //guardar direccion
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(template)
                    .withProcedureName(Queries.SP_INSERT_COR_DIRECCION_RETURNING_PK);
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("COD_POSTAL", "")
                    .addValue("IDE_DEPARTAMENTO",datosEmpleador.getDepartamento())
                    .addValue("IDE_MUNICIPIO",datosEmpleador.getMunicipio())
                    .addValue("IDE_PAIS",1)
                    .addValue("VAL_DIRECCION",datosEmpleador.getDireccion())
                    .addValue("VAL_CIUDAD","");
            Map<String,Object> out = jdbcCall.execute(in);
            Object id = out.get("RETURN_ID_DIRECCION");
            Object [] args = {
                    id, datosEmpleador.getEmail(), datosEmpleador.getNombreRazonSocial(), datosEmpleador.getNombreRepresentanteLegat(),
                    datosEmpleador.getNroNit(), datosEmpleador.getTelefono(), datosEmpleador.getTipoEmpleador(), datosEmpleador.getCelular(),
                    idRegTramite
            };
            template.update(Queries.INSERT_EMPLEADOR,args);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
