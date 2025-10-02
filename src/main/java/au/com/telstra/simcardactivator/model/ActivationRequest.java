package au.com.telstra.simcardactivator.model;

public class ActivationRequest {

    private String iccid;
    private String customerEmail;

    public ActivationRequest(String iccid, String customerEmail) {
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
