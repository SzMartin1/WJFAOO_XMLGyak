<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">
    <xsl:template match = "/">

        <html>
            <body>
                <h2>Vizsgak- for-each, value-of</h2>

                <table border="4">
                    <tr bgcolor="#9acd32">
                        <th>Kurzus</th>
                        <th>Helyszin</th>
                        <th>Idopont</th>
                        <th>Oktato</th>
                        <th>Jegy</th>
                    </tr>

                    <!--for-each feldolgozasi utasitas. megkeres az XPath kifejezesnek ... -->

                    <xsl:for-each select="root/vizsgak/vizsga/vizsga">
                        <tr>
                            <td><xsl:value-of select="kurzus"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="idopont"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="jegy"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>