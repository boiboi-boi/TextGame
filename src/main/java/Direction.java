public enum Direction {
    UP ("ВВЕРХ"),
    DOWN ("ВНИЗ"),
    WEST ("ЗАПАД"),
    EAST ("ВОСТОК"),
    NORTH ("СЕВЕР"),
    SOUTH ("ЮГ");

    private String title;

    Direction(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return title;
    }
}
