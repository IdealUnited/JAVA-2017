package com.idealunited.inf.exception;

public class AppUnTxException extends AbstractException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4306833053683004022L;

	public AppUnTxException() {
        super();
    }

    public AppUnTxException(String message) {
        super(message);
    }

    public AppUnTxException(Throwable cause) {
        super(cause);
    }

    public AppUnTxException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an AppUnTxException with exception ID and error level
     * 
     * @param message
     *            The exception ID, then retrieve detailed error message based
     *            on it
     * @param errorLevel
     *            The error level used for monitoring
     * @param arguments
     *            The arguments replaced the agruments of error message             
     */
    public AppUnTxException(String message,Object... arguments) {
        super(message);
        this.arguments=arguments;
    }

}
