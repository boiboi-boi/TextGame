public enum Moveable {

    STATIONARY(""),
    MOBILE ("");

    private String title;

    Moveable(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return title;
    }
}
