package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class RestauranteTest
{
    private Restaurante restaurante;

    @BeforeEach
    void setUp( ) throws Exception
    {
        restaurante = new Restaurante( );
        restaurante.cargarInformacionRestaurante(
            new File( "data/ingredientes.txt" ),
            new File( "data/menu.txt" ),
            new File( "data/combos.txt" )
        );
    }

    @Test
    void testCargarInformacion( )
    {
        assertFalse( restaurante.getIngredientes( ).isEmpty( ), "No se cargaron ingredientes." );
        assertFalse( restaurante.getMenuBase( ).isEmpty( ), "No se cargó el menú base." );
        assertFalse( restaurante.getMenuCombos( ).isEmpty( ), "No se cargaron los combos." );
    }

    @Test
    void testIniciarYAgregarPedido( ) throws Exception
    {
        restaurante.iniciarPedido( "Santiago", "Bogotá" );
        assertNotNull( restaurante.getPedidoEnCurso( ), "No se creó el pedido en curso." );
        
        ProductoMenu prod = new ProductoMenu( "corral", 14000 );
        restaurante.agregarProducto( prod );
        
        // 14000 neto + 19% IVA = 16660
        assertEquals( 16660, restaurante.getPedidoEnCurso( ).getPrecioTotalPedido( ), 
                     "El precio total del pedido (neto + IVA) no es correcto." );
    }

    @Test
    void testExcepciones( ) throws Exception
    {
        // 1. Primero probamos NoHayPedidoEnCursoException (cuando realmente no hay pedido)
        assertThrows( NoHayPedidoEnCursoException.class, 
                     () -> restaurante.cerrarYGuardarPedido( ) );
        
        // 2. Ahora creamos un pedido y probamos YaHayUnPedidoEnCursoException
        restaurante.iniciarPedido( "Juan", "Calle 1" );
        assertThrows( YaHayUnPedidoEnCursoException.class, () -> 
            restaurante.iniciarPedido( "Pedro", "Calle 2" )
        );
    }
}