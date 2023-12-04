package basic;

public class CarAgent {
    private  Hyundai hyundai;
    public CarAgent() {

        hyundai = new Hyundai();
    }

    public void order(){
        Money money = new Money(50000000);
        System.out.printf(" 제네시스의 가격은 " +money.getAmount());

        Car car = hyundai.sell(money);
        System.out.printf("현대에서 가서 차 가져옴"+car.getName());
    }
}
