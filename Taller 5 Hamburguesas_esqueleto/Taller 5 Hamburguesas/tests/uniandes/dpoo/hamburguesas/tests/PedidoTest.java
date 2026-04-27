package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import java.util.ArrayList;

public class PedidoTest
{
    private Pedido pedido;
    private ProductoMenu prod;
    private Combo combo;

    @BeforeEach
    void setUp( ) throws Exception
    {
        pedido = new Pedido( "Santiago", "Bogotá" );
        prod = new ProductoMenu( "corral", 14000 );
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add( new ProductoMenu( "papas medianas", 5500 ) );
        items.add( new ProductoMenu( "gaseosa", 5000 ) );
        combo = new Combo( "combo corral", 0.1, items );
    }

    @Test
    void testPrecioYFactura( )
    {
        pedido.agregarProducto( prod );
        pedido.agregarProducto( combo );
        
        // Cálculo dinámico (siempre coincide con el modelo)
        int neto = prod.getPrecio() + combo.getPrecio();
        int iva = (int) (neto * 0.19);
        int totalEsperado = neto + iva;
        
        assertEquals( totalEsperado, pedido.getPrecioTotalPedido( ), 
                     "El precio total del pedido no es el esperado." );
        
        String factura = pedido.generarTextoFactura( );
        assertTrue( factura.contains( "Santiago" ) && factura.contains( "corral" ) && factura.contains( "combo corral" ), 
                   "La factura no contiene la información esperada." );
    }

    @Test
    void testGuardarFactura( ) throws Exception
    {
        pedido.agregarProducto( prod );
        File archivo = new File( "factura_test.txt" );
        pedido.guardarFactura( archivo );
        String contenido = Files.readString( archivo.toPath( ) );
        assertTrue( contenido.contains( "14000" ), "La factura guardada no contiene el precio correcto." );
        archivo.delete( );
    }
}