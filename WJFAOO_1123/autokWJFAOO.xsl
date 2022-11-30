<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">
    <xsl:template match = "/">

        <html>
            <body>
                <h2>Autok- for-each, value-of</h2>

                <table border="4">
                    <tr bgcolor="#9acd32">
                        <th>Tipus</th>
                        <th>Ar</th>
                        <th>Szin</th>
                        <th>Tulaj</th>
                            <th>Nev</th>
                            <th>Varos</th>
                    </tr>

                    <!--for-each feldolgozasi utasitas. megkeres az XPath kifejezesnek ... -->

                    <xsl:for-each select="autok/auto">


                        <tr>
                            <td><xsl:value-of select="tipus"/></td>
                            <td><xsl:value-of select="ar"/></td>
                            <td><xsl:value-of select="szin"/></td>
                            <td><xsl:value-of select="tulaj"/></td>
                            <td><xsl:value-of select="nev"/></td>
                            <td><xsl:value-of select="varos"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>