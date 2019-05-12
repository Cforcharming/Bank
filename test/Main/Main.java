package Main;

import Controllers.MainController;
import Controllers.PanelController;
import Pages.DepositPanel;
import org.junit.jupiter.api.Test;

class Main {

    private MainController mainController = new MainController();
    private PanelController panelController = mainController.getPanelController();

    @Test
    void testPanel() {
        panelController.push(new DepositPanel(mainController));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {}
    }
}
