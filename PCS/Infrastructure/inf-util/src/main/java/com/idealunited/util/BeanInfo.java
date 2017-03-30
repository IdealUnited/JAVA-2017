package com.idealunited.util;

import java.lang.reflect.Method;


public class BeanInfo {
    private String property;
    private String getMethodName;
    private String setMethodName;
    private String parameterType;
    private String importType;
    private String description;
    private Method readMethod;
    private Method writeMethod;
    private Class propertyType;
    
    public BeanInfo() {
        super();
    }

    /**
     * @return Returns the getMethodName.
     */
    public String getGetMethodName() {
        return getMethodName;
    }

    /**
     * @param getMethodName The getMethodName to set.
     */
    public void setGetMethodName(String getMethodName) {
        this.getMethodName = getMethodName;
    }

    /**
     * @return Returns the parameterType.
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * @param parameterType The parameterType to set.
     */
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * @return Returns the property.
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property The property to set.
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return Returns the setMethodName.
     */
    public String getSetMethodName() {
        return setMethodName;
    }

    /**
     * @param setMethodName The setMethodName to set.
     */
    public void setSetMethodName(String setMethodName) {
        this.setMethodName = setMethodName;
    }

    /**
     * @return Returns the importType.
     */
    public String getImportType() {
        return importType;
    }

    /**
     * @param importType The importType to set.
     */
    public void setImportType(String importType) {
        this.importType = importType;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the readMethod.
     */
    public Method getReadMethod() {
        return readMethod;
    }

    /**
     * @param readMethod The readMethod to set.
     */
    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    /**
     * @return Returns the writeMethod.
     */
    public Method getWriteMethod() {
        return writeMethod;
    }

    /**
     * @param writeMethod The writeMethod to set.
     */
    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }

    /**
     * @return Returns the propertyType.
     */
    public Class getPropertyType() {
        return propertyType;
    }

    /**
     * @param propertyType The propertyType to set.
     */
    public void setPropertyType(Class propertyType) {
        this.propertyType = propertyType;
    }
}
