package simpledb.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class that wraps Java ints as database constants.
 * @author Sahil Aggarwal
 */
public class TimestampConstant implements Constant {
   private Date val;
   
   /**
    * Create a constant by wrapping the specified int.
    * @param n the int value
    */
   public TimestampConstant(long n) {
	   val = new Date(n);
   }
   
   public TimestampConstant(String s) {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   formatter.setLenient(false);
	   try {
		   val = formatter.parse(s);
	   } catch (ParseException e) {
		   throw new InvalidDateFormatException();
	   }
   }
   
   /**
    * Unwraps the Integer and returns it.
    * @see simpledb.query.Constant#asJavaVal()
    */
   public Object asJavaVal() {
      return val;
   }
   
   public boolean equals(Object obj) {
	   if(obj instanceof StringConstant){
	    	  StringConstant sc = (StringConstant) obj;
	    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	  formatter.setLenient(false);
	    	  try {
				return sc != null && val.equals(formatter.parse(sc.asJavaVal()));
	    	  } catch (ParseException e) {
	    		throw new InvalidDateFormatException();
	    	  }
	   }
	   else{
	    	  TimestampConstant tsc = (TimestampConstant) obj;
	    	  return tsc != null && val.equals(tsc.val); 
	   }
   }
   
   public boolean lessthan(TimestampConstant tsc){
	   return this.val.before(tsc.val); 
   }
   
   public int compareTo(Constant c) {
	   if(c instanceof StringConstant){
	    	  StringConstant sc = (StringConstant) c;
	    	  return this.compareTo(new TimestampConstant(sc.toString()));
	   }
	   else{
	    	  TimestampConstant tsc = (TimestampConstant) c;
	    	  return val.compareTo(tsc.val); 
	   }
   }
   
   public int hashCode() {
      return val.hashCode();
   }
   
   public String toString() {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	   return formatter.format(val);
   }
}
