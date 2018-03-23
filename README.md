# Kotlin Collision Detector
Performs collision detection on convex 2D polygons by means of the separating axis theorem.

This is a Kotlin port of the collision detection logic used by pyTanks 
[here](https://github.com/JoelEager/pyTanks.Server/blob/master/gameLogic/collisionDetector.py). Both this 
implementation and the Python implementation located there are configured to test the execution time of this algorithm.

This algorithm has also been ported to Rust [here](https://github.com/JoelEager/Rust-Collision-Detector).

## Setup
Import this repo's contents into IntelliJ as a new Kotlin project.

## Usage
```bash
java [kotlin args] MainKt [iterations]
```

## Testing results
**10,000 iterations on a custom desktop with an AMD A10-6800K:**

| Mode            | Time for Kotlin | Time for Python   |
| --------------- | --------------- | ----------------- |
| With max_dist   | 0.346 seconds   | 15.844 seconds    |
| Without         | 0.767 seconds   | 40.617 seconds    |

(See the Rust results [here](https://github.com/JoelEager/Rust-Collision-Detector#testing-results).)