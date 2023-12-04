package interfaces;

public class Client {



    public static void main(String[] args) {
        CarAgent carAgent = new CarAgent();
        CarMaker carMaker = new Ssangyong();
        carAgent.setMaker(carMaker);
        carAgent.order();

    }
     //인터페이스를 통한 약한 결합
}
