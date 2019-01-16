import java.util.Scanner;


public class Main{
    public static void main(String[] args){
        System.out.println("Project Started!");
    }
}


class Pasture{
    private static final int size = 5; // number of cows
    private static final int limit  = 250; // maximum number of moves
    private int number; // number of moves
    private Location shepherd;
    private Location cattle[];
    private Location cane;
    private Location wolf;
    private Location stall;
    private Location initialLocation;
    private boolean hasCane;

    public Pasture(){
        cattle = new Location[size];
        number = 0;
    }

    public Pasture(boolean random){
        this();
        if(random){
            //---------------------------------------------------------------------------------------------------
            //---------------------------------------------------------------------------------------------------
            //---------------------------------------------------------------------------------------------------
            //---------------------------------------------------------------------------------------------------
            //---------------------------------------------------------------------------------------------------
        }
        else{
            cattle[0] = new Location(2, 2);
            cattle[1] = new Location(4, 6);
            cattle[2] = new Location(5, 2);
            cattle[3] = new Location(6, 8);
            cattle[4] = new Location(9, 9);
            shepherd  = new Location(8, 5);
            cane = new Location(7, 5);
            wolf = new Location(10, 3);
            stall = new Location(2, 10);
        }
        initialLocation = shepherd;
    }

    private void print(String str){
        System.out.println(str);
    }

    public Pasture(Scanner inputStream){
        print("Please enter the coordinates of the shepherd:");
        int row = inputStream.nextInt();
        int col = inputStream.nextInt();
        shepherd = new Location(row, col);
        for(int i = 0; i < size; i++){
            print("Please enter the coordinates of the next cow:");
            row = inputStream.nextInt();
            col = inputStream.nextInt();
            cattle[i] = new Location(row, col);
        }
        print("Please enter the coordinates of the cane:");
        row = inputStream.nextInt();
        col = inputStream.nextInt();
        cane = new Location(row, col);
        print("Please enter the coordinates of the wolf:");
        row = inputStream.nextInt();
        col = inputStream.nextInt();
        wolf = new Location(row, col);
        print("Please enter the coordinates of the stall:");
        row = inputStream.nextInt();
        col = inputStream.nextInt();
        stall = new Location(row, col);
        initialLocation = shepherd;
    }

    private boolean hasCane(Location location){
        return cane.equals(location);
    }

    private boolean hasCattle(Location location){
        for(int i = 0; i < size; i++)
            if(location.equals(cattle[i]))
                return true;
        return false;
    }

    private boolean hasWolf(Location location){
        return wolf.equals(location);
    }

    private void end(){
        System.exit(0);
    }

    private boolean checkMove(Direction dir){
        if(number == limit){
            print("Your time is finished!");
            end();
        }
        number++;
        if(Direction.intermediate(dir))
            return false;
        Location location = shepherd.getLocation(dir);
        if(!location.isValid())
            return false;
        return false;
    }

    public Location getInitialLocation(){
        return initialLocation;
    }

    public boolean nearToWolf(){
        return shepherd.adjacentTo(wolf);
    }

    public boolean nearToCane(){
        return shepherd.adjacentTo(cane);
    }

    public boolean nearToCattle(){
        for(int i= 0; i < size; i++)
            if(shepherd.adjacentTo(cattle[i]) == true)
                return true;
        return false;
    }
}


class Shepherd extends Entity{
    private Location location;
    private final Pasture pasture;
    private boolean foundCane;

    public Shepherd(Pasture pasture, Location location){
        this.pasture = pasture;
        location = pasture.getInitialLocation();
        foundCane = false;
    }

    public Shepherd(Pasture pasture, int row, int col){
        this(pasture, new Location(row, col));
    }
}


class Location{
    private static final int size = 10;
    private int row;
    private int col;

    public Location(){}

    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public boolean equals(Object object){
        if(object == this)
            return true;
        if(!(object instanceof Location))
            return false;
        Location other = (Location) object;
        return (row == other.getRow() && col == other.getCol());
    }

    public boolean adjacentTo(Location location){
        int dr = row - location.getRow();
        int dc = col - location.getCol();
        return (dr >= -1 && dr <= 1 && dc >= -1 && dc <= 1);
    }

    public boolean isValid(){
        return (row > 0 && row <= size && col > 0 && col <= size);
    }

    public Location getLocation(Direction dir){
        Location result;
        switch(dir){
            case N:
                result = new Location(row - 1, col);
                break;
            case E:
                result = new Location(row, col + 1);
                break;
            case S:
                result = new Location(row + 1, col);
                break;
            case W:
                result = new Location(row, col - 1);
                break;
            case NE:
                result = new Location(row - 1, col + 1);
                break;
            case SE:
                result = new Location(row + 1, col + 1);
                break;
            case SW:
                result = new Location(row + 1, col - 1);
                break;
            case NW:
                result = new Location(row - 1, col - 1);
                break;
            default:
                result = new Location(0, 0); // The code actually doesn't reach here.
        }
        return result;
    }
}


enum Direction{
    N, E, S, W, NE, SE, SW, NW;

    public static boolean cardinal(Direction dir){
        return (dir == N || dir == E || dir == S || dir == W);
    }

    public static boolean intermediate(Direction dir){
        return !cardinal(dir);
    }
}






































