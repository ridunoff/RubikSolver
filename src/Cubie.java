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


    public void rotate(char direction){

    }



}
