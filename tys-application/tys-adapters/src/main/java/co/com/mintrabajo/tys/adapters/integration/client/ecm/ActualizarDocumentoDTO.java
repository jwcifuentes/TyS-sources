
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActualizarDocumentoDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActualizarDocumentoDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="strNroRadicado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strValFilenet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intIdDocumento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="intIdTramTipologia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="strAsunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarDocumentoDTO", propOrder = {
    "strNroRadicado",
    "strValFilenet",
    "intIdDocumento",
    "intIdTramTipologia",
    "strAsunto",
    "strBase64"
})
public class ActualizarDocumentoDTO {

    protected String strNroRadicado;
    protected String strValFilenet;
    protected Integer intIdDocumento;
    protected Integer intIdTramTipologia;
    protected String strAsunto;
    protected String strBase64;

    /**
     * Gets the value of the strNroRadicado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrNroRadicado() {
        return strNroRadicado;
    }

    /**
     * Sets the value of the strNroRadicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrNroRadicado(String value) {
        this.strNroRadicado = value;
    }

    /**
     * Gets the value of the strValFilenet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrValFilenet() {
        return strValFilenet;
    }

    /**
     * Sets the value of the strValFilenet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrValFilenet(String value) {
        this.strValFilenet = value;
    }

    /**
     * Gets the value of the intIdDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntIdDocumento() {
        return intIdDocumento;
    }

    /**
     * Sets the value of the intIdDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntIdDocumento(Integer value) {
        this.intIdDocumento = value;
    }

    /**
     * Gets the value of the intIdTramTipologia property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntIdTramTipologia() {
        return intIdTramTipologia;
    }

    /**
     * Sets the value of the intIdTramTipologia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntIdTramTipologia(Integer value) {
        this.intIdTramTipologia = value;
    }

    /**
     * Gets the value of the strAsunto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAsunto() {
        return strAsunto;
    }

    /**
     * Sets the value of the strAsunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAsunto(String value) {
        this.strAsunto = value;
    }

    /**
     * Gets the value of the strBase64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrBase64() {
        return strBase64;
    }

    /**
     * Sets the value of the strBase64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrBase64(String value) {
        this.strBase64 = value;
    }

}
