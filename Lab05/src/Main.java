import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class SnakeBiteException extends Exception {
    SnakeBiteException(String message) {
        super(message);
    }
}
class CricketBiteException extends Exception {
    CricketBiteException(String message) {
        super(message);
    }
}
class VultureBiteException extends Exception {
    VultureBiteException(String message) {
        super(message);
    }
}
class TrampolineException extends Exception {
    TrampolineException(String message) {
        super(message);
    }
}
class GameWinnerException extends Exception {
    GameWinnerException(String message) {
        super(message);
    }
}

abstract class Tile{
    int length;
    int no_of_creature;
    int move;

    abstract void move() throws Exception;

}

class Snake extends Tile{
    @Override
    public void move() throws Exception{
        int snake_move ;
    }

}

class Cricket extends Tile{
    @Override
    public void move() throws Exception{
        int cricket_move ;
    }

}

class Vulture extends Tile{
    @Override
    public void move() throws Exception{
        int vulture_move ;
    }


}

class Trampoline extends Tile{
    @Override
    public void move() throws Exception{
        int snake_move ;
    }

}

class White extends Tile{
    @Override
    public void move() throws Exception{
        int white_move ;
    }

}

class Board{
    public void Cricket_board(ArrayList<Integer> cricket_arr , ArrayList<Integer> white_arr, int tiles) {
        int no_of_creature = cricket_arr.size();
        Random rand = new Random();
        for (int i = 0; i < no_of_creature; i++) {
            int rrr = rand.nextInt(white_arr.size());
            int y = white_arr.get(rrr);
            cricket_arr.add(y);
            white_arr.remove(rrr);

        }
    }
    public void Snake_board(ArrayList<Integer> snake_arr , ArrayList<Integer> white_arr, int tiles) {
        int no_of_creature = snake_arr.size();
        Random rand = new Random();
        for(int i =0 ; i < no_of_creature ; i++){
            int rrr = rand.nextInt(white_arr.size());
            int x = white_arr.get(rrr);
            snake_arr.add(x);
            white_arr.remove(rrr);
        }
    }
    public void Vulture_board(ArrayList<Integer> vulture_arr , ArrayList<Integer> white_arr, int tiles) {
        int no_of_creature = vulture_arr.size();
        Random rand = new Random();
        for(int i =0 ; i < no_of_creature ; i++){
            int rrr = rand.nextInt(white_arr.size());
            int x = white_arr.get(rrr);
            vulture_arr.add(x);
            white_arr.remove(rrr);
        }
    }

}

class Dice{
    public int Dice(){
        int dice = (int)(Math.random()*6+1);
        return dice;
    }
}

class Game{
    int tiles;
    Board bord = new Board();

    Random rand = new Random();
    Scanner in = new Scanner(System.in);
    Tile snake = new Snake();
    Tile cricket = new Cricket();
    Tile vulture = new Vulture();
    Tile trampoline = new Trampoline();
    ArrayList<Integer> snake_arr = new ArrayList<>();
    ArrayList<Integer> vulture_arr = new ArrayList<>();
    ArrayList<Integer> white_arr = new ArrayList<>();
    ArrayList<Integer> tram_arr = new ArrayList<>();
    ArrayList<Integer> cricket_arr = new ArrayList<>();

    public  void game_on(int tiles, String name){
        Player play = new Player(name);
        for(int i = 1; i < tiles+1 ; i++){
            white_arr.add(i);
        }
        bord.Cricket_board(cricket_arr,white_arr,tiles);
        bord.Snake_board(snake_arr,white_arr,tiles);
        bord.Vulture_board(vulture_arr,white_arr,tiles);
        for(int i =0 ; i < snake.no_of_creature ; i++){
            int rrr = rand.nextInt(white_arr.size());
            int x = white_arr.get(rrr);
            snake_arr.add(x);
            white_arr.remove(rrr);
        }

        for(int i =0 ; i < cricket.no_of_creature ; i++){
            int rrr = rand.nextInt(white_arr.size()) ;
            int y = white_arr.get(rrr);
            cricket_arr.add(y);
            white_arr.remove(rrr);
        }
        for(int i =0 ; i < vulture.no_of_creature ; i++){
            int rrr = rand.nextInt(white_arr.size()) ;
            int z = white_arr.get(rand.nextInt(white_arr.size()));
            vulture_arr.add(z);
            white_arr.remove(rrr);
        }
        for(int i =0 ; i <trampoline.no_of_creature ; i++){
            int rrr = rand.nextInt(white_arr.size()) ;
            int l = white_arr.get(rand.nextInt(white_arr.size()));
            tram_arr.add(l);
            white_arr.remove(rrr);
        }
        int count = 0;
        int tile_counter = 1;
        int snake_count = 0;
        int vulture_count = 0;
        int crickket_bite = 0;
        int tram_count = 0;

        while (tile_counter < tiles+1){
            int dice = Dice();
            count++;
            if(tile_counter == 1 && dice != 6){
                System.out.println(">>[Roll- " + count + "]: " + name +" rolled " +dice +
                        " at Tile-1, OOPs you need 6 to start");
                count++;
                continue;

            }
            if(tile_counter == 1 && dice == 6){
                System.out.println(">>[Roll- " + count + "]: " +name  +" rolled 6 at Tile-1." +
                        " You are out of the cage! You get a free roll");
                int dice1 = Dice();
                count++;
                //tile_counter = tile_counter + dice1 ;
                //System.out.println(">>[Roll- "+ count + "]: " + name + " rolled "+ dice1+ " at Tile-1, " +
                  //      "landed on Tile " + tile_counter);
            }
            tile_counter = tile_counter + dice ;
            System.out.println(">>[Roll- "+count + "]: " +name + " rolled "+ dice +" at Tile-" +(tile_counter-dice) +
                    ", landed on Tile " +tile_counter);
            System.out.println(">>        Trying to shake the Tile-" + tile_counter);
            for(int snakepos : snake_arr) {
                if(tile_counter == snakepos) {
                    snake_count++;
                    try{
                        throw new SnakeBiteException(">>          Hiss...! I am a Snake, you go back" +snake.move +" tiles!");
                    }
                    catch(SnakeBiteException e){
                        System.out.println(e.getMessage());
                    }

                    tile_counter = tile_counter - snake.move ;
                    if(tile_counter<0){
                        tile_counter = 1;
                    }
                    System.out.println(">>          "+name +" moved to Tile "+ tile_counter+" back ");
                }
            }

            for(int cricket_pos : cricket_arr){
                if(tile_counter == cricket_pos){
                    crickket_bite++;
                    try{
                        throw new CricketBiteException(">>           Chirp...! I am a Cricket, you go back tile-"+ cricket.move);
                    }
                    catch (CricketBiteException e){
                        System.out.println(e.getMessage());
                    }

                    tile_counter = tile_counter - cricket.move ;
                    if(tile_counter<0){
                        tile_counter = 1;
                    }
                    System.out.println(">>         " + name +" moved to Tile " + tile_counter);
                }
            }

            for(int vulture_pos : vulture_arr ){
                if(tile_counter == vulture_pos){
                    vulture_count++;
                    try{
                        throw new VultureBiteException(">>        Yapping...! I am a Vulture, you go back "+vulture.move+" tiles!");
                    }
                    catch(VultureBiteException e){
                        System.out.println(e.getMessage());
                    }
                    tile_counter = tile_counter - vulture.move;
                    if(tile_counter<0){
                        tile_counter = 1;
                    }
                    System.out.println(">>          " +name + " moved to tile- "+ tile_counter);
                }
            }

            for(int tram_pos : tram_arr){
                if(tile_counter == tram_pos) {
                    tram_count++;
                    try{
                        throw new TrampolineException(">>        PingPong! I am a Trampoline, you advance tile- " + trampoline.move);
                    }
                    catch (TrampolineException e){
                        e.getMessage();
                    }
                    tile_counter = tile_counter + trampoline.move;
                    System.out.println(">>       "+ name+ " moved to tile- " +tile_counter );
                }
            }

            System.out.println(">>       I am a White tile!" );
            System.out.println(">>       "+ name + " moved to tile- " + tile_counter);


        }
        try{
            throw new GameWinnerException(">>         "+name + " wins the race in "+ count + " rolls!");
        }
        catch (GameWinnerException e){
            System.out.println(e.getMessage());
        }
        System.out.println(">>            Total Snake Bites = "+snake_count+"\n" +
                ">>            Total Vulture Bites = "+vulture_count+"\n" +
                ">>            Total Cricket Bites = "+crickket_bite+"\n" +
                ">>            Total Trampolines = "+tram_count);

    }
    public int Dice(){
        int dice = (int)(Math.random()*6+1);
        return dice;
    }

    public void generate_no(){

        for(int i = 0; i < 4 ; i++){
            int x = 1 + (int)(Math.random() * ((10 - 2) + 1));
            if(i==0){
                snake.no_of_creature = x;
            }
            if(i==1){
                cricket.no_of_creature = x;
            }
            if(i==2){
                vulture.no_of_creature = x;
            }
            if(i==3){
                trampoline.no_of_creature = x;
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            int y = 1 + (int)(Math.random() * ((10 - 2) + 1));
            if(i==0){
                snake.move = y;
            }
            if(i==1){
                cricket.move = y;
            }
            if(i==2){
                vulture.move = y;
            }
            if(i==3){
                trampoline.move = y;
            }

        }

        System.out.println(">>Danger : there are " + snake.no_of_creature + ", " + cricket.no_of_creature +
                ", " + vulture.no_of_creature + " no of snakes, cricket and vulture");
        System.out.println(">>Each snake, cricket, vulture can you throw back by " +
                snake.move + ", " + cricket.move + ", " + vulture.move + " no of tiles");
        System.out.println(">>Good news : There are " + trampoline.no_of_creature +
                " number of Trampolines on your track!");
        System.out.println(">>Good news: Each trampoline can help you advance by "
                + trampoline.move + " no of tiles!") ;

    }



}

class Player{
    final String name;
    Player(String name){
        this.name = name;
    }


}

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        int length = 0;
        String name = "";
        boolean done = false;
        while(!done) {
            System.out.println(">>Enter total number of tiles on the track(length>40)");
            try {
                Scanner scan = new Scanner(System.in);
                length = scan.nextInt();
                done = true;
            }
            catch(InputMismatchException inp) {
                System.out.println("Wrong input");
                System.out.println("Enter again");
            }
        }
        boolean boo = false;


        game.tiles = length;
        System.out.println(">>Setting up the race track");


        game.generate_no();

        while(!boo) {
            System.out.println(">>Enter player name");
            try {
                Scanner scan = new Scanner(System.in);
                name = scan.next();
                boo = true;
            }
            catch(InputMismatchException inp) {
                System.out.println("Wrong input");
                System.out.println("Enter again");
            }
        }
        Player player = new Player(name);





        System.out.println(">>Starting the game with " + player.name + " at tile-1");
        System.out.println(">>Control transferred to computer for rolling dice for " + player.name);
        System.out.println(">>Hit Enter to start the game");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(">>Game on ====================>> ");
        game.game_on(length,name);


    }
}
