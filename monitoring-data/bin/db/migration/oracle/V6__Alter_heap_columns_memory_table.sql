ALTER TABLE MEMORY_USAGE DROP COLUMN MEMORY_USAGE;

ALTER TABLE MEMORY_USAGE ADD (TOTAL_HEAP NUMBER(*,2), FREE_HEAP NUMBER(*,2) );