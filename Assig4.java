/*
 *  Joseph Cortez
 *  Lyndsay Hackett
 *  Mokhlis Awad
 *  Ahdia Fuller
 *  
 *  Assig 4 Class: An Interface and set of classes to print out barcodes and secret messages. 
 *                 
 */

public class Assig4
{

   public static void main(String[] args)
   {
      // first barcode to display and decode
      String[] sImageIn =
         {
            "                                               ",
            "                                               ",
            "                                               ",
            "     * * * * * * * * * * * * * * * * * * * * * ",
            "     *                                       * ",
            "     ****** **** ****** ******* ** *** *****   ",
            "     *     *    ****************************** ",
            "     * **    * *        **  *    * * *   *     ",
            "     *   *    *  *****    *   * *   *  **  *** ",
            "     *  **     * *** **   **  *    **  ***  *  ",
            "     ***  * **   **  *   ****    *  *  ** * ** ",
            "     *****  ***  *  * *   ** ** **  *   * *    ",
            "     ***************************************** ",  
            "                                               ",
            "                                               ",
            "                                               "

         };      
               
            
        //second barcode to display and decode
        String[] sImageIn_2 =
         {
               "                                          ",
               "                                          ",
               "* * * * * * * * * * * * * * * * * * *     ",
               "*                                    *    ",
               "**** *** **   ***** ****   *********      ",
               "* ************ ************ **********    ",
               "** *      *    *  * * *         * *       ",
               "***   *  *           * **    *      **    ",
               "* ** * *  *   * * * **  *   ***   ***     ",
               "* *           **    *****  *   **   **    ",
               "****  *  * *  * **  ** *   ** *  * *      ",
               "**************************************    ",
               "                                          ",
               "                                          ",
               "                                          ",
               "                                          "

         };
        
        // creates BarcodeImage object to hold first barcode and sends to DataMatrix for decoding        
        BarcodeImage bc = new BarcodeImage(sImageIn);
        DataMatrix dm = new DataMatrix(bc);
        
        // first secret message
        dm.translateImageToText();
        dm.displayTextToConsole();
        dm.displayImageToConsole();
         
        // second secret message
        bc = new BarcodeImage(sImageIn_2);
        dm.scan(bc);
        dm.translateImageToText();
        dm.displayTextToConsole();
        dm.displayImageToConsole();
   }

}

// defines I/O and basic methods of DataMatrix class 
interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);
   public boolean readText(String text);
   public boolean generateImageFromText();
   public boolean translateImageToText();
   public void displayTextToConsole();
   public void displayImageToConsole();
}

// contains methods to store, modify, and retrieve data in a 2D image
class BarcodeImage implements Cloneable
{
   
}

// scans a copy of image and displays image and translated text to console
class DataMatrix implements BarcodeIO
{
   
}




//Phase 3

/*
 *  DataMatrix Class
 */
public class DataMatrix implements BarcodeIO {

	// Fields
	public static final char BLACK_CHAR = '*';
	public static final char WHITE_CHAR = ' ';
	private BarcodeImage image;
	private String text;
	private int actualWidth;
	private int actualHeight;

	// Constructor (no argument)
	public DataMatrix() {
		scan(new BarcodeImage());
		readText("");
		actualWidth = 0;
		actualHeight = 0;
	}
