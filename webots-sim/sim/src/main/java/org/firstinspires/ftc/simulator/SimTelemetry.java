package org.firstinspires.ftc.simulator;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

public class SimTelemetry implements Telemetry {
    private final List<String> lines = new ArrayList<>();
    private boolean autoClear = true;
    private String itemSeparator = " | ";
    private String captionValueSeparator = " : ";
    private final SimLog log = new SimLog();

    @Override
    public Item addData(String caption, String format, Object... args) {
        String value = String.format(format, args);
        lines.add(caption + captionValueSeparator + value);
        return new SimItem(caption, value);
    }

    @Override
    public Item addData(String caption, Object value) {
        lines.add(caption + captionValueSeparator + value);
        return new SimItem(caption, String.valueOf(value));
    }

    @Override
    public <T> Item addData(String caption, Func<T> valueProducer) {
        return addData(caption, valueProducer.value());
    }

    @Override
    public <T> Item addData(String caption, String format, Func<T> valueProducer) {
        return addData(caption, format, valueProducer.value());
    }

    @Override
    public boolean removeItem(Item item) { return false; }

    @Override
    public void clear() { lines.clear(); }

    @Override
    public void clearAll() { lines.clear(); }

    @Override
    public String addAction(Runnable action) { return ""; }

    @Override
    public boolean removeAction(String token) { return false; }

    @Override
    public boolean update() {
        StringBuilder sb = new StringBuilder("[Telemetry] ");
        for (int i = 0; i < lines.size(); i++) {
            if (i > 0) sb.append(itemSeparator);
            sb.append(lines.get(i));
        }
        System.out.println(sb.toString());
        if (autoClear) {
            lines.clear();
        }
        return true;
    }

    @Override
    public Line addLine(String lineCaption) {
        lines.add(lineCaption);
        return new SimLine();
    }

    @Override
    public boolean isAutoClear() { return autoClear; }

    @Override
    public void setAutoClear(boolean autoClear) { this.autoClear = autoClear; }

    @Override
    public int getMsTransmissionInterval() { return 250; }

    @Override
    public void setMsTransmissionInterval(int ms) {}

    @Override
    public String getItemSeparator() { return itemSeparator; }

    @Override
    public void setItemSeparator(String s) { this.itemSeparator = s; }

    @Override
    public void setCaptionValueSeparator(String s) { this.captionValueSeparator = s; }

    @Override
    public String getCaptionValueSeparator() { return captionValueSeparator; }

    @Override
    public Log log() { return log; }

    // --- Inner classes ---

    private class SimItem implements Item {
        private String caption;
        private String value;

        SimItem(String caption, String value) {
            this.caption = caption;
            this.value = value;
        }

        @Override public String getCaption() { return caption; }
        @Override public Item setCaption(String caption) { this.caption = caption; return this; }
        @Override public Item setValue(String format, Object... args) { this.value = String.format(format, args); return this; }
        @Override public Item setValue(Object value) { this.value = String.valueOf(value); return this; }
        @Override public <T> Item setValue(Func<T> vp) { this.value = String.valueOf(vp.value()); return this; }
        @Override public <T> Item setValue(String format, Func<T> vp) { this.value = String.format(format, vp.value()); return this; }
        @Override public Item setRetained(Boolean retained) { return this; }
        @Override public boolean isRetained() { return false; }
        @Override public Item addData(String caption, String format, Object... args) { return SimTelemetry.this.addData(caption, format, args); }
        @Override public Item addData(String caption, Object value) { return SimTelemetry.this.addData(caption, value); }
    }

    private class SimLine implements Line {
        @Override public Item addData(String caption, String format, Object... args) { return SimTelemetry.this.addData(caption, format, args); }
        @Override public Item addData(String caption, Object value) { return SimTelemetry.this.addData(caption, value); }
    }

    private static class SimLog implements Log {
        private int capacity = 9;
        private DisplayOrder displayOrder = DisplayOrder.OLDEST_FIRST;

        @Override public int getCapacity() { return capacity; }
        @Override public void setCapacity(int capacity) { this.capacity = capacity; }
        @Override public DisplayOrder getDisplayOrder() { return displayOrder; }
        @Override public void setDisplayOrder(DisplayOrder displayOrder) { this.displayOrder = displayOrder; }
        @Override public void add(String entry) { System.out.println("[Telemetry Log] " + entry); }
        @Override public void add(String format, Object... args) { System.out.println("[Telemetry Log] " + String.format(format, args)); }
        @Override public void clear() {}
    }
}
