package br.unicamp.ic.inf335.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author gpabacherli@gmail.com
 *         Testes unitários da classe Anunciante
 */
public class AnuncianteBeanTest {

  @Test
  public void testGettersAndSetters() {
    AnuncianteBean anunciante = new AnuncianteBean();

    String expectedNome = "John Doe";
    String expectedCPF = "498.424.490-92";

    ArrayList<AnuncioBean> expectedAnuncios = new ArrayList<AnuncioBean>();
    for (int i = 0; i < 5; i++) {
      expectedAnuncios.add(new AnuncioBean());
    }

    anunciante.setNome(expectedNome);
    anunciante.setCPF(expectedCPF);
    anunciante.setAnuncios(expectedAnuncios);

    assertEquals(expectedNome, anunciante.getNome());
    assertEquals(expectedCPF, anunciante.getCPF());
    assertEquals(expectedAnuncios, anunciante.getAnuncios());
  }

  @Test
  public void testConstructorEmpty() {
    AnuncianteBean anunciante = new AnuncianteBean();
    assertEquals("", anunciante.getNome());
    assertEquals("", anunciante.getCPF());
    assertTrue(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 0);
  }

  @Test
  public void testConstructorWithParams() {
    AnuncianteBean anunciante = new AnuncianteBean();

    String expectedNome = "John Doe";
    String expectedCPF = "498.424.490-92";

    ArrayList<AnuncioBean> anuncios = new ArrayList<AnuncioBean>();
    for (int i = 0; i < 5; i++) {
      anuncios.add(new AnuncioBean());
    }

    anunciante = new AnuncianteBean(expectedNome, expectedCPF, anuncios);

    assertEquals(expectedNome, anunciante.getNome());
    assertEquals(expectedCPF, anunciante.getCPF());
    assertTrue(anunciante.getAnuncios().size() == 5);
  }

  @Test
  public void testAddAnuncio() {
    AnuncianteBean anunciante = new AnuncianteBean();
    AnuncioBean anuncio = new AnuncioBean();

    assertTrue(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 0);

    anunciante.addAnuncio(anuncio);
    assertFalse(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 1);
  }

  @Test
  public void testRemoveAnuncio() {
    AnuncianteBean anunciante = new AnuncianteBean();
    AnuncioBean anuncio = new AnuncioBean();

    assertTrue(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 0);

    anunciante.addAnuncio(anuncio);
    assertFalse(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 1);

    anunciante.removeAnuncio(0);
    assertTrue(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 0);
  }

  @Test
  public void testValorMedioAnuncios() {
    AnuncianteBean anunciante = new AnuncianteBean();

    AnuncioBean anuncio1 = new AnuncioBean();
    anuncio1.getProduto().setValor(6.0);
    anuncio1.setDesconto(0.5);

    AnuncioBean anuncio2 = new AnuncioBean();
    anuncio2.getProduto().setValor(12.0);
    anuncio2.setDesconto(0.5);

    anunciante.addAnuncio(anuncio1);
    anunciante.addAnuncio(anuncio2);
    assertTrue(anunciante.getAnuncios().size() == 2);

    Double expected = 4.5;
    Double actual = anunciante.valorMedioAnuncios();
    assertEquals(expected, actual);
  }
}
