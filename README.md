# Assig4
Phase 1:
interface BarcodeIO: An Interface (which you define) called BarcodeIO that defines the I/O and basic methods of any barcode class 
which might implement it.

Phase 2:
class BarcodeImage implements Cloneable.An object of this BarcodeImage class will be one of the main member-objects of the class 
that comes next. BarcodeImage will describe the 2D dot-matrix pattern, or "image".  It will contain some methods for storing, modifying 
and retrieving the data in a 2D image. The interpretation of the data is not part of this class.  Its job is only to manage the optical 
data. It will implement Cloneable interface because it contains deep data.

Phase 3: 
class DataMatrix implements BarcodeIO. The class that will contain both a BarcodeImage member object and a text String member that 
represents the message encoded in the embedded image. This class has all the fun. This is not a true Datamatrix because, for one thing, 
there is no Reed-Solomon error correction. 

Phase 4: 
draw the UML diagram that represents the classes
