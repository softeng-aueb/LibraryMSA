package com.mgiandia.library.catalog.contacts;


import com.mgiandia.library.loans.contacts.Address;
import com.mgiandia.library.loans.contacts.ZipCode;
import com.mgiandia.library.util.BasicEqualTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void testEqualsObject() {
        Address address1 = new Address();
        Address address2 = new Address();
        
        Assertions.assertFalse(address1.equals(null));
        Assertions.assertEquals(address1, address2);
        Assertions.assertEquals(address1.hashCode(), address2.hashCode());
        
        address1.setCity("Ellada");
        Assertions.assertFalse(address1.equals(address2));
        Assertions.assertFalse(address1.hashCode() == address2.hashCode());
        address2.setCity("Ellada");
        Assertions.assertEquals(address1, address2);
        Assertions.assertEquals(address1.hashCode(), address2.hashCode());
    }
    
    @Test
    public void testEqualsAndHashCode() {
        BasicEqualTester<Address> equalsTester = new BasicEqualTester<Address>();
        Address address = new Address();
        equalsTester.setObjectUnderTest(address);
        
        equalsTester.otherObjectIsNull();
        
        equalsTester.otherObjectIsOfDifferentType(new Object());
        
        Address address2 = new Address() ;
        equalsTester.bothObjectsHaveNoState(address2);
        
        address.setStreet("Patision");
        equalsTester.otherObjectsHasNoState(address2);
        
        equalsTester.sameReferences(address);
        
        address2.setStreet("Patision");
        equalsTester.bothObjectsHaveSameState(address2);
        
        address.setNumber("76");
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setNumber("87");
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setNumber("76");
        equalsTester.bothObjectsHaveSameState(address2);
        
        address.setCity("Athens");
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setCity("Lamia");
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setCity("Athens");
        equalsTester.bothObjectsHaveSameState(address2);
        
        address.setCountry("Greece");
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setCountry("Italy");
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setCountry("Greece");
        equalsTester.bothObjectsHaveSameState(address2);
        
        address.setZipCode(new ZipCode("111"));
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setZipCode(new ZipCode("222"));
        equalsTester.objectsHaveDifferentState(address2);
        
        address2.setZipCode(new ZipCode("111"));
        equalsTester.bothObjectsHaveSameState(address2);
        
        
    }

}
