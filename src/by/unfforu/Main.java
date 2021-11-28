package by.unfforu;

public class Main
{
    /**
     * Main
     * @paramargs
     */
    public static void main(String[] args) {
        StructXml ListProduct = new StructXml();

        String Path = "appliances_db.xml";

        ListProduct.Read(Path,"Teapot");

    }
}
