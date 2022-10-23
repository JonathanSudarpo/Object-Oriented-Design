# README for the Imageine Program #

## README ##

This README details the Imageine program - an Image Processing program created for Northeastern University’s Object-Oriented Design course: CS-3500. The Imageine program is able to load files of various types which include PPM, PNG, JPEG, BMP, and others to conduct various operations on each pixel of the image and save the outcome of the operations conducted on the image as a new file of any type. This README, in specific, contains specifications and descriptions of the code written for part three of the assignment. 

The Imageine program utilizes the Model-View-Controller design pattern to instantiate an instance of the program. 

In particular, the model represents an image-file using a 2-D array of Pixels - a class created that holds RGB (Red, Green, Blue) values of color - which are stored in a hashmap of String to Pixel. We decided to create a Pixel class as it allows us to conduct operations on each Pixel of a given image enabling more flexible, specific, and unique operations to be supported especially for future reusability. We decided to stick with the Model-View-Controller design for part two of the assignment as it allowed us to retains the same functionality supported by the assignment one code, while offering the ability to add support and functionalities for other file-types that are non-PPM through extending the design. In specific, we extended assignment one's ImageManipulatorModel interface as a BetterImageManipulatorModel interface implemented in the GeneralImageManipulatorModel class which now has the ability to perform operations on any file-types. We have this new compatibility as our save and load methods in GeneralImageManipulatorModel now uses two new utility methods 'readGeneral' and 'writeGeneral' which uses the ImageIO library to read and write various file-types (in assignment one we had readPPM and writePPM which only supported PPM file types). This new model interface also supports two new functionalities which are transform and filter that allow us to apply supplied transformation and/or filter matrixes to our images respectively. The transform method currently supports the ability to transform images into greyscale or sepia, and the filter methods support the ability to blur and sharpen an image. For part three of the assignment, the previous implementation of the model remains unchanged. In this iteration, we created a new interface named ImageManipulatorViewModel which supports the ability to get information regarding the state of the model. The direct implementation of this interface can be seen in the EvenBetterImageManipulatorViewModel class which includes methods that are able to get information pertaining to a model's state, particularly useful when trying to display the model in a GUI view. We then updated our BetterImageManipulatorModel interface as a new EvenBetterImageManipulatorModel interface that supports all our old functionalities while also allowing the ability to get the state of the model by extending both the ImageManipulatorViewModel and the BetterImageManipulatorModel interfaces. This interface was implemented in the EvenBetterImageManipulatorModleImpl class which supports functionalities from all iterations of the model. We decided to make these update as it would allow us to access and display the model's information within the GUI view, which we weren't able to do using the old implementation. 


The view enables users to read operations that have been inputted into the program’s controller as text denoting commands that have been inputted and acted upon by the user and the program respectively. In this third iteration of our program, the view now supports the ability to be displayed in a GUI. This is due to our new ImageManipulatorGUIView interface which is implemented in the ImageManipulatorSwingView class that displays operations depending on the methods being called. Along with this, a class we made named ImagePanel that extends JPanel and allows us to display and draw the current image being manipulated. The view also has a new histogram feature that displays the frequency of pixel-intensities for each channel value (Red, Green, and Blue), along with the average intensity across the three channel values. This implementation can be seen in the HistogramPanel class which extends JPanel in order to show the created chart.

Lastly, the controller utilizes a scanner to read user input and conduct the specified operations (decided upon by switch-case statements) while outputting the appropriate view for each case of operations. For the second part of the assignment, we did *not* change our ImageManipulatorController interface. We updated our implementation of this interface to support our new model interface - BetterImageManipulatorModel. For part three of the assignment, we implemented the call-back design pattern in a new Features interface that allows us to link controller features to the GUI, and users to interact with the program using the GUI instead of command-line/script inputs. The implementation of this interface can be seen in our new class ImageManipulatorGUIController that supports all our old image-manipulating functionalities while allowing the program to be interacted with through a GUI.

In order to run the program, a user should either run the main method supplied in the ImageManipulatorSwing class, or use a terminal/command-prompt to navigate into the res folder and run the "assignment6.jar" file with the following command: java -jar assignment6.jar. To interact with the program, the user only needs to click the buttons in the GUI which corresponds to the operations available in part two of the assignment, and are listed below: 

	- load button -> opens a file chooser wherein the user is able to pick a file to be loaded into the program from the user's system.
	- save button -> saves the current iteration of the image onto a file-path of the user's choosing.
	- greyscale button and text-box -> Greyscales the image based on the component supplied in the text-box.
	- vertical-flip button -> vertically-flips the image.
	- horizontal-flip button -> horizontally-flips the image.
	- brighten button and text-box -> brightens or dims the image based on the incremenet supplied in the text-box.
	- sepia button -> applies sepia onto the image.
	- sharpen button -> sharpens the image.
	- blur button -> blurs the image.

 
For more information and specific examples on how to use the program, please read the USEME file supplied with this project.

** Class/Interface Design **
	1. Model
		- Pixel Class: A class that represents a singular pixel in an image and holds Red-Green-Blue color values that when combined are able to make various pixel colors to compose a whole image. Assignment two's implementation of the pixel class has been updated to clamp values of pixels to be between 0-255.  
		- ImageManipulatorModel Interface: Holds operations that are offered by the model for an ImageManipulator. These include loading an image, saving an image, and operations that manipulate the content of an image. 
		- AbstractImageManipulatorModel Class: An abstract class that implements the ImageManipulatorModel as it contains operation for an abstracted model object such as a different image file type. An abstract class was used to allow code reusability/reduce code duplication for future additional features such as more operations and the support of non-PPM image types.
		- PPMImageManipulatorModel Class: A class that extends the AbstractImageManipulatorModel class as it is a model object which potentially shares non-private/non-unique methods with other models. This class contains method code that is unique to a PPM-Image. In specific, the ability to load and save a PPM-image text file.
		- BetterImageManipulatorModel Interface: Holds operations that are offered by the model for an ImageManipulator that now supports most file-types that include non-PPM file-types. These include loading an image, saving an image, and operations that manipulate the content of an image. In specific, these operations (on-top of those previously supported by assignment one's implementation) now include the ability to apply transformations and filters on an image.
		- GeneralImageManipulatorModel Class: A class that implements the BetterImageManipulatorModel interface and supports its new methods (filter and transform) as well as the operations previously defined in the old ImageManipulatorModel interface. 
		- ImageManipulatorViewModel interface: Represents a view model for an Image Manipulator. Returns relevant information about the model.
		- BetterImageManipulatorModel interface: An updated version of the ImageManipulatorModel that now contains two new operations which are filter and transform. Having this retains the usability of all our previous classes and interfaces, while adding new functionalities and support.
		- EvenBetterImageManipulatorModel interface: Represents the Image Manipulation Software. Differs from BetterImageManipulator in that it has a state.
		- EvenBetterImageManipulatorModelImpl class: Represents the Image Manipulation Software. Can return information about its state.
		- EvenBetterImageManipulatorViewModel class: Represents a view model for the Image Manipulator. Allows users to get a view of the model without exposing the implementation.
	2. View
		- ImageManipulatorView Interface: Contains operations that are offered by the view for an ImageManipulator. In specific, the ability to render a message of type String to some output. 
		- ImageManipulatorTextView Class: This class implements the ImageManipulatorView as it is a view object which holds the code for operations that outputs a text-based view describing the actions and operations that have been conducted in an instance of the program. This class takes in an appendable which denotes the output destination of the text-based view.
		- CorruptibleImageManipulatorTextView Class: An instance of the text view class that has been corrupted and throws an IOException. This class is used for testing and to represent how methods in the ImageManipulatorView throws an IOException given the transmission of a message to the data destination (appendable) fails. 
		- HistogramPanel class: Creates a histogram based on the frequency of various pixel values appearing in a given image. In specific, it shows the red, green, blue, and intensity (average of three values) components of an image.
		- ImageManipulatorGUIView interface: Represents the Image Manipulator Program. Displays a graphical view of the program.
		- ImageManipulatorSwingView class: Represents a graphical view of the Image Manipulation Software. Makes use of the Java Swing library.
		- ImagePanel class: Draws the current image being manipulated. Gets the current image from a controller and information about that image from a view model.
	3. Controller
		- ImageManipulatorController Interface: Contains the operations that are offered by the controller for the Imageine program. In specific, it contains the method that allows the program to run an instance of the program. 
		- ImageManipulatorControllerImpl Class: This class implements the ImageManipulatorController as it is the implementation (written out code) of the controller interface. This class takes in a model, view, and readable user inputs as arguments for the constructor. Using these three arguments, this class outputs a certain view and model based on the user input that has been parsed through the scanner item. 
		- Features interface: Represents the features that the controller will have. The controller should implement this interface and support the methods provided.
		- ImageManipulatorGUIController class: Controls the Image Manipulator Model. Communicates between the model and the view, and helps take in user input
	4. Main
		- ImageManipulator Class: Contains the main method that allows users to run the Imageine program and supply arguments through the command-line as represented by an array of String.
		- ImageManipulatorSwing Class: Contains the updated main method that allows user to run and interact with the Imageine program through a GUI.

** Method/Constructor Design **
	1. Model
		- Pixel class:
			- Constructor: Takes in integer values that represent Red-Blue-Green color values. The constructor throws an IllegalArgumentException if any of the values are negative. 
			- Brighten: Takes in an integer value denoting the increment to brighten the Pixel by. If the supplied integer is positive, each of the pixel's fields are incremented by the integer value with a maximum of 255. If the integer value is negative, each of the pixel's fields are decreased by the integer amount with a minimum of 0.
			- greyscale: Take a component and utilizes a switch case to set the values of a Pixels field according to the various operations. 
			- equals: Overrides equals to more specifically compare if two pixels represent the same thing. 
			- hashCode: Overrides hashCode by hashing the three fields of the Pixel.
		- ImageManipulatorModel Interface:
			- loadImage: Takes in a fileName and a destName as arguments that signifies what file is being loaded and how it should be referred to in the hashmap of files respectively. In particular, the loadImage reads the supplied image such that it can be represented in some manner using the Pixel class. This method throws an error if the filePath supplied doesn't point to any file.
			- saveImage: Takes in a destPath and a fileName as argument which denotes what name the file is to be saved as, and the file within the hashmap of files that is being saved respectively. It throws an IOException if the file to be saved can't be transmitted to the output destination. 
			- greyScale: Takes in a component type, fileName, and destName as arguments. Applies greyscale of type component to the image denoted by fileName and stores it in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on can't be found.
	 		- verticalFlip: Takes in a fileName and a destName. Vertically flips the imaged denoted by filename and stores the resulting image in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on doesn't exist.
			- horizontalFlip: Takes in a fileName and a destName. Horizontally flips the image denoted by filename and stores the resulting image in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on doesn't exist.
			- brighten: Takes in an integer increment, a fileName and a destName. Brightens the image denoted by fileName by the integer increment and saves the resulting image in the hashmap of files as the destName. Throws an IllegalArgumentException if the file being operated on doesn't exist.
		- AbstractImageManipulatorModel class:
			- loadImage: Allows an image of some abstracted type to be loaded. However, it's an abstract method denoting that each model has different underlying code that allows the program to read the image and translate it as Pixels.
			- saveImage: Allows an image that has been operated on, or loaded into the program to be saved as some file with the supplied destPath. However, it's an abstract method denoting that each model has different underlying code that allows the program to read the image being operated on and write it as some image file. 
			- greyscale: For each pixel in the image, apply the greyscale component operation on the pixel. Afterwards, put the new image in the hashmap of files. 
			- verticalFlip: For each pixel in the image, flip their column position but keep the row position same. Afterwards, put the new image in the hashmap of files.
			- horizontalFlip: For each pixel in the image, flip their row position but keep the column position same. Afterwards, put the new image in the hashmap of files.
			- brighten: For each pixel in the image, brighten by the supplied increment. Afterwards, put the new image in the hashmap of files. 
		- PPMImageManipulatorModel class: 
			- Constructor: Takes in no fields and sets files as a new HashMap mapping String to a 2-D array of Pixels. 
			- loadImage: Read the PPM image and put it in the hashmap of files.
			- saveImage: Gets the PPM image and writes it as a new PPM file.
		- BetterImageManipulatorModel interface:
			- filter: Creates a filtered version of the image called destName by applying the given filter.
			- transform: Creates a transformed version of the image called destName by applying the given transform.
		- GeneralImageManipulatorModel class:
			- loadImage: Takes in a fileName and a destName as arguments that signifies what file is being loaded and how it should be referred to in the hashmap of files respectively. In particular, the loadImage reads the supplied image such that it can be represented in some manner using the Pixel class. This method throws an error if the filePath supplied doesn't point to any file.
			- saveImage: Takes in a destPath and a fileName as argument which denotes what name the file is to be saved as, and the file within the hashmap of files that is being saved respectively. It throws an IOException if the file to be saved can't be transmitted to the output destination. 
			- filter: Applies the supplied filter matrix to the supplied image.
			- transform: Applies the supplied transformation matrix to the supplied image.
		- ImageManipulatorViewModel interface: 
			- loadImage: Loads the image from the specified path, and names it so that it can be referred to by the name the user inputs. 
			- saveImage: Saves the image of the given name to the specified file path.
			- greyscale: Greyscales the given image according to the given component, referred to as the given name.
			- verticalFlip: Flips an image of the given name vertically and refers to the flipped image as the new name.
			- horizontalFlip: Flips an image of the given name horizontally and refers to the flipped image as the new name.
			- brighten: Brightens the given image by the given increment and refers to it as the new name.
		- BetterImageManipulatorModel interface:
			- filter: Creates a filtered version of the image called destName by applying the given filter.
			- colorTransformation: Creates a transformed version of the image called destName by applying the given transform.
		- EvenBetterImageManipulatorModel interface: only inherits the methods of both BetterImageManipulatorModel, and ImageManipulatorViewModel. 
		- EvenBetterImageManipulatorModelImpl class:
			- Constructor: Takes in a hashmap of string to 2-D array of pixels which represents the files stored in the program.
			- getColorAt: Gets the color of a singular pixel in an image.
			- getBoardWidth: Gets the width of the image.
			- getBoardHeight: Gets the height of the image.
			- getHistogram: Gets the histogram values of the image.
		- EvenBetterImageManipulatorViewModel class:
			- Constructor: Takes in a model who's state we want to access for the view.
			- getColorAt: Gets the color of a singular pixel in an image.
			- getBoardWidth: Gets the width of the image.
			- getBoardHeight: Gets the height of the image.
			- getHistogram: Gets the histogram values of the image.

	2. View
		- ImageManipulatorView Interface:
			- renderMessage: Takes in a message of type String and renders it to the appendable. Throws an IOException if the message can't be transmitted to the appendable.
		- ImageManipulatorTextView class:
			- Constructor: Takes in an appendable as output and throws an IllegalArgumentException if the appendable is null.
			- renderMessage: Renders the supplied message to the output.
		- ImageManipulatorGUIView interface: 
			- addFeatures: Adds features by connecting the view to the controller, so that the user's inputs are reflected in the view.
			- loadImage: Returns the file of the image that the client requests to load through the GUI.
			- saveImage: Returns the file where the client wants to save their image.
			- brighten: Returns how much the client wants to brighten or darken the image by.
			- greyscale: Returns the component that the client wants to greyscale their image by.
			- renderMessage: Renders a message to the client.
			- refresh: Refreshes the view with the latest iteration of the image.
		- ImageManipulatorSwingView class: 
			- Constructor: Takes in a model to be viewed.
			- addFeatures: Adds features by connecting the view to the controller, so that the user's inputs are reflected in the view.
			- loadImage: Returns the file of the image that the client requests to load through the GUI.
			- saveImage: Returns the file where the client wants to save their image.
			- brighten: Returns how much the client wants to brighten or darken the image by.
			- greyscale: Returns the component that the client wants to greyscale their image by.
			- renderMessage: Renders a message to the client.
			- refresh: Refreshes the view with the latest iteration of the image.
		- HistogramPanel class:
			- Constructor: Takes in a model to render the histogram based on. 
			- paintComponent: The paint component over-riden from JPanel so that we can show the histogram instead of the paintComponent from JPanel.
			- acceptController: Accepts a feature into the controller.
		- ImagePanel class:
			- Constructor: Takes in a model to render the image panel with.
			- paintComponent:Over-rides JPanel's paintComponent in order to display our image processing program.
			- acceptController: Accepts the controller. Used for callbacks.

	3. Controller
		- ImageManipulatorController Interface:
			- launchProgram: Launches an instance of the program.
		- ImageManipulatorControllerImpl class:
			- Constructor: Takes in an ImageManipulatorModel, ImageManipulatorView, and a Readable. Throws an IllegalArgumentException if any of these arguments are null. The controller outputs the correct model and view based on the input.
			- launchProgram: Launches the program and runs through the program based on the users input. There is a switch-case block that handles each possible command that the program can handle. Within each of the case, we take the respective arguments and supply it to the methods that will conduct the operation on the image. 
		- Features Interface:
			- loadImage: Loads the image at the given file.
			- saveImage: Saves the image to the given file.
			- verticalFlip: Vertically flips the image being worked on.
			- horizontalFlip: Horizontally flips the image being worked on.
			- brighten: Brightens the image being worked on.
			- greyscale: Greyscales the image by the given component.
			- filter: Filters the image currently being worked on with the given filter.
			- transform: Filters the image currently being worked on with the given filter.
			- getCurrentImageName: Gets the name of the current image.
		- ImageManipulatorGUIController: 
			- Constructor: Takes in an EvenBetterImageManipulatorModel and a ImageManipulatorGUIView. The model will be used to render information onto the view.
			- loadImage: Loads the image at the given file.
			- saveImage: Saves the image to the given file.
			- verticalFlip: Vertically flips the image being worked on.
			- horizontalFlip: Horizontally flips the image being worked on.
			- brighten: Brightens the image being worked on.
			- greyscale: Greyscales the image by the given component.
			- filter: Filters the image currently being worked on with the given filter.
			- transform: Filters the image currently being worked on with the given filter.
			- getCurrentImageName: Gets the name of the current image.
	4. Main
		- ImageManipulator class:
			- Main: Instantiates the program and allows the user to interact with the program. Our new updated main can now also take in a -file file-path as an arg.


** Acknowledgements **

This program was able to be created with the guidance provided by the Professors and Teaching Assistants of Northeastern University’s Object-Oriented Design course. 

** Install **

After downloading or cloning the project files from this repository, open the program and navigate to the ImageManipulatorSwing class. Load your own images into the program package (or stick with the ones we’ve supplied) and run the main method. You should be greeted with a welcome message along with instructions to view all the commands offered by our program!

** License **
Sudarpo, Jonathan. “Monster.ppm” 
Shutterstock. n.d. Koala Bear Climbing On Tree. Retrieved June 9, 2022 (https://www.shutterstock.com/image-photo/koala-bear-climbing-on-tree-1087953089?irclickid=VX8RJ8xB8xyIT0l2t-RrFXUXUkD2sPROD233zs0&irgwc=1&utm_medium=Affiliate&utm_campaign=TinEye&utm_source=77643&utm_term=). "Koala.ppm" 
KindPng. n.d. Watermelon Pixel Art, HD Png Download. Retrieved June 17, 2022 (https://www.kindpng.com/imgv/iiwwTTw_watermelon-pixel-art-hd-png-download/). "watermelon.png" 




