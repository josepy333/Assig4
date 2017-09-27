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

/*
 * BarcodeImage Class
 * Contains methods to store, modify, and retrieve data in a 2D image
 */
class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   
   private boolean[][] image_data;
   
   
   /* Default Constructor
    * Creates a 2D array and inserts empty data into it (false).
    */
   public BarcodeImage() 
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         for (int k = 0; k < MAX_WIDTH; k++)
         {
            image_data[i][k] = false;
         }
      }
   }
   
   
   /* Non-Default Constructor 
    * Accepts a 1D array of strings as an argument and converts it into a 2D array of bools.
    */
   public BarcodeImage(String[] str_data)
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      // Validate the length of str_data input
      if (checkSize(str_data) == true) 
      {
         for (int i = 0; i < str_data.length; i++)
         {
            for (int k = 0; k < str_data[i].length(); k++)
            {
               if (str_data[i].charAt(k) == '*')
               {
                  image_data[MAX_HEIGHT - str_data.length + i][k] = true;
               }
               else
               {
                  image_data[MAX_HEIGHT - str_data.length + i][k] = false;
               }
            }
         }
      }
      
      // checkSize failed
      else 
      {
         System.out.println("The supplied data was not valid. Please try again.");
      }
      
   }
   
   
   /*
    * Accessor - getPixel
    * Validates the values for row and column to ensure they are within
    * bounds of 0 and MAX_HEIGHT and MAX_WIDTH.
    * Returns the pixel at [row][column] if valid.
    * Returns false if not valid.
    */
   public boolean getPixel(int row, int col)
   {
      if (row < 0 || col < 0)
      {
         return false;
      }
      if (row > MAX_HEIGHT || col > MAX_WIDTH)
      {
         return false;
      }
      
      return image_data[row][col];
   }
   
   
   /*
    * Mutator - setPixel
    * Validates the values for row and column to ensure they are within
    * bounds of 0 and MAX_HEIGHT and MAX_WIDTH
    * Returns true and sets the pixel at [row][column] if valid
    * Returns false if not valid.
    */
   public boolean setPixel(int row, int col, boolean value)
   {
      if (row < 0 || col < 0)
      {
         return false;
      }
      if (row > MAX_HEIGHT || col > MAX_WIDTH)
      {
         return false;
      }

      image_data[row][col] = value;
      
      return true;
   }
   
   
   /*
    * clone
    * An overridden version of clone from Cloneable
    */
   public BarcodeImage clone()
   {
      try
      {
         BarcodeImage cloned = (BarcodeImage)super.clone();
         cloned.image_data = image_data.clone();
         return cloned;
      }
      catch (CloneNotSupportedException e)
      {
         return null;
      }  
   }
   
   
   /* checkSize
    * A private util used to validate whether the string data passed into the BarcodeImage
    * non-default constructor is within the bounds of MAX_HEIGHT and MAX_WIDTH, is not zero or null.
    * Returns false if the lenght of the data is equal to, or less than zero, or greater than 
    * MAX_HEIGHT or MAX_WIDTH.
    * Returns true if the size is valid.
    */
   private boolean checkSize(String[] data) 
   {
      if (data.length <= 0) {
         return false;
      }
      if (data.length > MAX_HEIGHT) {
         return false;
      }
      if (data.length > MAX_WIDTH) {
         return false;
      }
      if (data == null) {
         return false;
      }
      
      return true;
   }
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
   
   public boolean scan(final BarcodeImage bc)
   {
      //try
      //{
         this.image = bc.clone();
         cleanImage();
         actualWidth = computeSignalWidth();
         actualHeight = computeSignalHeight();
         return true;
      //} catch (CloneNotSupportedException e)
     // {
         // do nothing
      //}
      //return false; 
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
      return BarcodeImage.MAX_WIDTH - 1;
      //throw new RuntimeException("ERROR SCANNING");
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
      return BarcodeImage.MAX_HEIGHT - 1;
      //throw new RuntimeException("ERROR SCANNING");
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
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
         if(!image.getPixel(BarcodeImage.MAX_HEIGHT - 1, i))
         {
            return i;
         }
      }
      return BarcodeImage.MAX_WIDTH - 1;
   }
   
   private int computeSignalHeight()
   {
      for(int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         if(image.getPixel(i, 0))
         {
            return BarcodeImage.MAX_HEIGHT - i;
         }
      }
      return BarcodeImage.MAX_HEIGHT - 1;
   }
   
   // Encodes a string into a barcode image
   public boolean readText(String text)
   {
      if(text.length() < BarcodeImage.MAX_WIDTH)
      {
         this.text = text + "*";
         return true;
      }
      return false;
   }
   // Method to generate BarcodeImage from text
   public boolean generateImageFromText() 
   {
      String[] str_data = new String[10];
      for (int i = 0; i < str_data.length; i++)
      {
         str_data[i] = ""+BLACK_CHAR;
      }
      for (int col = 1; col <= text.length(); col++) 
      {
         int ascii = (int) text.charAt(col-1);
         for (int row = 1; row <= str_data.length - 2; row++)
         {
            int y = Math.abs(row - (str_data.length - 2));
            int yval = (int) Math.pow(2, y);
            if (yval <= ascii)
            {
               ascii -= yval;
               str_data[row] += BLACK_CHAR;
            }
            else
            {
               str_data[row] += WHITE_CHAR;
            }
         }
      }
      for (int i = 0; i < text.length() + 1; i++)
      {
         if (i % 2 == 0)
            str_data[0] += WHITE_CHAR;
         else
            str_data[0] += BLACK_CHAR;
         str_data[str_data.length-1] += BLACK_CHAR;
      }
      for (int i = 1; i < str_data.length - 1; i++)
      {
         if (i % 2 == 0)
            str_data[i] += WHITE_CHAR;
         else
            str_data[i] += BLACK_CHAR;
      }
      image = new BarcodeImage(str_data);
      cleanImage();
      return true;
   }

   // Method to generate text from BarcodeImage
	
  public boolean translateImageToText() 
  {
     text = "";
     for (int col = 1; col <= getActualWidth(); col++)
     {
        int ascii = 0;
	int i = 0;
	for (int row = BarcodeImage.MAX_HEIGHT - 2; row >= BarcodeImage.MAX_HEIGHT - 1 - getActualHeight(); row--)
	{
	   if (image.getPixel(row, col))
	   {
	      ascii += Math.pow(2, i);
	   }
	   i++;
	}
	text += (char) ascii;
     }
     return true;
   }	
   public void displayTextToConsole() 
   {
      System.out.println(text);
   }
   public void displayImageToConsole()
   {
      String onlyBarcode = "";
      for(int i = 0; i < actualWidth + 2; i++)
      {
         onlyBarcode += "-";
      }

      for(int row = BarcodeImage.MAX_HEIGHT - actualHeight; row < BarcodeImage.MAX_HEIGHT; row++)
      {
         onlyBarcode += "|";
         for(int col = 0; col < actualWidth; col++)
         {
            onlyBarcode += image.getPixel(row, col) ? BLACK_CHAR : WHITE_CHAR;
         }
         onlyBarcode += "|\n";
      }
      
      for(int i = 0; i < actualWidth + 2; i++)
      {
         onlyBarcode += "-";
      }
      System.out.println(onlyBarcode);
   }
		
}
