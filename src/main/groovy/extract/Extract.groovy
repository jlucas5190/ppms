package extract

/**
 * Created by lucasj8 on 7/10/2016.
 */
abstract class Extract {

    def removeSpecialCharacters(String s){
        return s.replace("[ ](?=[ ])|[^-_,A-Za-z0-9 ]+", "");
    }
}
