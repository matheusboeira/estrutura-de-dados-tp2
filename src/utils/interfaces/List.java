package utils.interfaces;

public interface List<T> {
    public void addStart(T element);
    public void addEnd(T element);
    public boolean isEmpty();
    public boolean remove(T element);
    public int size();
}
