package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosVinculacionPracticasAdolecente {
    private String nombrePractica;
    private boolean practicaGratuita;
    private List<ObjetoPractica> objectoPractica;
    private String descripcionDetallada;
    private HorarioLaboral horarioLaboral;
    private String nombreEntidadInstitucion;
    private String formaEntrega;
    private Long idFormaEntrega;
    private String lugar;
    private Long idPeriodicidadReconocimiento;
}
