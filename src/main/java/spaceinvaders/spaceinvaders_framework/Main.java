package spaceinvaders.spaceinvaders_framework;

public class Main implements Runnable {
	
    /**
     * the main Thread we use for the game.
     */
    private Thread thread;

    public static void main(String argv[]) {
    	Menu gameMenu = new Menu();
    	//while (true) {
            while (gameMenu.isRunning()) {
            	gameMenu.runMenu();
            }
            Main main = new Main();
            gameMenu.check();

    	//}

    }
    
    public Main() {
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
        
        end();	
    }
    
    public void end() {
    	//Menu gameMenu = new Menu();
        ScoreMenu s_menu = new ScoreMenu();
        //gameMenu.runMenu();
        s_menu.show();
    }
}
