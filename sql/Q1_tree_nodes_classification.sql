-- Oracle SQL solution: Classify each node in TREE as ROOT, INNER, or LEAF
-- ROOT  = P_ID IS NULL
-- LEAF  = node has no children
-- INNER = node has parent and at least one child

-- Drop and recreate table for demo purposes
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE tree';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -942 THEN RAISE; END IF; -- ignore "table does not exist"
END;
/

CREATE TABLE tree (
    id   NUMBER PRIMARY KEY,
    p_id NUMBER
);

-- Sample data
INSERT INTO tree (id, p_id) VALUES (1, NULL);
INSERT INTO tree (id, p_id) VALUES (2, 1);
INSERT INTO tree (id, p_id) VALUES (3, 1);
INSERT INTO tree (id, p_id) VALUES (4, 2);
INSERT INTO tree (id, p_id) VALUES (5, 2);
INSERT INTO tree (id, p_id) VALUES (6, 4);

COMMIT;

-- Query solution
SELECT
    t.id,
    CASE
        WHEN t.p_id IS NULL THEN 'ROOT'
        WHEN NOT EXISTS (SELECT 1 FROM tree c WHERE c.p_id = t.id) THEN 'LEAF'
        ELSE 'INNER'
    END AS type
FROM tree t
ORDER BY t.id;
