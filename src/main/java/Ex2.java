import GUI.Window;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import implentations.DirectedWeightedGraphAlgorithmsImpl;
import implentations.DirectedWeightedGraphImpl;

//TODO:make a readme
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
        if(!ans.load(json_file)){
            ans.init(new DirectedWeightedGraphImpl());
        }

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
        if(args.length >= 1){
            runGUI(args[0]);
        }else{
            runGUI("");
        }

    }
}