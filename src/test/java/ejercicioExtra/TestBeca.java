package ejercicioExtra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class TestBeca {

    Utils utilsMock = Mockito.mock(Utils.class);
    MockedStatic<Helpers> helpersStatic= Mockito.mockStatic(Helpers.class);


    @ParameterizedTest
    @CsvSource({ "1111111,SI APLICA A BECA", 							// Si aplica beca
            "2222222,NO APLICA A BECA POR PROMEDIO ACADEMICO",			//No aplica beca
            "3333333,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",	//Materias insufisientes
    })
    public void recomendacionBecaTest(int ci, String expectedResult) {
        Mockito.when(utilsMock.getNota(123456)).thenReturn(91);
        Mockito.when(utilsMock.getNota(654321)).thenReturn(65);
        Mockito.when(utilsMock.getNota(147258)).thenReturn(100);



        helpersStatic.when(()-> Helpers.aplicaBeca(123456)).thenReturn(true);
        helpersStatic.when(()-> Helpers.aplicaBeca(654321)).thenReturn(true);
        helpersStatic.when(()-> Helpers.aplicaBeca(147258)).thenReturn(false);
        Beca beca = new Beca(utilsMock);
        String actualResult = beca.recomendacionBeca(ci);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el resultado es incorrecto");

    }
}