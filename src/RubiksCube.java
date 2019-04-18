import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {
    ArrayList<Character> sol=new ArrayList<Character>();
    char prevRot;
    RubiksCube preCube=null;
    Cubie cubie1;
    Cubie cubie2;
    Cubie cubie3;
    Cubie cubie4;
    Cubie cubie5;
    Cubie cubie6;
    Cubie cubie7;
    Cubie cubie8;
    Cubie cubie11;
    char[] face1;
    char[] face2;
    char[] face3;
    char[] face4;
    char[] face5;
    char[] face6;

    ArrayList<char[]> faceArray;


    // initialize a solved rubiks cube
    public RubiksCube() {
        // TODO

        //Each Cubie is read from the top right corner of the cube
        //The counts of positions go counter clockwise of the top layer
        //and then also counterclockwise of the bottom layer directly below 1-4
        sol.clear();

        cubie1 = new Cubie('b', 'r', 'w');
        cubie2 = new Cubie('w', 'r', 'g');
        cubie3 = new Cubie('g', 'r', 'y');
        cubie4 = new Cubie('y', 'r', 'b');
        cubie5 = new Cubie('w', 'o', 'b');
        cubie6 = new Cubie('g', 'o', 'w');
        cubie7 = new Cubie('y', 'o', 'g');
        cubie8 = new Cubie('b', 'o', 'y');


        //face1 = new char[4];
        face1 = new char[]{'r', 'r', 'r', 'r'};  //red
        face2 = new char[]{'w', 'w', 'w', 'w'};  //white
        face3 = new char[]{'b', 'b', 'b', 'b'};  //blue
        face4 = new char[]{'g', 'g', 'g', 'g'};  //green
        face5 = new char[]{'y', 'y', 'y', 'y'};  //yellow
        face6 = new char[]{'o', 'o', 'o', 'o'};  //orange


        faceArray = new ArrayList<>();
        faceArray.add(face1);
        faceArray.add(face2);
        faceArray.add(face3);
        faceArray.add(face4);
        faceArray.add(face5);
        faceArray.add(face6);
    }


    // creates a copy of the rubiks cube
    public RubiksCube(RubiksCube r) {
        // TODO
        prevRot = r.prevRot;

        cubie1 = new Cubie(r.cubie1.front, r.cubie1.top, r.cubie1.rightSide);
        cubie2 = new Cubie(r.cubie2.front, r.cubie2.top, r.cubie2.rightSide);
        cubie3 = new Cubie(r.cubie3.front, r.cubie3.top, r.cubie3.rightSide);
        cubie4 = new Cubie(r.cubie4.front, r.cubie4.top, r.cubie4.rightSide);
        cubie5 = new Cubie(r.cubie5.front, r.cubie5.top, r.cubie5.rightSide);
        cubie6 = new Cubie(r.cubie6.front, r.cubie6.top, r.cubie6.rightSide);
        cubie7 = new Cubie(r.cubie7.front, r.cubie7.top, r.cubie7.rightSide);
        cubie8 = new Cubie(r.cubie8.front, r.cubie8.top, r.cubie8.rightSide);
        //sol = r.sol;
        preCube=r.preCube;
        updateFaces();

    }

    public void updateFaces() {


        face1 = new char[]{cubie1.top, cubie2.top, cubie3.top, cubie4.top};  //red
        face2 = new char[]{cubie1.rightSide, cubie2.front, cubie5.front, cubie6.rightSide};  //white
        face3 = new char[]{cubie1.front, cubie4.rightSide, cubie5.rightSide, cubie8.front};  //blue
        face4 = new char[]{cubie2.rightSide, cubie3.front, cubie6.front, cubie7.rightSide};  //green
        face5 = new char[]{cubie3.rightSide, cubie4.front, cubie8.rightSide, cubie7.front};  //yellow
        face6 = new char[]{cubie5.top, cubie6.top, cubie7.top, cubie8.top};  //orange

        faceArray = new ArrayList<>();

        faceArray.add(face1);
        faceArray.add(face2);
        faceArray.add(face3);
        faceArray.add(face4);
        faceArray.add(face5);
        faceArray.add(face6);

    }

    public void printFace() {
        for (int i = 0; i < 6; i++) {
            System.out.println(new String(faceArray.get(i)));
        }
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        // TODO
        char[] currentFace;
        int counter = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int counter2 = 0;
                for (int k = 0; k < faceArray.get(j).length; k++) {
                    if (other.faceArray.get(i)[k] == faceArray.get(j)[k]) {
                        counter2++;
                    }
                    //System.out.println(counter2);
                    if (counter2 == faceArray.get(j).length) {
                        counter++;
                    }
                }
            }
        }

        if (counter == 6) return true;

        return false;
    }

    /**
     * return a hashCode for this rubik's cube.
     * <p>
     * Your hashCode must follow this specification:
     * if A.equals(B), then A.hashCode() == B.hashCode()
     * <p>
     * Note that this does NOT mean:
     * if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        int hashcode = 0;
        for (int i = 0; i < face1.length; i++) {
            hashcode += (int) face1[i] * i;
            hashcode += (int) face2[i] * i;
            hashcode += (int) face3[i] * i;
            hashcode += (int) face4[i] * i;
            hashcode += (int) face5[i] * i;
            hashcode += (int) face6[i] * i;
        }
        //System.out.println(hashcode);
        return hashcode;
    }


    public boolean isSolved() {
        // TODO
        boolean topCount = false;
        boolean bottomCount = false;
        char[] topCubies = new char[8];
        topCubies[0] = cubie1.top;
        topCubies[1] = cubie2.top;
        topCubies[2] = cubie3.top;
        topCubies[3] = cubie4.top;
        topCubies[4] = cubie5.top;
        topCubies[5] = cubie6.top;
        topCubies[6] = cubie7.top;
        topCubies[7] = cubie8.top;

        if (topCubies[0] == topCubies[1] && topCubies[2] == topCubies[3] && topCubies[1] == topCubies[3]) {
            topCount = true;
        }

        if (topCubies[4] == topCubies[5] && topCubies[6] == topCubies[7] && topCubies[4] == topCubies[7]) {
            bottomCount = true;
        }

        if (topCount && bottomCount) return true;

        return false;
    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {

        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
//        printFace();
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        // TODO

        RubiksCube rotated = new RubiksCube(this);
        //rotated.sol.addAll(this.sol);

        if (c == 'u') {
            Cubie temp = cubie1;
            rotated.cubie1 = cubie2;
            rotated.cubie2 = cubie3;
            rotated.cubie3 = cubie4;
            rotated.cubie4 = temp;
            rotated.updateFaces();
            //rotated.sol.add('U');

            //rotated.printFace();

        }
        if (c == 'U') {
            //rotate cubies 1 2 3 and 4 CCW
            //cubie11 = new Cubie(this.cubie1.front, this.cubie1.top, this.cubie1.rightSide);

            rotated.cubie1 = cubie4;
            rotated.cubie2 = cubie1;
            rotated.cubie3 = cubie2;
            rotated.cubie4 = cubie3;
            rotated.updateFaces();
         //   rotated.sol.add('u');

            //rotated.printFace();
        }
        if (c == 'R') {
            //rotate cubies 1, 2, 5 and 6 CW
            //cubie11 = new Cubie(this.cubie1.front, this.cubie1.top, this.cubie1.rightSide);

            rotated.cubie1 = cubie5.rotateCCW();
            rotated.cubie2 = cubie1.rotateCW();
            rotated.cubie6 = cubie2.rotateCCW();
            rotated.cubie5 = cubie6.rotateCW();
            rotated.updateFaces();
           // rotated.sol.add('r');

            //rotated.printFace();;

        }
        if (c == 'r') {
            //rotate cubies 1, 2, 5 and 6 CCW

            rotated.cubie1 = cubie2.rotateCCW();
            rotated.cubie2 = cubie6.rotateCW();
            rotated.cubie6 = cubie5.rotateCCW();
            rotated.cubie5 = cubie1.rotateCW();
            rotated.updateFaces();
            //rotated.sol.add('R');

            //rotated.printFace();
        }
        if (c == 'F') {
            rotated.cubie1 = cubie4.rotateCW();
            rotated.cubie4 = cubie8.rotateCCW();
            rotated.cubie8 = cubie5.rotateCW();
            rotated.cubie5 = cubie1.rotateCCW();
            rotated.updateFaces();
            //rotated.sol.add('f');

            //rotated.printFace();

        }
        if (c == 'f') {
            rotated.cubie1 = cubie5.rotateCW();
            rotated.cubie4 = cubie1.rotateCCW();
            rotated.cubie8 = cubie4.rotateCW();
            rotated.cubie5 = cubie8.rotateCCW();
            rotated.updateFaces();
            //rotated.sol.add('F');

            //rotated.printFace();
        }

        return rotated;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size) {
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    public Iterable<RubiksCube> neighbors() {
        List<RubiksCube> choices = new ArrayList<RubiksCube>();
        char[] moves = {'U', 'u', 'F', 'f', 'R', 'r'};
        for (char m : moves) {
            //System.out.println(sol);
            RubiksCube buddy = new RubiksCube(this);
            //buddy.sol.addAll(this.sol);
            //buddy.sol.add(m);

            buddy.prevRot=m;
            buddy.preCube=this;
            choices.add(buddy.rotate(m));
            //System.out.println("hiyq");
            //System.out.println(buddy.sol);

        }
        return choices;
    }

    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        HashSet<RubiksCube> visitedMap = new HashSet<>();
        Queue<RubiksCube> q = new LinkedList<RubiksCube>();
        visitedMap.add(this);
        q.add(this);
        while (!q.isEmpty()) {
            RubiksCube currState = q.poll();//This could also be pollLast or pollFirst.... unclear which of the three
            for (RubiksCube currNeighbor : currState.neighbors()) {
                {if (currNeighbor.isSolved()) {
                    System.out.println("solved");
                    sol.clear();
               
                    while (currNeighbor.preCube!=null)
                    {
                        char temp = currNeighbor.prevRot;
                        System.out.println(temp);
                        sol.add(temp);
                        currNeighbor=currNeighbor.preCube;

                    }
                    Collections.reverse(sol);
                    System.out.println(sol);
                    return sol;            }
                    if(!visitedMap.contains(currNeighbor)){
                        visitedMap.add(currNeighbor);
                        q.add(currNeighbor);
                        }


                    }
                }

            }


        //System.out.println(sol);
        return new ArrayList<>();

    }

}
