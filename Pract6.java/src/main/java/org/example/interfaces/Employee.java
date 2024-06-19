package interfaces;

import java.util.ArrayList;

public interface Employee {

    public void checkHeretics(Integer hereticID, int requiredAmount, ArrayList<ArrayList<Object>> heretics);
    public void ListenToTheCaptain(ArrayList<ArrayList<Object>> captainOrders);
    public void ListenToTerminationOrders(ArrayList<ArrayList<Object>> captainOrders);
    public void ListenToEnlighteningOrders(ArrayList<ArrayList<Object>> captainOrders);

    public void ListenToCleaningOrders(ArrayList<ArrayList<Object>> captainOrders);
    public void CheckPossibilities(Integer employeeId, ArrayList<ArrayList<Object>> employees);
    public void ExecuteOrder(
            Integer employeeID,
            Integer orderID,
            ArrayList<ArrayList<Object>> employees,
            ArrayList<ArrayList<Object>> captainOrders,
            ArrayList<ArrayList<Object>> heretics
    );

}
