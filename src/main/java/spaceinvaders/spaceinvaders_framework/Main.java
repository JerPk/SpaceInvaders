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
        gameMenu.check();
        // create and start the main thread of our game.
        thread = new Thread(this);
        thread.start();
    }
    
    public void restart() {

        // create and start the main thread of our game.
        thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
    	System.out.println("running");
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
}
