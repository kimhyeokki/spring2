package interfaces;

public class Kia implements CarMaker {
    @Override
    public Car sell(Money money){
        System.out.println("기아 자동차 돈주면 차를 팝니다"+money.getAmount());
        Car car = new Car(" K9");
        System.out.printf("기아에서 만든 차이름"+car.getName());
        return car;
    }
}