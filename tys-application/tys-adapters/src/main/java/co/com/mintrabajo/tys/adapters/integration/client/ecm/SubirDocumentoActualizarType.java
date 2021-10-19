
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subirDocumentoActualizar_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subirDocumentoActualizar_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoEntrada"/>
 *         &lt;element name="strCodigoDependencia_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strNroRadicado_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strBase64_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intTramiteTipologia_in" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="strCorPlantilla_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strNombreDocumento_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intIdDocumento_in" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="strIdFilenet_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subirDocumentoActualizar_Type", propOrder = {
    "encabezadoEntrada",
    "strCodigoDependenciaIn",
    "strNroRadicadoIn",
    "strBase64In",
    "intTramiteTipologiaIn",
    "strCorPlantillaIn",
    "strNombreDocumentoIn",
    "intIdDocumentoIn",
    "strIdFilenetIn"
})
public class SubirDocumentoActualizarType {

    @XmlElement(name = "EncabezadoEntrada", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoEntradaType encabezadoEntrada;
    @XmlElement(name = "strCodigoDependencia_in", required = true)
    protected String strCodigoDependenciaIn;
    @XmlElement(name = "strNroRadicado_in", required = true)
    protected String strNroRadicadoIn;
    @XmlElement(name = "strBase64_in", required = true)
    protected String strBase64In;
    @XmlElement(name = "intTramiteTipologia_in")
    protected int intTramiteTipologiaIn;
    @XmlElement(name = "strCorPlantilla_in", required = true)
    protected String strCorPlantillaIn;
    @XmlElement(name = "strNombreDocumento_in", required = true)
    protected String strNombreDocumentoIn;
    @XmlElement(name = "intIdDocumento_in")
    protected int intIdDocumentoIn;
    @XmlElement(name = "strIdFilenet_in", required = true)
    protected String strIdFilenetIn;

    /**
     * Gets the value of the encabezadoEntrada property.
     * 
     * @return
     *     possible object is
     *     {@link EncabezadoEntradaType }
     *     
     */
    public EncabezadoEntradaType getEncabezadoEntrada() {
        return encabezadoEntrada;
    }

    /**
     * Sets the value of the encabezadoEntrada property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncabezadoEntradaType }
     *     
     */
    public void setEncabezadoEntrada(EncabezadoEntradaType value) {
        this.encabezadoEntrada = value;
    }

    /**
     * Gets the value of the strCodigoDependenciaIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCodigoDependenciaIn() {
        return strCodigoDependenciaIn;
    }

    /**
     * Sets the value of the strCodigoDependenciaIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCodigoDependenciaIn(String value) {
        this.strCodigoDependenciaIn = value;
    }

    /**
     * Gets the value of the strNroRadicadoIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrNroRadicadoIn() {
        return strNroRadicadoIn;
    }

    /**
     * Sets the value of the strNroRadicadoIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrNroRadicadoIn(String value) {
        this.strNroRadicadoIn = value;
    }

    /**
     * Gets the value of the strBase64In property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrBase64In() {
        return strBase64In;
    }

    /**
     * Sets the value of the strBase64In property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrBase64In(String value) {
        this.strBase64In = value;
    }

    /**
     * Gets the value of the intTramiteTipologiaIn property.
     * 
     */
    public int getIntTramiteTipologiaIn() {
        return intTramiteTipologiaIn;
    }

    /**
     * Sets the value of the intTramiteTipologiaIn property.
     * 
     */
    public void setIntTramiteTipologiaIn(int value) {
        this.intTramiteTipologiaIn = value;
    }

    /**
     * Gets the value of the strCorPlantillaIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCorPlantillaIn() {
        return strCorPlantillaIn;
    }

    /**
     * Sets the value of the strCorPlantillaIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCorPlantillaIn(String value) {
        this.strCorPlantillaIn = value;
    }

    /**
     * Gets the value of the strNombreDocumentoIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrNombreDocumentoIn() {
        return strNombreDocumentoIn;
    }

    /**
     * Sets the value of the strNombreDocumentoIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrNombreDocumentoIn(String value) {
        this.strNombreDocumentoIn = value;
    }

    /**
     * Gets the value of the intIdDocumentoIn property.
     * 
     */
    public int getIntIdDocumentoIn() {
        return intIdDocumentoIn;
    }

    /**
     * Sets the value of the intIdDocumentoIn property.
     * 
     */
    public void setIntIdDocumentoIn(int value) {
        this.intIdDocumentoIn = value;
    }

    /**
     * Gets the value of the strIdFilenetIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrIdFilenetIn() {
        return strIdFilenetIn;
    }

    /**
     * Sets the value of the strIdFilenetIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrIdFilenetIn(String value) {
        this.strIdFilenetIn = value;
    }

}
