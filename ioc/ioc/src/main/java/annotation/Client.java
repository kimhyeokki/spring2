package annotation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {



    public static void main(String[] args) {
     ApplicationContext context = new ClassPathXmlApplicationContext("car-config-annotation.xml");
     CarAgent carAgent = context.getBean("carAgent", CarAgent.class);
     System.out.println("carAgent==="+carAgent);
     carAgent.order();

        System.out.println("========================");

        CarMaker carMaker = context.getBean("kia", Kia.class); //이름이 없는 경우에는 메서드를 통째로 가져옴
        carAgent.setMaker(carMaker);
        carAgent.order();
    }
     //인터페이스를 통한 약한 결합
}
