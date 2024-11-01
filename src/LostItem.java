public class LostItem {
    private String name;
    private String description;
    private String lostDate;
    private String lostPlace;
    private String whetherHavePersonalID;
    private String identifiableInfo;
    private String randomCode;

    public LostItem(String name, String description, String lostDate, String lostPlace, String whetherHavePersonalID, String identifiableInfo, String randomCode) {
        this.name = name;
        this.description = description;
        this.lostDate = lostDate;
        this.lostPlace = lostPlace;
        this.whetherHavePersonalID = whetherHavePersonalID;
        this.identifiableInfo = identifiableInfo;
        this.randomCode = randomCode;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLostDate() {
        return lostDate;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public String getWhetherHavePersonalID() {
        return whetherHavePersonalID;
    }

    public String getIdentifiableInfo() {
        return identifiableInfo;
    }

    public String getRandomCode(){
        return randomCode;
    }

    @Override
    public String toString() {
        return "LostItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lostDate='" + lostDate + '\'' +
                ", lostPlace='" + lostPlace + '\'' +
                ", whetherHavePersonalID='" + whetherHavePersonalID + '\'' +
                ", identifiableInfo='" + identifiableInfo + '\'' +
                '}';
    }
}