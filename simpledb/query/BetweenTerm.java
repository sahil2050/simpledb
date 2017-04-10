package simpledb.query;

import simpledb.record.Schema;

/**
 * A Between term is a check if a timestamp field is b/w two given timestamps.
 * @author Sahil Aggarwal
 *
 */

public class BetweenTerm {
	private FieldNameExpression fldname;
	private TimestampConstant ll,ul;
	
	public BetweenTerm(FieldNameExpression fldname, TimestampConstant ll, TimestampConstant ul){
		if(ul.lessthan(ll))throw new InvalidIntervalException();
		this.fldname = fldname;
		this.ll = ll;
		this.ul = ul;
	}
	
	/**
    * @param s the scan
    * @return true if fld expression have the value b/w ll and ul in the scan
    */
	public boolean isSatisfied(Scan s) {
      TimestampConstant fldval = (TimestampConstant) fldname.evaluate(s);
      return (ll.lessthan(fldval) && fldval.lessthan(ul));
   }
	public Constant equatesWithConstant(String fldname) {
		if(this.fldname.asFieldName() == fldname)
			return ll;
		else return null;
	}
	

    public String toString(){
    	return fldname.toString() + " between " + ll.toString() + " " + ul.toString();
    }
    
    public boolean appliesTo(Schema sch) {
        return fldname.appliesTo(sch);
    }
    
}