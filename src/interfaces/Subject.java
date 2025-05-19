package interfaces;

public interface Subject {

    void registerObs(Observer o);
    void removeObs(Observer o);
    void notifyObs(String content);

}
