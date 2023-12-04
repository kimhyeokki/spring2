package interfaces;

public class CarAgent {
    private CarMaker carMaker;
/*
    public CarAgent() {
        carMaker = new Hyundai();
    }
*/
    public void setMaker(CarMaker carMaker) {
        this.carMaker = carMaker;
    }

    public void order(){
        Money money = new Money(50000000);
        System.out.println(" 차의 가격은 " +money.getAmount());

        Car car = carMaker.sell(money);
        System.out.println("차 가져옴"+car.getName());

    }
}
