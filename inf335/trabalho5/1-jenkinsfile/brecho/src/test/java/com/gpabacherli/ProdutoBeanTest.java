package com.gpabacherli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

/**
 * @author gpabacherli@gmail.com
 *         Testes unitários da classe Produto
 */
public class ProdutoBeanTest {

    @Test
    public void testGettersAndSetters() {
        ProdutoBean produto = new ProdutoBean();

        String expectedCodigo = "123456";
        String expectedNome = "Livro";
        String expectedDescricao = "Descricao do livro";
        Double expectedValor = 25.0;
        String expectedEstado = "Novo";

        produto.setCodigo(expectedCodigo);
        produto.setNome(expectedNome);
        produto.setDescricao(expectedDescricao);
        produto.setValor(expectedValor);
        produto.setEstado(expectedEstado);

        assertEquals(expectedCodigo, produto.getCodigo());
        assertEquals(expectedNome, produto.getNome());
        assertEquals(expectedDescricao, produto.getDescricao());
        assertEquals(expectedValor, produto.getValor());
        assertEquals(expectedEstado, produto.getEstado());
    }

    @Test
    public void testConstructorEmpty() {
        ProdutoBean produto = new ProdutoBean();

        assertEquals("", produto.getCodigo());
        assertEquals("", produto.getNome());
        assertEquals("", produto.getDescricao());
        assertEquals(0.0, produto.getValor());
        assertEquals("", produto.getEstado());
    }

    @Test
    public void testConstructorWithParams() {

        String expectedCodigo = "123456";
        String expectedNome = "Livro";
        String expectedDescricao = "Descricao do livro";
        Double expectedValor = 25.0;
        String expectedEstado = "Novo";

        ProdutoBean produto = new ProdutoBean(expectedCodigo, expectedNome, expectedDescricao, expectedValor,
                expectedEstado);

        assertEquals(expectedCodigo, produto.getCodigo());
        assertEquals(expectedNome, produto.getNome());
        assertEquals(expectedDescricao, produto.getDescricao());
        assertEquals(expectedValor, produto.getValor());
        assertEquals(expectedEstado, produto.getEstado());
    }

    @Test
    public void testProdutoSort() {
        ArrayList<ProdutoBean> produtos = new ArrayList<ProdutoBean>();

        ProdutoBean produto1 = new ProdutoBean();
        Double valor3 = 20.62;
        produto1.setValor(valor3);
        produtos.add(produto1);

        ProdutoBean produto2 = new ProdutoBean();
        Double valor2 = 20.5;
        produto2.setValor(valor2);
        produtos.add(produto2);

        ProdutoBean produto3 = new ProdutoBean();
        Double valor0 = 10.0;
        produto3.setValor(valor0);
        produtos.add(produto3);

        ProdutoBean produto4 = new ProdutoBean();
        Double valor1 = 20.0;
        produto4.setValor(valor1);
        produtos.add(produto4);

        ProdutoBean produto5 = new ProdutoBean();
        Double valor4 = 20.65;
        produto5.setValor(valor4);
        produtos.add(produto5);

        Collections.sort(produtos);

        // list should be sorted as
        // [ valor0, valor1, valor2, valor3, valor4]
        // [ 10.0, 20.0, 20.5, 20.62, 20.65]
        // [produto3, produto4, produto2, produto1, produto5]

        for (int i = 0; i < produtos.size(); i++) {
            if (i != 0) {
                assertTrue(produtos.get(i).getValor() > produtos.get(i - 1).getValor());
            }
        }

        assertEquals(valor0, produtos.get(0).getValor());
        assertEquals(valor1, produtos.get(1).getValor());
        assertEquals(valor2, produtos.get(2).getValor());
        assertEquals(valor3, produtos.get(3).getValor());
        assertEquals(valor4, produtos.get(4).getValor());
    }

}
