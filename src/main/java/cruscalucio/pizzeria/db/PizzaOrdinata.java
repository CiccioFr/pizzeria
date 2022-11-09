/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cruscalucio.pizzeria.db;

import interfacce.IPrimaryKey;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author WS2
 */
@Entity
@Table(name = "pizzeordinate")
@NamedQueries({
    @NamedQuery(name = "PizzaOrdinata.findAll", query = "SELECT p FROM PizzaOrdinata p"),
    @NamedQuery(name = "PizzaOrdinata.findById", query = "SELECT p FROM PizzaOrdinata p WHERE p.id = :id")})
public class PizzaOrdinata implements Serializable, IPrimaryKey {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_ordine", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ordine idOrdine;
    @JoinColumn(name = "id_pizza", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pizza idPizza;

    public PizzaOrdinata() {
    }

    public PizzaOrdinata(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ordine getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(Ordine idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Pizza getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(Pizza idPizza) {
        this.idPizza = idPizza;
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
        if (!(object instanceof PizzaOrdinata)) {
            return false;
        }
        PizzaOrdinata other = (PizzaOrdinata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cruscalucio.pizzeria.db.PizzaOrdinata[ id=" + id + " ]";
    }
    
}
