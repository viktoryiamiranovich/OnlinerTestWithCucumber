package onliner.pageobject;

public enum Filters {
    BRAND("Производитель"),
    MAXPRICE("до"),
    RESOLUTION("Разрешение"),
    MINDIAGONAL("facet.value.from"),
    MAXDIAGONAL("facet.value.to");

    private String value;

    Filters(final String setValue){
        value = setValue;
    }

    public String toString(){
        return value;
    }
}
