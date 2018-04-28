/*
 * 注意!!!!!：本代码在139行 在showDirectedGraph函数的这个语句：
 * GraphViz gViz=new GraphViz("D:\\workspace\\lab1", "D:\\Program Files\\graphviz\\bin\\dot.exe");
 * 前一个路径是生成的有向图的文件位置，后一个路径是graphviz画图软件bin文件夹中的dot.exe的路径
 */
package lab1;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner; 
import java.io.*;

public class Graph {
	
	private final int MAX=200;
	private String fileContentArray[];//字符串内容数组 如{"to","expolre","to"...}
	private int sequence[] = new int[MAX];//字符串单词的数字编号的数组 如{"0","1","0"...}
	private HashMap<String , Integer> map1 = new HashMap<String , Integer>();//map1是每个单词的位置 比如说to->0 
	private HashMap<Integer , String> map2 = new HashMap<Integer , String>();//map2是每个位置的单词 比如说0->to 
	private int num;//不重复的单词总数
	private int [][]matrix = new int[MAX][MAX];//邻接矩阵
	//private ArrayList<ArrayList<String>> matrix= new ArrayList<ArrayList<String>>();
	private String[] differentStr = new String[MAX];//不重复单词的数组 如{"to","expolre",...}
	private String[] StrWalkArray= new String[MAX];;//随即游走出的单词的数组
	private int numWalk=0;//随机游走输出的单词个数
	private int numPath=0;//路径长度
	
	//用来存储最终结果，找不到文件返回""，找到文件之后返回处理好的字符串内容.
	public String getTheFileInput(String fileName){
		//str是最终返回的内容
		String str = "";
		
		//strTemp用来临时存放读取的内容
		String strTemp = "";
		
		
		
		//打开文件
		File myFile=new File(fileName);
		
		//如果文件不存在
		if(!myFile.exists()){
			//文件不存在，返回""。
			return str;
		}
		
		//如果文件存在
		try {
            BufferedReader in = new BufferedReader(new FileReader(myFile));
            String s;
            
            //依次将读到的字符加在strTemp的尾部,这里是按行读取，无法逐个判断字符是否合法，还不知道怎么逐个读取，准备后面再扫描一次来处理
            while ((s = in.readLine()) != null) {
            	strTemp = strTemp + ' ' + s;
            }
            //in.close();
        } 
        catch (IOException e) {
            e.getStackTrace();
        }
		
		//字符串的整理操作，去除不合法字符，多余的空格，和大写变小写的操作。
		for(int i = 0; i < strTemp.length(); i++){
			
			//大写字母变小写
			strTemp = strTemp.toLowerCase();	
			
			//合法字符与空格都添加进str，可能会有多个空格，只要分割时用split("\\s+")就可以忽略多个空格的问题
			if((strTemp.charAt(i) >= 'a' && strTemp.charAt(i) <= 'z') || strTemp.charAt(i) == ' '){
				
				//添加到str的尾部
				str = str + strTemp.charAt(i);
			}
			
			//其他全部忽略。
		}
		
		//到此返回的字符串是一个只有小写字母和空格组成的字符串，空格可以有多个，其他特殊符号全部被忽视。
		//System.out.println(str);
		return str;
	}
	
	//将字符大串转化为字符串内容数组  如{"to","expolre",...}
	public void getThefileContentArray(String fileName){
		int i;
		String fileContent = getTheFileInput(fileName);
		//把获得的大字符串按空格分割
		this.fileContentArray = fileContent.split("\\s+");
		
		for(i = 0; i < this.fileContentArray.length-1; i++){
			fileContentArray[i]= fileContentArray[i+1];
		}
		fileContentArray[i] = null;
		
//		for(i = 0; i < this.fileContentArray.length;i++){
//			
//			System.out.println(fileContentArray[i]);
//		}
		//System.out.println("单词的个数 含重复"+fileContentArray.length-1);
		//System.out.println(fileContentArray[0]);
	}
	
	//获得邻接矩阵
	public void getTheMatrix(){
		int i;
		int j=0;

		for (i=0; i<fileContentArray.length-1; i++){
            if( map1.containsKey(fileContentArray[i]) == false ){//如果键不存在，就添加
            	
            	map1.put(fileContentArray[i],j);//map1< (to,0),(explore,1)... >
               map2.put(j,fileContentArray[i]);//map2< (0,to),(1,explore)... >
                
               j++;
            }
        }
		this.num = map1.size();//不重复的单词的个数
		//System.out.println("num="+num);
		
		for (i=0; i<fileContentArray.length-1; i++){
//			System.out.println("i="+i);
//			System.out.println(fileContentArray[i]);
//			System.out.println(map1.get(fileContentArray[i]));
			sequence[i] = map1.get(fileContentArray[i]);//字符串单词的数字编号的数组 如{"0","1","0"...}
		}
		for (i=0; i<fileContentArray.length-2; i++){
			//System.out.println(sequence[i]);
			(matrix[sequence[i]][sequence[i+1]])++;//邻接矩阵
			
		}
		for (i=0; i<num; i++){
			this.differentStr[i] = map2.get(i);//不重复单词的数组 如{"to","expolre",...}
			//System.out.println("不重复单词列表"+differentStr[i]);
		}
		 
	}
	
	//将有向图以邻接矩阵的形式展现出来
	public void showDirectedGraph(){
		GraphViz gViz=new GraphViz("D:\\workspace\\tjblab1", "D:\\Program Files\\graphviz\\bin\\dot.exe");
        gViz.start_graph();
        int int1, int2;
        for (int i=0; i<fileContentArray.length-2; i++){
        	int1=this.sequence[i];
        	int2=this.sequence[i+1];
        	gViz.addln(map2.get(int1)+"->"+map2.get(int2));
        }
        gViz.end_graph();
        System.out.println("有向图digraph已经以图形文件形式保存到磁盘");
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        System.out.println("有向图的邻接矩阵为：");
		for (int i=0; i<this.num; i++){
			System.out.printf("%15s", this.differentStr[i]);
		}
		System.out.println();
		
		for (int i=0; i<this.num; i++)
		{
			for (int j=0; j<this.num; j++)
			{
				System.out.printf("%15d", matrix[i][j]);
			}
			System.out.println();
		}
		
		
	}

	//查询桥接词
	public String queryBridgeWords(String str1,String str2){
		String aim = "";//输出的字符串
		String bridegwords = "";//桥连词
		int int1,int2;

		if (map1.containsKey(str1)==false && map1.containsKey(str2)==false){
			aim="No \""+str1+"\"and\""+str2+"\" in the graph!";
		}
		else if (map1.containsKey(str1)==false){
			aim="No \""+str1+"\" in the graph!";
		}
		else if (map1.containsKey(str2)==false){
			aim="No \""+str2+"\" in the graph!";
		}
		else{
			int1 = map1.get(str1);
			int2 = map1.get(str2);
			for(int i=0; i<map1.size(); i++)     
	        {
	          if(matrix[int1][i]!=0 && matrix[i][int2]!=0)
	          {
	        	  bridegwords += map2.get(i)+" ";
	          }
	        }

	        if( bridegwords ==null || bridegwords.isEmpty())
	           aim="No bridge words from \""+str1+"\" to \""+str2+"\"!";
	        else
	        {
	           aim="The bridge words from \""+str1+"\" to \""+str2+"\" : "+bridegwords;
	           
	        }
		}
		//System.out.println(aim);
		
		return aim;
	}
	
	//获取str1和str2的桥连词的大字符串 如{"to strange "}，用于下一函数generateNewText
	public String GetBright(String str1,String str2){

        String bridegwords="";
        int int1,int2;
        
        if(map2.containsValue(str1)==false || map2.containsValue(str2)==false)
        {
            return null;
        }
        else
        {
        	int1=map1.get(str1);
            int2=map1.get(str2);

            //获得桥连词
            for(int i=0;i<map1.size();i++)
            {
              if(matrix[int1][i]!=0 && matrix[i][int2]!=0)   
            	  bridegwords += map2.get(i)+" ";
            }

            if( bridegwords ==null || bridegwords.isEmpty())
            	bridegwords=null;
            //System.out.println(bridegwords);
            return bridegwords;
        }
      }
	
	//产生新文本
	public String generateNewText(String input){
		int i;
		String tempstrinput="";//
		for(i = 0; i < input.length(); i++){
			
			//大写字母变小写
			//input = input.toLowerCase();	
			
			//合法字符与空格都添加进str，可能会有多个空格，只要分割时用split("\\s+")就可以忽略多个空格的问题
			if((input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') || input.charAt(i) == ' '){
				
				//添加到str的尾部
				tempstrinput = tempstrinput + input.charAt(i);
			}
			
			//其他全部忽略。
		}
	    String []strinput=tempstrinput.split("\\s+");//输入的字符串数组
//	    for(i = 0; i < strinput.length-1; i++){
//	    	strinput[i]= strinput[i+1];
//		}
//	    strinput[i] = null;
	    
	    String []bridegwordsarray;
	    String bridegwords="", aim="";

	    aim=strinput[0]+" ";
	    for(i=0; i<strinput.length-1; i++){
	    	if(GetBright(strinput[i],strinput[i+1])==null || GetBright(strinput[i],strinput[i+1]).isEmpty()){
	        	aim += strinput[i+1]+" ";
	        }
	        else{
	        	bridegwords=GetBright(strinput[i],strinput[i+1]);
	        	bridegwordsarray=bridegwords.split(" ");
	            Random random=new Random();
	            int h=random.nextInt(bridegwordsarray.length);
	            aim += bridegwordsarray[h]+" "+strinput[i+1]+" ";
	        }
	    }
	    //System.out.println("输出的新文本为：");
	    //System.out.println(aim);
	    return aim;
	}
	
	//输出为文本，并以文件形式写入磁盘
	public void outputFile(String aim){
		 try{
			 PrintWriter out = new PrintWriter(
					 new BufferedWriter(
							 new OutputStreamWriter(
									 new FileOutputStream("output.txt")))
			);
			out.println(aim);
			out.close();
		 }catch (FileNotFoundException e){
			 e.printStackTrace();
		 }
	}
	
	
	//最短路径
	public String calcShortestPath(String word1,String word2){
		String aim="";//最短路径
		int int1,int2;
		numPath=1;

        if(map2.containsValue(word1)==false || map2.containsValue(word2)==false)
        {
            return null;
        }
        
        int1= map1.get(word1);
        int2= map1.get(word2);

        int [][]P=new int[num][num];
        int [][]tempmarix=new int[num][num];//tempmarix是临时的邻接矩阵
        
        //floyd算法创建 矩阵P 用于存储中间信息
        for(int i=0;i<num;i++)   
        {
          for(int j=0;j<num;j++)
          {
            if(this.matrix[i][j]!=0){
              
            	tempmarix[i][j]=matrix[i][j];
            }
            else if(i!=j)
            {
            	tempmarix[i][j]=9999;
            }
          }
        }
         for(int i=0;i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
                if(tempmarix[i][j]!=9999){
                    P[i][j]=j;
                }
                else
                    P[i][j]=0;
            }
        }

        for (int k = 0; k < num; ++k)
        {
            for (int i = 0; i < num; ++i)
            {
                for (int j = 0; j < num; ++j)
                {
                    if (tempmarix[i][k] + tempmarix[k][j] < tempmarix[i][j]) {
                    	tempmarix[i][j] = tempmarix[i][k] + tempmarix[k][j];
                        P[i][j]=P[i][k];
                    }
                }
            }
        }

        int next=P[int1][int2];

        if(next==0)
        {
        	
        	aim="No path from "+word1+" to "+word2+"!";
        }
        else{
        	aim=map2.get(int1);
	        aim+="->";
	
	        while(next!=int2){
	        	aim+=map2.get(next)+"->";
	            next=P[next][int2];
	            numPath++;
	        }
	
	        aim=aim+map2.get(int2);
	        System.out.println("路径长度 "+(numPath));
        }
        System.out.println(aim);
		return aim;
	}
	
	//判断第key个节点有无后继结点
	public boolean JudgeIfHaveNext(int key){
		int i;
		boolean flag=false;
		for (i=0; i<map1.size(); i++){
			if (matrix[key][i] != 0){
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	//随机游走
	public String randomWalk() {
		
		String aim=" ";
		boolean flag1=true;
		int i;//当前节点位置
		int j=0;//后继节点位置
		int visit[][]=new int [map1.size()][map1.size()];//visit矩阵
		Random random=new Random();
        i=random.nextInt(map1.size());
        i=0;
        //System.out.println("i="+i);
		this.StrWalkArray[numWalk]=differentStr[i];
		aim+=differentStr[i]+" ";
		
		if (JudgeIfHaveNext(i)==false)//检查第i个节点有无后继
			flag1=false;
    	
    	 while (flag1==true)
    	 {
    		 while (true){//此时i一定有后继
    			 j=(int)(Math.random()*(map1.size()));
    			 //System.out.println("j="+j);
    			 if (matrix[i][j]!=0) {//直到找到第i个节点的后继结点为止, j为i的后继
    				 visit[i][j]++;
    				 aim+=differentStr[j]+" ";
    				 numWalk++;
    				 this.StrWalkArray[numWalk]=differentStr[j];

    				 break;
    			 }
    		 }
    		 if (visit[i][j]==2 || JudgeIfHaveNext(j)==false){//路径已经走过俩次，或者j没有后继结点
    			 break;
    		 }
    		 i=j;//此时i成为了i的后继节点
    	 }
    	 //System.out.println(aim);
    	 return aim;
	}
	
	//随机游走重构函数    测试专用!!!!!
	public String randomWalk(int random1, int random2) {
		
		String aim=" ";
		boolean flag1=true;
		int i;//当前节点位置
		int j=0;//后继节点位置
		int visit[][]=new int [map1.size()][map1.size()];//visit矩阵
		Random random=new Random();
        i=random.nextInt(map1.size());
        i=random1;//测试专用!!!!!

		this.StrWalkArray[numWalk]=differentStr[i];
		aim+=differentStr[i]+" ";
		
		if (JudgeIfHaveNext(i)==false)//检查第i个节点有无后继
			flag1=false;
    	
		j=random2-1;
    	 while (flag1==true)
    	 {
    		 while (true){//此时i一定有后继
    			 j=(int)(Math.random()*(map1.size()));
    			 j++;//测试专用!!!!!
    			 if (matrix[i][j]!=0) {//直到找到第i个节点的后继结点为止, j为i的后继
    				 visit[i][j]++;
    				 aim+=differentStr[j]+" ";
    				 numWalk++;
    				 this.StrWalkArray[numWalk]=differentStr[j];

    				 break;
    			 }
    		 }
    		 if (visit[i][j]==2
    				 || JudgeIfHaveNext(j)==false){//路径已经走过俩次，或者j没有后继结点
    			 break;
    		 }
    		 i=j;//此时i成为了i的后继节点
    	 }
    	 //System.out.println("aim: "+aim);
    	 return aim;
	}
	
	
	public static void main(String[] args){
		//获取文件名
		System.out.println("输入文件名:");
		Scanner sc= new Scanner(System.in);
		String fileName = sc.nextLine();
		
		Graph gr = new Graph();
		gr.getThefileContentArray(fileName);
		gr.getTheMatrix();
		gr.showDirectedGraph();
		
		System.out.println("求桥连词,请输入word1和word2:");
		String str1=sc.next();
		String str2=sc.next();
		sc.nextLine();//必须有此操作  读取回车，否则下一个sc.nextLine()读不了
		System.out.println(gr.queryBridgeWords(str1,str2));
		
		System.out.println("请输入新文本:");
		String input=sc.nextLine();
		System.out.println("输出的新文本为：");
	    System.out.println(gr.generateNewText(input));
		//seek to explore new and exciting synergies
		
	    System.out.println("求最短路径,请输入起点word1和终点word2:");
		str1=sc.next();
		str2=sc.next();
		sc.nextLine();//必须有此操作  读取回车
		System.out.println("最短路径为:"+gr.calcShortestPath(str1, str2));
		
		gr.randomWalk();
		//gr.randomWalk(0,0);
		String aim=" ";
		int countWalk=gr.numWalk;//获得随机游走输出的最多的单词个数
		int count=0;//计数器
		System.out.println("随机游走请输入'1'，结束随机游走请输入'0'，请输入：");
		int flag=sc.nextInt();
		gr.outputFile(aim);
		while (flag != 0 && count<=countWalk){
			aim += (gr.StrWalkArray[count]+" ");
			count++;
			gr.outputFile(aim);
			System.out.println("遍历的节点已经写入output.txt文本");
			System.out.println("继续随机游走请输入'1'，结束随机游走请输入'0'，请输入：");
			flag=sc.nextInt();
		}
		System.out.println("随机游走结束");
		
			
	}
	
	
}

class  GraphViz{
    private String runPath = "";
    private String dotPath = ""; 
    private String runOrder="";
    private String dotCodeFile="digraphcode.txt";
    private String resultGif="digraph";
    private StringBuilder graph = new StringBuilder();

    Runtime runtime=Runtime.getRuntime();

    public void run() {
        File file=new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph.toString(), runPath);
        creatOrder();
        try {
            runtime.exec(runOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatOrder(){
        runOrder+=dotPath+" ";
        runOrder+=runPath;
        runOrder+="\\"+dotCodeFile+" ";
        runOrder+="-T gif ";
        runOrder+="-o ";
        runOrder+=runPath;
        runOrder+="\\"+resultGif+".gif";
        System.out.println(runOrder);
    }

    public void writeGraphToFile(String dotcode, String filename) {
        try {
            File file = new File(filename+"\\"+dotCodeFile);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.close();
        } catch (java.io.IOException ioe) { 
            ioe.printStackTrace();
        }
     }  

    public GraphViz(String runPath,String dotPath) {
        this.runPath=runPath;
        this.dotPath=dotPath;
    }

    public void add(String line) {
        graph.append("\t"+line);
    }

    public void addln(String line) {
        graph.append("\t"+line + "\n");
    }

    public void addln() {
        graph.append('\n');
    }

    public void start_graph() {
        graph.append("digraph G {\n") ;
    }

    public void end_graph() {
        graph.append("}") ;
    }   
} 