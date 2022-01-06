
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class ResourceLoader
{
	Map<String,BufferedImage> imageMap;


	private static ResourceLoader singleInstance; 
	
	public static ResourceLoader getLoader ()
	{
		if (singleInstance == null)	
		    singleInstance = new ResourceLoader();
		
		return singleInstance;
	}
	
    private ResourceLoader ()
    {    	
    	imageMap = new HashMap<String,BufferedImage>();
    }
    
	// Loads an image from the given path
    public BufferedImage getImage (String imageName)
    {
    	
    	if (imageMap.containsKey("Assets/" + imageName))
    		return imageMap.get("Assets/" + imageName);
    	
    	BufferedImage result;
    	
		// Load the image
    	try
    	{	
	    	ClassLoader myLoader = this.getClass().getClassLoader();
	        InputStream imageStream = myLoader.getResourceAsStream("Assets/" + imageName);
	        result = javax.imageio.ImageIO.read(imageStream); 
	        
	        imageMap.put("Assets/" + imageName, result);
	        
	        return result;
    	}
    	catch (Exception e)
        {
			// If the image doesn't exist, return a blank image
            System.err.println ("Could not load one of the files: " + imageName);
            System.exit(0); 
            return null; 
        }    
    	
       
    }
}
