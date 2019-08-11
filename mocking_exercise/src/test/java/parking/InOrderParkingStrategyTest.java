package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
       ParkingLot parkingLot=mock(ParkingLot.class);
       Car car=mock(Car.class);
       when(parkingLot.getName()).thenReturn("parkingLot1");
       when(car.getName()).thenReturn("car1");
       InOrderParkingStrategy inOrderParkingStrategy=new InOrderParkingStrategy();
       Receipt receipt=inOrderParkingStrategy.createReceipt(parkingLot,car);
       Assert.assertEquals("parkingLot1",receipt.getParkingLotName());
       Assert.assertEquals("car1",receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car car=mock(Car.class);
        when(car.getName()).thenReturn("aodi");
        InOrderParkingStrategy inOrderParkingStrategy=new InOrderParkingStrategy();
        Receipt receipt=inOrderParkingStrategy.createNoSpaceReceipt(car);
        Assert.assertEquals("aodi",receipt.getCarName());
        Assert.assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        //Car car=mock(Car.class);
        Car car=new Car("jsajs");
        //when(car.getName()).thenReturn("aodi");
        List<ParkingLot> parkingLotList=new ArrayList<>();
        InOrderParkingStrategy inOrderParkingStrategy=Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt=inOrderParkingStrategy.park(parkingLotList,car);
        Mockito.verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(car);
}

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        Car car=mock(Car.class);
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot parkingLot=new ParkingLot("PAK1",20);
        when(car.getName()).thenReturn("jhgk");
        parkingLotList.add(parkingLot);
        InOrderParkingStrategy inOrderParkingStrategy=Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt=inOrderParkingStrategy.park(parkingLotList,car);
        Mockito.verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot,car);
        Mockito.verify(inOrderParkingStrategy,times(0)).createNoSpaceReceipt(car);
	}

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        Car car=mock(Car.class);
        when(car.getName()).thenReturn("jhgk");
        ParkingLot parkingLot=new ParkingLot("PAK1",1);
        parkingLot.getParkedCars().add(car);
        List<ParkingLot>parkingLots= Arrays.asList(parkingLot);
        InOrderParkingStrategy inOrderParkingStrategy=Mockito.spy(new InOrderParkingStrategy());
        Receipt receipt=inOrderParkingStrategy.park(parkingLots,car);
        Mockito.verify(inOrderParkingStrategy,times(0)).createReceipt(parkingLot,car);
        Mockito.verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        Car car=mock(Car.class);
      when(car.getName()).thenReturn("lili");
      ParkingLot parkingLot=new ParkingLot("PAK2",1);
      parkingLot.getParkedCars().add(car);
      ParkingLot parkingLot1=new ParkingLot("PAK3",1);
      List<ParkingLot>parkingLots=Arrays.asList(parkingLot,parkingLot1);
      InOrderParkingStrategy inOrderParkingStrategy=Mockito.spy(new InOrderParkingStrategy());
      Receipt receipt=inOrderParkingStrategy.park(parkingLots,car);
      Mockito.verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot1,car);
      Mockito.verify(inOrderParkingStrategy,times(0)).createNoSpaceReceipt(car);
    }


}
