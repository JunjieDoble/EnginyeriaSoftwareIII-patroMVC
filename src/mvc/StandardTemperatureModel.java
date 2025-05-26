package mvc;

import interfaces.Observer;
import interfaces.TemperatureModelInterface;
import interfaces.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StandardTemperatureModel implements Runnable, TemperatureModelInterface, Subject{
    /*TODO: Ensure that  StandardTemperatureModel implements the corresponding interface (other than Runnable)*/

    private List<Observer> observers;
    private int targetTemperature;
    private int currentTemperature;
    private Thread thread;
    private boolean stopThread;


    public StandardTemperatureModel(){
        Random rand = new Random();
        currentTemperature = rand.nextInt(0,31);
        targetTemperature = currentTemperature;
        observers = new ArrayList<>();
        stopThread = false;

    }

    @Override
    public void on() {
        thread = new Thread(this);
        thread.start();
        //TODO: may require additional code
    }

    @Override
    public void off() {
        stopThread = true;
        thread.interrupt();
        //TODO: May require additional code
    }

    /*TODO: Complete with appropriate methods and ensure that they work as expected. You may also need to add code to
    *  on and off methods above*/

    @Override
    public void run() {
        while(!stopThread) {
            try {
                Thread.sleep(3000);
                //TODO: Add code here to ensure that current temperature moves towards target temperature (in +1 or -1 increments).
                //TODO: View should be updated
                if (currentTemperature < targetTemperature) {
                    currentTemperature += 1;
                } else if (currentTemperature > targetTemperature) {
                    currentTemperature -= 1;
                }
                notifyObs(String.valueOf(currentTemperature), String.valueOf(targetTemperature));
            } catch (Exception e) {

            }
        }
        stopThread = false; //We reset stopThread
    }
    @Override
    public void registerObs(Observer o){
        observers.add(o);
    }
    @Override
    public void removeObs(Observer o){
        observers.remove(o);
    }
    @Override
    public void notifyObs(String current, String target){
        for(Observer o:observers){
            o.update(current, target);
        }
    }

    @Override
    public void setTargetTemperature(int temp) {
        targetTemperature = temp;

        //notificar a la view?
        notifyObs(String.valueOf(currentTemperature),String.valueOf(targetTemperature));
    }

    @Override
    public int getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }
}
