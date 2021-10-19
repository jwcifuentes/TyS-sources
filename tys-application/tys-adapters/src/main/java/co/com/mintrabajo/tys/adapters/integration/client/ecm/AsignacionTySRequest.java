package co.com.mintrabajo.tys.adapters.integration.client.ecm;

public class AsignacionTySRequest {
	
	
	private String DecisionID;
	private String grado;
	private String tipoDT; 
	private Boolean tipoRadi;
	private String tramite;
	private String usuario;
	private String dTerritorial;
	
	
	public AsignacionTySRequest() {
		super();		
	}

	public AsignacionTySRequest(String decisionID, String grado, String tipoDT, Boolean tipoRadi, String tramite,
			String usuario, String dTerritorial) {
		super();
		DecisionID = decisionID;
		this.grado = grado;
		this.tipoDT = tipoDT;
		this.tipoRadi = tipoRadi;
		this.tramite = tramite;
		this.usuario = usuario;
		this.dTerritorial = dTerritorial;
	}




	public String getDecisionID() {
		return DecisionID;
	}


	public void setDecisionID(String decisionID) {
		DecisionID = decisionID;
	}


	public String getGrado() {
		return grado;
	}


	public void setGrado(String grado) {
		this.grado = grado;
	}


	public String getTipoDT() {
		return tipoDT;
	}


	public void setTipoDT(String tipoDT) {
		this.tipoDT = tipoDT;
	}


	public Boolean getTipoRadi() {
		return tipoRadi;
	}


	public void setTipoRadi(Boolean tipoRadi) {
		this.tipoRadi = tipoRadi;
	}


	public String getTramite() {
		return tramite;
	}


	public void setTramite(String tramite) {
		this.tramite = tramite;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getdTerritorial() {
		return dTerritorial;
	}


	public void setdTerritorial(String dTerritorial) {
		this.dTerritorial = dTerritorial;
	}
	
	
}
