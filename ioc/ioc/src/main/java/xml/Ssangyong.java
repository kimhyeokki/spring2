package xml;

public class Ssangyong implements CarMaker {
    @Override
    public Car sell(Money money) {
        System.out.printf("쌍용 자동차 돈주면 차를 팝니다"+money.getAmount());
        Car car = new Car(" 체어맨");
        System.out.printf("쌍용에서 만든 차이름"+car.getName());
        return car;

    }
}
