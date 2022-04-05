import java.util.*;

/*public class State {
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
    }*/
