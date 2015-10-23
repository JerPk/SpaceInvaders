package spaceinvaders.spaceinvaders_framework;

import state.Executor;

public class Main {

    public static void main(String argv[]) {
    	Executor ex = new Executor();
    	ex.run();
    	/*
    	Menu gameMenu = new Menu();
        while (gameMenu.isRunning()) {
        	gameMenu.runMenu();
        }

        Game game = new Game();
        game.start();
        */
    }
    
    public Main() {

    }

/*    
    public void run() {
        Game game = new Game();
        game.start();
        

        	

        }
        
    	Menu gameMenu = new Menu();
    	//ScoreMenu s_menu = new ScoreMenu();
        while (gameMenu.isRunning()) {
        	gameMenu.runMenu();
        	
        }
        //s_menu.show();  
        restart();
    }
    
    /**
     * the getter method for the thread of the game. mainly used for testing.
     * 
     * @return thread
    
    public Thread getThread() {
        return thread;
    }
    */
}
