package com.mrp_engine.repository;

import com.mrp_engine.model.BomLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BomLinkRepository extends JpaRepository<BomLink, Long> {
    // Finds immediate children components linked to a parent
    List<BomLink> findByParentItemId(Long parentItemId);
}