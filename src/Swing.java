import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Swing {

	public static void main(String[] args) {

		JFrame frame = new JFrame("简易日历程序");
		
        JPanel panel = new JPanel(new GridLayout(4, 2));
        
    	Map<String, String> data=new HashMap<>();//资料
		Map<String, String> keywords = new HashMap<>();//关键词
		Map<String, Integer> sumnum = new HashMap<>();//每月汇总
    	
        
        new DataSwing();//开辟一个空间
        
        // 创建按钮
        JButton button_year = new JButton("查询任意年的日历"); 
        JButton button_month = new JButton("查询任意月的日历");
        JButton button_getweek = new JButton("查询任意日期的星期数");
        JButton button_getnowmonth = new JButton("显示当月日历");
        JButton button_getnowday = new JButton("显示当前日期");
        JButton button_getnowtime = new JButton("显示当前时间");
        JButton button_adddata = new JButton("增加记账记录");
        JButton button_getdata = new JButton("查询记账记录");
        // 点击事件监听器
        
        button_year.addActionListener(new ActionListener() //查询任意年的日历
        {
            public void actionPerformed(ActionEvent e) {
                // 在按钮点击时执行的操作
            	String input = JOptionPane.showInputDialog("请输入一个年份:");
                try {
                    int year = Integer.parseInt(input);
                    // 在这里可以使用接收到的数字进行相应的操作
                    //new DataSwing();
            		DataSwing.calendarswing(year);
                } catch (NumberFormatException ex) {
                	if (input!=null) {
                		JOptionPane.showMessageDialog(null, "输入错误");
                    }
                } catch(Exception ex ){}
            }
        });
        button_month.addActionListener(new ActionListener() //查询任意月的日历
        {
            public void actionPerformed(ActionEvent e) {
                // 在按钮点击时执行的操作
            	String input = JOptionPane.showInputDialog("请输入一个年份和月份并以\".\"隔开(例如:2023.6):");
                try {
                	String[] getinput= input.split("\\.");
                    int year = Integer.parseInt(getinput[0]);
                    int month = Integer.parseInt(getinput[1]);
                    // 在这里可以使用接收到的数字进行相应的操作
                    //new DataSwing();
            		DataSwing.calendarswing(year,month);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误");
                }catch(Exception ex ){}
            	
            }
        });
        button_getweek.addActionListener(new ActionListener() //查询任意日期的星期数
        {
            public void actionPerformed(ActionEvent e) {
                // 在按钮点击时执行的操作
            	String input = JOptionPane.showInputDialog("请输入日期并以\".\"分隔(例如:2023.6.1):");
                try {
                	String[] getinput= input.split("\\.");
                    //new DataSwing();
                    DataSwing.week(getinput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误");
                }catch(Exception ex ){}
            }
        });
        button_getnowmonth.addActionListener(new ActionListener() //显示当月日历
        {
            public void actionPerformed(ActionEvent e) {
                // 在按钮点击时执行的操作
            	//new DataSwing();
                DataSwing.nowmonthcalendar();
            }
        });
        button_getnowday.addActionListener(new ActionListener() //显示当前日期
        {
            public void actionPerformed(ActionEvent e) {
               // 在按钮点击时执行的操作
            	new DataSwing();
            	DataSwing.getNowDay();
            }
        });
        button_getnowtime.addActionListener(new ActionListener() //显示当前时间
        {
            public void actionPerformed(ActionEvent e) {
                        // 在按钮点击时执行的操作
                new DataSwing();
                DataSwing.getNowTime();      	
            }
        });
        button_adddata.addActionListener(new ActionListener() //增加记录
        {
            public void actionPerformed(ActionEvent e) {
                        // 在按钮点击时执行的操作
                new DataSwing();
                DataSwing.addData(data,keywords,sumnum);
                
            }
        });
        button_getdata.addActionListener(new ActionListener() //增加记录
                {
                    public void actionPerformed(ActionEvent e) {
                                // 在按钮点击时执行的操作
                    	
                        new DataSwing();
                        DataSwing.getData(data,keywords,sumnum);;
                    }
                });
        panel.add(button_year); // 将按钮添加到面板
        panel.add(button_month); 
        panel.add(button_getweek); 
        panel.add(button_getnowmonth); 
        panel.add(button_getnowday); 
        panel.add(button_getnowtime); 
        panel.add(button_adddata);
        panel.add(button_getdata);
        frame.getContentPane().add(panel); // 将面板添加到窗口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(460, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
class DataSwing
{
	public static void calendarswing(int year)//
	{
		Calendar cal=Calendar.getInstance();
		//Scanner sc=new Scanner(System.in);
		JFrame frame = new JFrame(year+"年的日历");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // 将文本区域放入滚动面板
		cal.set(Calendar.YEAR,year);
		for(int i=1;i<=12;i++)
		{
			cal.set(Calendar.MONTH,i);
			month_calendar(i,year,textArea);
		}
		
        frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void calendarswing(int year,int month)
	{
		Calendar cal=Calendar.getInstance();
		//Scanner sc=new Scanner(System.in);
		JFrame frame = new JFrame(year+"年"+month+"月的日历");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // 将文本区域放入滚动面板
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
		month_calendar(month,year,textArea);
        
        frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 215);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static int getDay(int month,int year)//获取每月的天数
	{
		int[] days= {31,28,31,30,31,30,31,31,30,31,30,31};
		if(isLeap(year))
		{
			days[1]++;
		}
		return days[month];
	}
	public static void nowmonthcalendar()//当前月的日历
	{
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		JFrame frame = new JFrame(year+"年"+(month+1)+"月的日历");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // 将文本区域放入滚动面板
        
		month_calendar(month+1,year,textArea);
		
		frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void getNowDay()
	{
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		int day=cal.get(Calendar.DATE);
		
		JFrame frame = new JFrame("当前日期");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        textArea.append(year+"年"+(month+1)+"月"+day+"日");
        textArea.setFont(new Font("",Font.PLAIN,25));
        frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void getNowTime()
	{
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		int day=cal.get(Calendar.DATE);
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int minute=cal.get(Calendar.MINUTE);
		int seconds=cal.get(Calendar.SECOND);
		JFrame frame = new JFrame("当前时间");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        textArea.append(year+"年"+(month+1)+"月"+day+"日"+hour+"时"+minute+"分"+seconds+"秒");
        textArea.setFont(new Font("",Font.PLAIN,25));
        frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void month_calendar(int month,int year,JTextArea textArea)//打印该月的日历
	{
		
		Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // 设置年份和月份，月份需要减1，因为 Calendar 类中月份是从 0 开始的

        // 获取该月份的天数和第一天的星期几
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 打印月份和年份
        String[] monthNames = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
        textArea.append("\t\t"+monthNames[month-1] + " " + year+"\n");

        // 打印星期几的标题行
        String[] weekDays = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        
        textArea.append("——————————————————————————————————————————————————————\n");
        
        for (String weekDay : weekDays) {
        	textArea.append( weekDay+"\t");
        }
        textArea.append("\n");

        // 打印空白格子，用于对齐第一天
        for (int i = 1; i < firstDayOfWeek; i++) {
        	textArea.append(" "+"\t");
        }

        // 打印日期
        for (int day = 1; day <= daysInMonth; day++) {
        	textArea.append( day+"\t");
            if ((firstDayOfWeek + day - 1) % 7 == 0) { // 每周换行
            	textArea.append("\n");
            }
        }
        textArea.append("\n");
        textArea.append("——————————————————————————————————————————————————————\n");
	}
	public static void week(String[] str)//打印该日期的星期
	{
		Calendar cal=Calendar.getInstance();
		JFrame frame = new JFrame("某日的星期");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // 将文本区域放入滚动面板
        
		int year=Integer.parseInt(str[0]);
		int month=Integer.parseInt(str[1]);
		int day=Integer.parseInt(str[2]);
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH,month-1);
		cal.set(Calendar.DAY_OF_MONTH,day-1);
		int n=cal.get(Calendar.DAY_OF_WEEK);
		String[] week= {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		textArea.append("          "+week[n-1]);
		textArea.setFont(new Font("",Font.PLAIN,25));
		
		frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static boolean isLeap(int cal)//判断是否为闰年
	{
		if(cal%400==0)
		{
			return true;
		}
		else if(cal%100==0)
		{
			return false;
		}
		else if(cal%4==0)
		{
			return true;
		}
		else {
			return false;
		}
	}
	public static void addData(Map<String, String> data,Map<String, String> keywords,Map<String, Integer> sumnum)
	{

		JFrame frame = new JFrame("增加的记账记录");
        JPanel panel = new JPanel(new GridLayout(6, 2));
        
        JLabel idLabel = new JLabel("记账id:");//标题
        JTextField idtxt = new JTextField(20);//内容
        
        JLabel dateLabel = new JLabel("记账日期(例2023.5.29):");
        JTextField datetxt = new JTextField(20);
        
        JLabel typeLabel = new JLabel("记账类型:");
        JTextField typetxt = new JTextField(20);
        
        JLabel numLabel = new JLabel("支出费用:");
        JTextField numtxt = new JTextField(20);
        
        JLabel keyLabel = new JLabel("关键字:");
        JTextField keytxt = new JTextField(20);
        
        JButton button = new JButton("确认");//确认
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idtxt.getText();
                String date = datetxt.getText();
                String type= typetxt.getText();
                String s;
                try {//记录数据
                	int num= Integer.parseInt(numtxt.getText());
                	String key= keytxt.getText();
                    
                    handle_num(sumnum,date,num);
                    handle_other(data,keywords,key,date,num,id,type);
                    
                    
                    s="记录成功";
                    
                }catch(Exception ex) {
                	s="记录失败";
                }
                

                JFrame frame_ = new JFrame(s);
                
                JButton button_ = new JButton("确认");//确认
                button_.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	frame_.dispose();
                    }});
                button_.setFont(new Font("",Font.PLAIN,25));
                JPanel panel_ = new JPanel(new GridLayout(1, 1));
            	panel_.add(button_);
            	frame_.getContentPane().add(panel_); // 将面板添加到窗口
                frame_.setSize(250, 100);
                frame_.setLocationRelativeTo(null);
                frame_.setVisible(true);
                
                
                frame.dispose();
            }
        });
        panel.add(idLabel);
        panel.add(idtxt);
        panel.add(dateLabel);
        panel.add(datetxt);
        panel.add(typeLabel);
        panel.add(typetxt);
        panel.add(numLabel);
        panel.add(numtxt);
        panel.add(keyLabel);
        panel.add(keytxt);
        panel.add(button);
        
        
        frame.getContentPane().add(panel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
	}
	public static void handle_num(Map<String, Integer> sumnum, String date, int num) {//处理每个月数据之和
		String date_final=date.split("\\.")[0]+"."+date.split("\\.")[1];
		
		int year=Integer.parseInt(date.split("\\.")[0]);
		int month=Integer.parseInt(date.split("\\.")[1]);
		int days=Integer.parseInt(date.split("\\.")[2]);
		if(days>getDay(month,year))
		{
		}
		else if(sumnum.get(date_final)==null)
		{
			sumnum.put(date_final, num);
		}
		else
		{
			sumnum.put(date_final,sumnum.get(date_final)+num);
		}
	}
	public static void handle_other(Map<String, String> data,Map<String, String> keywords, String keys_all,String date, int num,String id,String type) {//处理每个月数据之和
		if(data.get(date)==null)
		{
			data.put(date, "id:"+id+"\n"+date+": "+type+"类   支出"+num+"元\n");
		}
		else
		{
			data.put(date, data.get(date)+"id:"+id+"\n"+date+": "+type+"类   支出"+num+"元\n");
		}
		String[] keys=keys_all.split(";");
		for (int i=0;i<keys.length;i++) {
			String key=keys[i];
			System.out.println(key);
			if(keywords.get(key)==null)
			{
	            keywords.put(key, "id:"+id+"\n"+date+": "+type+"类   支出"+num+"元\n");
			}
			else
			{
	            keywords.put(key, keywords.get(key)+"id:"+id+"\n"+date+": "+type+"类   支出"+num+"元\n");
			}
		}
		System.out.println(keywords.get("a"));
	}
	public static void getData(Map<String, String> data,Map<String, String> keywords,Map<String, Integer> sumnum) {
		JFrame frame = new JFrame("查询记账记录");
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel choiceLabel = new JLabel("选择查询模式");
        JComboBox<String> choiceComboBox = new JComboBox<>(new String[]{"日期查找","关键词查找(可用“;”分离)", "查询某月份记账总和"});

        
        JLabel Label = Label = new JLabel("请输入:");//标题;
        final JTextField Txt = new JTextField(20);//内容
        
        
        JButton button = new JButton("确认");//确认
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String choice = (String) choiceComboBox.getSelectedItem();
            	String memories = null;
            	if(choice.equals("日期查找"))
                {
                    String date=Txt.getText();
                    memories=data.get(date);
                }
                else if(choice.equals("关键词查找(可用“;”分离)"))
                {
                    String key=Txt.getText();
                    memories=keywords.get(key);
                }
                else if(choice.equals("查询某月份记账总和"))
                {
                    String date=Txt.getText();
                    memories=Integer.toString(sumnum.get(date));

                }
            	display(memories);
            }});
        
        panel.add(choiceLabel);
        panel.add(choiceComboBox);
        panel.add(button);
        panel.add(Label);
        panel.add(Txt);
        
        
        frame.getContentPane().add(panel);
        frame.setSize(380, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public static void display(String s)
	{
		JFrame frame = new JFrame("查询结果");
        JTextArea textArea = new JTextArea();
        textArea.setText(s); 
        JScrollPane scrollPane = new JScrollPane(textArea); // 将文本区域放入滚动面板

        frame.getContentPane().add(scrollPane); // 将滚动面板添加到窗口
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
