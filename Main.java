import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int numRecipes = 0;
        String recName, recLink;
        ArrayList<Recipe> recSearch = new ArrayList<Recipe>();
        File recDirectory = new File("C:\\Users\\kpopc\\Desktop\\School\\Hackathon\\Recipes Folder");
        File[] recList = recDirectory.listFiles();
        for (File file : recList) {
            recSearch.add(new Recipe(file));
        }
        numRecipes = recSearch.size();
        FileWriter fw = new FileWriter("C:\\Users\\kpopc\\Desktop\\School\\Hackathon\\recipelist.json");

        //System.out.println("Testing recipe database...");
        fw.write("{\n" + "\t\"recipes\": [\n");
        for(int i = 0 ; i < numRecipes ; i++)
        {
            //System.out.printf("Recipe: %s\nLink: %s\n", recSearch.get(i).getRecName(), recSearch.get(i).getRecLink());
            recSearch.get(i).checkRequiresOven();
            recSearch.get(i).checkRequiresStove();
            recSearch.get(i).checkRequiresSlowCooker();
            recSearch.get(i).checkRequiresInstantPot();
            recSearch.get(i).checkRequiresMicrowave();
            recSearch.get(i).checkHasSharpObjects();
            recSearch.get(i).checkIfOneDish();
            /* //Displaying stats
            System.out.printf("Requires Oven? %b\n", recSearch.get(i).getOvenRequirement());
            System.out.printf("Requires Stove? %b\n", recSearch.get(i).getStoveRequirement());
            System.out.printf("Requires Slow Cooker? %b\n", recSearch.get(i).getSlowRequirement());
            System.out.printf("Requires Instant Pot? %b\n", recSearch.get(i).getIPRequirement());
            System.out.printf("Requires Microwave? %b\n", recSearch.get(i).getMicroRequirement());
            System.out.printf("Has Sharp Objects? %b\n", recSearch.get(i).getIfSharp());
            System.out.printf("Is a \"One-Dish\" Recipe? %b\n\n", recSearch.get(i).getIfOneDish());

            fw.write(recSearch.get(i).getRecName() + "|" + recSearch.get(i).getRecLink() + "||" + recSearch.get(i).getOvenRequirement() + "|" + recSearch.get(i).getStoveRequirement() + "|"
                    + recSearch.get(i).getSlowRequirement() + "|" + recSearch.get(i).getIPRequirement() + "|" + recSearch.get(i).getMicroRequirement() + "||"
                    + recSearch.get(i).getIfSharp() + "||" + recSearch.get(i).getIfOneDish());
            fw.write("\n");
             */
            fw.write("\t\t{\n");
            fw.write("\t\t \"name\": \"" + recSearch.get(i).getRecName() + "\",\n");
            fw.write("\t\t \"link\": \"" + recSearch.get(i).getRecLink() + "\",\n");
            fw.write("\t\t \"oven\": \"" + recSearch.get(i).getOvenRequirement() + "\",\n");
            fw.write("\t\t \"stove\": \"" + recSearch.get(i).getStoveRequirement() + "\",\n");
            fw.write("\t\t \"slowcooker\": \"" + recSearch.get(i).getSlowRequirement() + "\",\n");
            fw.write("\t\t \"instantpot\": \"" + recSearch.get(i).getIPRequirement() + "\",\n");
            fw.write("\t\t \"microwave\": \"" + recSearch.get(i).getMicroRequirement() + "\",\n");
            fw.write("\t\t \"sharp\": \"" + recSearch.get(i).getIfSharp() + "\",\n");
            fw.write("\t\t \"onedish\": \"" + recSearch.get(i).getIfOneDish() + "\"\n");
            if (i != numRecipes - 1)
            {
                fw.write("\t\t},\n");
            }
            else
            {
                fw.write("\t\t}");
            }
        }
        fw.write("\n\t]\n}");
        fw.close();
        System.out.printf("Total Recipes: %d\n\n", numRecipes);

        /* //Testing predetermined search queries
        ArrayList<Recipe> searchResult;
        System.out.println("Displaying recipes that AT MOST use an oven:");
        searchResult = new ArrayList<Recipe>(recSearch);
        searchResult.removeIf(j -> (j.getSlowRequirement() || j.getStoveRequirement() || j.getIPRequirement() || j.getMicroRequirement()));
        for(int i = 0 ; i < searchResult.size() ; i++)
        {
            System.out.printf("Recipe: %s\nLink: %s\n", searchResult.get(i).getRecName(), searchResult.get(i).getRecLink());
        }
        System.out.println("\n\nDisplaying recipes that AT MOST use a stove:");
        searchResult = new ArrayList<Recipe>(recSearch);
        searchResult.removeIf(j -> (j.getSlowRequirement() || j.getOvenRequirement() || j.getIPRequirement() || j.getMicroRequirement()));
        for(int i = 0 ; i < searchResult.size() ; i++)
        {
            System.out.printf("Recipe: %s\nLink: %s\n", searchResult.get(i).getRecName(), searchResult.get(i).getRecLink());
        }
        System.out.println("\n\nDisplaying recipes that AT MOST use an oven, stove, OR slow cooker (Safety Filer ON):");
        searchResult = new ArrayList<Recipe>(recSearch);
        searchResult.removeIf(j -> (j.getIPRequirement() || j.getMicroRequirement() || j.getIfSharp()));
        for(int i = 0 ; i < searchResult.size() ; i++)
        {
            System.out.printf("Recipe: %s\nLink: %s\n", searchResult.get(i).getRecName(), searchResult.get(i).getRecLink());
        }
         */
    }
}
