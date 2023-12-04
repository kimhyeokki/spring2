package xml;

public class Hyundai implements CarMaker {
    @Override
    public Car sell(Money money){
        System.out.printf("현대 자동차 돈주면 차를 팝니다"+money.getAmount());
        Car car = new Car("제네시스");
        System.out.printf("현대에서 만든 차이름"+car.getName());
        return car;
    }
}
