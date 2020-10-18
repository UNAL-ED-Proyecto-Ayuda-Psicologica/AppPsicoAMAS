package PsicObj;
import DataStructures.SimplyLinkedList;
public class Login {

        SimplyLinkedList<Psico> listPsico;
        SimplyLinkedList<NoPsico> listNoPsico;

        public Login() {
            listPsico = new SimplyLinkedList<Psico>();
            listNoPsico = new SimplyLinkedList<NoPsico>();

            Psico objPsico = new Psico("Pepito","pepito1","pepito123",null);
            Psico objPsico2 = new Psico("Anita", "Ana", "1234",null);

            listPsico.insert(objPsico);
            listPsico.insert(objPsico2);

            NoPsico objNoPsico = new NoPsico("Juan","Juanito","12345",null);
            NoPsico objNoPsico2 = new NoPsico("Carlos","carlitos","carlitos12",null);
            listNoPsico.insert(objNoPsico);
            listNoPsico.insert(objNoPsico2);
        }

        public boolean logIn(Psico objUser){
            for (int i = 0; i < listPsico.length(); i++) {
                if(listPsico.getK(i).compareTo(objUser)==0){
                    //System.out.println("Iniciaste Sesion");
                    return true;
                }
            }
            return false;
        }

        public boolean logIn(NoPsico objUser){
            for (int i = 0; i < listNoPsico.length(); i++) {
                if(listNoPsico.getK(i).compareTo(objUser)==0){
                    //System.out.println("Iniciaste Sesion");
                    return true;
                }
            }
            return false;
        }
    }


