package dumad.surveyproject;

/**
 * Created by Ritler on 10/28/17.
 * This interface defines method for converting from original object to POJO object.
 * ConvertObj is the type of the POJO object.
 * Only non-POJO classes should implement this interface
 */

public interface POJO<ConvertObj>{


    /**
     * Converts from the original and complex object to a POJO object.
     * @return the POJO object
     */
    public abstract ConvertObj convert();
}
