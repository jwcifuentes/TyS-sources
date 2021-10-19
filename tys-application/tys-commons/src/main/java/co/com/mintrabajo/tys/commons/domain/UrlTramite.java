package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "newInstance")
public class UrlTramite {
    private Long idUrl;
    private Long idTramite;
    private String url;
    private boolean esVisible;
}
