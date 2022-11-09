package cruscalucio.pizzeria.db;

import interfacce.IPrimaryKey;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Francesco
 */
@Entity
@Table(name = "pizze")
@NamedQueries({
    @NamedQuery(name = "Pizza.findAll", query = "SELECT p FROM Pizza p"),
    @NamedQuery(name = "Pizza.findById", query = "SELECT p FROM Pizza p WHERE p.id = :id"),
    @NamedQuery(name = "Pizza.findByNome", query = "SELECT p FROM Pizza p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pizza.findByIngredienti", query = "SELECT p FROM Pizza p WHERE p.ingredienti = :ingredienti"),
    @NamedQuery(name = "Pizza.findByPrezzo", query = "SELECT p FROM Pizza p WHERE p.prezzo = :prezzo")})
public class Pizza implements Serializable, IPrimaryKey {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "ingredienti")
    private String ingredienti;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prezzo")
    private Double prezzo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPizza")
    private Collection<PizzaOrdinata> pizzaOrdinataCollection;

    public Pizza() {
    }

    public Pizza(Integer id) {
        this.id = id;
    }

    public Pizza(Integer id, String nome, String ingredienti) {
        this.id = id;
        this.nome = nome;
        this.ingredienti = ingredienti;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
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
        if (!(object instanceof Pizza)) {
            return false;
        }
        Pizza other = (Pizza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cruscalucio.pizzeria.db.Pizza[ id=" + id + " ]";
    }
    
}
