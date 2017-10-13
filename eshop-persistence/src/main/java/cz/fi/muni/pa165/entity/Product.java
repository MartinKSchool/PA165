package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.Color;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="ESHOP_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private Color color;

    @Temporal(value = TemporalType.DATE)
    private Date addedDate;

    public Product(Long catId){ this.id = catId; }

    public Product(){ }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getId(){
        return this.id;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product ESHOPPRODUCT = (Product) o;

        if (getId() != null ? !getId().equals(ESHOPPRODUCT.getId()) : ESHOPPRODUCT.getId() != null) return false;
        if (!getName().equals(ESHOPPRODUCT.getName())) return false;
        if (color != ESHOPPRODUCT.color) return false;
        return addedDate != null ? addedDate.equals(ESHOPPRODUCT.addedDate) : ESHOPPRODUCT.addedDate == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (addedDate != null ? addedDate.hashCode() : 0);
        return result;
    }
}
