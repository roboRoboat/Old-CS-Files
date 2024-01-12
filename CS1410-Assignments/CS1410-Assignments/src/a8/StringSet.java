package a8;

// A StringSet is a collection of non-null strings, with no duplicates
// (i.e., no two elements may be equal).  
public class StringSet {
    
	// To hold the set data, use a DynamicArray.
	private DynamicArray2 data; 
    
	// Creates an empty StringSet object 
	public StringSet() {
    	// FILL IN
    	 data = new DynamicArray2(); 
    }
    
    // Throws an IllegalArgumentException if e is null, otherwise adds
    // e to the set if there is not already an element in the set equal
    // to e
    public void insert(String e) {
    	if (e == null) {
    		throw new IllegalArgumentException(); 
    	}
    	if (!this.contains(e)) {
    		data.add(e);
    	}
    	// FILL IN
    }
    
    // Throws an IllegalArgumentException if e is null, otherwise
    // indicates whether the set contains e
    public boolean contains(String e) {
    	// FILL IN
    	if (e == null) {
    		throw new IllegalArgumentException(); 
    	}
    	for (int datIndex = 0; datIndex < data.size(); datIndex++) {
    		if (data.get(datIndex).equals(e)) {
    			return true; 
    		}
    	}
        return false;
    }
    
    // Throws an IllegalArgumentException if e is null, otherwise
    // removes e from the set
    public void remove(String e) {
    	if (e == null) {
    		throw new IllegalArgumentException(); 
    	}
    	
    	if (contains(e)) {
    		for (int i = 0; i < size(); i++) {
    			if (data.get(i).equals(e)) {
    				data.remove(i); 
    			}
    		}
    	}
    	// FILL IN
    }
    
    // Returns the number of strings in the set
    public int size() {
    	// FILL IN
    	
	// DO NOT return 0. The following statement is a temporary 
	// placeholder to prevent a compiler error.  Remove when you 
	// implement this method.
        return data.size();
    } 
    
    public String get(int i) {
		if (i < 0 || i > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		return data.get(i);
	}
    
    // Computes and returns the union of the StringSet that calls this method and the 
    // StringSet argument to the method.
    // The original StringSets should not be changed. The union set contains every 
    // element of each of the original StringSets.
    // Throws an IllegalArgumentException if other is null.
    public StringSet union(StringSet other) {
    	// FILL IN
    	if (other == null) {
    		throw new IllegalArgumentException(); 
    	}
		StringSet newSet = this; 
    	// modify the return to return the union.
    	for (int oIndex = 0; oIndex < other.size(); oIndex++) {
    		newSet.insert(other.get(oIndex));
    	}
    	
    	return newSet;
    }

    // Computes and returns the intersection of the StringSet that calls this method 
    // and the StringSet argument to the method.
    // The original StringSets should not be changed. The intersection set contains 
    // only the elements that are in both of the StringSets.
    // Throws an IllegalArgumentException if other is null.
    // Hint - in this StringSet class, even a StringSet parameter like other
    // has direct access to its own instance variables.
    public StringSet intersection(StringSet other) {
    	// FILL IN
    	if (other == null) {
    		throw new IllegalArgumentException(); 
    	}
		StringSet intersection = new StringSet();
		
		for (int tIndex = 0; tIndex < size(); tIndex++) {
			for (int oIndex = 0; oIndex < other.size(); oIndex++) {
				if (data.get(tIndex).equals(other.get(oIndex))){
					intersection.insert(data.get(tIndex));
				}
			}
		}
    	// modify the return to return the intersection.
    	return intersection;
    }

    // Returns a formatted string version of this set
    // Examples: If set contains "a" and "b", this method should 
    // return the string "{a, b}".  If the set is empty, this 
    // method should return the string "{}".
    public String toString() {
    	// FILL IN
    	String set = "{"; 
    	if(size() > 0) 
    		set += data.get(0);
		
		for(int i = 1; i < size(); i++) 
			set += ", " + data.get(i);
		
		return set + "}"; 
   	
	// DO NOT return null. The following statement is a temporary 
	// placeholder to prevent a compiler error.  Remove when you 
	// implement this method.
    }
}