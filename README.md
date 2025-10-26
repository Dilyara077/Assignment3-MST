# Assignment 3 — MST (Prim vs Kruskal)

## Objective
Implement and compare **Prim’s** and **Kruskal’s** algorithms for the Minimum Spanning Tree (MST).  
Input graphs are read from JSON, results are saved to JSON and CSV for comparison.

## Files
- `src/main/java/com/mst/` → Java source code  
- `input/ass_3_input.json` → Input data  
- `output/ass_3_output.json` → Output results  
- `ass_3_comparison.csv` → Comparison data (Prim vs Kruskal)

## Summary of Results
| Criterion | Prim | Kruskal |
|------------|------|----------|
| Execution Time | Faster on dense graphs | Slightly slower |
| Operations | Fewer | More (due to sorting) |
| Total MST Cost | Same | Same |

**Conclusion:**  
Both algorithms give the same MST cost, but **Prim’s algorithm** performs better on dense graphs,  
while **Kruskal’s** is more efficient for sparse graphs.

## Author
Dilyara (Assignment 3 — Design and Analysis of Algorithms)
