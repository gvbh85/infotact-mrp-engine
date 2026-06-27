WITH RECURSIVE bom_explosion AS (

    -- ANCHOR (base query) — start from the root product
    SELECT
        bl.parent_item_id,
        bl.child_item_id,
        bl.quantity_required AS multiplier
    FROM bom_link bl
    WHERE bl.parent_item_id = 1   -- Bicycle's item ID

    UNION ALL

    -- RECURSIVE PART — join children to their own children
    SELECT
        bl.parent_item_id,
        bl.child_item_id,
        be.multiplier * bl.quantity_required AS multiplier
    FROM bom_link bl
    INNER JOIN bom_explosion be
        ON bl.parent_item_id = be.child_item_id
)

SELECT
    i.id AS item_id,
    i.name AS item_name,
    i.type AS item_type,
    i.unit_of_measure,
    SUM(be.multiplier) * 500 AS total_required_quantity
    -- 500 = target quantity (replace with your actual order quantity)
FROM bom_explosion be
JOIN item i ON i.id = be.child_item_id
WHERE i.type = 'RAW_MATERIAL'
GROUP BY i.id, i.name, i.type, i.unit_of_measure;




-- Clean up Query for the newly created stock after completing the testing

SET SQL_SAFE_UPDATES = 0;
DELETE FROM purchase_order WHERE item_id = 12;
DELETE FROM bom_link WHERE child_item_id = 12;
DELETE FROM item WHERE id = 12;            