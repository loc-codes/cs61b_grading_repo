package randomizedtest;

public interface AList<Item> {
    public void addLast(Item x);

    public Item getLast();
    public Item get(int i);
    public int size();
    public Item removeLast();
}