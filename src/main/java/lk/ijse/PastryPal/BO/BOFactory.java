package lk.ijse.PastryPal.BO;

import lk.ijse.PastryPal.BO.Custom.impl.*;
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
                return new EmployeeBOImpl();
            case ITEMS:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case PRODUCTS:
                return new ProductsBOImpl();
            case SUPPLIERS:
                return new SuppliersBOImpl();
            default:
                return null;
        }
    }
}
