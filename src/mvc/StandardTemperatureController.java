package mvc;

import interfaces.Observer;
import interfaces.Subject;
import interfaces.TemperatureControllerInterface;
import interfaces.TemperatureModelInterface;

public class StandardTemperatureController implements TemperatureControllerInterface{
    /*TODO: Ensure that it implements the right interface*/

    TemperatureModelInterface model;
    TemperatureView view;

    public StandardTemperatureController(TemperatureModelInterface model){
        /*TODO: Create view and assign class attributes*/
        this.model = model;
        this.view = new TemperatureView(this, model);
        view.createView();
        view.createControls();

        if (model instanceof Subject) {
            ((Subject) model).registerObs(view);
        }

        /*TODO: Prepare UI */
        view.disableAll();
    }

    @Override
    public void start(){
        //todo
        model.on();
        view.enableControlMode();
    }

    @Override
    public void stop(){
        //todo
        model.off();
        view.disableAll();
    }

    /*TODO: Complete with the interface methods. Some tips below.*/

    /*TODO: Start method is invoked when the user clicks File->Start
    * It should invoke model.on
    * It should also prepare the UI, enabling and disabling the right elements
    *
    *  */


    /*TODO: Stop method is invoked when the user clicks File->Stop
     * It should invoke model.off
     * It should also prepare the UI, enabling and disabling the right elements
     *
     *  */

    @Override
    public void increaseTemperature() {
        int currentTemp = model.getTargetTemperature();
        model.setTargetTemperature(currentTemp+1);
    }

    @Override
    public void decreaseTemperature() {
        int currentTemp = model.getTargetTemperature();
        model.setTargetTemperature(currentTemp-1);
    }

    @Override
    public void setTemperature(int temp) {
        model.setTargetTemperature(temp);
    }

}
