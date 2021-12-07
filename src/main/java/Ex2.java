import GUI.Window;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import implentations.DirectedWeightedGraphAlgorithmsImpl;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) {
        DirectedWeightedGraphAlgorithms dwga = new DirectedWeightedGraphAlgorithmsImpl();
        dwga.load(json_file);
         return dwga.getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;

        ans = new DirectedWeightedGraphAlgorithmsImpl();
        ans.load(json_file);

        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        Window w = new Window(alg);
    }

    public static void main(String[] args) {
        long heapsize = Runtime.getRuntime().totalMemory();
        runGUI("data/G1.json");
    }
}