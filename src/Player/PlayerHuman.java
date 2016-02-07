package Player;


import Map.Position;
import Map.Map;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darkos
 */
public class PlayerHuman extends Player{

    public PlayerHuman(Map.TokenPlayer token) {
        super(token);
    }

    
    @Override
    public Position getActionPosition(Map map) {
        //recupperer entrée utilisateur
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrz la position de jeu en X:");
        int x = readEntry(sc)-1;
        System.out.println("Entrz la position de jeu en Y:");
        int y = readEntry(sc)-1;
        return new Position(x,y);
    }
    
    private int readEntry(Scanner sc){
        String entry = "";
        int val =-1;
        boolean valid = false;
        do{
            entry = sc.nextLine();
            
            if(entry.isEmpty()){
                System.out.println("Veuillez entrer une valeur.");
                continue;
            }
            try{
                val = Integer.parseInt(entry);
            }catch(Exception e){
                System.out.println("La valeur entrée doit etre un entier.");
                continue;
            }
            if(val<=0){
                System.out.println("La valeur entrée doit etre supérieure a 0.");
                continue;
            }
            if(val > Map.SIZE){
                System.out.println("La valeur entrée doit etre inférieure a "+ (Map.SIZE + 1) +".");
                continue;
            }
            valid = true;
        }while (!valid);
        
        return val;
    }
}
