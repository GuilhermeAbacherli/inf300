package br.unicamp.ic.inf335.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * @author gpabacherli@gmail.com
 *         Testes unitários da classe Anunciante
 */
public class AnuncianteBeanTest {

  AnuncianteBean anunciante = new AnuncianteBean();
  AnuncioBean anuncio = new AnuncioBean();

  @Test
  public void testConstructor() {
    anunciante = new AnuncianteBean();
  }

  @Test
  public void testAddAnuncio() {
    assertTrue(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 0);

    anunciante.addAnuncio(anuncio);
    assertFalse(anunciante.getAnuncios().isEmpty());
    assertTrue(anunciante.getAnuncios().size() == 1);
  }

  @Test
  public void testRemoveAnuncio() {
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
    AnuncioBean anuncio1 = new AnuncioBean();
    anuncio1.getProduto().setValor(6.0);
    anuncio1.setDesconto(0.5);

    AnuncioBean anuncio2 = new AnuncioBean();
    anuncio2.getProduto().setValor(12.0);
    anuncio2.setDesconto(0.5);

    anunciante.addAnuncio(anuncio1);
    anunciante.addAnuncio(anuncio2);
    assertTrue(anunciante.getAnuncios().size() == 2);

    Double expected = Double.valueOf(4.5);
    Double actual = anunciante.valorMedioAnuncios();
    assertEquals(expected, actual);
  }
}
