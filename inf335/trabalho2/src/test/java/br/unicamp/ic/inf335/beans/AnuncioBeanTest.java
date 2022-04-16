package br.unicamp.ic.inf335.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author gpabacherli@gmail.com
 *         Testes unitários da classe Anuncio
 */
public class AnuncioBeanTest implements java.io.Serializable {

    @Test
    public void testGettersAndSetters() throws MalformedURLException {
        AnuncioBean anuncio = new AnuncioBean();

        ProdutoBean expectedProduto = new ProdutoBean();
        ArrayList<URL> expectedFotosUrl = new ArrayList<URL>();
        for (int i = 0; i < 5; i++) {
            expectedFotosUrl.add(new URL("http://example.com/images/img" + i + ".jpeg"));
        }
        Double expectedDesconto = 0.5;

        anuncio.setProduto(expectedProduto);
        anuncio.setFotosUrl(expectedFotosUrl);
        anuncio.setDesconto(expectedDesconto);

        assertEquals(expectedProduto, anuncio.getProduto());

        assertFalse(anuncio.getFotosUrl().isEmpty());
        assertTrue(anuncio.getFotosUrl().size() == 5);

        assertEquals(expectedDesconto, anuncio.getDesconto());
    }

    @Test
    public void testConstructorEmpty() {
        AnuncioBean anuncio = new AnuncioBean();

        assertNotNull(anuncio.getProduto());
        assertNotNull(anuncio.getFotosUrl());
        assertEquals(0.0, anuncio.getDesconto());
    }

    @Test
    public void testConstructorWithParams() throws MalformedURLException {

        ProdutoBean expectedProduto = new ProdutoBean();
        ArrayList<URL> expectedFotosUrl = new ArrayList<URL>();
        for (int i = 0; i < 5; i++) {
            expectedFotosUrl.add(new URL("http://example.com/images/img" + i + ".jpeg"));
        }

        Double expectedDesconto = 0.5;

        AnuncioBean anuncio = new AnuncioBean(expectedProduto, expectedFotosUrl, expectedDesconto);

        assertEquals(expectedProduto, anuncio.getProduto());
        assertFalse(anuncio.getFotosUrl().isEmpty());
        assertTrue(anuncio.getFotosUrl().size() == 5);
        assertEquals(expectedDesconto, anuncio.getDesconto());
    }

    @Test
    public void testDescontoMaximo() {
        AnuncioBean anuncio = new AnuncioBean();
        anuncio.setDesconto(99.99);
        assertEquals(1.0, anuncio.getDesconto());
    }

    @Test
    public void testDescontoMinimo() {
        AnuncioBean anuncio = new AnuncioBean();
        anuncio.setDesconto(-99.99);
        assertEquals(0.0, anuncio.getDesconto());
    }

    @Test
    public void testGetValorComDesconto() {

        ProdutoBean produto = new ProdutoBean();
        produto.setValor(100.0);

        AnuncioBean anuncio = new AnuncioBean();
        anuncio.setProduto(produto);
        anuncio.setDesconto(0.5);

        assertEquals(50.0, anuncio.getValor());
    }
}
