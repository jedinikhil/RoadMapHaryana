# HaryanRoadWay: Shortest Path Finder

## Overview

This Java program implements Dijkstra's algorithm to find the shortest path between cities based on three criteria: distance, time, or cost. It models a road network between cities in Haryana and allows the user to query the minimum path between two cities by selecting a specific parameter.

### Key Features:
- Models a road network with cities and routes.
- Supports finding the shortest path based on distance, time, or cost.
- Implements Dijkstra's algorithm for efficient pathfinding.

## Components

- **HRway**: The main class that manages the road network and implements the shortest path finding logic.
- **Edge**: A helper class that represents a route between two cities, storing the distance, time, and cost.
- **Node**: A helper class used in Dijkstra’s algorithm to represent a city and its associated attributes (distance, time, and cost).

## How It Works

1. The program creates a road network between several cities in Haryana.
2. Each route between two cities has three attributes: distance, time, and cost.
3. Based on user input, the program finds the shortest path between two cities using Dijkstra’s algorithm, choosing the shortest route based on distance, time, or cost.
4. The shortest path is output to the console.

## Usage

### 1. Running the Program
- Compile and run the `HaryanRoadWay.java` file.
  
```bash
javac HaryanRoadWay.java
java HaryanRoadWay
