package project2;

import java.util.*;

public class LinkShortener {

 private Map<String, String> urlMap;
 private Map<String, String> reverseMap;
 private final String domain = "http://short.ly/"; 

 public LinkShortener() {
     urlMap = new HashMap<>();
     reverseMap = new HashMap<>();
 }

 public String shortenUrl(String longUrl) {
     if (reverseMap.containsKey(longUrl)) {
         return domain + reverseMap.get(longUrl);
     }

     String shortUrl = generateShortUrl(longUrl);
     urlMap.put(shortUrl, longUrl);
     reverseMap.put(longUrl, shortUrl);
     return domain + shortUrl;
 }

 public String expandUrl(String shortUrl) {
     String key = shortUrl.replace(domain, "");
     return urlMap.getOrDefault(key, "Error: URL not found!");
 }

 private String generateShortUrl(String longUrl) {
     String uniqueId = Integer.toHexString(longUrl.hashCode());
     while (urlMap.containsKey(uniqueId)) {
         uniqueId += "1"; 
     }
     return uniqueId;
 }

 public static void main(String[] args) {
     LinkShortener shortener = new LinkShortener();
     Scanner scanner = new Scanner(System.in);

     System.out.println("Welcome to the Link Shortener Application!");
     while (true) {
         System.out.println("\nChoose an option:");
         System.out.println("1. Shorten a URL");
         System.out.println("2. Expand a URL");
         System.out.println("3. Exit");

         int choice = scanner.nextInt();
         scanner.nextLine(); 

         switch (choice) {
             case 1:
                 System.out.print("Enter the long URL: ");
                 String longUrl = scanner.nextLine();
                 String shortUrl = shortener.shortenUrl(longUrl);
                 System.out.println("Shortened URL: " + shortUrl);
                 break;

             case 2:
                 System.out.print("Enter the short URL: ");
                 String shortInput = scanner.nextLine();
                 String expandedUrl = shortener.expandUrl(shortInput);
                 System.out.println("Expanded URL: " + expandedUrl);
                 break;

             case 3:
                 System.out.println("Exiting the application. Goodbye!");
                 scanner.close();
                 System.exit(0);

             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }
}
