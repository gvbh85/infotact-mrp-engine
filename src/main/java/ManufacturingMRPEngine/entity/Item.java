package ManufacturingMRPEngine.entity;

import java.util.List;

import ManufacturingMRPEngine.enums.ItemType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;         // FINISHED_GOOD, SUB_ASSEMBLY, RAW_MATERIAL

    private String unitOfMeasure;  // e.g. "pcs", "kg", "meters"

    private String description;

    // Self-referencing relationship — a parent item can have many child items
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Item parentItem;

    // ✅ Reverse side — all children of this item
    @OneToMany(mappedBy = "parentItem", cascade = CascadeType.ALL)
    private List<Item> childItems;
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setType(ItemType type) {
    	this.type = type;
    }
    
    public void setUnitOfMeasure(String unitOfMeasure) {
    	this.unitOfMeasure = unitOfMeasure;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getUnitOfMeasure() {
    	return unitOfMeasure;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public ItemType getType() {
    	return type;
    }
    
}
