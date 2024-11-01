public class LostItem {
    private String name;
    private String description;
    private String lostDate;
    private String lostPlace;
    private String whetherHavePersonalID;
    private String identifiableInfo;

    public LostItem(String name, String description, String lostDate, String lostPlace, String whetherHavePersonalID, String identifiableInfo) {
        this.name = name;
        this.description = description;
        this.lostDate = lostDate;
        this.lostPlace = lostPlace;
        this.whetherHavePersonalID = whetherHavePersonalID;
        this.identifiableInfo = identifiableInfo;
    }

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

    public String getIdentifiableInfo() {
        return identifiableInfo;
    }
}
