package com.mrp_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mrp_engine.entity.BomLink;

import java.util.List;

@Repository
public interface BomLinkRepository extends JpaRepository<BomLink, Long> {
	
	// Get all children of a given parent item
    List<BomLink> findByParentItemId(Long parentItemId);

    // Get all parents that use a given child item
    List<BomLink> findByChildItemId(Long childItemId);

    // Check if a specific BOM link already exists
    boolean existsByParentItemIdAndChildItemId(Long parentItemId, Long childItemId);
    
    @Query(value = """
    	    WITH RECURSIVE bom_explosion AS (
    	        SELECT bl.parent_item_id, bl.child_item_id,
    	               bl.quantity_required AS multiplier
    	        FROM bom_link bl
    	        WHERE bl.parent_item_id = :productId

    	        UNION ALL

    	        SELECT bl.parent_item_id, bl.child_item_id,
    	               be.multiplier * bl.quantity_required
    	        FROM bom_link bl
    	        INNER JOIN bom_explosion be
    	            ON bl.parent_item_id = be.child_item_id
    	    )
    	    SELECT i.id AS itemId, i.name AS itemName,
    	           i.type AS itemType, i.unit_of_measure AS unitOfMeasure,
    	           SUM(be.multiplier) * :targetQty AS requiredQuantity
    	    FROM bom_explosion be
    	    JOIN item i ON i.id = be.child_item_id
    	    WHERE i.type = 'RAW_MATERIAL'
    	    GROUP BY i.id, i.name, i.type, i.unit_of_measure
    	    """, nativeQuery = true)
    	List<Object[]> explodeBomNative(@Param("productId") Long productId,
    	                                  @Param("targetQty") Double targetQty);
	
}
