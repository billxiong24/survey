package dumad.surveyproject;

/**
 * Created by Ritler on 10/28/17.
 * This interface defines a method for transforming from POJO back to original object.
 * DeserializeObj is the type of the deserialized/original object.
 * Only POJO classes should implement this interface.
 */

public interface Deserializable<DeserializeObj> {

    /**
     * Transform from POJO object bacm to original object.
     * @return the original object that was transformed to the POJO object.
     */
    public abstract DeserializeObj deserialize();
}
