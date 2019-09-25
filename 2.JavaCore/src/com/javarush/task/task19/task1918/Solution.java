package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class Solution {
    public static void main(String[] args) {
        if(args.length<1) {
            System.out.println("Invalid arguments");
            return;
        }
        String tag = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = reader.readLine();
            reader.close();
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String content = "";
            String str;
            while ((str = input.readLine()) != null)
                content = content + str;
            input.close();
            // parse <tag>...</tag>
            content = content.replaceAll("\n","").replaceAll("\r","");
            //System.out.println("CONTENT: "+content);
//            String openTag = "<"+tag;
//            String closeTag="</"+tag+">";
//            // opening indexes
//            List<Integer> startIndex = new ArrayList<Integer>();
//            List<Integer> endIndex = new ArrayList<Integer>();
//            // start index
//            int i  = content.indexOf(openTag);
//            while(i>=0) {
//                startIndex.add(i);
//                i = content.indexOf(openTag, i+1);
//            }
//            i = content.indexOf(closeTag);
//            while(i>=0) {
//                endIndex.add(i);
//                i = content.indexOf(closeTag,i+1);
//            }
//            //System.out.println("ST:"+startIndex.size()+",E:"+endIndex.size());
//            for(i=0; i<startIndex.size()-1; i++) {
//                for(int j=0; j<endIndex.size(); j++) {
//                    if(endIndex.get(j)>startIndex.get(i) && endIndex.get(j)<startIndex.get(i+1)) {
//                        System.out.println(content.substring(startIndex.get(i),endIndex.get(j))+closeTag);
//                        break;
//                    }
//                }
//            }
            Document doc = Jsoup.parse(content,"",Parser.xmlParser());
            Elements elements = doc.select(tag);
            for(Element e : elements) {
                System.out.println(e.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
