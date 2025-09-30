# Software Engineer Assessment

This repository contains my solutions for the Software Engineer Assessment.  
I picked one question from each section (Problem Solving, System Design, SQL, REST API).

---

## Problem Solving
- Solution: `src/main/java/com/assessment/problemsolving/CountAnalogousArrays.java`
- Unit tests: `src/test/java/com/assessment/problemsolving/CountAnalogousArraysTest.java`

---

## System Design
- Payment Service implementation with two payment methods:
    - `CreditCardPaymentMethod` – redeems reward points (up to 10% or $10 max)
    - `PayPalPaymentMethod` – splits into two installments (5% interest on the second)
- Files under `src/main/java/com/assessment/systemdesign/`
- Unit tests under `src/test/java/com/assessment/systemdesign/`

---

## SQL
- File: `sql/Q1_tree_nodes_classification.sql`
- Classifies nodes in a tree table as **ROOT / INNER / LEAF**
- Includes table definition, sample inserts, and the query

---

## REST API
- Spring Boot application to fetch top-rated outlets with pagination
- Files under `src/main/java/com/assessment/restapi/`
- Endpoint: GET /outlets/top-rated?page=0&size=3&minRating=4.7
- Returns outlets sorted by rating desc, then name asc, then id asc
