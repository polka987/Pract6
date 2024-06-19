package models;

import interfaces.Captain;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class CaptainModel extends PrisonPerson implements Captain {
    private static final Logger captainLog = Logger.getLogger(CaptainModel.class.getName());

    static Scanner sc = new Scanner(System.in);

    public CaptainModel() throws IOException {
        FileHandler fileHandler = new FileHandler("CaptainLog.log", true);
        captainLog.addHandler(fileHandler);
        captainLog.setLevel(Level.ALL);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    @Override
    public void CreateNewOrder(ArrayList<ArrayList<Object>> captainOrders) throws IOException {
        ArrayList<Object> newOrder = new ArrayList<>();
        System.out.println("Select the order type\n(\n\t1 - termination\n\t2 - read sermons\n\t3 - clean\n)\n");
        Integer orderType = 0;
        try{
            orderType = sc.nextInt();
        }
        catch (Exception e){
            captainLog.severe("Неверный ввод. Ожидалось число");
        }
        while(orderType>3 || orderType<1){
            captainLog.warning("Неверный ввод, введите число от 1 до 3");
            System.out.println("Reenter the operation number");
            try{
                orderType = sc.nextInt();
            }
            catch (Exception e){
                captainLog.severe("Неверный ввод. Ожидалось число");
                return;
            }
        }
        newOrder.add(orderType);
        System.out.println("Enter the guilty heretics IDs");
        ArrayList<Integer> heretics = new ArrayList<>();
        do {
            try{
                heretics.add(sc.nextInt());
                captainLog.info("Еретик добавлен в лист");
            }
            catch (Exception e){
                captainLog.severe("Неверный ввод. Ожидалось число");
                return;
            }
            System.out.println("Stop adding heretics (y/n)");
        } while (!sc.nextLine().equalsIgnoreCase("y"));
        newOrder.add(heretics.stream().distinct().collect(Collectors.toList()));
        System.out.println("Enter order text");
        newOrder.add(sc.nextLine());
        sc.nextLine();
        captainOrders.add(newOrder);
        captainLog.info("Создан новый приказ");
    }

    @Override
    public void DeleteOrder(Integer orderID, ArrayList<ArrayList<Object>> captainOrders) {
        AtomicReference<Integer> orderToDeleteIndex = new AtomicReference<>(null);
        captainOrders.forEach(o -> {
            if(orderID.equals((int)o.getFirst()))
                orderToDeleteIndex.set(captainOrders.indexOf(o));
        });
        if(orderToDeleteIndex.get() == null) {
            captainLog.warning("Приказ с таким ID не существует");
        }
        else {
            captainOrders.remove(orderToDeleteIndex.get());
            captainLog.info("Приказ удалён");
        }
    }

    @Override
    public void RaiseEmployee(ArrayList<ArrayList<Object>> employees) {
        AtomicInteger employeeIndex = new AtomicInteger(-1);
        AtomicInteger accessLevel = new AtomicInteger(-1);
        employees.forEach(e -> {
            if(((int)e.getLast()) < 3)
                System.out.printf("%d, Access level - %d", (int)e.getFirst(), (int)e.getLast());
        });
        System.out.println("Input employee ID");
        Integer employeeID = 0;
        try{
            employeeID = sc.nextInt();
        }
        catch (Exception e){
            captainLog.severe("Неверный ввод. Ожидалось число");
            return;
        }
        Integer finalEmployeeID = employeeID;
        employees.forEach(e -> {
            if(finalEmployeeID.equals(e.getFirst()) && (int)e.getLast()<3){
                employeeIndex.set(employees.indexOf(e));
                accessLevel.set((int)e.getLast());
            }
        });
        if(employeeIndex.get() == -1 || accessLevel .get() == -1){
            captainLog.warning("Неверный ID");
            return;
        }
        employees.get(employeeIndex.get()).set(1,accessLevel.get()+1);
        captainLog.info("Уровень успеха повышен");
    }

    @Override
    public void LowerEmployee(ArrayList<ArrayList<Object>> employees) {
        AtomicInteger employeeIndex = new AtomicInteger(-1);
        AtomicInteger accessLevel = new AtomicInteger(-1);
        employees.forEach(e -> {
            if(((int)e.getLast()) > 1)
                System.out.printf("%d, Access level - %d", (int)e.getFirst(), (int)e.getLast());
        });
        System.out.println("Input employee ID");
        Integer employeeID = 0;
        try{
            employeeID = sc.nextInt();

        }
        catch (Exception e){
            captainLog.severe("Неверный ввод. Ожидалось число");
            return;
        }
        Integer finalEmployeeID = employeeID;
        employees.forEach(e -> {
            if(finalEmployeeID.equals(e.getFirst()) && (int)e.getLast()>1){
                employeeIndex.set(employees.indexOf(e));
                accessLevel.set((int)e.getLast());
            }
        });
        if(employeeIndex.get() == -1 || accessLevel .get() == -1){
            captainLog.warning("Подчинённого с таким ID не существует");
            return;
        }
        employees.get(employeeIndex.get()).set(1,accessLevel.get()+1);
        captainLog.info("Уровень успеха понижен");

    }
}
