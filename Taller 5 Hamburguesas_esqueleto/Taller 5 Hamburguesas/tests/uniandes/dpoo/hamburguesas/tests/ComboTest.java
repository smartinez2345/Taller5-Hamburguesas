package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest
{
    private Combo combo;

    @BeforeEach
    void setUp( ) throws Exception
    {
        ArrayList<ProductoMenu> items = new ArrayList<>( );
        items.add( new ProductoMenu( "corral", 14000 ) );
        items.add( new ProductoMenu( "papas medianas", 5500 ) );
        items.add( new ProductoMenu( "gaseosa", 5000 ) );
        combo = new Combo( "combo corral", 0.1, items );
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "combo corral", combo.getNombre( ) );
    }

    @Test
    void testGetPrecio( )
    {
        assertEquals( 22050, combo.getPrecio( ) ); // 24500 * 0.9
    }

    @Test
    void testGenerarTextoFactura( )
    {
        String texto = combo.generarTextoFactura( );
        assertTrue( texto.contains( "Combo combo corral" ) && texto.contains( "0.1" ) && texto.contains( "22050" ) );
    }
}