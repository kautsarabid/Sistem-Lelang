package pattern.observer;

public interface Subject {
    void tambahObserver(Observer observer);
    void hapusObserver(Observer observer);
    void notifyObservers(String message);
}
