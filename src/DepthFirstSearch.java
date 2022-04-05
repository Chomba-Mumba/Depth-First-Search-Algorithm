import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

 class State {
    public char[][] positions;

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof State)){
            return false;
        }
        return Arrays.deepEquals(((State) obj).getArr(), positions);
    }
    @Override
    public int hashCode(){
        return java.util.Arrays.deepHashCode(positions);
    }

    public State(char[][] state){
        this.positions = state;
    }

    //make a copy of an array
    public char[][] copyArr(){
        char[][] arr = new char[3][3];
        for (int i=0; i<positions.length;i++){
            System.arraycopy(positions[i],0,arr[i],0,positions[i].length);
        }
        return arr;
    }

    //function to move the "0" which acts as the blank box one space right
    public char[][] rightMove(int[] pos){

        char[][] arr = this.copyArr();

        int row = pos[0];
        int clmn = pos[1];
        //if the clmn>2 we can't move right, that would be outside the puzzle
        if (clmn<2) {
            char temp = arr[row][clmn];
            arr[row][clmn] = arr[row][clmn+1];
            arr[row][clmn+1] = temp;
        }

        return arr;
    }
    //function to move the "0" which acts as the blank box one space left
    public char[][] leftMove(int[] pos){

        char[][] arr = this.copyArr();

        int row = pos[0];
        int clmn = pos[1];
        //if clmn is greater than 0 we can't move left
        if (clmn>0) {
            char temp = arr[row][clmn];
            arr[row][clmn] = arr[row][clmn-1];
            arr[row][clmn-1] = temp;
        }

        return arr;
    }
    //function to move the "0" which acts as the blank box one space up
    public char[][] upMove(int[] pos){

        char[][] arr = this.copyArr();
        int row = pos[0];
        int clmn = pos[1];

        //if the row position of the ' ' is <0 we can't move up
        if (row>0) {
            char temp = arr[row][clmn];
            arr[row][clmn] = arr[row-1][clmn];
            arr[row-1][clmn] = temp;
        }

        return arr;
    }
    //function to move the "0" which acts as the blank box one space down
    public char[][] downMove(int[] pos){

        char[][] arr = this.copyArr();
        int row = pos[0];
        int clmn = pos[1];
        //if the row position is >2 we can't move down
        if (row<2) {
            char temp = arr[row][clmn];
            arr[row][clmn] = arr[row+1][clmn];
            arr[row+1][clmn] = temp;
        }

        return arr;
    }
    public char[][] getArr(){
        return positions;
    }
}

public class DepthFirstSearch {

    //helper function to check for the presence of a state inside fringe
    static boolean contain(Stack<State> fringe,  char[][] arr){
        for(State array : fringe){
            if(Arrays.deepEquals(array.getArr(), arr)){
                return true;
            }
        }
        return false;
    }


    public static HashSet<State> R(State currentState){
        //states stored in a hashset for more efficient search using hash values
        HashSet<State> explored = new HashSet<>();
        Stack<State> fringe = new Stack<>();
        fringe.push(currentState);
        while (!fringe.empty()){
            currentState = fringe.pop();
            char[][] currentStateArr = currentState.getArr();
            explored.add(currentState);

            //store position of ' '
            int[] pos = new int[2];
            for (int y=0; y<currentStateArr.length; y++){
                for(int x=0; x<currentStateArr[y].length;x++){
                    if ((currentStateArr[y][x])== ' '){
                        pos[0] = y;
                        pos[1] = x;
                    }
                }
            }


            for (State state : successors(currentState,pos)){
                //if state not in the fringe or the explored set add it to the fringe
                char[][] statArr = state.getArr();
                if(!(explored.contains(state)||(contain(fringe,statArr)))){
                    fringe.push(state);
                }
            }
        }
        return explored;
    }

    public static ArrayList<State> successors( State state, int[] pos){
        ArrayList<State> output = new ArrayList<>();

        State movedLeft = new State(state.leftMove(pos));
        output.add(movedLeft);

        State movedRight = new State(state.rightMove(pos));
        output.add (movedRight);

        State movedUp = new State(state.upMove(pos));
        output.add (movedUp);

        State movedDown = new State(state.downMove(pos));
        output.add (movedDown);

        return output;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String set1 = scan.nextLine();
        String set2 = scan.nextLine();
        //convert input strings to char arrays
        char[][] S1 = new char[3][3];
        char[][] S2 = new char[3][3];

        int cnt=0;
        for (int i=0; i<3; i++){
            for (int x=0; x<3; x++){
                S1[i][x] = set1.charAt(cnt);
                cnt++;
            }
        }
        cnt=0;
        for (int i=0; i<3; i++){
            for (int x=0; x<3; x++){
                S2[i][x] = set2.charAt(cnt);
                cnt++;
            }
        }
        State stateS1 = new State(S1);
        State stateS2 = new State(S2);

        HashSet<State> statesS1 = R(stateS1);
        HashSet<State> statesS2 = R(stateS2);
        ArrayList<State> statesS1S2 = new ArrayList<>();

       for (State state1 : statesS1){
            if (statesS2.contains(state1)){
                statesS1S2.add(state1);

            }
        }
        try {
            //empty previous result from file
            PrintWriter pw = new PrintWriter("Output.txt");
            pw.close();
            //write new result to file
            FileWriter myWriter = new FileWriter("Output.txt",true);
            myWriter.write(statesS1.size() + " Possible States for: " + Arrays.deepToString(stateS1.getArr()) + "\n");
            for (State state : statesS1) {
                myWriter.write(Arrays.deepToString(state.getArr()) + "\n");
            }
            myWriter.write(statesS2.size() + " Possible States for: " + Arrays.deepToString(stateS2.getArr()) + "\n");
            for (State state : statesS2) {
                myWriter.write(Arrays.deepToString(state.getArr()) + "\n");
            }
            myWriter.write(statesS1S2.size() + " States in the intersection of the two initial states" + "\n");
            for (State state : statesS1S2) {
                myWriter.write(Arrays.deepToString(state.getArr()) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


