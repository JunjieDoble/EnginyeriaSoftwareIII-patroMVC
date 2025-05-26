package mvc;

import interfaces.Observer;
import interfaces.TemperatureModelInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import interfaces.Subject;

public class ReadOnlyTemperatureModel implements Runnable, TemperatureModelInterface, Subject {
    /*TODO: Ensure that ReadOnlyTemperatureModel implements the corresponding interface (other than Runnable)*/

    private int currentTemperature;
    private List<Observer> observers;
    private Thread thread;
    private boolean stopThread;

    public ReadOnlyTemperatureModel(){
        Random rand = new Random();
        observers = new ArrayList<>();
        currentTemperature = rand.nextInt(-30,40);
    }


    @Override
    public void on() {
        thread = new Thread(this);
        thread.start();
        stopThread = false;
        //TODO: may require additional code
    }

    @Override
    public void off() {
        stopThread = true;
        thread.interrupt();
        //TODO: may require additional code
    }

    /*TODO: Complete with appropriate methods and ensure that they work as expected. You may also need to add code to
     *  on and off methods above*/

    @Override
    public void run() {
        while (!stopThread) {
            try {
                Thread.sleep(2000);
                Random rand = new Random();
                currentTemperature += rand.nextInt(-1, 2);
                System.out.println("Current temperature " + currentTemperature); //This is left for easier debugging
                notifyObs(String.valueOf(currentTemperature), null);
                //TODO: View should be updated
            } catch (Exception e) {

            }
        }
        stopThread = false;
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
        for(interfaces.Observer o:observers){
            o.update(current, target);
        }
    }

    @Override
    public void setTargetTemperature(int temp) {

    }

    @Override
    public int getTargetTemperature() {
        return 0;
    }

    @Override
    public int getCurrentTemperature() {
        return currentTemperature;
    }
}
