package com.mrp_engine.controller;

import com.mrp_engine.dto.request.BomRequest;
import com.mrp_engine.entity.BomLink;
import com.mrp_engine.service.BomLinkService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bom-links")
@CrossOrigin(origins = "*")
public class BomLinkController {

    private final BomLinkService bomLinkService;

    public BomLinkController(BomLinkService bomLinkService) {
        this.bomLinkService = bomLinkService;
    }

    @GetMapping
    public ResponseEntity<List<BomLink>> getAllBomLinks() {
        return ResponseEntity.ok(
                bomLinkService.getAllBomLinks());
    }

    @GetMapping("/parent/{parentItemId}")
    public ResponseEntity<List<BomLink>> getChildrenOf(
            @PathVariable Long parentItemId) {

        return ResponseEntity.ok(
                bomLinkService.getChildrenOf(parentItemId));
    }

    @PostMapping
    public ResponseEntity<BomLink> createBomLink(

            @Valid @RequestBody BomRequest request) {

        BomLink created =
                bomLinkService.createBomLink(

                        request.getParentItemId(),

                        request.getChildItemId(),

                        request.getQuantityRequired());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBomLink(
            @PathVariable Long id) {

        bomLinkService.deleteBomLink(id);

        return ResponseEntity.ok(
                "BOM link deleted successfully.");
    }

}