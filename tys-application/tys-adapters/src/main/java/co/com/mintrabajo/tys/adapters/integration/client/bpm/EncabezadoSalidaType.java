
package co.com.mintrabajo.tys.adapters.integration.client.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EncabezadoSalidaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncabezadoSalidaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PeticionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RtaCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RtaDescripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Criticidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RtaCodigoHost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RtaDescripcionHost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncabezadoSalidaType", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", propOrder = {
    "peticionId",
    "rtaCodigo",
    "rtaDescripcion",
    "criticidad",
    "rtaCodigoHost",
    "rtaDescripcionHost"
})
public class EncabezadoSalidaType {

    @XmlElement(name = "PeticionId", required = true)
    protected String peticionId;
    @XmlElement(name = "RtaCodigo", required = true)
    protected String rtaCodigo;
    @XmlElement(name = "RtaDescripcion", required = true)
    protected String rtaDescripcion;
    @XmlElement(name = "Criticidad", required = true)
    protected String criticidad;
    @XmlElement(name = "RtaCodigoHost", required = true)
    protected String rtaCodigoHost;
    @XmlElement(name = "RtaDescripcionHost", required = true)
    protected String rtaDescripcionHost;

    /**
     * Gets the value of the peticionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeticionId() {
        return peticionId;
    }

    /**
     * Sets the value of the peticionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeticionId(String value) {
        this.peticionId = value;
    }

    /**
     * Gets the value of the rtaCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtaCodigo() {
        return rtaCodigo;
    }

    /**
     * Sets the value of the rtaCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtaCodigo(String value) {
        this.rtaCodigo = value;
    }

    /**
     * Gets the value of the rtaDescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtaDescripcion() {
        return rtaDescripcion;
    }

    /**
     * Sets the value of the rtaDescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtaDescripcion(String value) {
        this.rtaDescripcion = value;
    }

    /**
     * Gets the value of the criticidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCriticidad() {
        return criticidad;
    }

    /**
     * Sets the value of the criticidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCriticidad(String value) {
        this.criticidad = value;
    }

    /**
     * Gets the value of the rtaCodigoHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtaCodigoHost() {
        return rtaCodigoHost;
    }

    /**
     * Sets the value of the rtaCodigoHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtaCodigoHost(String value) {
        this.rtaCodigoHost = value;
    }

    /**
     * Gets the value of the rtaDescripcionHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtaDescripcionHost() {
        return rtaDescripcionHost;
    }

    /**
     * Sets the value of the rtaDescripcionHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtaDescripcionHost(String value) {
        this.rtaDescripcionHost = value;
    }

}
