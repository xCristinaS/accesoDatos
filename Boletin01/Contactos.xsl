<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match='/'>
        <html>
            <xsl:apply-templates/>
        </html>
    </xsl:template>
    <xsl:template match='Contactos'>
        <head>
            <title>LISTADO DE CONTACTOS</title>
        </head>
        <body>
            <h1>LISTA DE CONTACTOS</h1>
            <table border='1'>
                <tr>
					<th>Direcion</th>
					<th>Telefono</th>
                    <th>Nombre</th>
                    <th>CodPostal</th>
                    <th>DeboMoney</th>
                    <th>Cantidad</th>

                </tr>
                <xsl:apply-templates select='Contacto'/>
            </table>
        </body>
    </xsl:template>
    <xsl:template match='Contacto'>
        <tr>
            <xsl:apply-templates/>
        </tr>
    </xsl:template>
    <xsl:template match='direccion|telefono|nombreCont|codP|deboMoney|cantDeb'>
        <td>
            <xsl:apply-templates/>
        </td>
    </xsl:template>
</xsl:stylesheet>