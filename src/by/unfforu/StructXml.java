package by.unfforu;

import java.io.IOException;
import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * This class have method sort and print in console
 */
public class StructXml
{
    /**
     * Structur need for memores product
     */
    private static ArrayList<NodeList> List = new ArrayList<NodeList>();
    /**
     * Stuctur need for memores name product
     */
    private static ArrayList<String> ListName = new ArrayList<String>();

    /**
     * Matrix for sort List
     */
    private static int[][] Mat;

    /**
     * Main method
     * @param path
     * @param findNameProduct
     */
    public static void Read(String path, String findNameProduct) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(path);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            // Просматриваем все подэлементы корневого - т.е. продукты
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    ListName.add(book.getNodeName());
                    NodeList bookProps = book.getChildNodes();
                    List.add(bookProps);
                }
            }
            BuildList(List, findNameProduct);


        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (
                SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (
                IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    /**
     * Method fund name product and print in console sort by price
     * @param paramList
     * @param nameProduct
     */
    private static void BuildList(ArrayList<NodeList> paramList, String nameProduct) {
        int j=1;
        int MatElement = 0;
        Mat = new int[100][2];
        /**
         * Cread List
         */
        for (int elementList = 0; elementList < paramList.size(); elementList++) {
            NodeList bookProps = paramList.get(elementList);
            if (ListName.get(elementList) == nameProduct) {
                Node bookProp = bookProps.item(j);
                // Если нода не текст, то это один из параметров книги - печатаем
                if (bookProp.getNodeType() != Node.TEXT_NODE) {
                    String rew = bookProp.getFirstChild().getTextContent();
                    int NambersNode = StringToInt(rew.intern());
                    Mat[MatElement][0] = NambersNode;
                    Mat[MatElement][1] = elementList;
                    MatElement = MatElement+1;
                }
            }
        }
        Mat = MatSortLowToHigh(Mat);

        /**
         * Print no sort List
         */
        for (int elementList = 0; elementList < paramList.size(); elementList++) {
            NodeList bookProps = paramList.get(elementList);
            if (ListName.get(elementList) == nameProduct) {
                System.out.println(ListName.get(elementList));
                for(j = 0; j < bookProps.getLength(); j++) {
                    //  System.out.println(bookProps.getNodeName());
                    Node bookProp = bookProps.item(j);
                    // Если нода не текст, то это один из параметров книги - печатаем
                    if (bookProp.getNodeType() != Node.TEXT_NODE) {
                        System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                    }
                }
                System.out.println("===========>>>>");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int i=0;
        /**
         * Print sort List
         */
        while(Mat[i][0]!=0)
        {
            NodeList bookProps = paramList.get(Mat[i][1]);
            if (ListName.get(Mat[i][1]) == nameProduct) {
                System.out.println(ListName.get(Mat[i][1]));
                for(j = 0; j < bookProps.getLength(); j++) {
                    //  System.out.println(bookProps.getNodeName());
                    Node bookProp = bookProps.item(j);
                    // Если нода не текст, то это один из параметров книги - печатаем
                    if (bookProp.getNodeType() != Node.TEXT_NODE) {
                        System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                    }
                }
                System.out.println("===========>>>>");
            }
            i=i+1;
        }

    }

    /**
     * Transformation string in integer
     * @param str2
     * @return
     */
    private static int StringToInt(String str2){
        int get =0;
        try {
            get = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }
        return get;
    }


    /**
     * Sort matrix
     * @param MatElement
     * @return
     */
    private static int[][] MatSortLowToHigh(int[][] MatElement)
    {
        boolean flag = false;
        int matSort;
        while(flag == false) {
            flag  = true;
            for (int i = 0; i < MatElement.length-1; i++) {
                if((MatElement[i][0]>MatElement[i+1][0])&&(MatElement[i+1][0]!=0))
                {
                    matSort = MatElement[i][0];
                    MatElement[i][0]=MatElement[i+1][0];
                    MatElement[i+1][0] = matSort;
                    matSort = MatElement[i][1];
                    MatElement[i][1]=MatElement[i+1][1];
                    MatElement[i+1][1]= matSort;
                    flag = false;
                }
            }
        }
        return MatElement;
    }

}
