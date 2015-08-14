package eei.cornerseees.shared.model.beans;

/**
 * Created by username on 8/11/15.
 */
public interface LizardBean extends SerializableBean {
    String getColor();

    void setColor(String color);

    String getName();

    void setName(String name);

    long getLength();

    void setLength(long length);
}
