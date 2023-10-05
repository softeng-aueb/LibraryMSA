package com.mgiandia.library.catalog.contacts;

import com.mgiandia.library.loans.contacts.ZipCode;
import com.mgiandia.library.util.BasicEqualTester;
import org.junit.jupiter.api.Test;

public class ZipCodeTest {

    @Test
    public void equalsAndHashCode(){
        BasicEqualTester<ZipCode> equalsTester = new BasicEqualTester<ZipCode> ();
        equalsTester.setObjectUnderTest(new ZipCode(null));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.bothObjectsHaveNoState(new ZipCode(null));
        
        equalsTester.setObjectUnderTest(new ZipCode("11111"));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new ZipCode(null));
        equalsTester.objectsHaveDifferentState(new ZipCode("222"));
        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(new ZipCode("11111"));                
    }
}
