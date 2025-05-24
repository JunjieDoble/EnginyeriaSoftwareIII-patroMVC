package interfaces;

public interface TemperatureControllerInterface {

    public void start();
    public void stop();

    public void increaseTemperature();
    public void decreaseTemperature();
    public void setTemperature(int temp);

}
