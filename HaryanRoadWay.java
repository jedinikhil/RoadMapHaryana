import java.util.*;

class HRway {
    private final Map<String, Map<String, Edge>> adj;

    public HRway() {
        adj = new HashMap<>();
    }

    // Adds a city to the adjacency list
    public void addCity(String city) {
        adj.putIfAbsent(city, new HashMap<>());
    }

    // Adds a route between two cities (undirected graph)
    public void addRoute(String startCity, String endCity, int dist, int time, int cost) {
        adj.get(startCity).put(endCity, new Edge(dist, time, cost));
        adj.get(endCity).put(startCity, new Edge(dist, time, cost)); // As this is an undirected graph
    }

    // Function to find the shortest path based on the choice (distance, time, cost)
    public void findShortestPath(String start, String end, String choice) {
        Map<String, Integer> Dist = new HashMap<>();
        Map<String, Integer> Time = new HashMap<>();
        Map<String, Integer> Cost = new HashMap<>();
        PriorityQueue<Node> pq;

        // Choosing the priority queue based on user choice (distance, time, or cost)
        if (choice.equals("distance")) {
            pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.dis));
        } else if (choice.equals("time")) {
            pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.time));
        } else { // Default is cost
            pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        }

        // Initialize all cities with maximum values
        for (String key : adj.keySet()) {
            Dist.put(key, Integer.MAX_VALUE);
            Time.put(key, Integer.MAX_VALUE);
            Cost.put(key, Integer.MAX_VALUE);
        }

        // Set the starting city distance, time, and cost to 0
        Dist.put(start, 0);
        Time.put(start, 0);
        Cost.put(start, 0);

        pq.add(new Node(start, 0, 0, 0));

        // Dijkstra's algorithm to find the shortest path
        while (!pq.isEmpty()) {
            Node rem = pq.poll(); // Get the node with the smallest distance/time/cost
            String city = rem.city;

            // If we reached the destination, break out of the loop
            if (city.equals(end)) {
                break;
            }

            // Check all neighbors of the current city
            for (Map.Entry<String, Edge> entry : adj.get(city).entrySet()) {
                String neighbor = entry.getKey();
                Edge edge = entry.getValue();
                int newDist = rem.dis + edge.dis;
                int newTime = rem.time + edge.time;
                int newCost = rem.cost + edge.cost;

                // Update distance if a shorter path is found
                if (choice.equals("distance") && newDist < Dist.get(neighbor)) {
                    Dist.put(neighbor, newDist);
                    pq.add(new Node(neighbor, newDist, newTime, newCost));
                }
                // Update time if a faster path is found
                else if (choice.equals("time") && newTime < Time.get(neighbor)) {
                    Time.put(neighbor, newTime);
                    pq.add(new Node(neighbor, newDist, newTime, newCost));
                }
                // Update cost if a cheaper path is found
                else if (choice.equals("cost") && newCost < Cost.get(neighbor)) {
                    Cost.put(neighbor, newCost);
                    pq.add(new Node(neighbor, newDist, newTime, newCost));
                }
            }
        }

        // Output the result based on the choice
        System.out.println("Minimum " + choice + " from " + start + " to " + end + ":");
        if (choice.equals("distance")) {
            System.out.println("Distance: " + Dist.get(end));
        } else if (choice.equals("time")) {
            System.out.println("Time: " + Time.get(end));
        } else if (choice.equals("cost")) {
            System.out.println("Cost: " + Cost.get(end));
        }
    }
}

// Helper class for edges (stores distance, time, and cost)
class Edge {
    int dis;
    int time;
    int cost;

    Edge(int dis, int time, int cost) {
        this.dis = dis;
        this.time = time;
        this.cost = cost;
    }
}

// Helper class for nodes (stores city, distance, time, and cost)
class Node {
    String city;
    int dis;
    int time;
    int cost;

    Node(String city, int dis, int time, int cost) {
        this.city = city;
        this.dis = dis;
        this.time = time;
        this.cost = cost;
    }
}

// Main class to run the program
public class HaryanRoadWay {
    public static void main(String[] args) {
        HRway bus = new HRway();

        // Add cities
        bus.addCity("Ambala");
        bus.addCity("Karnal");
        bus.addCity("Panipat");
        bus.addCity("Hisar");
        bus.addCity("Chandigarh");
        bus.addCity("Rohtak");
        bus.addCity("Sonipat");
        bus.addCity("Faridabad");
        bus.addCity("Gurgaon");
        bus.addCity("Sirsa");
        bus.addCity("Bhiwani");
        bus.addCity("Nuh");

        // Add routes (distance, time, cost)
        bus.addRoute("Ambala", "Karnal", 40, 45, 100);
        bus.addRoute("Karnal", "Panipat", 30, 35, 80);
        bus.addRoute("Panipat", "Hisar", 60, 75, 150);
        bus.addRoute("Hisar", "Chandigarh", 100, 120, 200);
        bus.addRoute("Ambala", "Chandigarh", 50, 60, 120);
        bus.addRoute("Rohtak", "Sonipat", 50, 60, 110);
        bus.addRoute("Sonipat", "Faridabad", 70, 80, 140);
        bus.addRoute("Faridabad", "Gurgaon", 20, 30, 50);
        bus.addRoute("Gurgaon", "Chandigarh", 210, 210, 350);
        bus.addRoute("Sirsa", "Bhiwani", 60, 75, 130);
        bus.addRoute("Bhiwani", "Hisar", 40, 50, 90);
        bus.addRoute("Nuh", "Faridabad", 40, 50, 100);
        bus.addRoute("Ambala", "Rohtak", 100, 120, 150);
        bus.addRoute("Karnal", "Hisar", 120, 150, 250);
        bus.addRoute("Panipat", "Rohtak", 90, 100, 200);

        // Test cases for finding shortest path
        bus.findShortestPath("Ambala", "Chandigarh", "distance");
        bus.findShortestPath("Ambala", "Chandigarh", "time");
        bus.findShortestPath("Ambala", "Chandigarh", "cost");
    }
}
