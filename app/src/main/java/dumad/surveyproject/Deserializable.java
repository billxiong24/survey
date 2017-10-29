package dumad.surveyproject;

/**
 * Created by Ritler on 10/28/17.
 */

public interface Deserializable<DeserializeObj> {

    public abstract DeserializeObj deserialize();
}
