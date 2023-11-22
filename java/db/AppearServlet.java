package db;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppearServlet")
public class AppearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AppearServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String item = request.getParameter("item");
		String order = request.getParameter("order");
		String submit = request.getParameter("submit");
		String newnumber = request.getParameter("newnumber");
		String newshicode = request.getParameter("newshicode");
		String deleteid = request.getParameter("deleteid");
		String newyear = request.getParameter("newyear");
		String newmonth = request.getParameter("newmonth");
		String newdate = request.getParameter("newdate");
		String newhour = request.getParameter("newhour");
		String newminute = request.getParameter("newminute");
		String newsecond = request.getParameter("newsecond");
		String shimei = request.getParameter("shimei");
		System.out.printf("\n%s:%s:%s:\n",item,order,submit);
		System.out.printf("%s:%s:\n",newnumber,newshicode);
		System.out.printf("%s:%s:\n",deleteid,shimei);
		
		if(submit != null) {
			if(submit.equals("並び変え")) {
			}else if(submit.equals("登録")) {
				if(newyear == "" || newmonth == "" || newdate == "" || newhour == "" || newminute == "" || newsecond == "") {
					insert(newnumber,newshicode);
				}else {
					insert(newnumber,newshicode,newyear,newmonth,newdate,newhour,newminute,newsecond);
				}
			}else if(submit.equals("削除")) {
				delete(deleteid);
			}else if(submit.equals("検索")) {
			}
		}
		
		selectAll(request,response,item,order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/appear.jsp");
		dispatcher.forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	void selectAll(HttpServletRequest request,HttpServletResponse response,String item,String order)throws ServletException{
		AppearDAO appearDAO = new AppearDAO();
		List<Appear> list = appearDAO.findAll(item,order);
		request.setAttribute("list",list);
	}
	
	void insert(String newnumber,String newshicode) {
		AppearDAO appearDAO = new AppearDAO();
		try {
			int num = Integer.parseInt(newnumber);
			int code = Integer.parseInt(newshicode);
			appearDAO.insert(num,code);
		}catch(NumberFormatException e) {
			System.out.println("不正な番号または市コードが入力されました"+e.getMessage());
		}
	}
	
	void insert(String newnumber,String newshicode,String newyear,String newmonth,String newdate,String newhour,String newminute,String newsecond) {
		AppearDAO appearDAO = new AppearDAO();
		try {
			int num = Integer.parseInt(newnumber);
			int code = Integer.parseInt(newshicode);
			int year = Integer.parseInt(newyear);
			int month = Integer.parseInt(newmonth);
			int date = Integer.parseInt(newdate);
			int hour = Integer.parseInt(newhour);
			int minute = Integer.parseInt(newminute);
			int second = Integer.parseInt(newsecond);
			appearDAO.insert(num,code,year,month,date,hour,minute,second);
		}catch(NumberFormatException e) {
			System.out.println("不正な番号または市コードが入力されました"+e.getMessage());
		}
	}
	
	void delete(String deleteid) {
		AppearDAO appearDAO = new AppearDAO();
		 try {
			 int id = Integer.parseInt(deleteid);
			 appearDAO.delete(id);
		 } catch (NumberFormatException e) {
			 System.out.println("不正なIDが入力されました"+e.getMessage());
		 }
	}
}
