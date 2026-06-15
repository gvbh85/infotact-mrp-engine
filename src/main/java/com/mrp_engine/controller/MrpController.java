package com.mrp_engine.controller;

import com.mrp_engine.dto.BomExplosionResult;
import com.mrp_engine.service.MrpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mrp")
@CrossOrigin(origins = "*")
public class MrpController {

	private final MrpService mrpService;

    public MrpController(MrpService mrpService) {
        this.mrpService = mrpService;
    }

    /**
     * POST /api/mrp/explode
     * Body: { "productId": 1, "targetQuantity": 500 }
     * Returns: flat list of all raw materials needed
     */
    @PostMapping("/explode")
    public ResponseEntity<List<BomExplosionResult>> explodeBom(
            @RequestBody Map<String, Object> request) {

        Long   productId = Long.valueOf(
                                  request.get("productId").toString());
        Double targetQuantity = Double.valueOf(
                                  request.get("targetQuantity").toString());

        List<BomExplosionResult> results =
                mrpService.explodeBom(productId, targetQuantity);

        return ResponseEntity.ok(results);
    }
	
}
