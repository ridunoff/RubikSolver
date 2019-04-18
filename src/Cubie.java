public class Cubie {

    char top;       //color
    char rightSide; //color
    char front;     //color

    public Cubie(char front, char top, char rightSide){
        this.front = front;
        this.top = top;
        this.rightSide = rightSide;
    }


    public boolean equals(Cubie other){
        if(other.top == top && other.rightSide == rightSide && other.front == front){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Character.toString(front);
    }

    public Cubie rotateCW(){
        Cubie newCubie = new Cubie(this.front, this.top, this.rightSide);
        char temp = top;
        newCubie.top = front;
        newCubie.front = rightSide;
        newCubie.rightSide = temp;
        return newCubie;
    }

    public Cubie rotateCCW(){
        //System.out.println(this.top);
        Cubie newCubie = new Cubie(this.front, this.top, this.rightSide);
        char temp = top;
        newCubie.top = rightSide;

        newCubie.rightSide = front;
        newCubie.front = temp;
        return newCubie;
    }



}
