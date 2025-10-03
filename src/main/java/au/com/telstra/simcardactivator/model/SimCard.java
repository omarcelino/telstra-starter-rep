package au.com.telstra.simcardactivator.model;

public class SimCard {

    private String iccid;
    private String customerEmail;

    public SimCard(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

    public String getIccid() {
        return iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
