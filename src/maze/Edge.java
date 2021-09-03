package maze;

import java.util.ArrayList;
import java.util.Objects;

public class Edge implements Comparable<Edge> {
    final int point1;
    final int point2;
    final int weight;

    public Edge(int point1, int point2, int weight) {
        this.point1 = point1;
        this.point2 = point2;
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point1, point2, weight) + 111;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            if (this.point1 == ((Edge) obj).point1 ||
                    this.point1 == ((Edge) obj).point2 ||
                    this.point2 == ((Edge) obj).point2 ||
                    this.point2 == ((Edge) obj).point1) {
                return true;
            }
        }
        if (obj instanceof Integer) {
            if (this.point1 == (int) obj) {
                return true;
            } else if (this.point2 == (int) obj) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Edge t) {
        if (this.weight > t.weight) {
            return 1;
        } else if (this.weight < t.weight) {
            return -1;
        }
        return 0;
    }

    public boolean check(ArrayList<Edge> result) {
        return !(result.contains(new Edge(this.point1, -1, 0)) && result.contains(new Edge(-1, this.point2, 0)));
    }
}