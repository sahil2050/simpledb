package simpledb.query;

/**
 * A runtime exception indicating that the 
 * no of insertions has gone beyond max value
 * @author Sahil Aggarwal
 */
@SuppressWarnings("serial")
public class MemoryException extends RuntimeException {
	public MemoryException(){}
}
