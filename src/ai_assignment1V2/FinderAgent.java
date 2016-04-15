package ai_assignment1V2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class FinderAgent<T extends Puzzle> {

    //PRIVATE VARIABLES
    private final Node<T> start;
    private final T end;
    private final int MAX_NODES;
    private int NumNodes = 0;
    private int NumExploredStates = 0;
    private int NumGeneratedNodes = 0;

    //PRIVATE FUNCTIONS
    private LinkedList<Node<T>> constructPath(Node<T> node) {
        LinkedList<Node<T>> finalPath = new LinkedList<>();
        Node<T> pathNode = node;
        NumNodes = pathNode.getDISTANCE_COST();
        while (pathNode.getRoot() != null) {
            finalPath.addFirst(pathNode);
            pathNode = pathNode.getRoot();
        }
        finalPath.addFirst(start);
        //NumNodes = finalPath.size();
        return finalPath;
    }

    private long factorial(long number) {
        if (number <= 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    //CONSTRUCTOR
    public FinderAgent(T start, T end) {
        this.start = new Node(start, null, 0, EnumDir.ROOT);
        this.end = (T) end.returnDeepCopy();
        this.MAX_NODES = (int) factorial(this.start.getData().getColumns() * this.start.getData().getRows());
    }

    //COPY CONSTRUCTOR
    public FinderAgent(FinderAgent<T> copy) {
        this.start = new Node(copy.getStart());
        this.end = (T) copy.getEnd().returnDeepCopy();
        this.MAX_NODES = copy.getMaxNodes();
    }

    //GETTERS
    public Node<T> getStart() {
        return new Node(start);
    }

    public T getEnd() {
        return (T) end.returnDeepCopy();
    }

    public int getNumNodes() {
        return NumNodes;
    }

    public int getMaxNodes() {
        return MAX_NODES;
    }

    public int getNumExploredStates() {
        return NumExploredStates;
    }

    public int getNumGeneratedNodes() {
        return NumGeneratedNodes;
    }

    @Override
    public String toString() {
        return "Intial Puzzle State: " + Arrays.toString(start.getData().getData()) + " Goal Puzzle State: " + Arrays.toString(end.getData());
    }

    //PUBLIC FUNCTION REQUIRED FOR SEARCH
    //Search Algorithms using a priority Queue and a heursitic function
    public LinkedList<Node<T>> search(SearchAlgorithm<Node<T>> algo, EnumHeursitic heu) {
//        /**
//         * Sets the limit of the number of nodes that the algorithm may use
//         * when checking its logic.
//         */
//        algo.setLimit(start, new Node<>(end));

        /**
         * HashMap is use for the closed list, It holds states that have been
         * visited PriorityQueue is used for the open list, It hold all states
         * yet to be visited I went with a PriorityQueue with a comparator as
         * this allows the solution to be highly generic.
         */
        Map<Integer, Node<T>> closed = new HashMap<>();
        PriorityQueue<Node<T>> open = new PriorityQueue<>(16, algo.getComparator());

        /**
         * This line is here as it removes the warning that I was getting,
         * further down in the code.
         */
        Node<T> origin = start;

        /**
         * Set the HEURSITIC_COST for the initial node, and then adds the node
         * to the open list.
         */
        start.setHEURSITIC_COST(heu.value(start.getData(), end));
        open.add(start);

        /**
         * The main loop of searching, with the configurations set out by the
         * algorithm.
         */
        while (!open.isEmpty()) {
            /**
             * Grabs the first Item of the queue, the queue is order by the
             * comparator to fit the algorithm.
             */
            origin = open.poll();
            int watcherDepth = algo.getNodeCost(origin);
            /**
             * Checks if the current node has already been visited, if it has
             * been visited then it will skip over the node if it hasn't then it
             * will set the node to be visited.
             */
            if (closed.containsKey(origin.hashCode() + algo.getNodeCost(origin))) {
                continue;
            }
            closed.put(origin.hashCode() + algo.getNodeCost(origin), origin);

            /**
             * Checks the current node to confirm that it complies with the
             * Algorithm.
             */
            if (algo.limitReached(origin)) {
                continue;
            }

            /**
             * Checks if the current states meets the goal state If true then
             * will calculate the explored states and return the path to the
             * goal.
             */
            if (origin.isGoal(end)) { //early exit once path found
                NumExploredStates = closed.size();
                return constructPath(origin);
            }

            /**
             * Will generate the neighbouring nodes, This can either be a node
             * of null.
             */
            Node<T> neighbours[] = origin.genNeighbours();

            /**
             * Will loop through the neighbours, and if it isn't null, it will
             * calculate its heursitic add the node to the open list, and then
             * increment the number of nodes generated.
             */
            for (int index = 0; index < neighbours.length; index++) {
                if (neighbours[index] != null) {
                    neighbours[index].setHEURSITIC_COST(heu.value(neighbours[index].getData(), end));
                    open.add(neighbours[index]);
                    NumGeneratedNodes++;
                }
            }
        }

        /**
         * If the algorithm has a limiter aspect, this will reset the searching
         * function, and increase the limiter, before starting the search again.
         */
        if (algo.limitReached(origin)) {
            algo.setLimit(origin, new Node<>(end));
            closed.clear();
            open.clear();
            return search(algo, heu);
        }

        /**
         * Will return the root node as a path if no path could be found.
         */
        return constructPath(start);
    }
    //CUSTOM SEARCHING ALGORHYTHIMS

}
