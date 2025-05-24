package interfaces;

public interface TemperatureModelInterface {

    public void on();
    public void off();

    public void setTargetTemperature(int temp);
    public int getTargetTemperature();
    public int getCurrentTemperature();
}
