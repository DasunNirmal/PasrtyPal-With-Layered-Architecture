package lk.ijse.PastryPal.DAO;

import lk.ijse.PastryPal.DAO.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        COMPLAIN,CUSTOMER,EMPLOYEE,ITEMS,QUERY,ORDER,ORDER_DETAILS,PRODUCTS,SUPPLIERS
    }

    public SuperDAO getDAO (DAOTypes daoTypes) {
        switch (daoTypes) {
            case COMPLAIN:
                return new ComplainDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEMS:
                return new ItemDAOImpl();
            case QUERY:
                return new JoinQueryDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailDAOImpl();
            case PRODUCTS:
                return new ProductDAOImpl();
            case SUPPLIERS:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}
