package spaceinvaders.spaceinvaders_framework;

public class Main {

    public static void main(String argv[]) {
    	//while (true) {
            Menu gameMenu = new Menu();
            
            while (gameMenu.isRunning()) {
            	gameMenu.runMenu();
            }
            Game game = new Game();
            game.start();
            //if (!game.getRunning()) {
            	
            //}
    	//}

    }
    
	
}
