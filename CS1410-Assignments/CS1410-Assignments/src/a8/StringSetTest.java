package a8;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StringSetTest {
    
    @Test(timeout = 1000)
    public void saStringSet_toStringEmptyArray(){
        StringSet ss = new StringSet();
        assertEquals("{}", ss.toString());
    }
    
    @Test(timeout = 1000)
    public void sbStringSet_toStringCommonCase(){
        StringSet ss = new StringSet();
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert("TA");
        String[] elems = {"hello", "goodbye", "why", "TA"};
        for (int i = 0; i < elems.length; i++)
            assertTrue(ss.contains(elems[i]));
    }
    
    @Test(timeout = 1000)
    public void scStringSet_SizeEmptySet(){
        StringSet ss = new StringSet();
        assertEquals(0, ss.size());
    }
    
    @Test(timeout = 1000)
    public void sdStringSet_SizeCommonCase(){
        StringSet ss = new StringSet();
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert("TA");
        assertEquals(4, ss.size());
    }
    
    @Test(timeout = 1000)
    public void seStringSet_ContainsFalse(){
        StringSet ss = new StringSet();
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert("TA");
        assertFalse(ss.contains("ashlee"));
    }
    
    @Test(timeout = 1000)
    public void sfStringSet_ContainsTrue(){
        StringSet ss = new StringSet();
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert("TA");
        assertTrue("Contains failed, check for == usage",ss.contains("TA"));
    }

    @Test(timeout = 1000)
    public void sgStringSet_DoubleAddSameObject(){
        StringSet ss = new StringSet();
        String TA = "TA";
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert(TA);
        ss.insert(TA);
        assertEquals("Double add failed on same object - something more than == problem",4,ss.size());
        
    }

    @Test(timeout = 1000)
    public void shStringSet_DoubleAddSameValue(){
        StringSet ss = new StringSet();
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert("TA");
        ss.insert("TA");
        assertEquals("Double add failed, check for == instead of equals",4,ss.size());
        
    }
        
    @Test(timeout = 1000)
    public void skStringSet_RemoveAValidValue(){
        StringSet ss = new StringSet();
        
        for(int i = 0; i < 15; i++){
            ss.insert("A" + 2);
            assertEquals(1, ss.size());
        }
        
        ss.remove("A2");
        
        assertEquals(0, ss.size());
        
        assertEquals("{}", ss.toString());
    }
    
    @Test(timeout = 1000)
    public void slStringSet_RemoveAValueThatDoesNotExist(){
        StringSet ss = new StringSet();
        
        for(int i = 0; i < 15; i++){
            ss.insert("A" + i);
        }
        
        ss.remove("B0");
        
        assertEquals(15, ss.size());

        String[] elems = {"A0","A1","A2","A3","A4","A5","A6","A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14"};
        for (int i = 0; i < elems.length; i++)
            assertTrue(ss.contains(elems[i]));
    }
    
    @Test(timeout = 1000)
    public void smStringSet_UnionWithAllDifferentValues(){
        StringSet aValues = new StringSet();
        
        for(int i = 0; i < 7; i++){
            aValues.insert("A" + i);
        }
        
        StringSet bValues = new StringSet();
        
        for(int i = 0; i < 7; i++){
            bValues.insert("B" + i);
        }
        
        StringSet _union = aValues.union(bValues);
        
        assertEquals(14, _union.size());
        String[] elems = {"A0","A1","A2","A3","A4","A5","A6","B0","B1","B2","B3","B4","B5","B6"};
        for (int i = 0; i < elems.length; i++)
            assertTrue(_union.contains(elems[i]));
        
//      assertEquals("{A0, A1, A2, A3, A4, A5, A6, B0, B1, B2, B3, B4, B5, B6}", _union.toString());
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void sqStringSet_UnionWithNullInput(){
        StringSet first = new StringSet();
        
        StringSet _union = first.union(null);
    }
        
    @Test(expected=IllegalArgumentException.class)
    public void suStringSet_InsertWithNullInput(){
        StringSet ss = new StringSet();
        
        for(int i = 0; i < 15; i++){
            ss.insert("A" + 2);
            assertEquals(1, ss.size());
        }
        
        ss.insert(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void svStringSet_RemoveWithNullInput(){
        StringSet ss = new StringSet();
        
        for(int i = 0; i < 15; i++){
            ss.insert("A" + 2);
            assertEquals(1, ss.size());
        }
        
        ss.remove(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void swStringSet_ContainsErrorBecauseOfNull(){
        StringSet ss = new StringSet();
        ss.insert("hello");
        ss.insert("goodbye");
        ss.insert("why");
        ss.insert("TA");
        assertFalse(ss.contains(null));
    }


}
