package ManufacturingMRPEngine.service;

import ManufacturingMRPEngine.entity.BomLink;
import ManufacturingMRPEngine.entity.Item;
import ManufacturingMRPEngine.repository.BomLinkRepository;
import ManufacturingMRPEngine.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BomLinkService {
	
	private final BomLinkRepository bomLinkRepository;
	
    private final ItemRepository itemRepository;

    public BomLinkService(BomLinkRepository bomLinkRepository,
                          ItemRepository itemRepository) {
        this.bomLinkRepository = bomLinkRepository;
        this.itemRepository = itemRepository;
    }

    // Create a new BOM link between parent and child
    public BomLink createBomLink(Long parentItemId, Long childItemId,
                                  Double quantityRequired) {

        // Prevent linking an item to itself
        if (parentItemId.equals(childItemId)) {
            throw new RuntimeException("Parent and child item cannot be the same.");
        }

        // Prevent duplicate links
        if (bomLinkRepository.existsByParentItemIdAndChildItemId(
                parentItemId, childItemId)) {
            throw new RuntimeException("BOM link already exists between these items.");
        }

        Item parent = itemRepository.findById(parentItemId)
                .orElseThrow(() -> new RuntimeException(
                        "Parent item not found: " + parentItemId));

        Item child = itemRepository.findById(childItemId)
                .orElseThrow(() -> new RuntimeException(
                        "Child item not found: " + childItemId));

        BomLink link = new BomLink(parent, child, quantityRequired);
        return bomLinkRepository.save(link);
    }

    // Get all BOM links for a parent item
    public List<BomLink> getChildrenOf(Long parentItemId) {
        return bomLinkRepository.findByParentItemId(parentItemId);
    }

    // Get all BOM links
    public List<BomLink> getAllBomLinks() {
        return bomLinkRepository.findAll();
    }

    // Delete a BOM link
    public void deleteBomLink(Long id) {
        bomLinkRepository.deleteById(id);
    }
	
}
