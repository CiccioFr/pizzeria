/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruscalucio.pizzeria.db;

import interfacce.IPrimaryKey;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author WS2
 */
@Entity
@Table(name = "ordini")
@NamedQueries({
    @NamedQuery(name = "Ordine.findAll", query = "SELECT o FROM Ordine o"),
    @NamedQuery(name = "Ordine.findById", query = "SELECT o FROM Ordine o WHERE o.id = :id"),
    @NamedQuery(name = "Ordine.findByData", query = "SELECT o FROM Ordine o WHERE o.data = :data"),
    @NamedQuery(name = "Ordine.findByOra", query = "SELECT o FROM Ordine o WHERE o.ora = :ora"),
    @NamedQuery(name = "Ordine.findByOraConsegna", query = "SELECT o FROM Ordine o WHERE o.oraConsegna = :oraConsegna")})
public class Ordine implements Serializable, IPrimaryKey {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "ora")
    @Temporal(TemporalType.TIME)
    private Date ora;
    @Column(name = "ora_consegna")
    @Temporal(TemporalType.TIME)
    private Date oraConsegna;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdine")
    private Collection<PizzaOrdinata> pizzaOrdinataCollection;

    public Ordine() {
    }

    public Ordine(Integer id) {
        this.id = id;
    }

    public Ordine(Integer id, Date data, Date ora) {
        this.id = id;
        this.data = data;
        this.ora = ora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getOra() {
        return ora;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public Date getOraConsegna() {
        return oraConsegna;
    }

    public void setOraConsegna(Date oraConsegna) {
        this.oraConsegna = oraConsegna;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Collection<PizzaOrdinata> getPizzaOrdinataCollection() {
        return pizzaOrdinataCollection;
    }

    public void setPizzaOrdinataCollection(Collection<PizzaOrdinata> pizzaOrdinataCollection) {
        this.pizzaOrdinataCollection = pizzaOrdinataCollection;
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
        if (!(object instanceof Ordine)) {
            return false;
        }
        Ordine other = (Ordine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cruscalucio.pizzeria.db.Ordine[ id=" + id + " ]";
    }
    
}
