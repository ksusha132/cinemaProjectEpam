package epam.cinemaProject.pojo.counter;

public class DiscountCounter {
    private Boolean loggedUser;
    private String type;
    private Integer count;

    public Boolean getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Boolean loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
