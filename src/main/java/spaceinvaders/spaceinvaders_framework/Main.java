package spaceinvaders.spaceinvaders_framework;

public class Main {

    public static void main(String argv[]) {
    	Menu gameMenu = new Menu();
        ScoreMenu s_menu = new ScoreMenu();
    	//while (true) {
            while (gameMenu.isRunning()) {
            	gameMenu.runMenu();
            }
            Game game = new Game();
            game.start();
            gameMenu.check();
            while (game.getRunning()) {
            	System.out.println("");
            }
            gameMenu.runMenu();
            s_menu.show();
    	//}

    }
    
	
}
