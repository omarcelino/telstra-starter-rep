package au.com.telstra.simcardactivator.dto;

public class SimCardResponse {
    private String iccid;
    private String customerEmail;
    private boolean active;

    public SimCardResponse(String iccid, String customerEmail, boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    public String getIccid() {return iccid;}
    public String getCustomerEmail() {return customerEmail;}
    public boolean isActive() {return active;}

}
