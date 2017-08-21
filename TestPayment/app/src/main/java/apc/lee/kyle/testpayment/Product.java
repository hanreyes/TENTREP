package apc.lee.kyle.testpayment;

/**
 * Created by gc on 8/20/2017.
 */

public class Product {

    String m_name;
    double m_value;

    Product(String name, double value){
        m_name = name;
        m_value = value;
    }

    public String getM_name() {
        return m_name;
    }

    public double getM_value() {
        return m_value;
    }
}
