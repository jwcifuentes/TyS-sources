package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.Parametro;
import co.com.mintrabajo.tys.commons.domain.Persona;
import co.com.mintrabajo.tys.commons.domain.SocioEmpresa;
import co.com.mintrabajo.tys.persistence.Queries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonaDao {
    private static final Logger LOGGER = LogManager.getLogger(PersonaDao.class);
    @Autowired
    private JdbcTemplate template;

    private Object [] extraerNombresApellidos(Persona persona){
        Object [] nombresApellidos = new String[4];
        if (persona.getNombreCompleto() == null) return nombresApellidos;
        String [] partes = persona.getNombreCompleto().split(" ");
        if(partes.length == 2){
            nombresApellidos[0] = partes[0];
            nombresApellidos[2] = partes[1];
        }
        else if(partes.length == 4){
            for (int i = 0; i<partes.length; i++) {
                nombresApellidos[i] = partes[i];
            }
        }
        return nombresApellidos;
    }

    public void actualizarPersonas(long idRegTramite, List<Persona> listaPersonas) {
        if(listaPersonas==null) return;
        for (Persona persona: listaPersonas) {
            LOGGER.info("Actualizando datos persona ", persona.getIdTipoPersona());
            String updateQuery = null;
            Object [] nombresApellidos = new Object[4];
            if(isEmptyNombresApellidos(persona)){
                nombresApellidos = extraerNombresApellidos(persona);
            }
            else {
                nombresApellidos[0] = persona.getPrimerNombre();
                nombresApellidos[1] = persona.getSegundoNombre();
                nombresApellidos[2] = persona.getPrimerApellido();
                nombresApellidos[3] = persona.getSegundoApellido();
            }
            List<Object> args = new ArrayList<>();
            args.addAll(Arrays.asList(nombresApellidos));
            if(persona.getIdTipoPersona().equals("NNA")){
                updateQuery = Queries.UPDATE_PERSONA_NNNA_BY_IDEREGTRAMITE;
                args.add(new java.sql.Time(persona.getFechaNacimiento().getTime()));
                args.add(persona.getNombreInstitucionEducativa());
                args.add(persona.getJornadaEscolar());
                args.add(persona.getCuantosHijos());
                args.add(persona.getGenero());
                args.add(persona.getCorreoElectronico());
                args.add(idRegTramite);
                //actualizarDireccionPersonaNNA(idRegTramite,persona);
                //guardarListaListaDiscapacidad(idRegTramite,persona.getCondicionDiscapacidad());
            }
            else if(persona.getIdTipoPersona().equals("ADOL")) {
                args.add(persona.getIdTipoRegimen());
                args.add(persona.getNombreRegimen());
                args.add(idRegTramite);
                updateQuery = Queries.UPDATE_PERSONA_ADOL_BY_TIPO_AND_IDTRAMITE;
            }
            else {
                args.add(idRegTramite);
                args.add(persona.getIdTipoPersona());
                updateQuery = Queries.UPDATE_PERSONA_NOMBRE_APELLIDOS_BY_TIPO_AND_IDTRAMITE;
            }
            if(updateQuery!=null){
                try{
                    //template.update(updateQuery,args.toArray());
                }
                catch (Exception e){
                    e.printStackTrace();
                    throw e;
                }
            }
        }
    }
    private boolean isEmptyNombresApellidos(Persona persona){
        return ((persona.getPrimerNombre()==null || persona.getPrimerNombre().isEmpty())
                && (persona.getSegundoNombre()==null || persona.getSegundoNombre().isEmpty())
                && (persona.getPrimerApellido()==null || persona.getPrimerApellido().isEmpty())
                && (persona.getSegundoApellido()==null || persona.getSegundoApellido().isEmpty())
        );
    }
    private void guardarListaListaDiscapacidad(long idRegTramite, List<Parametro> condicionDiscapacidad) {
        if(condicionDiscapacidad==null){
            return;
        }
        for (Parametro parametro: condicionDiscapacidad) {
            Object [] args = {idRegTramite,parametro.getId()};
            template.update(Queries.INSERT_PERSONA_DISCAPACIDAD,args);
        }
    }

    private void actualizarDireccionPersonaNNA(long idRegTramite, Persona personaNNA) {
        if(!personaNNA.getIdTipoPersona().equals("NNA")) return;;
        Object [] args = {
                personaNNA.getTipoUbicacion(), personaNNA.getNombreUbicacion(),
                personaNNA.getTipoZona(), idRegTramite
        };
        template.update(Queries.UPDATE_COR_DIRECCION_NNA,args);
    }

    public void actualizarSocios(Long idRegistroTramite, List<SocioEmpresa> listaSocios) {
        if(listaSocios==null) return;
        List<Persona> socios = listaSocios.stream().map((socio)-> Persona.newInstance()
                .idTipoPersona(socio.getIdTipoPersona())
                .idTipoIdentificacion(socio.getIdTipoIdentificacion())
                .numeroIdentificacion(socio.getNumeroIdentificacion())
                .nombreCompleto(socio.getNombreCompleto())
                .primerNombre(socio.getPrimerNombre())
                .segundoNombre(socio.getSegundoNombre())
                .primerApellido(socio.getPrimerApellido())
                .segundoApellido(socio.getSegundoApellido())
                .build()).collect(Collectors.toList());
        actualizarPersonas(idRegistroTramite,socios);
    }
}
