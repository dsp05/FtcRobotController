package org.firstinspires.ftc.robotcore.external;

public interface Telemetry {

    Item addData(String caption, String format, Object... args);
    Item addData(String caption, Object value);
    <T> Item addData(String caption, Func<T> valueProducer);
    <T> Item addData(String caption, String format, Func<T> valueProducer);

    boolean removeItem(Item item);
    void clear();
    void clearAll();

    String addAction(Runnable action);
    boolean removeAction(String token);

    boolean update();

    Line addLine(String lineCaption);

    boolean isAutoClear();
    void setAutoClear(boolean autoClear);

    int getMsTransmissionInterval();
    void setMsTransmissionInterval(int msTransmissionInterval);

    String getItemSeparator();
    void setItemSeparator(String itemSeparator);

    void setCaptionValueSeparator(String separator);
    String getCaptionValueSeparator();

    Log log();

    interface Item {
        String getCaption();
        Item setCaption(String caption);
        Item setValue(String format, Object... args);
        Item setValue(Object value);
        <T> Item setValue(Func<T> valueProducer);
        <T> Item setValue(String format, Func<T> valueProducer);
        Item setRetained(Boolean retained);
        boolean isRetained();
        Item addData(String caption, String format, Object... args);
        Item addData(String caption, Object value);
    }

    interface Line {
        Item addData(String caption, String format, Object... args);
        Item addData(String caption, Object value);
    }

    interface Log {
        int getCapacity();
        void setCapacity(int capacity);
        DisplayOrder getDisplayOrder();
        void setDisplayOrder(DisplayOrder displayOrder);
        void add(String entry);
        void add(String format, Object... args);
        void clear();

        enum DisplayOrder {
            OLDEST_FIRST, NEWEST_FIRST
        }
    }
}
