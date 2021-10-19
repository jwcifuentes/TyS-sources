
package co.com.mintrabajo.tys.adapters.integration.client.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerDatosSeccionalResponse_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerDatosSeccionalResponse_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoSalida"/>
 *         &lt;element name="strIdSeccion_out" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCodigoSeccion_out" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strIdSubFondo_out" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDatosSeccionalResponse_Type", propOrder = {
    "encabezadoSalida",
    "strIdSeccionOut",
    "strCodigoSeccionOut",
    "strIdSubFondoOut"
})
public class ObtenerDatosSeccionalResponseType {

    @XmlElement(name = "EncabezadoSalida", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoSalidaType encabezadoSalida;
    @XmlElement(name = "strIdSeccion_out")
    protected String strIdSeccionOut;
    @XmlElement(name = "strCodigoSeccion_out")
    protected String strCodigoSeccionOut;
    @XmlElement(name = "strIdSubFondo_out")
    protected String strIdSubFondoOut;

    /**
     * Gets the value of the encabezadoSalida property.
     * 
     * @return
     *     possible object is
     *     {@link EncabezadoSalidaType }
     *     
     */
    public EncabezadoSalidaType getEncabezadoSalida() {
        return encabezadoSalida;
    }

    /**
     * Sets the value of the encabezadoSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncabezadoSalidaType }
     *     
     */
    public void setEncabezadoSalida(EncabezadoSalidaType value) {
        this.encabezadoSalida = value;
    }

    /**
     * Gets the value of the strIdSeccionOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrIdSeccionOut() {
        return strIdSeccionOut;
    }

    /**
     * Sets the value of the strIdSeccionOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrIdSeccionOut(String value) {
        this.strIdSeccionOut = value;
    }

    /**
     * Gets the value of the strCodigoSeccionOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCodigoSeccionOut() {
        return strCodigoSeccionOut;
    }

    /**
     * Sets the value of the strCodigoSeccionOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCodigoSeccionOut(String value) {
        this.strCodigoSeccionOut = value;
    }

    /**
     * Gets the value of the strIdSubFondoOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrIdSubFondoOut() {
        return strIdSubFondoOut;
    }

    /**
     * Sets the value of the strIdSubFondoOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrIdSubFondoOut(String value) {
        this.strIdSubFondoOut = value;
    }

}
