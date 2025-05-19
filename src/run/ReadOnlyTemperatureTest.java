package run;

import mvc.ReadOnlyTemperatureController;
import mvc.ReadOnlyTemperatureModel;
import interfaces.TemperatureControllerInterface;
import interfaces.TemperatureModelInterface;

public class ReadOnlyTemperatureTest {

    public static void main (String[] args) {
        TemperatureModelInterface model = new ReadOnlyTemperatureModel();
        TemperatureControllerInterface controller = new ReadOnlyTemperatureController(model);
    }
}
