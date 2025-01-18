package com.sviryd.mikhail.assignment_system_technology;

import com.sviryd.assignment_system_technology.CipherCVCInMsgConverter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// 1.2 Задача 2
public class Task2Test extends TestCase {
    public static final String RAW = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n"
            + "<DeliverySMS> \n"
            + "    <PackNum>16692535</PackNum> \n"
            + "    <ProvID>2</ProvID> \n"
            + "    <SMS> \n"
            + "        <DBSMSId>146920910</DBSMSId> \n"
            + "        <CrmID>0</CrmID> \n"
            + "        <ServiceType>6</ServiceType> \n"
            + "        <PhoneTarget>375336110000</PhoneTarget> \n"
            + "        <PhoneSource>1200</PhoneSource> \n"
            + "        <Message>Priorbank 10/07 18:00. Code: ШИФР po karte DK0500. Spravka: 80172890000</Message> \n"
            + "        <SendStart>0</SendStart> \n"
            + "        <SendStop>1439</SendStop> \n"
            + "        <Priority>0</Priority> \n"
            + "    </SMS> \n"
            + "    <SMS> \n"
            + "        <DBSMSId>146920909</DBSMSId> \n"
            + "        <CrmID>0</CrmID> \n"
            + "        <ServiceType>4</ServiceType> \n"
            + "        <PhoneTarget>375298810000</PhoneTarget> \n"
            + "        <PhoneSource>1200</PhoneSource> \n"
            + "        <Message>Priorbank 10/07 18:00. M-kod=H000, Platezh s DK8800, schet platezha 000000000. Summa 5.00 BYN. Spravka: 487 VhGfTg00000</Message> \n"
            + "        <SendStart>0</SendStart> \n"
            + "        <SendStop>1439</SendStop> \n"
            + "        <Priority>0</Priority> \n"
            + "    </SMS> \n"
            + "</DeliverySMS>";
    public static final String EXPECTED = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n" +
            "<DeliverySMS> \n" +
            "    <PackNum>16692535</PackNum> \n" +
            "    <ProvID>2</ProvID> \n" +
            "    <SMS> \n" +
            "        <DBSMSId>146920910</DBSMSId> \n" +
            "        <CrmID>0</CrmID> \n" +
            "        <ServiceType>6</ServiceType> \n" +
            "        <PhoneTarget>375336110000</PhoneTarget> \n" +
            "        <PhoneSource>1200</PhoneSource> \n" +
            "        <Message>Priorbank 10/07 18:00. Code: --- po karte DK0500. Spravka: 80172890000</Message> \n" +
            "        <SendStart>0</SendStart> \n" +
            "        <SendStop>1439</SendStop> \n" +
            "        <Priority>0</Priority> \n" +
            "    </SMS> \n" +
            "    <SMS> \n" +
            "        <DBSMSId>146920909</DBSMSId> \n" +
            "        <CrmID>0</CrmID> \n" +
            "        <ServiceType>4</ServiceType> \n" +
            "        <PhoneTarget>375298810000</PhoneTarget> \n" +
            "        <PhoneSource>1200</PhoneSource> \n" +
            "        <Message>Priorbank 10/07 18:00. M-kod=H000, Platezh s DK8800, schet platezha 000000000. Summa 5.00 BYN. Spravka: 487 VhGfTg00000</Message> \n" +
            "        <SendStart>0</SendStart> \n" +
            "        <SendStop>1439</SendStop> \n" +
            "        <Priority>0</Priority> \n" +
            "    </SMS> \n" +
            "</DeliverySMS>";
    public Task2Test(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(Task2Test.class);
    }

    public void testApp() {
        String actual = new CipherCVCInMsgConverter().maskCipherCVCInPack(RAW);
        assertFalse(RAW.equals(EXPECTED));
        assertEquals(actual, EXPECTED);
    }
}
