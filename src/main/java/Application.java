import com.example.pageobjects.Google;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static  void main(String[] args){
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfigMain.class);

        Google google = appContext.getBean("google", Google.class);
//        CustomerService customerService = appContext.getBean("customerService", CustomerService.class);

//        System.out.println(customerService.findAll().get(0).getFirstname());
        google.goTo();
        google.getDriver().quit();
    }
}
