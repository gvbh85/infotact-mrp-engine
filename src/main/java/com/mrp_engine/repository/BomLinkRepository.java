package com.mrp_engine.repository;

import com.mrp_engine.model.BomLink;
import com.mrp_engine.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BomLinkRepository extends JpaRepository<BomLink, Long> {
    // Change findByParent to findByParentItem
    List<BomLink> findByParentItem(Item parentItem);
}