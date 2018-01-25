package stevens.cs562.EMFQuery.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import stevens.cs562.EMFQuery.Business.EMFVariables;
import stevens.cs562.EMFQuery.Business.GenerateFile;


public class FileInput {
	private String fileName;

	public BufferedReader readFile() {
		String path = System.getProperty("user.dir");
		BufferedReader bufferedReader = null ;
		Scanner scan = new Scanner(System.in);
		int flag = 1;
		do{
			System.out.println("Enter the name of your input file:[the default path is the src\\](example: example) ");
			fileName = scan.nextLine();
			//this path is for linux and Mac
			path = path+"/src/"+fileName;
			// this path is for Windows
			///path = path+"\\src\\"+fileName;   
			File file = new File(path);
			try {
				
				String encoding = "UTF-8";
				if (file.isFile() && file.exists()) { // the file is exit
					// Reader reader = null;
					InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
					bufferedReader = new BufferedReader(read);
				} else {
					
					System.out.println(path);
					System.err.println("The file is no exist!!");
					flag = 0;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}while(flag==0);

		return bufferedReader;
	}
	
	public String writeFile(ConnectDB connectdb, EMFVariables varibles) throws IOException{
		FileWriter fw = null;
		// initial the output file name and package name
		String className = "Output.java";
		String packageName = "stevens.cs562.EMFQuery.Output";
		GenerateFile generate = new GenerateFile();
		// set attributes of output file 
		generate.setFileName(className);
		generate.setPackageName(packageName);
		generate.setTableName(connectdb.getTableStructure());
		generate.setVaribles(varibles);
		// initial the importList
		ArrayList<String> importList = new ArrayList<String>();
		importList.add("java.sql.Statement");
		importList.add("java.sql.DriverManager");
		importList.add("java.sql.Connection");
		importList.add("java.util.List");
		importList.add("java.util.ArrayList");
		importList.add("java.util.HashMap");
		importList.add("java.util.Iterator");
		importList.add("java.sql.ResultSet");
		// set importList attribute of output file 
		generate.setImportList(importList);
		try{
            
            fw = new FileWriter("src//stevens//cs562//EMFQuery//Output//"+className);
            // write it to the disk
            fw.write(generate.write(connectdb));

            
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fw != null){
                fw.close();
            }
        }
		return className;
    }
	
	public EMFVariables load() throws IOException {
		boolean flag = false;
		EMFVariables varibles = new EMFVariables();
		BufferedReader bf = readFile();
		String selectAttribute = bf.readLine();
		//#SELECT ATTRIBUTE(S)
		String attribute = bf.readLine();
		String[] attributes = attribute.split(",");
		ArrayList<String> varibles_s = new ArrayList<String>();
		for (int i = 0; i < attributes.length; i++) {
			String att = attributes[i].trim();
			varibles_s.add(att);
		}
		//System.out.println(varibles_s);
		
		
		//#NUMBER OF GROUPING VARIABLES(n)
		String numberCluse = bf.readLine();
		//System.out.println(numberCluse);
		String number = bf.readLine();
		//System.out.println(number);
		
		
		//#GROUPING ATTRIBUTES(V)
		String group = bf.readLine();
		//System.out.println(group);
		String groupAttr = bf.readLine();
		String[] groupAttrs = groupAttr.split(",");
		ArrayList<String> varibles_v = new ArrayList<String>();
		for (int i = 0; i < groupAttrs.length; i++) {
			String att = groupAttrs[i].trim();
			varibles_v.add(att);
		}
		//System.out.println(varibles_v);
		//#F-VECT([F])
		String f = bf.readLine();
		//System.out.println(f);
		String fv = bf.readLine();
		String[] fvs = fv.split(",");
		ArrayList<String> varibles_f = new ArrayList<String>();
		for (int i = 0; i < fvs.length; i++) {
			String vaf = fvs[i].trim();
			varibles_f.add(vaf);
		}
		//System.out.println(varibles_f);
		//#SELECT CONDITION-VECT([sigma])  number
		String sigmaClause = bf.readLine();
		//System.out.println(sigmaClause);
		
		ArrayList<ArrayList> varibles_sigmas = new ArrayList<ArrayList>();
		
		for (int i = 0; i <  Integer.parseInt(number); i++) {
			String sigma = bf.readLine();
			String[] sigmas = sigma.split("and");
			ArrayList<String> varibles_sigma = new ArrayList<String>();
			for (int j = 0; j < sigmas.length; j++) {
				String sigmaValue = sigmas[j].trim();
				varibles_sigma.add(sigmaValue);
			}
			varibles_sigmas.add(varibles_sigma);
		}
		
		//System.out.println(varibles_sigmas);
		//#HAVING CONDITION(G)
		String HavingCluse = bf.readLine();
		//System.out.println(HavingCluse);
		String HavingCondition = bf.readLine();
		ArrayList<String> varibles_g = new ArrayList<String>();
		if(!HavingCondition.startsWith("#")){
			String[] havingConditions = HavingCondition.split("and");
			
			for (int i = 0; i < havingConditions.length; i++) {
				String att = havingConditions[i].trim();
				varibles_g.add(att);
			}
			//System.out.println(varibles_g);
		}else{
			flag = true;
		}
	
		//#WHERE CONDITION
		String where ;
		if(!flag){
			String WHEREClause = bf.readLine();
			//System.out.println(WHEREClause);
			 where = bf.readLine();
			//System.out.println(where);
		}else{
			String WHEREClause = HavingCondition;
			//System.out.println(WHEREClause);
			 where = bf.readLine();
			//System.out.println(where);
		}
		varibles.setN(Integer.parseInt(number));
		varibles.setS(varibles_s);
		varibles.setV(varibles_v);
		varibles.setF(varibles_f);
		varibles.setSigma(varibles_sigmas);
		varibles.setG(varibles_g);
		varibles.setWhere(where);
		
		//System.out.println(varibles);
		
		return varibles;
		
	}
	
	
}
