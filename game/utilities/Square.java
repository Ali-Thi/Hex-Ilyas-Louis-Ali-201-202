package game.utilities;

/**
 * Represent a square of the board
 */
public class Square {
    private int value; //square's value
    private Integer pathIndex; //index of the path where the square belongs

    public Square(){
    	value = 0;
        pathIndex = null;
    }

    public int getValue() {
        return this.value;
    }

    public boolean setValue(int value) {
        if (value != 0) {
            this.value = value;
            return true;
        }
        return false;
    }

    public int getPathIndex() {
        return this.pathIndex;
    }

    public void setPathIndex(int pathIndex) {
        this.pathIndex = pathIndex;
    }
}
