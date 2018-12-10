package epam.cinemaProject.pojo.counter;

public class DiscountCounter {
    private Boolean loggedUser;
    private DiscountType type;
    private Integer count;

    public Boolean getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Boolean loggedUser) {
        this.loggedUser = loggedUser;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
