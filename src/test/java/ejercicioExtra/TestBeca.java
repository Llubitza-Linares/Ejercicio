package ejercicioExtra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Locale;

public class TestBeca {
    Utils utilsMock = Mockito.mock(Utils.class);

    MockedStatic<Helpers> helpersStatic= Mockito.mockStatic(Helpers.class);


    @ParameterizedTest
    @CsvSource({
            "123456,Si aplica a beca",
            "654321,Si aplica a beca",
            "111111,Si aplica a beca",

            "200,NO APLICA A BECA POR PROMEDIO ACADEMICO",
            "201,NO APLICA A BECA POR PROMEDIO ACADEMICO",
            "202,NO APLICA A BECA POR PROMEDIO ACADEMICO",

            "300,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",
            "301,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",
            "302,EL ESTUDIANTE NO CURSO AUN EL 60% DE LAS MATERIAS",
    })
    public void recomendacionBecaTest(int ci, String expectedResult) {


        Mockito.when(utilsMock.getNota(123456)).thenReturn(91);
        Mockito.when(utilsMock.getNota(654321)).thenReturn(92);
        Mockito.when(utilsMock.getNota(111111)).thenReturn(100);

        Mockito.when(utilsMock.getNota(200)).thenReturn(70);
        Mockito.when(utilsMock.getNota(201)).thenReturn(70);
        Mockito.when(utilsMock.getNota(202)).thenReturn(70);

        Mockito.when(utilsMock.getNota(300)).thenReturn(100);
        Mockito.when(utilsMock.getNota(301)).thenReturn(0);
        Mockito.when(utilsMock.getNota(302)).thenReturn(50);

        helpersStatic.when(()-> Helpers.aplicaBeca(100)).thenReturn(true);
        helpersStatic.when(()-> Helpers.aplicaBeca(200)).thenReturn(true);
        helpersStatic.when(()-> Helpers.aplicaBeca(300)).thenReturn(false);

        Beca beca = new Beca(utilsMock);
        String actualResult = beca.recomendacionBeca(ci);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el resultado es incorrecto");
        helpersStatic.close();
    }
}