-- ====================================================================
-- PART 1 & 2: MANUFACTURING ITEMS & BILL OF MATERIALS (BOM) HIERARCHY
-- ====================================================================

-- 1. Insert Items (Using INSERT IGNORE to prevent duplicate primary key failures)
INSERT IGNORE INTO items (id, sku, name, item_type, created_at) 
VALUES (1, 'BIKE-001', 'Mountain Bicycle', 'FINISHED_GOOD', NOW());

INSERT IGNORE INTO items (id, sku, name, item_type, created_at) 
VALUES (2, 'WHL-002', 'All-Terrain Wheel', 'SUB_ASSEMBLY', NOW());

INSERT IGNORE INTO items (id, sku, name, item_type, created_at) 
VALUES (3, 'SPK-003', 'Stainless Steel Spoke', 'RAW_MATERIAL', NOW());

INSERT IGNORE INTO items (id, sku, name, item_type, created_at) 
VALUES (4, 'TIR-004', 'Rubber Tire 26-inch', 'RAW_MATERIAL', NOW());

INSERT IGNORE INTO items (id, sku, name, item_type, created_at) 
VALUES (5, 'FRM-005', 'Aluminum Frame', 'RAW_MATERIAL', NOW());


-- 2. Insert BOM Links (Using REPLACE INTO so configurations refresh if edited)
REPLACE INTO bom_links (id, parent_id, child_id, quantity_per_parent) 
VALUES (1, 1, 2, 2);

REPLACE INTO bom_links (id, parent_id, child_id, quantity_per_parent) 
VALUES (2, 1, 5, 1);

REPLACE INTO bom_links (id, parent_id, child_id, quantity_per_parent) 
VALUES (3, 2, 3, 30);

REPLACE INTO bom_links (id, parent_id, child_id, quantity_per_parent) 
VALUES (4, 2, 4, 1);


-- ====================================================================
-- PART 3: INVENTORY TRACKING (STOCK LEVELS & SAFETY BUFFER)
-- ====================================================================

-- Seed Stock Quantities (Using REPLACE INTO to reset stock levels cleanly on boot)
REPLACE INTO inventory (id, item_id, quantity_on_hand, safety_stock) 
VALUES (1, 1, 5, 2);   -- Mountain Bicycle in stock

REPLACE INTO inventory (id, item_id, quantity_on_hand, safety_stock) 
VALUES (2, 2, 12, 5);  -- All-Terrain Wheels in stock

REPLACE INTO inventory (id, item_id, quantity_on_hand, safety_stock) 
VALUES (3, 3, 150, 50);-- Stainless Steel Spokes in stock

REPLACE INTO inventory (id, item_id, quantity_on_hand, safety_stock) 
VALUES (4, 4, 8, 10);  -- Rubber Tires in stock

REPLACE INTO inventory (id, item_id, quantity_on_hand, safety_stock) 
VALUES (5, 5, 3, 2);   -- Aluminum Frames in stock