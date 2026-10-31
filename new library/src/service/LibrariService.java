package service;

import exception.DuplicateItemException;
import exception.ItemAlreadyBorrowedException;
import exception.ItemNotFoundException;
import model.Item;
import model.ItemStatus;
import storage.Storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibrariService {
    private Storage<Item> storage = new Storage<>();
    private static final String FILE_NAME = "library_data.bin";

    public void addItem(Item item) throws DuplicateItemException {
        if (storage.findById(item.getId()) != null) {
            throw new DuplicateItemException("Предмет з ID " + item.getId() + " вже існує!");
        }
        storage.add(item);
    }

    public void borrowItem(String id) throws ItemNotFoundException, ItemAlreadyBorrowedException {
        Item item = storage.findById(id);

        if (item == null) {
            throw new ItemNotFoundException("Предмет с ID = " + id + " не найден");
        }

        if (item.getStatus() == ItemStatus.BORROWED) {
            throw new ItemAlreadyBorrowedException("Предмет " + id + " уже был взят");
        }
        item.setStatus(ItemStatus.BORROWED);
    }

    public void returnItem(String id) throws ItemNotFoundException{
        Item item = storage.findById(id);

        if(item == null){
            throw new ItemNotFoundException("предмет с id = " + id + " не найден");
        }

        if(item.getStatus() == ItemStatus.BORROWED){
            item.setStatus(ItemStatus.AVAILABLE);
        }else{
            System.out.println("предмет и так есть в наличии");
        }
    }

    public ArrayList<Item> listAll(){
        return storage.getAll();
    }

    public void setData() {
        try (FileOutputStream fos = new FileOutputStream("library_data.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {


            oos.writeObject(this.storage);
            System.out.println("Данные успешно сохранены в файл!");

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public void loadData() throws Exception {
        try (FileInputStream fis = new FileInputStream("library_data.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {


            this.storage = (Storage<Item>) ois.readObject();
            System.out.println("Данные успешно загружены из файла!");

        } catch (FileNotFoundException e) {
            System.out.println("Сохраненных данных не обнаружено, создана новая база.");
        }
    }
}
