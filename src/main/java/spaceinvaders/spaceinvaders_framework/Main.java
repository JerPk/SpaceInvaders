package spaceinvaders.spaceinvaders_framework;

public class Main implements Runnable {
	
    /**
     * the main Thread we use for the game.
     */
    private Thread thread;

    public static void main(String argv[]) {
            Main main = new Main();
    }
    
    public Main() {
    	Menu gameMenu = new Menu();
        while (gameMenu.isRunning()) {
        	gameMenu.runMenu();
        }
        // create and start the main thread of our game.
        thread = new Thread(this);
        thread.start();
        gameMenu.check();
    }
    
    public void restart() {

        // create and start the main thread of our game.
        thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        Game game = new Game();
        game.start();
        
        while (game.getRunning()) {
        	game.runGame();
        	
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                // Catch if needed
            }
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
