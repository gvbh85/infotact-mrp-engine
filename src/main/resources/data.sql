-- 1. Insert Items (Using INSERT IGNORE so it doesn't crash on duplicate restarts)
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


-- 2. Insert BOM Links (Defining the Recipe/Hierarchy)
-- Bicycle needs 2 Wheels
INSERT IGNORE INTO bom_links (id, parent_item_id, child_item_id, quantity_required) 
VALUES (1, 1, 2, 2);

-- Bicycle needs 1 Frame
INSERT IGNORE INTO bom_links (id, parent_item_id, child_item_id, quantity_required) 
VALUES (2, 1, 5, 1);

-- Wheel needs 30 Spokes
INSERT IGNORE INTO bom_links (id, parent_item_id, child_item_id, quantity_required) 
VALUES (3, 2, 3, 30);

-- Wheel needs 1 Tire
INSERT IGNORE INTO bom_links (id, parent_item_id, child_item_id, quantity_required) 
VALUES (4, 2, 4, 1);