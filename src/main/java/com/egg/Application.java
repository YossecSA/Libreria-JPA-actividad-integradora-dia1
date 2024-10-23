package com.egg;

import com.egg.menu.Menu;

public class Application {

    public static void main(String[] args) {

        try{
            Menu menu = new Menu();
        
            menu.mostrarMenu();
        }catch(Exception e){
            System.out.println("ERROR: " + e);
        }

    }
}
