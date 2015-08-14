package eei.cornerseees.shared.model;

import eei.cornerseees.shared.model.beans.LizardBean;

/**
 * Created by username on 8/10/15.
 */
public class Lizard implements LizardBean {
    public String color;
    public String name;
    public long length;

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getLength() {
        return length;
    }

    @Override
    public void setLength(long length) {
        this.length = length;
    }
}
