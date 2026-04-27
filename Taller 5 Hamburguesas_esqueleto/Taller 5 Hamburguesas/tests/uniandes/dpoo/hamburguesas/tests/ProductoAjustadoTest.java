package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest
{
    private ProductoAjustado ajustado;
    private ProductoMenu base;
    private Ingrediente queso, tomate;

    @BeforeEach
    void setUp( ) throws Exception
    {
        base = new ProductoMenu( "corral", 14000 );
        ajustado = new ProductoAjustado( base );
        queso = new Ingrediente( "queso mozzarella", 2500 );
        tomate = new Ingrediente( "tomate", 1000 );
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "corral", ajustado.getNombre( ) );
    }

    @Test
    void testGetPrecio( )
    {
        ajustado.agregarIngrediente( queso );
        ajustado.eliminarIngrediente( tomate );
        assertEquals( 16500, ajustado.getPrecio( ) );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        ajustado.agregarIngrediente( queso );
        ajustado.eliminarIngrediente( tomate );
        String texto = ajustado.generarTextoFactura( );
        assertTrue( texto.contains( "corral" ) && texto.contains( "+queso mozzarella" ) && texto.contains( "-tomate" ) && texto.contains( "16500" ) );
    }
}