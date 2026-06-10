package ManufacturingMRPEngine.repository;

import ManufacturingMRPEngine.entity.BomLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BomLinkRepository extends JpaRepository<BomLink, Long> {
	
	// Get all children of a given parent item
    List<BomLink> findByParentItemId(Long parentItemId);

    // Get all parents that use a given child item
    List<BomLink> findByChildItemId(Long childItemId);

    // Check if a specific BOM link already exists
    boolean existsByParentItemIdAndChildItemId(Long parentItemId, Long childItemId);
	
}
