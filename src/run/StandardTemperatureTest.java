package run;

import mvc.StandardTemperatureController;
import mvc.StandardTemperatureModel;
import interfaces.TemperatureControllerInterface;
import interfaces.TemperatureModelInterface;

public class StandardTemperatureTest {

    public static void main (String[] args) {
        TemperatureModelInterface model = new StandardTemperatureModel();
        TemperatureControllerInterface controller = new StandardTemperatureController(model);
    }


}
