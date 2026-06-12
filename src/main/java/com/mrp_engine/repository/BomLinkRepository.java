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
    @Query("SELECT COUNT(b) > 0 FROM BomLink b " + 
        "WHERE b.parentItem.id = :parentId " + 
        "AND b.childItem.id = :childId")
    boolean existsByParentItemIdAndChildItemId(
            @Param("parentId") Long parentId, 
            @Param("childId") Long childId);
	
}
