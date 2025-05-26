package mvc;

import interfaces.Observer;
import interfaces.Subject;
import interfaces.TemperatureControllerInterface;
import interfaces.TemperatureModelInterface;

import java.awt.event.ActionEvent;

public class ReadOnlyTemperatureController implements TemperatureControllerInterface {
    /*TODO: Ensure that it implements the right interface*/ //done

    private TemperatureView view;
    private TemperatureModelInterface model;

    public ReadOnlyTemperatureController(TemperatureModelInterface tempModel){
       /*TODO: Create view and assign class attributes*/ //done
        this.model = tempModel;
        this.view = new TemperatureView(this, model);
        view.createView();
        view.createControls();
        /*TODO: Prepare UI */
    }

    @Override
    public void start(){
        //do something
        model.on();
    }

    @Override
    public void stop(){
        //do something
        model.off();
    }

    /*TODO: Complete with the interface methods. Some tips below.*/

    /*TODO: Start method is invoked when the user clicks File->Start
     * It should invoke model.on
     * It should also prepare the UI, enabling and disabling the right elements
     *
     *  */


    /*TODO: Start method is invoked when the user clicks File->Stop
     * It should invoke model.off
     * It should also prepare the UI, enabling and disabling the right elements
     *
     *  */

    @Override
    public void increaseTemperature() {

    }

    @Override
    public void decreaseTemperature() {

    }

    @Override
    public void setTemperature(int temp) {

    }
}
