package ejercicioExtra;

public class Beca {
    Utils utils;

    public Beca(){
        utils=new Utils();

    }

    public Beca(Utils utils){
        this.utils=utils;
    }

    public String recomendacionBeca(int ci){
        if(Helpers.aplicaBeca(ci)){
            if(utils.getNota(ci) >=90){
                return "Si aplica beca";

            }else{
                return "No aplica a beca por promedio academico";
        }
            }else{
            return "El esrtudiante no curso aun el 60% de las materias";
        }
    }
}
