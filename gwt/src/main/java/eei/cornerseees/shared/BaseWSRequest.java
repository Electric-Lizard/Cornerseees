package eei.cornerseees.shared;

import eei.cornerseees.shared.model.beans.SerializableBean;

import java.lang.reflect.Type;


public class BaseWSRequest implements WSRequest {
    public String methodName;
    public SerializableBean[] methodParameters;

    public BaseWSRequest(String methodName, SerializableBean[] methodParameters) {
        this.methodName = methodName;
        this.methodParameters = methodParameters;
    }

    public BaseWSRequest(String methodName, SerializableBean methodParameter) {
        this.methodName = methodName;
        this.methodParameters = new SerializableBean[] {methodParameter};
    }

    public BaseWSRequest() {}

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public SerializableBean[] getMethodParameters() {
        return methodParameters;
    }

    @Override
    public void setMethodParameters(SerializableBean[] methodParameters) {
        this.methodParameters = methodParameters;
    }
}
