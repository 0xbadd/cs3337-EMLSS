import mainController.MainController;

public class Main {
    public static void main(String[] args) {
        MainController mc = new MainController();
        mc.startAcceptingEmergencyCalls();
        System.out.println("Finished taking calls and doing assignments");
    }
}
