package com.idealunited.inf.exception;

public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = -2184047757819573417L;
	protected Object[] arguments;

    public AbstractException() {
        super();
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an AbstractException with exception ID and error level
     * 
     * @param message
     *            The exception ID, then retrieve detailed error message based
     *            on it
     * @param errorLevel
     *            The error level used for monitoring
     * @param arguments
     *            The arguments replaced the agruments of error message             
     */
    public AbstractException(String message, Object... arguments) {
        super(message);
        this.arguments=arguments;
    }

    // /**
    // * Set error level
    // * @param errorLevel The errorLevel to set for this AbstractException
    // */
    // public void setErrorLevel(ErrorLevel errorLevel) {
    // this.errorLevel = errorLevel;
    // }

    /**
     * @return Returns the arguments.
     */
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * @param arguments
     *            The arguments to set.
     */
    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}