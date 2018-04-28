/*
 * ע��!!!!!����������139�� ��showDirectedGraph�����������䣺
 * GraphViz gViz=new GraphViz("D:\\workspace\\lab1", "D:\\Program Files\\graphviz\\bin\\dot.exe");
 * ǰһ��·�������ɵ�����ͼ���ļ�λ�ã���һ��·����graphviz��ͼ���bin�ļ����е�dot.exe��·��
 */
package lab1;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner; 
import java.io.*;

public class Graph {
	
	private final int MAX=200;
	private String fileContentArray[];//�ַ����������� ��{"to","expolre","to"...}
	private int sequence[] = new int[MAX];//�ַ������ʵ����ֱ�ŵ����� ��{"0","1","0"...}
	private HashMap<String , Integer> map1 = new HashMap<String , Integer>();//map1��ÿ�����ʵ�λ�� ����˵to->0 
	private HashMap<Integer , String> map2 = new HashMap<Integer , String>();//map2��ÿ��λ�õĵ��� ����˵0->to 
	private int num;//���ظ��ĵ�������
	private int [][]matrix = new int[MAX][MAX];//�ڽӾ���
	//private ArrayList<ArrayList<String>> matrix= new ArrayList<ArrayList<String>>();
	private String[] differentStr = new String[MAX];//���ظ����ʵ����� ��{"to","expolre",...}
	private String[] StrWalkArray= new String[MAX];;//�漴���߳��ĵ��ʵ�����
	private int numWalk=0;//�����������ĵ��ʸ���
	private int numPath=0;//·������
	
	//�����洢���ս�����Ҳ����ļ�����""���ҵ��ļ�֮�󷵻ش���õ��ַ�������.
	public String getTheFileInput(String fileName){
		//str�����շ��ص�����
		String str = "";
		
		//strTemp������ʱ��Ŷ�ȡ������
		String strTemp = "";
		
		
		
		//���ļ�
		File myFile=new File(fileName);
		
		//����ļ�������
		if(!myFile.exists()){
			//�ļ������ڣ�����""��
			return str;
		}
		
		//����ļ�����
		try {
            BufferedReader in = new BufferedReader(new FileReader(myFile));
            String s;
            
            //���ν��������ַ�����strTemp��β��,�����ǰ��ж�ȡ���޷�����ж��ַ��Ƿ�Ϸ�������֪����ô�����ȡ��׼��������ɨ��һ��������
            while ((s = in.readLine()) != null) {
            	strTemp = strTemp + ' ' + s;
            }
            //in.close();
        } 
        catch (IOException e) {
            e.getStackTrace();
        }
		
		//�ַ��������������ȥ�����Ϸ��ַ�������Ŀո񣬺ʹ�д��Сд�Ĳ�����
		for(int i = 0; i < strTemp.length(); i++){
			
			//��д��ĸ��Сд
			strTemp = strTemp.toLowerCase();	
			
			//�Ϸ��ַ���ո���ӽ�str�����ܻ��ж���ո�ֻҪ�ָ�ʱ��split("\\s+")�Ϳ��Ժ��Զ���ո������
			if((strTemp.charAt(i) >= 'a' && strTemp.charAt(i) <= 'z') || strTemp.charAt(i) == ' '){
				
				//��ӵ�str��β��
				str = str + strTemp.charAt(i);
			}
			
			//����ȫ�����ԡ�
		}
		
		//���˷��ص��ַ�����һ��ֻ��Сд��ĸ�Ϳո���ɵ��ַ������ո�����ж���������������ȫ�������ӡ�
		//System.out.println(str);
		return str;
	}
	
	//���ַ���ת��Ϊ�ַ�����������  ��{"to","expolre",...}
	public void getThefileContentArray(String fileName){
		int i;
		String fileContent = getTheFileInput(fileName);
		//�ѻ�õĴ��ַ������ո�ָ�
		this.fileContentArray = fileContent.split("\\s+");
		
		for(i = 0; i < this.fileContentArray.length-1; i++){
			fileContentArray[i]= fileContentArray[i+1];
		}
		fileContentArray[i] = null;
		
//		for(i = 0; i < this.fileContentArray.length;i++){
//			
//			System.out.println(fileContentArray[i]);
//		}
		//System.out.println("���ʵĸ��� ���ظ�"+fileContentArray.length-1);
		//System.out.println(fileContentArray[0]);
	}
	
	//����ڽӾ���
	public void getTheMatrix(){
		int i;
		int j=0;

		for (i=0; i<fileContentArray.length-1; i++){
            if( map1.containsKey(fileContentArray[i]) == false ){//����������ڣ������
            	
            	map1.put(fileContentArray[i],j);//map1< (to,0),(explore,1)... >
               map2.put(j,fileContentArray[i]);//map2< (0,to),(1,explore)... >
                
               j++;
            }
        }
		this.num = map1.size();//���ظ��ĵ��ʵĸ���
		//System.out.println("num="+num);
		
		for (i=0; i<fileContentArray.length-1; i++){
//			System.out.println("i="+i);
//			System.out.println(fileContentArray[i]);
//			System.out.println(map1.get(fileContentArray[i]));
			sequence[i] = map1.get(fileContentArray[i]);//�ַ������ʵ����ֱ�ŵ����� ��{"0","1","0"...}
		}
		for (i=0; i<fileContentArray.length-2; i++){
			//System.out.println(sequence[i]);
			(matrix[sequence[i]][sequence[i+1]])++;//�ڽӾ���
			
		}
		for (i=0; i<num; i++){
			this.differentStr[i] = map2.get(i);//���ظ����ʵ����� ��{"to","expolre",...}
			//System.out.println("���ظ������б�"+differentStr[i]);
		}
		 
	}
	
	//������ͼ���ڽӾ������ʽչ�ֳ���
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
        System.out.println("����ͼdigraph�Ѿ���ͼ���ļ���ʽ���浽����");
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        System.out.println("����ͼ���ڽӾ���Ϊ��");
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

	//��ѯ�ŽӴ�
	public String queryBridgeWords(String str1,String str2){
		String aim = "";//������ַ���
		String bridegwords = "";//������
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
	
	//��ȡstr1��str2�������ʵĴ��ַ��� ��{"to strange "}��������һ����generateNewText
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

            //���������
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
	
	//�������ı�
	public String generateNewText(String input){
		int i;
		String tempstrinput="";//
		for(i = 0; i < input.length(); i++){
			
			//��д��ĸ��Сд
			//input = input.toLowerCase();	
			
			//�Ϸ��ַ���ո���ӽ�str�����ܻ��ж���ո�ֻҪ�ָ�ʱ��split("\\s+")�Ϳ��Ժ��Զ���ո������
			if((input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') || input.charAt(i) == ' '){
				
				//��ӵ�str��β��
				tempstrinput = tempstrinput + input.charAt(i);
			}
			
			//����ȫ�����ԡ�
		}
	    String []strinput=tempstrinput.split("\\s+");//������ַ�������
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
	    //System.out.println("��������ı�Ϊ��");
	    //System.out.println(aim);
	    return aim;
	}
	
	//���Ϊ�ı��������ļ���ʽд�����
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
	
	
	//���·��
	public String calcShortestPath(String word1,String word2){
		String aim="";//���·��
		int int1,int2;
		numPath=1;

        if(map2.containsValue(word1)==false || map2.containsValue(word2)==false)
        {
            return null;
        }
        
        int1= map1.get(word1);
        int2= map1.get(word2);

        int [][]P=new int[num][num];
        int [][]tempmarix=new int[num][num];//tempmarix����ʱ���ڽӾ���
        
        //floyd�㷨���� ����P ���ڴ洢�м���Ϣ
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
	        System.out.println("·������ "+(numPath));
        }
        System.out.println(aim);
		return aim;
	}
	
	//�жϵ�key���ڵ����޺�̽��
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
	
	//�������
	public String randomWalk() {
		
		String aim=" ";
		boolean flag1=true;
		int i;//��ǰ�ڵ�λ��
		int j=0;//��̽ڵ�λ��
		int visit[][]=new int [map1.size()][map1.size()];//visit����
		Random random=new Random();
        i=random.nextInt(map1.size());
        i=0;
        //System.out.println("i="+i);
		this.StrWalkArray[numWalk]=differentStr[i];
		aim+=differentStr[i]+" ";
		
		if (JudgeIfHaveNext(i)==false)//����i���ڵ����޺��
			flag1=false;
    	
    	 while (flag1==true)
    	 {
    		 while (true){//��ʱiһ���к��
    			 j=(int)(Math.random()*(map1.size()));
    			 //System.out.println("j="+j);
    			 if (matrix[i][j]!=0) {//ֱ���ҵ���i���ڵ�ĺ�̽��Ϊֹ, jΪi�ĺ��
    				 visit[i][j]++;
    				 aim+=differentStr[j]+" ";
    				 numWalk++;
    				 this.StrWalkArray[numWalk]=differentStr[j];

    				 break;
    			 }
    		 }
    		 if (visit[i][j]==2 || JudgeIfHaveNext(j)==false){//·���Ѿ��߹����Σ�����jû�к�̽��
    			 break;
    		 }
    		 i=j;//��ʱi��Ϊ��i�ĺ�̽ڵ�
    	 }
    	 //System.out.println(aim);
    	 return aim;
	}
	
	//��������ع�����    ����ר��!!!!!
	public String randomWalk(int random1, int random2) {
		
		String aim=" ";
		boolean flag1=true;
		int i;//��ǰ�ڵ�λ��
		int j=0;//��̽ڵ�λ��
		int visit[][]=new int [map1.size()][map1.size()];//visit����
		Random random=new Random();
        i=random.nextInt(map1.size());
        i=random1;//����ר��!!!!!

		this.StrWalkArray[numWalk]=differentStr[i];
		aim+=differentStr[i]+" ";
		
		if (JudgeIfHaveNext(i)==false)//����i���ڵ����޺��
			flag1=false;
    	
		j=random2-1;
    	 while (flag1==true)
    	 {
    		 while (true){//��ʱiһ���к��
    			 j=(int)(Math.random()*(map1.size()));
    			 j++;//����ר��!!!!!
    			 if (matrix[i][j]!=0) {//ֱ���ҵ���i���ڵ�ĺ�̽��Ϊֹ, jΪi�ĺ��
    				 visit[i][j]++;
    				 aim+=differentStr[j]+" ";
    				 numWalk++;
    				 this.StrWalkArray[numWalk]=differentStr[j];

    				 break;
    			 }
    		 }
    		 if (visit[i][j]==2
    				 || JudgeIfHaveNext(j)==false){//·���Ѿ��߹����Σ�����jû�к�̽��
    			 break;
    		 }
    		 i=j;//��ʱi��Ϊ��i�ĺ�̽ڵ�
    	 }
    	 //System.out.println("aim: "+aim);
    	 return aim;
	}
	
	
	public static void main(String[] args){
		//��ȡ�ļ���
		System.out.println("�����ļ���:");
		Scanner sc= new Scanner(System.in);
		String fileName = sc.nextLine();
		
		Graph gr = new Graph();
		gr.getThefileContentArray(fileName);
		gr.getTheMatrix();
		gr.showDirectedGraph();
		
		System.out.println("��������,������word1��word2:");
		String str1=sc.next();
		String str2=sc.next();
		sc.nextLine();//�����д˲���  ��ȡ�س���������һ��sc.nextLine()������
		System.out.println(gr.queryBridgeWords(str1,str2));
		
		System.out.println("���������ı�:");
		String input=sc.nextLine();
		System.out.println("��������ı�Ϊ��");
	    System.out.println(gr.generateNewText(input));
		//seek to explore new and exciting synergies
		
	    System.out.println("�����·��,���������word1���յ�word2:");
		str1=sc.next();
		str2=sc.next();
		sc.nextLine();//�����д˲���  ��ȡ�س�
		System.out.println("���·��Ϊ:"+gr.calcShortestPath(str1, str2));
		
		gr.randomWalk();
		//gr.randomWalk(0,0);
		String aim=" ";
		int countWalk=gr.numWalk;//������������������ĵ��ʸ���
		int count=0;//������
		System.out.println("�������������'1'�������������������'0'�������룺");
		int flag=sc.nextInt();
		gr.outputFile(aim);
		while (flag != 0 && count<=countWalk){
			aim += (gr.StrWalkArray[count]+" ");
			count++;
			gr.outputFile(aim);
			System.out.println("�����Ľڵ��Ѿ�д��output.txt�ı�");
			System.out.println("�����������������'1'�������������������'0'�������룺");
			flag=sc.nextInt();
		}
		System.out.println("������߽���");
		
			
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