import java.io.*;
import java.util.Scanner;
public class Recipe {
    private boolean reqOven, reqStove, reqSlow, reqIP, reqMicro, hasSharp, oneDish;
    private String recName, recLink;
    int pos;
    private File recContent;
    private Scanner sc = null;
    private StringBuffer sb = new StringBuffer();

    public Recipe(File file) {
        recName = file.getName();
        pos = recName.lastIndexOf(".");
        if(pos > 0 && pos < (recName.length()-1))
        {
            recName = recName.substring(0, pos);
        }
        recContent = file;
        reqIP = false;
        reqMicro = false;
        reqOven = false;
        reqSlow = false;
        reqStove = false;
        hasSharp = false;
        oneDish = false;
    }
    public boolean getOvenRequirement() {
        return reqOven;
    }
    public boolean getStoveRequirement() {
        return reqStove;
    }
    public boolean getSlowRequirement() {
        return reqSlow;
    }
    public boolean getIPRequirement() {
        return reqIP;
    }
    public boolean getMicroRequirement() {
        return reqMicro;
    }
    public boolean getIfSharp() {
        return hasSharp;
    }
    public boolean getIfOneDish() {
        return oneDish;
    }
    public String getRecName() {
        return recName;
    }
    public String getRecLink() throws FileNotFoundException {
        sc = new Scanner(recContent);
        String link = "";
        while(sc.hasNextLine())
        {
            link = sc.nextLine();
        }
        return link;
    }
    public void showRecipe() throws FileNotFoundException {
        sc = new Scanner(recContent);
        while(sc.hasNextLine())
        {
            System.out.println(sc.nextLine());
        }
    }
    public void checkRequiresOven() throws FileNotFoundException {
        String[] keywords = {"oven" , "preheat", "broil"};
        for(int i = 0 ; i < 3 && !reqOven ; i++)
        {
            sc = new Scanner(recContent);
            while(sc.hasNextLine() && !reqOven)
            {
                String s = sc.nextLine().toLowerCase();
                if(s.contains(keywords[i]))
                {
                    reqOven = true;
                    break;
                }
            }
        }
    }

    public void checkRequiresStove() throws FileNotFoundException {
        String[] keywords = {"stove" , "boil" , "pan", "skillet" , "simmer" , "sauté" , "high heat" , "medium heat"};
        String[] ignore = {"one-pot or pan" , "sheet pan" , "sheet-pan"};
        for(int i = 0 ; i < 8 && !reqStove ; i++)
        {
            sc = new Scanner(recContent);
            while(sc.hasNextLine() && !reqStove)
            {
                String s = sc.nextLine().toLowerCase();
                if(s.contains(keywords[i]) && !((s.contains(ignore[0])) || s.contains(ignore[1]) || s.contains(ignore[2])))
                {
                    reqStove = true;
                    break;
                }
            }
        }
    }

    public void checkRequiresSlowCooker() throws FileNotFoundException {
        String[] keywords = {"crock pot" , "crockpot" , "slow cooker" , "slowcooker"};
        for(int i = 0 ; i < 4 && !reqSlow ; i++)
        {
            sc = new Scanner(recContent);
            while(sc.hasNextLine() && !reqSlow)
            {
                String s = sc.nextLine().toLowerCase();
                if(s.contains(keywords[i]))
                {
                    reqSlow = true;
                    reqStove = false; //crockpot = no stove
                    oneDish = true; //crockpot recipes are basically always just one dish
                    break;
                }
            }
        }
    }
    public void checkRequiresInstantPot() throws FileNotFoundException {
        String keyword = "instant pot";
        sc = new Scanner(recContent);
        while(sc.hasNextLine() && !reqIP)
        {
            String s = sc.nextLine().toLowerCase();
            if(s.contains(keyword))
            {
                reqIP = true;
                reqStove = false; //you won't need a stove if you're using an instant pot
                oneDish = true; //Instant pots are also a one dish thing
                break;
            }
        }
    }
    public void checkRequiresMicrowave() throws FileNotFoundException {
        String keyword = "microwave";
        sc = new Scanner(recContent);
        while(sc.hasNextLine() && !reqMicro)
        {
            String s = sc.nextLine().toLowerCase();
            if(s.contains(keyword))
            {
                reqMicro = true;
                break;
            }
        }
    }
    public void checkHasSharpObjects() throws FileNotFoundException {
        String[] keywords = {"chop" , "knife" , "slice" , "dice" , "mince" , "cubed"};
        String[] ignore = {"minced" , "diced"};
        for(int i = 0 ; i < 6 && !hasSharp ; i++)
        {
            sc = new Scanner(recContent);
            while(sc.hasNextLine() && !hasSharp)
            {
                String s = sc.nextLine().toLowerCase();
                if(s.contains(keywords[i]) && !((s.contains(ignore[0])) || s.contains(ignore[1])))
                {
                    hasSharp = true;
                    break;
                }
            }
        }
    }
    public void checkIfOneDish() throws FileNotFoundException {
        String[] keywords = {"one pot" , "one-pot" , "1-pot" , "one pan" , "one-pan" , "one dish" , "one-dish"};
        for(int i = 0 ; i < 7 && !oneDish ; i++)
        {
            sc = new Scanner(recContent);
            while(sc.hasNextLine() && !oneDish)
            {
                String s = sc.nextLine().toLowerCase();
                if(s.contains(keywords[i]))
                {
                    oneDish = true;
                    break;
                }
            }
        }
    }
}
