package com.idealunited.inf.exception;

public class AppException extends AbstractException{


	private static final long serialVersionUID = -6284445529172952686L;

	public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an AppException with exception ID and error level
     * 
     * @param message
     *            The exception ID, then retrieve detailed error message based
     *            on it
     * @param errorLevel
     *            The error level used for monitoring
     * @param arguments
     *            The arguments replaced the agruments of error message             
     */
    public AppException(String message,Object... arguments) {
        super(message);
        this.arguments=arguments;
    }

}
