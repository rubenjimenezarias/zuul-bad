
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
        GO("ir"), QUIT("salir"), HELP("ayuda"), LOOK("mirar"), EAT("comer"), BACK("volver"), TAKE("coger"), DROP("dejar"), UNKNOWN("");
        
        private String comando;
        
        private Option(String comando)
        {
            this.comando = comando;
        }
        
        public String getComando(){
            return comando;
        }
}
