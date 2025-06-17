package yamen.marcketplace.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.internal.Nullability;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(generator = "UUIDWay")
    @GenericGenerator(name = "UUIDWay", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String imageUrl;

    @Column(name = "categories_type")
    @Getter
    @Setter
    private String categoriesType;


    // This links the current category to its parent (e.g., sub → main)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id" , nullable = true)
    @JsonBackReference // Prevents infinite loop
    @Getter
    @Setter
    private Categories parent;

    // This allows a category to know its children (main → list of subs)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL )
    @JsonManagedReference // Paired with @JsonBackReference
    @Getter
    @Setter
    private List<Categories> subCategories;

    // this relation with product
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private List<Products> products;
}
