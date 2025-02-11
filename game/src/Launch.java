import game.ui.GameJFrame;
import game.ui.LoginJFrame;
import game.ui.RegisterJFrame;

public class Launch {
    public static void main(String[] args) {
        new GameJFrame();
        System.out.println(GameJFrame.path[0]);
        // new RegisterJFrame();
        // new LoginJFrame();
    }
}
