package storage;

import model.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class Storage<T extends Item> implements Serializable {
    public static final long serialVersionUID = 1L;
    private ArrayList<T> items = new ArrayList<>();

    public void add(T item){
        items.add(item);
    }

    public ArrayList<T> getAll(){
        return items;
    }

    public T findById(String id){
        for(T item: items){
            if(item.getId().equals(id)){
                return item;
            }

        }
        return null;
    }

    public boolean removeById(String id){
        T item = findById(id);

        if (item != null){
            items.remove(item);
            return true;
        }
        return false;
    }
}
