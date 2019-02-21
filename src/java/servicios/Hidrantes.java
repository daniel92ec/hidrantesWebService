/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "hidrantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hidrantes.findAll", query = "SELECT h FROM Hidrantes h")
    , @NamedQuery(name = "Hidrantes.findById", query = "SELECT h FROM Hidrantes h WHERE h.id = :id")
    , @NamedQuery(name = "Hidrantes.findByPrincipal", query = "SELECT h FROM Hidrantes h WHERE h.principal = :principal")
    , @NamedQuery(name = "Hidrantes.findByInterseccion", query = "SELECT h FROM Hidrantes h WHERE h.interseccion = :interseccion")
    , @NamedQuery(name = "Hidrantes.findByLatitud", query = "SELECT h FROM Hidrantes h WHERE h.latitud = :latitud")
    , @NamedQuery(name = "Hidrantes.findByLongitud", query = "SELECT h FROM Hidrantes h WHERE h.longitud = :longitud")})
public class Hidrantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "principal")
    private String principal;
    @Size(max = 2147483647)
    @Column(name = "interseccion")
    private String interseccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Column(name = "longitud")
    private BigDecimal longitud;

    public Hidrantes() {
    }

    public Hidrantes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getInterseccion() {
        return interseccion;
    }

    public void setInterseccion(String interseccion) {
        this.interseccion = interseccion;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hidrantes)) {
            return false;
        }
        Hidrantes other = (Hidrantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hidrantes{" + "id=" + id + ", principal=" + principal + ", interseccion=" + interseccion + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }

    
    
}
