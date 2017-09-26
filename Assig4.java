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

//Phase 3

/*
 *  DataMatrix Class
 *  scans a copy of image and displays image and translated text to console
 */
class DataMatrix implements BarcodeIO 
{
   // Fields
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;

   // Constructor (no argument)
   public DataMatrix()
   {
      image = new BarcodeImage();
      text = "";
      actualWidth = 0;
      actualHeight = 0;
   }
   
   // Constructor
   public DataMatrix(BarcodeImage image)
   {
      scan(image);
      text = "";
   }
   
   // Constructor
   public DataMatrix(String text)
   {
      readText(text);
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
   }
   
   public boolean scan(BarcodeImage bc)
   {
      try
      {
         this.image = bc.clone();
         cleanImage();
         actualWidth = computeSignalWidth();
         actualHeight = computeSignalHeight();
         return true;
      } catch (CloneNotSupportedException e)
      {
         // do nothing
      }
      return false; 
   }
   
   private void cleanImage()
   {
      moveImageToLowerLeft();
   }
   
   // Method that moves the image to the lower left corner
   private void moveImageToLowerLeft()
   {
      shiftImageLeft(horizontalScanner());
      shiftImageDown(verticalScanner());
   }
   
   // Shifts the image all the way down
   private void shiftImageDown(int offset)
   {
      if(offset == BarcodeImage.MAX_HEIGHT - 1)
      {
         return;
      }
      for(int col = 0; col < BarcodeImage.MAX_WIDTH; col++)
      {
         for(int row = offset; row >= 0; row++)
         {
            image.setPixel(row + (BarcodeImage.MAX_HEIGHT - offset - 1), col, image.getPixel(row, col));
            image.setPixel(row, col, false);
         }
      }
   }
   
   // Shifts the image all the way to the left
   private void shiftImageLeft(int offset)
   {
      if(offset == 0)
      {
         return;
      }
      for(int row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         for(int col = offset; col < BarcodeImage.MAX_WIDTH; col++)
         {
            image.setPixel(row, (col - offset), image.getPixel(row, col));
            image.setPixel(row, col, false);
         }
      }
   }
   
   // Scans the image horizontally
   private int horizontalScanner()
   {
      for(int col = 0; col < BarcodeImage.MAX_WIDTH; col++)
      {
         for(int row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
         {
            if(image.getPixel(row, col))
            {
               return col;
            }
         }
      }
      throw new RuntimeException("ERROR SCANNING");
   }
   
   // Scans image vertically
   private int verticalScanner()
   {
      for(int row = 0; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         for(int col = 0; col < BarcodeImage.MAX_WIDTH; col++)
         {
            if(image.getPixel(row, col))
            {
               return row;
            }
         }
      }
      throw new RuntimeException("ERROR SCANNING");
   }
   
   // Accessor method to get the actual width
   public int getActualWidth()
   {
      return actualWidth;
   }
   
   // Accessor method to get the actual height
   public int getActualHeight()
   {
      return actualHeight;
   }
   
   private int computeSignalWidth()
   {
      
   }
   
   private int computeSignalHeight()
   {
      
   }
   
   // Encodes a string into a barcode image
   public boolean readText(String text)
   {
      if(text.length() < barcodeImage.MAX_WIDTH)
      {
         this.text = text + "*";
         return true;
      }
      return false;
   }
   
   public boolean generateImageFromText()
   {
      
   }
   public boolean translateImageToText()
   {
      
   }
   public void displayTextToConsole()
   {
      
   }
   public void displayImageToConsole()
   {
      
   }
		
}
