import java.util.Arrays;

public class UnionFind {
    // TODO: Instance variables
    private int[] nodes;
    private int length;


    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        nodes = new int[N];
        length = N;
        Arrays.fill(nodes, -1);
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -nodes[find(v)];
    }

    /* Check if the input is out of array range*/
    public void checkArg(int v) {
        if (v >= length || v < 0) {
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        checkArg(v);
        return nodes[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        checkArg(v1);
        checkArg(v2);
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        checkArg(v);
        int origin = v;
        while (nodes[v] >= 0) {
            v = nodes[v];
        }
        if (v != origin) {
            nodes[origin] = v;
        }
        return v;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        checkArg(v1);
        checkArg(v2);
        if (v1 == v2) return;
        if (sizeOf(v1) > sizeOf(v2)) {
            int size = sizeOf(v2);
            nodes[find(v2)] = find(v1);
            nodes[find(v1)] -= size;
        } else {
            int size = sizeOf(v1);
            nodes[find(v1)] = find(v2);
            nodes[find(v2)] -= size;
        }
    }

}
