package interfaces;

import java.io.IOException;
import java.util.ArrayList;

public interface Captain {

    public void CreateNewOrder(ArrayList<ArrayList<Object>> captainOrders) throws IOException;
    public void DeleteOrder(Integer orderID, ArrayList<ArrayList<Object>> captainOrders);
    public void RaiseEmployee(ArrayList<ArrayList<Object>> employees);
    public void LowerEmployee(ArrayList<ArrayList<Object>> employees);

}
