import java.io.*;
import java.util.*;

public class JavaBuilder{
	public static void main(String[] args)throws Exception{
		
		String line, splitter[], var, upperVar, type;	//variable/ Arraylist declaration 
		ArrayList<String> variables=new ArrayList<>();	//
		ArrayList<String> types=new ArrayList<>();		//
		
		Scanner s = new Scanner(new File(args[0]));	//open input file provided in commandline
		String className=s.nextLine();
		FileWriter w=new FileWriter(new File(className + ".java"));				//open a file with class name for writing 
		
		w.write("public class " + className + "{\n\n");		//writes variables provided in input file//
		while(s.hasNextLine()){								//
			line=s.nextLine();								//
			w.write("\t" + line + ";\n");					//
			splitter=line.split(" ");						//
			variables.add(splitter[2]);						//saves variable names
			types.add(splitter[1]);							//saves variables types
		}
		
		w.write("\n\tpublic " + className + "(){}\n\n\tpublic " + className + "(");	//creates contructors//
		for(int i=0; i<variables.size(); i++){										//
			w.write(types.get(i) + " " + variables.get(i));							//writing parameters
			if(i<variables.size()-1)												//
				w.write(", ");														//
		}																			//
		w.write("){\n");															//
		for(String temp: variables){												//
			w.write("\t\tthis." + temp + "=" + temp + ";\n");						//writing assignment statements
		}																			//
		w.write("\t}\n\n");															//
		
		for(int i=0; i<variables.size(); i++){																					//writing getters and setters//
			var=variables.get(i);																								//
			upperVar=var.substring(0,1).toUpperCase() + var.substring(1);														//
			type=types.get(i);																									//
			w.write("\tpublic void set" + upperVar + "(" + type + " " + var + "){\n\t\tthis." + var + "=" + var + ";\n\t}\n\n");//setter
			w.write("\tpublic " + type + " get" + upperVar + "(){\n\t\treturn " + var + ";\n\t}\n\n");							//getter
		}
		
		w.write("}");	//closes class
			
		w.close();
		s.close();
	}
}