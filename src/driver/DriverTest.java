package driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverTest extends Application{
	private static List<HashMap<String,String>> list;
	private static List<String> errors = new ArrayList<String>();
	
	public static void main(String[] args){
		
		/*WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		list = ReadFile.getCsvInfo();
		System.out.println("用户名   \t密码\t\t表格数据邮箱 \t\t网络邮箱\t\t状态");
		for(int i=0;i<list.size();i++){
			String value = list.get(i).values().toString();
			String name =list.get(i).keySet().toString().substring(1, 11);
			String pwd = list.get(i).keySet().toString().substring(5, 11);
			String state = "错误";
		
			driver.navigate().to("http://www.ncfxy.com/login.jsp?name="+ name+ "&pwd="+pwd);
			WebElement result = driver.findElement(By.className("table"));
			List<WebElement> e1=result.findElements(By.tagName("td"));
			String email = e1.get(1).getText().toString();
			String webemail = value.substring(1, email.length()+1);
			if(email.equals(webemail)&& email!=null){
				state = "正确";
			}else{
				state = "错误";
				errors.add(name);
			}
			System.out.println(name+"  "+pwd+"  "+email+" " +webemail+ " " + state);
		}
		
		System.out.println("一共有"+list.size()+"条记录");
		if(errors.size()==0){
			System.out.println("无错误信息");
		}else{
			System.out.println("错误信息有"+errors.size()+"条,其用户名如下");
			for(int i=0;i<errors.size();i++){
				System.out.println(errors.get(i));
			}
		}*/
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Hello World");
		BorderPane root = new BorderPane();
		TextArea text = new TextArea("用户名\t\t密码\t\t\t表格邮箱\t\t\t\t\t网络邮箱\t\t\t状态\n");
		System.out.println("用户名\t密码\t\t表格邮箱\t\t\t\t网络邮箱\t\t\t状态");
		TextArea resultText = new TextArea("统计结果\n");
		root.setBottom(resultText);
		root.setCenter(text);
		stage.setScene(new Scene(root, 700, 500));
		
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		list = ReadFile.getCsvInfo();
		for(int i=0;i<list.size();i++){
			String value = list.get(i).values().toString();
			String name =list.get(i).keySet().toString().substring(1, 11);
			String pwd = list.get(i).keySet().toString().substring(5, 11);
			String state = "错误";
		
			driver.navigate().to("http://www.ncfxy.com/login.jsp?name="+ name+ "&pwd="+pwd);
			WebElement result = driver.findElement(By.className("table"));
			List<WebElement> e1=result.findElements(By.tagName("td"));
			String email = e1.get(1).getText().toString();
			String webemail = value.substring(1, email.length()+1);
			if(email.equals(webemail)&& email!=null){
				state = "正确";
			}else{
				state = "错误";
				errors.add(name);
			}
			if(email.length()<30){
				int l = 30-email.length();
				for(int j=1;j<l;j++){
					email+=" ";
					webemail+=" ";
				}
			}
			System.out.println(name+"  "+pwd+"  "+email+" " +webemail+ " " + state);
			text.setText(text.getText()+name+"  "+pwd+"  "+email+" " +webemail+ " " + state+"\n");
		}
		
		System.out.println("一共有"+list.size()+"条记录");
		resultText.setText(resultText.getText()+"一共有"+list.size()+"条记录\n");
		if(errors.size()==0){
			System.out.println("无错误信息");
			resultText.setText(resultText.getText()+"无错误信息\n");
		}else{
			System.out.println("错误信息有"+errors.size()+"条,其用户名如下");
			resultText.setText(resultText.getText()+""+"错误信息有"+errors.size()+"条,其用户名如下\n");
			for(int i=0;i<errors.size();i++){
				System.out.println(errors.get(i));
				resultText.setText(resultText.getText()+errors.get(i)+"\n");
			}
		}
		stage.show();
	}
	
}
