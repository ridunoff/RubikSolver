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
        char temp = top;
        this.top = front;
        this.front = rightSide;
        this.rightSide = temp;
        return this;
    }

    public Cubie rotateCCW(){
        //System.out.println(this.top);
        char temp = top;
        this.top = rightSide;

        this.rightSide = front;
        this.front = temp;
        return this;
    }



}
