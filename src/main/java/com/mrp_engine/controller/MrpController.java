package com.mrp_engine.controller;

import com.mrp_engine.dto.BomExplosionResult;
import com.mrp_engine.dto.request.MrpExplosionRequest;
import com.mrp_engine.service.MrpService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mrp")
@CrossOrigin(origins = "*")
public class MrpController {

    private final MrpService mrpService;

    public MrpController(MrpService mrpService) {
        this.mrpService = mrpService;
    }

    @PostMapping("/explode")
    public ResponseEntity<List<BomExplosionResult>> explodeBom(

            @Valid @RequestBody MrpExplosionRequest request) {

        List<BomExplosionResult> results =

                mrpService.explodeBom(

                        request.getProductId(),

                        request.getTargetQuantity());

        return ResponseEntity.ok(results);
    }

}