package com.mrp_engine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mrp_engine.entity.BomLink;
import com.mrp_engine.service.BomLinkService;


@RestController
@RequestMapping("/api/bom-links")
@CrossOrigin(origins = "*")
public class BomLinkController {
	
	private final BomLinkService bomLinkService;

    public BomLinkController(BomLinkService bomLinkService) {
        this.bomLinkService = bomLinkService;
    }

    // GET all BOM links
    @GetMapping
    public ResponseEntity<List<BomLink>> getAllBomLinks() {
        return ResponseEntity.ok(bomLinkService.getAllBomLinks());
    }

    // GET all children of a parent item
    @GetMapping("/parent/{parentItemId}")
    public ResponseEntity<List<BomLink>> getChildrenOf(
            @PathVariable Long parentItemId) {
        return ResponseEntity.ok(bomLinkService.getChildrenOf(parentItemId));
    }

    // POST create a new BOM link
    // Request body: { "parentItemId": 1, "childItemId": 2, "quantityRequired": 2.0 }
    @PostMapping
    public ResponseEntity<BomLink> createBomLink(
            @RequestBody Map<String, Object> request) {

        Long parentItemId = Long.valueOf(request.get("parentItemId").toString());
        Long childItemId  = Long.valueOf(request.get("childItemId").toString());
        Double quantity   = Double.valueOf(request.get("quantityRequired").toString());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bomLinkService.createBomLink(parentItemId, childItemId, quantity));
    }

    // DELETE a BOM link
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBomLink(@PathVariable Long id) {
        bomLinkService.deleteBomLink(id);
        return ResponseEntity.ok("BOM link deleted successfully.");
    }
	
}