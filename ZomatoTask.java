import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ZomatoTask {
    
    
    static String rulerName="None";
    static String rulerAlias="None";
    static String captured="";
    static boolean isRuler=false;
    static int conquer=0;
    static List<String> list=new ArrayList<String>();

    
    public final static String LAND="panda";
    public final static String WATER="octopus";
    public final static String ICE="mammoth";
    public final static String AIR="owl";
    public final static String FIRE="dragon";
    
    
    public static void main(String[] args)throws Exception {
            
            displayInstructions();
            displayResults();                           
            int count=getInputMessageAndCount();        
            
            if(count>=3){                               
               extractMessage();
               displayResults();
            }else{
                System.out.println("The king conquered only "+count+" kingdoms.\n Need atleast 3 kingdom to be a RULER");
            }
    } 
    
    
    public static void displayResults(){
        System.out.println("\nWho is ruler of Southeros?\nOutput: "+rulerName);
        System.out.println("Alias of Ruler?\nOutput: "+rulerAlias);
        System.out.println();
    }
    
    
    public static int getInputMessageAndCount() {
       Scanner sc=new Scanner(System.in);
            String input;
            int count=0;
            System.out.print("Input Messages to kingdoms from king shan\nInput : ");
            while ((input = sc.nextLine( )).length( ) > 0 )     {
                System.out.print("Input : ");
               if(input!=null&& input!=" "&& !input.isEmpty())
               {     list.add(input.toLowerCase().trim());
                     ++count;
               }        
            }
            sc.close();
            return count;
    }

    
    public static void extractMessage() {
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        String message = "",kingdom,animal;
        for(String input:list){
            kingdom=input.substring(0,input.indexOf(",")).trim();
            message=messageQuotes(input).trim();
            animal=getAnimal(kingdom);
            if(animal!=null){
                if(isContainsAnimal(message,animal)){
                    conquer++;
                    captured+=kingdom+",";
                }
            }
            else{
                System.out.println("Wrong Input.. !");
                System.exit(0);
            }
        }
        if(conquer>=3){
            rulerName="King Shan";
            rulerAlias=captured.substring(0,captured.length()-1);        
        }
    }
    
    
    public static String getAnimal(String kingdom){
        switch(kingdom){
            case "air":return AIR;
            case "fire":return FIRE;
            case "ice":return ICE;
            case "water":return WATER;
            case "land":return LAND;
            default: return null;
        }
    }
    
    
    public static String messageQuotes(String input){
        String message="";
         Pattern p = Pattern.compile("\"([^\"]*)\"");
         Matcher m = p.matcher(input);
            while (m.find()) {
                message+=m.group(1);
             }
         return message;
    }   
    
    public static boolean isContainsAnimal(String message,String animal) {
           
            
            Map<Character,Integer> mapAnimal=new HashMap<Character,Integer>();
            Map<Character,Integer> mapMessage=new HashMap<Character,Integer>();
            Map<Character,Boolean> mapResult=new HashMap<Character,Boolean>();
            
            
            mapAnimal=characterCount(animal);
            mapMessage=characterCount(message);
       
            boolean temp=false;
            for(Map.Entry i:mapAnimal.entrySet()){
               for(Map.Entry j:mapMessage.entrySet() ){
                   if(mapAnimal.containsKey(j.getKey())){
                      
                        if(((char)i.getKey()==(char)j.getKey())&&((int)j.getValue()>=(int)i.getValue())){
                            mapResult.put((char)i.getKey(), true);
                           
                        }
                        else{
                            if(!mapResult.containsKey((char)i.getKey()))
                               mapResult.put((char)i.getKey(),false);
                        }
                   }
               }
            }
            
            if(!mapResult.isEmpty()){
               
                Map.Entry entry=mapResult.entrySet().iterator().next();
                temp=(boolean) entry.getValue();

                for(Map.Entry i:mapResult.entrySet()){
                    temp=temp&&(boolean)i.getValue();
                }
            }
            return temp;
    }
   
    
    public static Map<Character,Integer> characterCount(String inputString) 
    { 
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>(); 
        char[] strArray = inputString.toCharArray();
        for (char c : strArray) { 
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1); 
            } 
            else {
                charCountMap.put(c, 1); 
            } 
        } 
         return charCountMap;
    } 

    public static void displayInstructions() {
        System.out.println("Instructions:\n * To stop the input - press enter key with empty line\n * Provide the message with double quotes\n________________________________________________________________");
    }
}
