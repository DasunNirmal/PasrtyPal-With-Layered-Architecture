package lk.ijse.PastryPal.BO;

import lk.ijse.PastryPal.BO.Custom.impl.ComplainBOImpl;
import lk.ijse.PastryPal.BO.Custom.impl.CustomerBOImpl;
import lk.ijse.PastryPal.DAO.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory () {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        COMPLAIN,CUSTOMER,EMPLOYEE,ITEMS,ORDER,PRODUCTS,SUPPLIERS
    }
    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case COMPLAIN:
                return new ComplainBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEMS:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case PRODUCTS:
                return new ProductDAOImpl();
            case SUPPLIERS:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}
